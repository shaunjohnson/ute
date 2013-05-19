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
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Class HttpUtilsTest.
 */
public class HttpUtilsTest {

	/** The Constant FULL_URL. */
	private static final String FULL_URL = "http://google.com/mail";

	/** The Constant RELATIVE_PATH. */
	private static final String RELATIVE_PATH = "mail";

	/** The Constant URL. */
	private static final String URL = "http://google.com";

	/**
	 * Test get full url.
	 */
	@Test
	public void testGetFullUrl() {
		// Test null source
		try {
			HttpUtils.getFullUrl(null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test null location
		final HttpSource httpSource = new HttpSource();
		try {
			HttpUtils.getFullUrl(httpSource);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test null location URL
		final HttpLocation location = new HttpLocation();
		httpSource.setLocation(location);
		try {
			HttpUtils.getFullUrl(httpSource);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test blank location URL
		location.setUrl("");
		try {
			HttpUtils.getFullUrl(httpSource);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Test URL only
		location.setUrl(URL);
		assertEquals(URL, HttpUtils.getFullUrl(httpSource));

		// Test URL with relative path
		httpSource.setRelativePath(RELATIVE_PATH);
		assertEquals(FULL_URL, HttpUtils.getFullUrl(httpSource));
	}
}
