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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PathUtils.
 */
public class PathUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PathUtils.class);

	/**
	 * Builds the full path.
	 * 
	 * @param rootPath the root path
	 * @param relativePath the relative path
	 * @return the string
	 */
	public static String buildFullPath(final String rootPath, final String relativePath) {
		final String prefix = "buildFullPath() :";

		LOGGER.debug("{} entered, root={}, relativepath=" + relativePath, prefix, rootPath);

		final String root = StringUtils.removeEnd(StringUtils.trimToEmpty(rootPath), "/");
		final String relative = StringUtils.removeStart(StringUtils.trimToEmpty(relativePath), "/");

		String fullPath;

		if (StringUtils.isBlank(relative)) {
			fullPath = root;
		}
		else {
			fullPath = StringUtils.join(new Object[] { root, "/", relative });
		}

		LOGGER.debug("{} returning {}", prefix, fullPath);

		return fullPath;
	}

	/**
	 * Instantiates a new path utils.
	 */
	private PathUtils() {
		throw new AssertionError();
	}
}
