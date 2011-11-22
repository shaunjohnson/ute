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
package net.lmxm.ute.validation.rules;

import static junit.framework.Assert.assertNotNull;
import static net.lmxm.ute.UteTestAssert.assertEmpty;
import static net.lmxm.ute.UteTestAssert.assertNotBlank;
import static net.lmxm.ute.UteTestAssert.assertNotEmpty;

import java.util.List;

import net.lmxm.ute.resources.types.ValidatorResourceType;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class RequiredTextValidationRuleTest.
 */
public class RequiredTextValidationRuleTest {

	/** The rule. */
	private ValidationRule rule = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		rule = new RequiredTextValidationRule(ValidatorResourceType.JOB_ID_REQUIRED);
	}

	/**
	 * Test required text validation rule.
	 */
	@Test
	public void testRequiredTextValidationRule() {
		new RequiredTextValidationRule(ValidatorResourceType.JOB_ID_REQUIRED);
	}

	/**
	 * Test required text validation rule null.
	 */
	@Test(expected = NullPointerException.class)
	public void testRequiredTextValidationRuleNull() {
		new RequiredTextValidationRule(null);
	}

	/**
	 * Test validate.
	 */
	@Test
	public void testValidate() {
		final List<String> messages = rule.validate("foobar");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate blank.
	 */
	@Test
	public void testValidateBlank() {
		final List<String> messages = rule.validate("    ");
		assertNotNull(messages);
		assertNotEmpty(messages);
		assertNotBlank(messages.get(0));
	}

	/**
	 * Test validate empty.
	 */
	@Test
	public void testValidateEmpty() {
		final List<String> messages = rule.validate("");
		assertNotNull(messages);
		assertNotEmpty(messages);
		assertNotBlank(messages.get(0));
	}

	/**
	 * Test validate object not string.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateObjectNotString() {
		new RequiredTextValidationRule(ValidatorResourceType.JOB_ID_REQUIRED).validate(42);
	}

	/**
	 * Test validate object null.
	 */
	@Test(expected = NullPointerException.class)
	public void testValidateObjectNull() {
		new RequiredTextValidationRule(ValidatorResourceType.JOB_ID_REQUIRED).validate(null);
	}
}
