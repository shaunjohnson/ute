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

import static com.google.common.base.Preconditions.checkNotNull;

import javax.swing.text.JTextComponent;

import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.resources.types.ValidatorResourceType;
import net.lmxm.ute.validation.rules.FileSystemLocationIdAlreadyInUseValidationRule;
import net.lmxm.ute.validation.rules.HttpLocationIdAlreadyInUseValidationRule;
import net.lmxm.ute.validation.rules.HttpLocationUrlTextValidationRule;
import net.lmxm.ute.validation.rules.JobIdAlreadyInUseValidationRule;
import net.lmxm.ute.validation.rules.PreferenceIdAlreadyInUseValidationRule;
import net.lmxm.ute.validation.rules.PropertyIdAlreadyInUseValidationRule;
import net.lmxm.ute.validation.rules.RequiredTextValidationRule;
import net.lmxm.ute.validation.rules.SubversionRepositoryLocationIdAlreadyInUseValidationRule;
import net.lmxm.ute.validation.rules.SubversionRepositoryLocationUrlTextValidationRule;
import net.lmxm.ute.validation.rules.TaskIdAlreadyInUseValidationRule;

/**
 * A factory for creating InputValidator objects.
 */
public final class InputValidatorFactory {

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param location the location
	 * @return the input validator
	 */
	private static InputValidator createFileSystemLocationIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final FileSystemLocation location) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.FILE_SYSTEM_LOCATION_ID_REQUIRED));
		validator.addRule(new FileSystemLocationIdAlreadyInUseValidationRule(location, configurationHolder));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @return the input validator
	 */
	public static InputValidator createFileSystemLocationPathValidator(final JTextComponent component) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.FILE_SYSTEM_LOCATION_PATH_REQUIRED));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param location the location
	 * @return the input validator
	 */
	private static InputValidator createHttpLocationIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final HttpLocation location) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.HTTP_LOCATION_ID_REQUIRED));
		validator.addRule(new HttpLocationIdAlreadyInUseValidationRule(location, configurationHolder));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @return the input validator
	 */
	public static InputValidator createHttpLocationUrlValidator(final JTextComponent component) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.HTTP_LOCATION_URL_REQUIRED));
		validator.addRule(new HttpLocationUrlTextValidationRule());

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param identifiableBean the identifiable bean
	 * @return the input validator
	 */
	public static InputValidator createIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final IdentifiableBean identifiableBean) {
		checkNotNull(identifiableBean, "IdentifiableBean is null");
		checkNotNull(component, "Component is null");
		checkNotNull(configurationHolder, "Configuration holder is null");

		final InputValidator inputValidator;
		if (identifiableBean instanceof FileSystemLocation) {
			inputValidator = createFileSystemLocationIdValidator(component, configurationHolder,
					(FileSystemLocation) identifiableBean);
		}
		else if (identifiableBean instanceof HttpLocation) {
			inputValidator = createHttpLocationIdValidator(component, configurationHolder,
					(HttpLocation) identifiableBean);
		}
		else if (identifiableBean instanceof Job) {
			inputValidator = createJobIdValidator(component, configurationHolder, (Job) identifiableBean);
		}
		else if (identifiableBean instanceof Preference) {
			inputValidator = createPreferenceIdValidator(component, configurationHolder, (Preference) identifiableBean);
		}
		else if (identifiableBean instanceof Property) {
			inputValidator = createPropertyIdValidator(component, configurationHolder, (Property) identifiableBean);
		}
		else if (identifiableBean instanceof SubversionRepositoryLocation) {
			inputValidator = createSubversionRepositoryLocationIdValidator(component, configurationHolder,
					(SubversionRepositoryLocation) identifiableBean);
		}
		else if (identifiableBean instanceof Task) {
			inputValidator = createTaskIdValidator(component, configurationHolder, (Task) identifiableBean);
		}
		else {
			throw new RuntimeException("Unsupported identifiable bean"); // TODO
		}

		return inputValidator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param job the job
	 * @return the input validator
	 */
	private static InputValidator createJobIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final Job job) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.JOB_ID_REQUIRED));
		validator.addRule(new JobIdAlreadyInUseValidationRule(job, configurationHolder));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param preference the preference
	 * @return the input validator
	 */
	private static InputValidator createPreferenceIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final Preference preference) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.PREFERENCE_ID_REQUIRED));
		validator.addRule(new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param property the property
	 * @return the input validator
	 */
	private static InputValidator createPropertyIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final Property property) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.PROPERTY_ID_REQUIRED));
		validator.addRule(new PropertyIdAlreadyInUseValidationRule(property, configurationHolder));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @return the input validator
	 */
	public static InputValidator createPropertyValueValidator(final JTextComponent component) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.PROPERTY_VALUE_REQUIRED));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param location the location
	 * @return the input validator
	 */
	private static InputValidator createSubversionRepositoryLocationIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final SubversionRepositoryLocation location) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(
				ValidatorResourceType.SUBVERSION_REPOSITORY_LOCATION_ID_REQUIRED));
		validator.addRule(new SubversionRepositoryLocationIdAlreadyInUseValidationRule(location, configurationHolder));

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @return the input validator
	 */
	public static InputValidator createSubversionRepositoryLocationUrlValidator(final JTextComponent component) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(
				ValidatorResourceType.SUBVERSION_REPOSITORY_LOCATION_URL_REQUIRED));
		validator.addRule(new SubversionRepositoryLocationUrlTextValidationRule());

		return validator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @param task the task
	 * @return the input validator
	 */
	private static InputValidator createTaskIdValidator(final JTextComponent component,
			final ConfigurationHolder configurationHolder, final Task task) {
		final InputValidator validator = new TextComponentValidator(component);

		validator.addRule(new RequiredTextValidationRule(ValidatorResourceType.TASK_ID_REQUIRED));
		validator.addRule(new TaskIdAlreadyInUseValidationRule(task, configurationHolder));

		return validator;
	}
}
