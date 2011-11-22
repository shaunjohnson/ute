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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.targets.FileSystemTarget;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * The Class FileSystemTargetUtilsTest.
 */
public class FileSystemTargetUtilsTest {

	/**
	 * Test get full path.
	 */
	@Test
	public void testGetFullPath() {
		// Null target
		try {
			FileSystemTargetUtils.getFullPath(null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null target location
		try {
			FileSystemTargetUtils.getFullPath(new FileSystemTarget());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null target location path
		try {
			final FileSystemTarget fileSystemTarget = new FileSystemTarget();

			fileSystemTarget.setLocation(new FileSystemLocation());

			FileSystemTargetUtils.getFullPath(fileSystemTarget);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		final FileSystemTarget fileSystemTarget = new FileSystemTarget();
		final FileSystemLocation fileSystemLocation = new FileSystemLocation();

		fileSystemLocation.setPath("path");
		fileSystemTarget.setLocation(fileSystemLocation);

		// Null relative path
		final String fullPath = FileSystemTargetUtils.getFullPath(fileSystemTarget);
		assertTrue(StringUtils.isNotBlank(fullPath));

		// Empty relative path
		fileSystemTarget.setRelativePath("");
		final String fullPathEmptyRelativePath = FileSystemTargetUtils.getFullPath(fileSystemTarget);
		assertTrue(StringUtils.isNotBlank(fullPathEmptyRelativePath));
		assertEquals(fullPath, fullPathEmptyRelativePath);

		// Blank relative path
		fileSystemTarget.setRelativePath("    ");
		final String fullPathBlankRelativePath = FileSystemTargetUtils.getFullPath(fileSystemTarget);
		assertTrue(StringUtils.isNotBlank(fullPathBlankRelativePath));
		assertEquals(fullPath, fullPathBlankRelativePath);

		// Valid relative path
		fileSystemTarget.setRelativePath("test");
		final String fullPathWithRelativePath = FileSystemTargetUtils.getFullPath(fileSystemTarget);
		assertTrue(StringUtils.isNotBlank(fullPathWithRelativePath));
		assertFalse(fullPath.equals(fullPathWithRelativePath));
		assertEquals(fullPath + "/" + "test", fullPathWithRelativePath);
	}
}
