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
package net.lmxm.ute.configuration;

import static org.junit.Assert.assertEquals;
import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.enums.SubversionDepth;
import net.lmxm.ute.exceptions.ConfigurationException;
import noNamespace.ScopeType;
import noNamespace.SubversionDepthType;

import org.junit.Test;

/**
 * The Class ConfigurationConversionUtilsTest.
 */
public class ConfigurationConversionUtilsTest {

	/**
	 * Test convert scope to scope type.
	 */
	@Test
	public void testConvertScopeToScopeType() {
		assertEquals(ScopeType.FILE, ConfigurationConversionUtils.convertScopeToScopeType(Scope.FILE));
		assertEquals(ScopeType.LINE, ConfigurationConversionUtils.convertScopeToScopeType(Scope.LINE));
	}

	/**
	 * Test convert scope to scope type null.
	 */
	@Test(expected = ConfigurationException.class)
	public void testConvertScopeToScopeTypeNull() {
		ConfigurationConversionUtils.convertScopeToScopeType(null);
	}

	/**
	 * Test convert scope type to scope.
	 */
	@Test
	public void testConvertScopeTypeToScope() {
		assertEquals(Scope.FILE, ConfigurationConversionUtils.convertScopeTypeToScope(ScopeType.FILE));
		assertEquals(Scope.LINE, ConfigurationConversionUtils.convertScopeTypeToScope(ScopeType.LINE));
	}

	/**
	 * Test convert scope type to scope null.
	 */
	@Test(expected = ConfigurationException.class)
	public void testConvertScopeTypeToScopeNull() {
		ConfigurationConversionUtils.convertScopeTypeToScope(null);
	}

	/**
	 * Test convert subversion depth to subversion depth type.
	 */
	@Test
	public void testConvertSubversionDepthToSubversionDepthType() {
		assertEquals(SubversionDepthType.EMPTY,
				ConfigurationConversionUtils.convertSubversionDepthToSubversionDepthType(SubversionDepth.EMPTY));
		assertEquals(SubversionDepthType.EXCLUDE,
				ConfigurationConversionUtils.convertSubversionDepthToSubversionDepthType(SubversionDepth.EXCLUDE));
		assertEquals(SubversionDepthType.FILES,
				ConfigurationConversionUtils.convertSubversionDepthToSubversionDepthType(SubversionDepth.FILES));
		assertEquals(SubversionDepthType.IMMEDIATES,
				ConfigurationConversionUtils.convertSubversionDepthToSubversionDepthType(SubversionDepth.IMMEDIATES));
		assertEquals(SubversionDepthType.INFINITY,
				ConfigurationConversionUtils.convertSubversionDepthToSubversionDepthType(SubversionDepth.INFINITY));
	}

	/**
	 * Test convert subversion depth to subversion depth type null.
	 */
	@Test(expected = ConfigurationException.class)
	public void testConvertSubversionDepthToSubversionDepthTypeNull() {
		ConfigurationConversionUtils.convertSubversionDepthToSubversionDepthType(null);
	}

	/**
	 * Test convert subversion depth type to subversion depth.
	 */
	@Test
	public void testConvertSubversionDepthTypeToSubversionDepth() {
		assertEquals(SubversionDepth.EMPTY,
				ConfigurationConversionUtils.convertSubversionDepthTypeToSubversionDepth(SubversionDepthType.EMPTY));
		assertEquals(SubversionDepth.EXCLUDE,
				ConfigurationConversionUtils.convertSubversionDepthTypeToSubversionDepth(SubversionDepthType.EXCLUDE));
		assertEquals(SubversionDepth.FILES,
				ConfigurationConversionUtils.convertSubversionDepthTypeToSubversionDepth(SubversionDepthType.FILES));
		assertEquals(SubversionDepth.IMMEDIATES,
				ConfigurationConversionUtils
						.convertSubversionDepthTypeToSubversionDepth(SubversionDepthType.IMMEDIATES));
		assertEquals(SubversionDepth.INFINITY,
				ConfigurationConversionUtils.convertSubversionDepthTypeToSubversionDepth(SubversionDepthType.INFINITY));
	}

	/**
	 * Test convert subversion depth type to subversion depth null.
	 */
	@Test(expected = ConfigurationException.class)
	public void testConvertSubversionDepthTypeToSubversionDepthNull() {
		ConfigurationConversionUtils.convertSubversionDepthTypeToSubversionDepth(null);
	}

}
