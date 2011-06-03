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
import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.DirectoryScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class FileSystemUtils.
 */
public final class FileSystemUtils {

	/** The Constant INSTANCE. */
	private static final FileSystemUtils INSTANCE = new FileSystemUtils();

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemUtils.class);

	/**
	 * Convert to file objects.
	 * 
	 * @param path the path
	 * @param fileReferences the file references
	 * @return the list
	 */
	public static List<File> convertToFileObjects(final String path, final List<FileReference> fileReferences) {
		final String prefix = "convertToFileObjects() :";

		LOGGER.debug("{} entered, path={}", prefix, path);

		Preconditions.checkArgument(StringUtils.isNotBlank(path), "Path may not be blank or null");

		final List<File> files = new ArrayList<File>();

		if (fileReferences == null) {
			LOGGER.debug("{} file references is null, unable to scan for files", prefix);
		}
		else {
			if (fileReferences.size() == 0) {
				LOGGER.debug("{} file references is empty, unable to scan for files", prefix);
			}
			else {
				final List<String> includes = new ArrayList<String>();
				for (final FileReference fileReference : fileReferences) {
					includes.add(fileReference.getName());
				}

				final DirectoryScanner directoryScanner = new DirectoryScanner();
				directoryScanner.setBasedir(path);
				directoryScanner.setCaseSensitive(true);
				directoryScanner.setFollowSymlinks(false);
				directoryScanner.setIncludes(includes.toArray(new String[0]));

				directoryScanner.scan();

				final String[] filenames = directoryScanner.getIncludedFiles();

				for (final String filename : filenames) {
					files.add(new File(path, filename));
				}
			}
		}

		LOGGER.debug("{} returning {} files", prefix, files.size());

		return files;
	}

	/**
	 * Gets the single instance of FileSystemUtils.
	 * 
	 * @return single instance of FileSystemUtils
	 */
	public static FileSystemUtils getInstance() {
		return INSTANCE;
	}

	/**
	 * Instantiates a new file system utils.
	 */
	private FileSystemUtils() {
		super();
	}

	/**
	 * Creates the directory.
	 * 
	 * @param path the path
	 * @return the file
	 */
	public File createDirectory(final String path) {
		final String prefix = "createDirectory() :";

		LOGGER.debug("{} entered", prefix);

		Preconditions.checkArgument(StringUtils.isNotBlank(path), "Path may not be blank or null");

		final File directory = new File(path);

		if (directory.exists()) {
			LOGGER.debug("{} directory already exists", prefix);
		}
		else {
			if (directory.mkdirs()) {
				LOGGER.debug("{} successfully created directory", prefix);
			}
			else {
				LOGGER.debug("{} failed to create directory", prefix);

				throw new RuntimeException("directory already exists"); // TODO Use appropriate exception
			}
		}

		LOGGER.debug("{} returning {}", prefix, directory);

		return directory;
	}

	/**
	 * Delete files.
	 * 
	 * @param path the path
	 * @param files the files
	 * @param statusChangeListener the status change listener
	 */
	public void deleteFiles(final String path, final List<FileReference> files,
			final StatusChangeListener statusChangeListener) {
		final String prefix = "execute() :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} path={}", prefix, path);
			LOGGER.debug("{} files={}", prefix, files);
			LOGGER.debug("{} statusChangeListener={}", prefix, statusChangeListener);
		}

		final String pathTrimmed = StringUtils.trimToNull(path);

		Preconditions.checkArgument(pathTrimmed != null, "Path may not be blank");
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		final File pathFile = new File(pathTrimmed);

		if (!pathFile.exists()) {
			LOGGER.debug("{} path does not exist, returning", prefix);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO, "Path \""
					+ pathFile + "\" do not exist"));

			return;
		}

		if (pathFile.isFile()) {
			LOGGER.debug("{} deleting file {}", prefix, pathFile.getName());

			FileUtils.deleteQuietly(pathFile);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO,
					"Deleting file \"" + pathFile + "\""));
		}
		else if (pathFile.isDirectory()) {
			LOGGER.debug("{} path is a directory", prefix);

			if (CollectionUtils.isEmpty(files)) {
				LOGGER.debug("{} deleting directory {}", prefix, pathFile.getName());

				FileUtils.deleteQuietly(pathFile);

				statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO,
						"Deleting directory \"" + pathFile + "\""));
			}
			else {
				LOGGER.debug("{} deleting {} files in a directory", prefix, prefix);

				for (final FileReference file : files) {
					final String fileName = file.getName();

					LOGGER.debug("{} deleting file {}", prefix, fileName);

					FileUtils.deleteQuietly(new File(pathFile, fileName));

					statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO,
							"Deleting file \"" + fileName + "\""));
				}
			}
		}
		else {
			LOGGER.error("{} path is not a file or directory, returning", prefix);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * File exists.
	 * 
	 * @param filePath the file path
	 * @return true, if successful
	 */
	public boolean fileExists(final String filePath) {
		final String prefix = "createDirectory() :";

		LOGGER.debug("{} entered, filePath={}", prefix, filePath);

		if (StringUtils.isBlank(filePath)) {
			LOGGER.debug("{} path is blank, returning false", prefix);

			return false;
		}

		final boolean exists = new File(filePath).exists();

		LOGGER.debug("{} returning {}", prefix, exists);

		return exists;
	}
}
