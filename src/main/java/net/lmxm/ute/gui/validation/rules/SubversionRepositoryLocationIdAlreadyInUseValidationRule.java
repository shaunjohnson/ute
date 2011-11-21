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
package net.lmxm.ute.gui.validation.rules;

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.configuration.ConfigurationUtils;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;

import org.codehaus.plexus.util.StringUtils;

/**
 * The Class SubversionRepositoryLocationIdAlreadyInUseValidationRule.
 */
public final class SubversionRepositoryLocationIdAlreadyInUseValidationRule extends AbstractTextComponentValidationRule {

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/** The error message. */
	private final String errorMessage;

	/** The subversion repository location. */
	private final SubversionRepositoryLocation subversionRepositoryLocation;

	/**
	 * Instantiates a new subversion repository location id already in use validation rule.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 * @param configurationHolder the configuration holder
	 */
	public SubversionRepositoryLocationIdAlreadyInUseValidationRule(
			final SubversionRepositoryLocation subversionRepositoryLocation,
			final ConfigurationHolder configurationHolder) {
		super();

		this.subversionRepositoryLocation = subversionRepositoryLocation;
		this.configurationHolder = configurationHolder;

		errorMessage = ResourcesUtils
				.getResourceMessage(ValidatorResourceType.SUBVERSION_REPOSITORY_LOCATION_ID_ALREADY_USED);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.AbstractTextComponentValidationRule#validateText(java.lang.String)
	 */
	@Override
	public List<String> validateText(final String text) {
		final List<String> messages = new ArrayList<String>();

		if (StringUtils.isNotBlank(text)) {
			final Configuration configuration = configurationHolder.getConfiguration();
			final SubversionRepositoryLocation existingLocation = ConfigurationUtils
					.findSubversionRepositoryLocationById(configuration, text);
			if (existingLocation != null && subversionRepositoryLocation != existingLocation) {
				messages.add(errorMessage);
			}
		}

		return messages;
	}
}
