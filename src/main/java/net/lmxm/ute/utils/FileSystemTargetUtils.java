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

import static com.google.common.base.Preconditions.checkNotNull;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.targets.FileSystemTarget;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FileSystemTargetUtils.
 */
public final class FileSystemTargetUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemTargetUtils.class);

	/**
	 * Gets the full path.
	 * 
	 * @param target the target
	 * 
	 * @return the full path
	 */
	public static String getFullPath(final FileSystemTarget target) {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered, target={}", prefix, target);

		checkNotNull(target, "Target may not be null");

		final FileSystemLocation location = target.getLocation();
		checkNotNull(location, "Target location may not be null");

		final String path = StringUtils.trimToNull(location.getPath());
		checkNotNull(path, "Target location path may not be blank");

		final String relativePath = target.getRelativePath();
		// Relative path may be null, StringUtils.join() will treat null values as empty strings

		final String fullPath = PathUtils.buildFullPath(path, relativePath);

		LOGGER.debug("{} returning {}", prefix, fullPath);

		return fullPath;
	}

	/**
	 * Instantiates a new file system target utils.
	 */
	private FileSystemTargetUtils() {
		throw new AssertionError();
	}
}
