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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextField;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.configuration.ConfigurationUtils;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;

import org.codehaus.plexus.util.StringUtils;

import com.google.common.base.Preconditions;

/**
 * The Class JobIdValidator.
 */
public final class JobIdValidator extends AbstractInputValidator {

	/** The Constant JOB_ID_ALREADY_USED. */
	private static final String JOB_ID_ALREADY_USED = ResourcesUtils
			.getResourceMessage(ValidatorResourceType.JOB_ID_ALREADY_USED);

	/** The Constant JOB_ID_REQUIRED. */
	private static final String JOB_ID_REQUIRED = ResourcesUtils
			.getResourceMessage(ValidatorResourceType.JOB_ID_REQUIRED);

	/**
	 * Adds the input validator.
	 * 
	 * @param job the job
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 */
	public static void addInputValidator(final Job job, final JComponent component,
			final ConfigurationHolder configurationHolder) {
		Preconditions.checkNotNull(job, "Job is null");
		Preconditions.checkNotNull(component, "Component is null");
		Preconditions.checkNotNull(configurationHolder, "Configuration holder is null");

		component.setInputVerifier(new JobIdValidator(job, component, configurationHolder));
	}

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/** The job. */
	private final Job job;

	/**
	 * Instantiates a new job id validator.
	 * 
	 * @param job the job
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 */
	private JobIdValidator(final Job job, final JComponent component, final ConfigurationHolder configurationHolder) {
		super(component);

		this.job = job;
		this.configurationHolder = configurationHolder;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.AbstractInputValidator#validate(javax.swing.JComponent)
	 */
	@Override
	protected List<String> validate(final JComponent component) {
		final List<String> messages = new ArrayList<String>();

		if (component instanceof JTextField) {
			final String text = ((JTextField) component).getText();

			if (StringUtils.isBlank(text)) {
				messages.add(JOB_ID_REQUIRED);
			}
			else {
				final Job existingJob = ConfigurationUtils.findJobById(configurationHolder.getConfiguration(), text);

				if (existingJob != null && job != existingJob) {
					messages.add(JOB_ID_ALREADY_USED);
				}
			}
		}
		else {
			messages.add("Error occurred validating input");
		}

		return messages;
	}

}
