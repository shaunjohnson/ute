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
package net.lmxm.ute.subversion.utils;

import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.executors.tasks.SubversionExportTaskExecutor;
import net.lmxm.ute.utils.PathUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class SubversionRepositoryLocationUtils.
 */
public final class SubversionRepositoryLocationUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionExportTaskExecutor.class);

	/**
	 * Gets the full url.
	 * 
	 * @param source the source
	 * 
	 * @return the full url
	 */
	public static String getFullUrl(final SubversionRepositorySource source) {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		Preconditions.checkNotNull(source, "Source may not be null");

		final SubversionRepositoryLocation location = source.getLocation();
		Preconditions.checkNotNull(location, "Source location may not be null");

		final String url = StringUtils.trimToNull(location.getUrl());
		Preconditions.checkNotNull(url, "Source location url may not be blank");

		final String relativePath = source.getRelativePath();
		// Relative path may be null, StringUtils.join() will treat null values as empty strings

		final String fullUrl = PathUtils.buildFullPath(url, relativePath);

		LOGGER.debug("{} returning {}", prefix, fullUrl);

		return fullUrl;
	}

	/**
	 * Instantiates a new subversion repository location utils.
	 */
	private SubversionRepositoryLocationUtils() {
		throw new AssertionError();
	}
}
