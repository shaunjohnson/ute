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

import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.sources.HttpSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Class HttpUtils.
 */
public class HttpUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * Gets the full url.
	 * 
	 * @param source the source
	 * @return the full url
	 */
	public static String getFullUrl(final HttpSource source) {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		checkNotNull(source, "Source may not be null");

		final HttpLocation location = source.getLocation();
		checkNotNull(location, "Source location may not be null");

		final String url = StringUtils.trimToNull(location.getUrl());
		checkNotNull(url, "Source location url may not be blank");

		final String relativePath = source.getRelativePath();
		// Relative path may be null, StringUtils.join() will treat null values as empty strings

		final String fullUrl = PathUtils.buildFullPath(url, relativePath);

		LOGGER.debug("{} returning {}", prefix, fullUrl);

		return fullUrl;
	}

    /**
     * Prevent instantiation.
     */
    private HttpUtils() {
        throw new AssertionError("Cannot be instantiated");
    }
}
