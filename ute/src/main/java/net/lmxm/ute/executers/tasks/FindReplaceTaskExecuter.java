/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executer.
 * 
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.executers.tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.PatternWrapper;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.executers.AbstractTaskExecuter;
import net.lmxm.ute.listeners.StatusChangeListener;
import net.lmxm.ute.utils.FileSystemTargetUtils;
import net.lmxm.ute.utils.FileSystemUtils;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class FindReplaceTaskExecuter.
 */
public final class FindReplaceTaskExecuter extends AbstractTaskExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FindReplaceTaskExecuter.class);

	/** The task. */
	private final FindReplaceTask task;

	/**
	 * Instantiates a new find replace task executer.
	 * 
	 * @param task the task
	 * @param statusChangeListener the status change listener
	 */
	public FindReplaceTaskExecuter(final FindReplaceTask task, final StatusChangeListener statusChangeListener) {
		super(statusChangeListener);

		Preconditions.checkNotNull(task, "Task may not be null");

		this.task = task;
	}

	/**
	 * Apply pattern.
	 * 
	 * @param text the text
	 * @param pattern the pattern
	 * @param replacement the replacement
	 * @return the string
	 */
	protected String applyPattern(final String text, final PatternWrapper pattern) {
		return pattern.getPattern().matcher(text).replaceAll(pattern.getReplacement());
	}

	/**
	 * Convert patterns to matchers.
	 * 
	 * @param findReplacePatterns the find replace patterns
	 * @return the list
	 */
	protected List<PatternWrapper> convertFindReplacePatternsToRegexPatterns(
			final List<FindReplacePattern> findReplacePatterns) {
		Preconditions.checkNotNull(findReplacePatterns, "Find replace patterns list may not be null");

		final List<PatternWrapper> patterns = new ArrayList<PatternWrapper>();

		for (final FindReplacePattern findReplacePattern : findReplacePatterns) {
			final Pattern regexPattern = Pattern.compile(findReplacePattern.getFind());
			final String replacement = StringUtils.trimToEmpty(findReplacePattern.getReplace());

			patterns.add(new PatternWrapper(regexPattern, replacement));
		}

		return patterns;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executers.ExecuterIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final String path = FileSystemTargetUtils.getFullPath(task.getTarget());
		final List<FileReference> files = task.getFiles();
		final List<FindReplacePattern> patterns = task.getPatterns();
		final Scope scope = task.getScope();

		findReplaceInFiles(path, files, patterns, scope);

		LOGGER.debug("{} returning", prefix);
	}

	/**
	 * Find replace content.
	 * 
	 * @param file the file
	 * @param patterns the patterns
	 * @param scope the scope
	 */
	protected void findReplaceContent(final File file, final List<PatternWrapper> patterns, final Scope scope) {
		final String prefix = "findReplaceContent() :";

		LOGGER.debug("{} entered, scope={}", prefix, scope);

		if (scope == Scope.FILE) {
			findReplaceFileContent(file, patterns);
		}
		else if (scope == Scope.LINE) {
			findReplaceFileLineContent(file, patterns);
		}
		else {
			LOGGER.error("{} unsupported scope {}", prefix, scope);

			throw new IllegalArgumentException("Unsupported scope");
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Find replace file content.
	 * 
	 * @param file the file
	 * @param patterns the patterns
	 */
	protected void findReplaceFileContent(final File file, final List<PatternWrapper> patterns) {
		final String prefix = "findReplaceFileContent() :";

		LOGGER.debug("{} entered, file={}", prefix, file);

		try {
			String fileContents = FileUtils.fileRead(file);

			for (final PatternWrapper pattern : patterns) {
				fileContents = applyPattern(fileContents, pattern);
			}

			FileUtils.fileWrite(file, fileContents);
		}
		catch (final IOException e) {
			LOGGER.error("{} Unable to read file {}", prefix, file);

			throw new RuntimeException("Unable to read file " + file.getAbsolutePath(), e);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Find replace file line content.
	 * 
	 * @param file the file
	 * @param patterns the patterns
	 */
	protected void findReplaceFileLineContent(final File file, final List<PatternWrapper> patterns) {
		final String prefix = "findReplaceFileLineContent() :";

		LOGGER.debug("{} entered, file={}", prefix, file);

		try {
			final String fileContents = FileUtils.fileRead(file);
			final String[] fileLines = fileContents.split("(\r\n)|(\n\r)|(\r)|(\n)");

			for (int i = 0; i < fileLines.length; i++) {
				String fileLine = fileLines[i];

				for (final PatternWrapper pattern : patterns) {
					fileLine = applyPattern(fileLine, pattern);
				}

				fileLines[i] = fileLine;
			}

			FileUtils.fileWrite(file, StringUtils.join(fileLines, System.getProperty("line.separator")));
		}
		catch (final IOException e) {
			LOGGER.error("{} Unable to read file {}", prefix, file);

			throw new RuntimeException("Unable to read file " + file.getAbsolutePath(), e);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Find replace in files.
	 * 
	 * @param path the path
	 * @param fileReferences the file references
	 * @param findReplacePatterns the find replace patterns
	 * @param scope the scope
	 */
	protected void findReplaceInFiles(final String path, final List<FileReference> fileReferences,
			final List<FindReplacePattern> findReplacePatterns, final Scope scope) {
		final String prefix = "findReplaceInFiles() :";

		LOGGER.debug("{} entered, path={}", prefix, path);

		final List<File> files = FileSystemUtils.convertToFileObjects(path, fileReferences);
		final List<PatternWrapper> patterns = convertFindReplacePatternsToRegexPatterns(findReplacePatterns);

		for (final File file : files) {
			if (file.isDirectory()) {
				LOGGER.debug("{} The file at {} is a directory, not a file; skipping", prefix, file);

				fireErrorStatusChange("The file at " + file.getAbsolutePath() + " is a directory, not a file; skipping");

				continue;
			}
			else {
				findReplaceContent(file, patterns, scope);

				fireInfoStatusChange("Find and replace executed on file " + file.getAbsolutePath());
			}
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
