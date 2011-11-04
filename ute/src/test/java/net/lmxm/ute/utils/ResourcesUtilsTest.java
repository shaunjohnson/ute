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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import net.lmxm.ute.resources.ResourcesUtils;

import org.junit.Test;

/**
 * The Class ResourcesUtilsTest.
 */
public class ResourcesUtilsTest {

	/**
	 * Test get application attributions.
	 */
	@Test
	public void testGetApplicationAttributions() {
		assertNotNull(ResourcesUtils.getApplicationAttributions());
		assertTrue(ResourcesUtils.getApplicationAttributions().length() > 0);
	}

	/**
	 * Test get application name.
	 */
	@Test
	public void testGetApplicationName() {
		assertNotNull(ResourcesUtils.getApplicationName());
		assertTrue(ResourcesUtils.getApplicationName().length() > 0);
	}

	/**
	 * Test get application version.
	 */
	@Test
	public void testGetApplicationVersion() {
		assertNotNull(ResourcesUtils.getApplicationVersion());
		assertTrue(ResourcesUtils.getApplicationVersion().length() > 0);
	}
}
