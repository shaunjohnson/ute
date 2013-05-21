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

import net.lmxm.ute.TestConfigurationHolders;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.configuration.ConfigurationHolder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static net.lmxm.ute.UteTestAssert.*;

/**
 * The Class PreferenceIdAlreadyInUseValidationRuleTest.
 */
public class PreferenceIdAlreadyInUseValidationRuleTest {

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
		configuration.getPreferences().add(new Preference(id));

		configurationHolder = new ConfigurationHolder() {
			@Override
			public Configuration getConfiguration() {
				return configuration;
			}
		};
	}

	/**
	 * Test preference id already in use validation rule.
	 */
	@Test
	public void testPreferenceIdAlreadyInUseValidationRule() {
		new PreferenceIdAlreadyInUseValidationRule(new Preference(), TestConfigurationHolders.BLANK_CONFIGURATION);
	}

	/**
	 * Test preference id already in use validation rule null configuration holder.
	 */
	@Test(expected = NullPointerException.class)
	public void testPreferenceIdAlreadyInUseValidationRuleNullConfigurationHolder() {
		new PreferenceIdAlreadyInUseValidationRule(new Preference(), null);
	}

    /**
     * Test configuration holder returning null configuration.
     */
    @Test(expected = IllegalStateException.class)
    public void testConfigurationHolderReturningNullConfiguration() {
        new PreferenceIdAlreadyInUseValidationRule(new Preference(),
                TestConfigurationHolders.CONFIGURATION_ALWAYS_NULL);
    }

	/**
	 * Test preference id already in use validation rule null location.
	 */
	@Test(expected = NullPointerException.class)
	public void testPreferenceIdAlreadyInUseValidationRuleNullLocation() {
		new PreferenceIdAlreadyInUseValidationRule(null, TestConfigurationHolders.BLANK_CONFIGURATION);
	}

	/**
	 * Test validate.
	 */
	@Test
	public void testValidate() {
		final Preference preference = new Preference(id);
		final ValidationRule rule = new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder);

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
		final Preference preference = new Preference(id);
		final ValidationRule rule = new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder);

		final List<String> messages = rule.validate("    ");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate different id.
	 */
	@Test
	public void testValidateDifferentId() {
		final Preference preference = new Preference(id);
		final ValidationRule rule = new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder);

		final List<String> messages = rule.validate("foobar");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate empty.
	 */
	@Test
	public void testValidateEmpty() {
		final Preference preference = new Preference(id);
		final ValidationRule rule = new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder);

		final List<String> messages = rule.validate("");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate null.
	 */
	@Test(expected = NullPointerException.class)
	public void testValidateNull() {
		final Preference preference = new Preference(id);
		final ValidationRule rule = new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder);

		final List<String> messages = rule.validate(null);
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate wrong type.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWrongType() {
		final Preference preference = new Preference(id);
		final ValidationRule rule = new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder);

		final List<String> messages = rule.validate(42);
		assertNotNull(messages);
		assertEmpty(messages);
	}
}
