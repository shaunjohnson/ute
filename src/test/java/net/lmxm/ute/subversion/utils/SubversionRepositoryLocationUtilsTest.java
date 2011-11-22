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
package net.lmxm.ute.subversion.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.subversion.utils.SubversionRepositoryLocationUtils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * The Class SubversionRepositoryLocationUtilsTest.
 */
public class SubversionRepositoryLocationUtilsTest {

	/**
	 * Test get full url.
	 */
	@Test
	public void testGetFullUrl() {
		// Null target
		try {
			SubversionRepositoryLocationUtils.getFullUrl(null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null target location
		try {
			SubversionRepositoryLocationUtils.getFullUrl(new SubversionRepositorySource());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null target location path
		try {
			final SubversionRepositorySource subversionRepositorySource = new SubversionRepositorySource();

			subversionRepositorySource.setLocation(new SubversionRepositoryLocation());

			SubversionRepositoryLocationUtils.getFullUrl(subversionRepositorySource);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		final SubversionRepositorySource subversionRepositorySource = new SubversionRepositorySource();
		final SubversionRepositoryLocation subversionRepositoryLocation = new SubversionRepositoryLocation();

		subversionRepositoryLocation.setUrl("http://ea");
		subversionRepositorySource.setLocation(subversionRepositoryLocation);

		// Null relative path
		final String fullPath = SubversionRepositoryLocationUtils.getFullUrl(subversionRepositorySource);
		assertTrue(StringUtils.isNotBlank(fullPath));

		// Empty relative path
		subversionRepositorySource.setRelativePath("");
		final String fullPathEmptyRelativePath = SubversionRepositoryLocationUtils
				.getFullUrl(subversionRepositorySource);
		assertTrue(StringUtils.isNotBlank(fullPathEmptyRelativePath));
		assertEquals(fullPath, fullPathEmptyRelativePath);

		// Blank relative path
		subversionRepositorySource.setRelativePath("    ");
		final String fullPathBlankRelativePath = SubversionRepositoryLocationUtils
				.getFullUrl(subversionRepositorySource);
		assertTrue(StringUtils.isNotBlank(fullPathBlankRelativePath));
		assertEquals(fullPath, fullPathBlankRelativePath);

		// Valid relative path
		subversionRepositorySource.setRelativePath("test");
		final String fullPathWithRelativePath = SubversionRepositoryLocationUtils
				.getFullUrl(subversionRepositorySource);
		assertTrue(StringUtils.isNotBlank(fullPathWithRelativePath));
		assertFalse(fullPath.equals(fullPathWithRelativePath));
		assertEquals(fullPath + "/" + "test", fullPathWithRelativePath);
	}
}
