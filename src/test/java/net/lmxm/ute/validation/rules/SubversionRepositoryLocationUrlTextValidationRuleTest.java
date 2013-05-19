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

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static net.lmxm.ute.UteTestAssert.*;

/**
 * The Class SubversionRepositoryLocationUrlTextValidationRuleTest.
 */
public class SubversionRepositoryLocationUrlTextValidationRuleTest {

	/** The rule. */
	private SubversionRepositoryLocationUrlTextValidationRule rule = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		rule = new SubversionRepositoryLocationUrlTextValidationRule();
	}

	/**
	 * Test validate.
	 */
	@Test
	public void testValidate() {
		final List<String> messages = rule.validate("foobar");
		assertNotNull(messages);
		assertNotEmpty(messages);
		assertNotBlank(messages.get(0));
	}

	/**
	 * Test validate blank.
	 */
	@Test
	public void testValidateBlank() {
		final List<String> messages = rule.validate("    ");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate empty.
	 */
	@Test
	public void testValidateEmpty() {
		final List<String> messages = rule.validate("");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate http.
	 */
	@Test
	public void testValidateHttp() {
		final List<String> messages = rule.validate("http://google.com");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate http fragment.
	 */
	@Test
	public void testValidateHttpFragment() {
		final List<String> messages = rule.validate("http://google.com#fragment");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate http params.
	 */
	@Test
	public void testValidateHttpParams() {
		final List<String> messages = rule.validate("http://google.com?name=value");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate http port.
	 */
	@Test
	public void testValidateHttpPort() {
		final List<String> messages = rule.validate("http://google.com:8080");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate https.
	 */
	@Test
	public void testValidateHttps() {
		final List<String> messages = rule.validate("https://google.com");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate null.
	 */
	@Test(expected = NullPointerException.class)
	public void testValidateNull() {
		final List<String> messages = rule.validate(null);
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate svn.
	 */
	@Test
	public void testValidateSvn() {
		final List<String> messages = rule.validate("svn://google.com");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate svn ssh.
	 */
	@Test
	public void testValidateSvnSsh() {
		final List<String> messages = rule.validate("svn+ssh://google.com");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate wrong type.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWrongType() {
		final List<String> messages = rule.validate(42);
		assertNotNull(messages);
		assertEmpty(messages);
	}
}
