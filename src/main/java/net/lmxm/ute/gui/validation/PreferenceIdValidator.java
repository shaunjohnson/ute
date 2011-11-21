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
package net.lmxm.ute.gui.validation;

import javax.swing.JComponent;

import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.configuration.ConfigurationUtils;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;

/**
 * The Class PreferenceIdValidator.
 */
public final class PreferenceIdValidator extends AbstractIdValidator {

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/**
	 * Instantiates a new preference id validator.
	 * 
	 * @param preference the preference
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 */
	protected PreferenceIdValidator(final Preference preference, final JComponent component,
			final ConfigurationHolder configurationHolder) {
		super(preference, component);

		this.configurationHolder = configurationHolder;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.AbstractIdValidator#getExistingObject(java.lang.String)
	 */
	@Override
	protected IdentifiableBean getExistingObject(final String id) {
		return ConfigurationUtils.findPreferenceById(configurationHolder.getConfiguration(), id);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.AbstractIdValidator#getObjectInUseMessage()
	 */
	@Override
	protected String getObjectInUseMessage() {
		return ResourcesUtils.getResourceMessage(ValidatorResourceType.PREFERENCE_ID_ALREADY_USED);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.AbstractIdValidator#getObjectRequiredMessage()
	 */
	@Override
	protected String getObjectRequiredMessage() {
		return ResourcesUtils.getResourceMessage(ValidatorResourceType.PREFERENCE_ID_REQUIRED);
	}
}
