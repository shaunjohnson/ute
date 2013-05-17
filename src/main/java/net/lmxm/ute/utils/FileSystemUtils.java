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
package net.lmxm.ute.utils;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.exceptions.TaskExecuterException;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.DirectoryScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

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

		checkArgument(StringUtils.isNotBlank(path), "Path may not be blank or null");

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

		checkArgument(StringUtils.isNotBlank(path), "Path may not be blank or null");

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
                throw new TaskExecuterException(ExceptionResourceType.DIRECTORY_ALREADY_EXISTS, directory.getAbsoluteFile());
			}
		}

		LOGGER.debug("{} returning {}", prefix, directory);

		return directory;
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
