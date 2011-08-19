/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executor.
 * 
 * Universal Task Executor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executor is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executor. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FindReplaceUtils.
 */
public class FindReplaceUtils {

	/** The Constant INSTANCE. */
	private static final FindReplaceUtils INSTANCE = new FindReplaceUtils();

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FindReplaceUtils.class);

	/**
	 * Gets the single instance of FindReplaceUtils.
	 * 
	 * @return single instance of FindReplaceUtils
	 */
	public static FindReplaceUtils getInstance() {
		return INSTANCE;
	}

	/**
	 * Apply pattern.
	 * 
	 * @param text the text
	 * @param pattern the pattern
	 * @param replacement the replacement
	 * @return the string
	 */
	private String applyPattern(final String text, final Pattern pattern, final String replacement) {
		return pattern.matcher(text).replaceAll(replacement);
	}

	/**
	 * Convert patterns to matchers.
	 * 
	 * @param findReplacePatterns the find replace patterns
	 * @return the map
	 */
	private SortedMap<Pattern, String> convertFindReplacePatternsToRegexPatterns(
			final List<FindReplacePattern> findReplacePatterns) {
		final SortedMap<Pattern, String> patterns = new TreeMap<Pattern, String>();

		for (final FindReplacePattern findReplacePattern : findReplacePatterns) {
			final Pattern regexPattern = Pattern.compile(findReplacePattern.getFind());
			final String replacement = StringUtils.trimToEmpty(findReplacePattern.getReplace());

			patterns.put(regexPattern, replacement);
		}

		return patterns;
	}

	/**
	 * Find replace content.
	 * 
	 * @param file the file
	 * @param patterns the patterns
	 * @param scope the scope
	 */
	private void findReplaceContent(final File file, final SortedMap<Pattern, String> patterns, final Scope scope) {
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
	private void findReplaceFileContent(final File file, final SortedMap<Pattern, String> patterns) {
		final String prefix = "findReplaceFileContent() :";

		LOGGER.debug("{} entered, file={}", prefix, file);

		try {
			String fileContents = FileUtils.fileRead(file);

			for (final Entry<Pattern, String> entry : patterns.entrySet()) {
				fileContents = applyPattern(fileContents, entry.getKey(), entry.getValue());
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
	private void findReplaceFileLineContent(final File file, final SortedMap<Pattern, String> patterns) {
		final String prefix = "findReplaceFileContent() :";

		LOGGER.debug("{} entered, file={}", prefix, file);

		try {
			final String fileContents = FileUtils.fileRead(file);
			final String[] fileLines = fileContents.split("(\r\n)|(\n\r)|(\r)|(\n)");

			for (int i = 0; i < fileLines.length; i++) {
				final String fileLine = fileLines[i];

				for (final Entry<Pattern, String> entry : patterns.entrySet()) {
					fileLines[i] = applyPattern(fileLine, entry.getKey(), entry.getValue());
				}
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
	 * @param statusChangeListener the status change listener
	 */
	public void findReplaceInFiles(final String path, final List<FileReference> fileReferences,
			final List<FindReplacePattern> findReplacePatterns, final Scope scope,
			final StatusChangeListener statusChangeListener) {
		final String prefix = "findReplaceInFiles() :";

		LOGGER.debug("{} entered, path={}", prefix, path);

		final List<File> files = FileSystemUtils.convertToFileObjects(path, fileReferences);
		final SortedMap<Pattern, String> patterns = convertFindReplacePatternsToRegexPatterns(findReplacePatterns);

		for (final File file : files) {
			if (file.isDirectory()) {
				LOGGER.debug("{} The file at {} is a directory, not a file; skipping", prefix, file);

				statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.ERROR,
						"The file at " + file.getAbsolutePath() + " is a directory, not a file; skipping"));

				continue;
			}
			else {
				findReplaceContent(file, patterns, scope);

				statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO,
						"Find and replace executed on file " + file.getAbsolutePath()));
			}
		}

		LOGGER.debug("{} leaving", prefix);
	}
}