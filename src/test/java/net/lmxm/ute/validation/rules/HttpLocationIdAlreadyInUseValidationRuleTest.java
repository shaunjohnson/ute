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

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.configuration.ConfigurationHolder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static net.lmxm.ute.UteTestAssert.*;

/**
 * The Class HttpLocationIdAlreadyInUseValidationRuleTest.
 */
public class HttpLocationIdAlreadyInUseValidationRuleTest {

	/** The Constant id. */
	private static final String id = "id";

	/** The rule. */
	private ConfigurationHolder configurationHolder = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		final Configuration configuration = new Configuration();
		configuration.getHttpLocations().add(new HttpLocation(id));

		configurationHolder = new ConfigurationHolder() {
			@Override
			public Configuration getConfiguration() {
				return configuration;
			}
		};
	}

	/**
	 * Test http location id already in use validation rule.
	 */
	@Test
	public void testHttpLocationIdAlreadyInUseValidationRule() {
		new HttpLocationIdAlreadyInUseValidationRule(new HttpLocation(), new ConfigurationHolder() {
			@Override
			public Configuration getConfiguration() {
				return new Configuration();
			}
		});
	}

	/**
	 * Test http location id already in use validation rule null configuration holder.
	 */
	@Test(expected = NullPointerException.class)
	public void testHttpLocationIdAlreadyInUseValidationRuleNullConfigurationHolder() {
		new HttpLocationIdAlreadyInUseValidationRule(new HttpLocation(), null);
	}

	/**
	 * Test http location id already in use validation rule null location.
	 */
	@Test(expected = NullPointerException.class)
	public void testHttpLocationIdAlreadyInUseValidationRuleNullLocation() {
		new HttpLocationIdAlreadyInUseValidationRule(null, new ConfigurationHolder() {
			@Override
			public Configuration getConfiguration() {
				return new Configuration();
			}
		});
	}

	/**
	 * Test validate.
	 */
	@Test
	public void testValidate() {
		final HttpLocation location = new HttpLocation(id);
		final ValidationRule rule = new HttpLocationIdAlreadyInUseValidationRule(location, configurationHolder);

		final List<String> messages = rule.validate("id");
		assertNotNull(messages);
		assertNotEmpty(messages);
		assertNotBlank(messages.get(0));
	}

	/**
	 * Test validate blank.
	 */
	@Test
	public void testValidateBlank() {
		final HttpLocation location = new HttpLocation(id);
		final ValidationRule rule = new HttpLocationIdAlreadyInUseValidationRule(location, configurationHolder);

		final List<String> messages = rule.validate("    ");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate different id.
	 */
	@Test
	public void testValidateDifferentId() {
		final HttpLocation location = new HttpLocation(id);
		final ValidationRule rule = new HttpLocationIdAlreadyInUseValidationRule(location, configurationHolder);

		final List<String> messages = rule.validate("foobar");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate empty.
	 */
	@Test
	public void testValidateEmpty() {
		final HttpLocation location = new HttpLocation(id);
		final ValidationRule rule = new HttpLocationIdAlreadyInUseValidationRule(location, configurationHolder);

		final List<String> messages = rule.validate("");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate null.
	 */
	@Test(expected = NullPointerException.class)
	public void testValidateNull() {
		final HttpLocation location = new HttpLocation(id);
		final ValidationRule rule = new HttpLocationIdAlreadyInUseValidationRule(location, configurationHolder);

		final List<String> messages = rule.validate(null);
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate wrong type.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWrongType() {
		final HttpLocation location = new HttpLocation(id);
		final ValidationRule rule = new HttpLocationIdAlreadyInUseValidationRule(location, configurationHolder);

		final List<String> messages = rule.validate(42);
		assertNotNull(messages);
		assertEmpty(messages);
	}
}
