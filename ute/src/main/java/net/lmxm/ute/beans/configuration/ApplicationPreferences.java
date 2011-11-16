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
package net.lmxm.ute.beans.configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.exceptions.ConfigurationException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ApplicationPreferences.
 */
public final class ApplicationPreferences {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPreferences.class);

	/** The Constant PREFERENCES_FILE_NAME. */
	private static final String PREFERENCES_FILE_NAME = "ute-prefs.xml";

	/** The configuration. */
	private final Configuration configuration;

	/**
	 * Instantiates a new application preferences.
	 * 
	 * @param configurationFile the configuration file
	 */
	public ApplicationPreferences(final File configurationFile) {
		final String prefix = "ApplicationPreferences() :";

		LOGGER.debug("{} entered. configurationFile={}", prefix, configurationFile);

		XMLConfiguration xmlConfiguration = null;

		try {
			xmlConfiguration = new XMLConfiguration();

			final File preferencesFile = new File(configurationFile.getParent(), PREFERENCES_FILE_NAME);

			if (!preferencesFile.exists()) {
				LOGGER.debug("{} preferences file does not exist, creating a new file", prefix);

				createEmptyPreferencesFile(preferencesFile);
			}

			LOGGER.debug("{} loading preferences", prefix);

			xmlConfiguration.load(preferencesFile);
		}
		catch (final org.apache.commons.configuration.ConfigurationException e) {
			LOGGER.debug(prefix + " ConfigurationException caught trying to load configuration", e);

			throw new ConfigurationException("Unable to load preferences file", e);
		}

		configuration = xmlConfiguration;

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Creates the empty preferences file.
	 * 
	 * @param preferencesFile the preferences file
	 */
	private void createEmptyPreferencesFile(final File preferencesFile) {
		final String prefix = "createEmptyPreferencesFile() :";

		LOGGER.debug("{} entered, preferencesFile={}", prefix, preferencesFile);

		try {
			if (preferencesFile.createNewFile()) {
				LOGGER.debug("{} preferences file created successfully", prefix);
			}
			else {
				LOGGER.error("{} unable to create preferences file", prefix);

				throw new ConfigurationException("Unable to create preferences file");
			}

			final FileWriter fileWriter = new FileWriter(preferencesFile);
			fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><preferences/>");
			fileWriter.close();
		}
		catch (final IOException e) {
			LOGGER.error("{} unable to create empty preferences file", prefix);

			throw new ConfigurationException("Unable to create empty preferences file", e);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Checks for all preferences.
	 * 
	 * @param preferences the preferences
	 * @return true, if successful
	 */
	public boolean hasAllPreferences(final List<Preference> preferences) {
		boolean hasAllPreferences = true;

		for (final Preference preference : preferences) {
			if (!hasPreference(preference.getId())) {
				hasAllPreferences = false;
				break;
			}
		}

		return hasAllPreferences;
	}

	/**
	 * Checks for preference.
	 * 
	 * @param key the key
	 * @return true, if successful
	 */
	private boolean hasPreference(final String key) {
		return StringUtils.isNotBlank(configuration.getString(key));
	}

	/**
	 * Load preference values.
	 * 
	 * @param preferences the preferences
	 */
	public void loadPreferenceValues(final List<Preference> preferences) {
		for (final Preference preference : preferences) {
			preference.setValue(configuration.getString(preference.getId()));
		}
	}

	/**
	 * Save preference values.
	 * 
	 * @param preferences the preferences
	 */
	public void savePreferenceValues(final List<Preference> preferences) {
		for (final Preference preference : preferences) {
			configuration.setProperty(preference.getId(), preference.getValue());
		}
	}
}
