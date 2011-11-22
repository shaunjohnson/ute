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

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.configuration.ConfigurationUtils;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class JobIdAlreadyInUseValidationRule.
 */
public final class JobIdAlreadyInUseValidationRule extends AbstractStringValidationRule {

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/** The error message. */
	private final String errorMessage;

	/** The job. */
	private final Job job;

	/**
	 * Instantiates a new job id already in use validation rule.
	 * 
	 * @param job the job
	 * @param configurationHolder the configuration holder
	 */
	public JobIdAlreadyInUseValidationRule(final Job job, final ConfigurationHolder configurationHolder) {
		super();

		this.job = job;
		this.configurationHolder = configurationHolder;

		errorMessage = ResourcesUtils.getResourceMessage(ValidatorResourceType.JOB_ID_ALREADY_USED);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.rules.AbstractStringValidationRule#validateString(java.lang.String)
	 */
	@Override
	protected List<String> validateString(final String string) {
		final List<String> messages = new ArrayList<String>();

		if (StringUtils.isNotBlank(string)) {
			final Configuration configuration = configurationHolder.getConfiguration();
			final Job existingJob = ConfigurationUtils.findJobById(configuration, string);
			if (existingJob != null && job != existingJob) {
				messages.add(errorMessage);
			}
		}

		return messages;
	}
}
