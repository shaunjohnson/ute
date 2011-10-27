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
import static org.junit.Assert.fail;

import net.lmxm.ute.configuration.ConfigurationUtils;

import org.junit.Test;

/**
 * The Class ConfigurationUtilsTest.
 */
public class ConfigurationUtilsTest {

	/**
	 * Test get file names.
	 */
	@Test
	public void testGetFileNames() {
		// Not yet implemented
	}

	/**
	 * Test get file system location.
	 */
	@Test
	public void testGetFileSystemLocation() {
		// Not yet implemented
	}

	/**
	 * Test get http location.
	 */
	@Test
	public void testGetHttpLocation() {
		// Not yet implemented
	}

	/**
	 * Test get job.
	 */
	@Test
	public void testGetJob() {
		// Not yet implemented
	}

	/**
	 * Test get property names.
	 */
	@Test
	public void testGetPropertyNames() {
		// Not yet implemented
	}

	/**
	 * Test get property values.
	 */
	@Test
	public void testGetPropertyValues() {
		// Not yet implemented
	}

	/**
	 * Test get subversion repository location.
	 */
	@Test
	public void testGetSubversionRepositoryLocation() {
		// Not yet implemented
	}

	/**
	 * Test interpolate job values.
	 */
	@Test
	public void testInterpolateJobValues() {
		// Not yet implemented
	}

	/**
	 * Test validate configuration.
	 */
	@Test
	public void testValidateConfiguration() {
		// Not yet implemented
	}

	/**
	 * Test validate does not contain properties.
	 */
	@Test
	public void testValidateDoesNotContainProperties() {
		ConfigurationUtils.validateDoesNotContainProperties(null);
		ConfigurationUtils.validateDoesNotContainProperties("");
		ConfigurationUtils.validateDoesNotContainProperties("    ");
		ConfigurationUtils.validateDoesNotContainProperties("abc");
		ConfigurationUtils.validateDoesNotContainProperties("a{b}c");

		try {
			ConfigurationUtils.validateDoesNotContainProperties("${}");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationUtils.validateDoesNotContainProperties("${abc}");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationUtils.validateDoesNotContainProperties("before${abc}");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationUtils.validateDoesNotContainProperties("${abc}after");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationUtils.validateDoesNotContainProperties("before${abc}after");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}
	}
}
