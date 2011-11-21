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
import net.lmxm.ute.gui.validation.rules.FileSystemLocationIdAlreadyInUseValidationRule;
import net.lmxm.ute.gui.validation.rules.HttpLocationIdAlreadyInUseValidationRule;
import net.lmxm.ute.gui.validation.rules.JobIdAlreadyInUseValidationRule;
import net.lmxm.ute.gui.validation.rules.PreferenceIdAlreadyInUseValidationRule;
import net.lmxm.ute.gui.validation.rules.PropertyIdAlreadyInUseValidationRule;
import net.lmxm.ute.gui.validation.rules.RequiredTextValidationRule;
import net.lmxm.ute.gui.validation.rules.SubversionRepositoryLocationIdAlreadyInUseValidationRule;
import net.lmxm.ute.gui.validation.rules.TaskIdAlreadyInUseValidationRule;
import net.lmxm.ute.gui.validation.rules.ValidationRule;
import net.lmxm.ute.resources.types.ValidatorResourceType;

import com.google.common.base.Preconditions;

/**
 * A factory for creating InputValidator objects.
 */
public final class InputValidatorFactory {

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param fileSystemLocation the file system location
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input validator
	 */
	private static InputValidator createFileSystemLocationIdValidator(final FileSystemLocation fileSystemLocation,
			final JTextComponent component, final ConfigurationHolder configurationHolder) {
		final List<ValidationRule> rules = new ArrayList<ValidationRule>();

		rules.add(new RequiredTextValidationRule(ValidatorResourceType.FILE_SYSTEM_LOCATION_ID_REQUIRED));
		rules.add(new FileSystemLocationIdAlreadyInUseValidationRule(fileSystemLocation, configurationHolder));

		return new TextComponentValidator(component, rules.toArray(new ValidationRule[0]));
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param httpLocation the http location
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input validator
	 */
	private static InputValidator createHttpLocationIdValidator(final HttpLocation httpLocation,
			final JTextComponent component, final ConfigurationHolder configurationHolder) {
		final List<ValidationRule> rules = new ArrayList<ValidationRule>();

		rules.add(new RequiredTextValidationRule(ValidatorResourceType.HTTP_LOCATION_ID_REQUIRED));
		rules.add(new HttpLocationIdAlreadyInUseValidationRule(httpLocation, configurationHolder));

		return new TextComponentValidator(component, rules.toArray(new ValidationRule[0]));
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param identifiableBean the identifiable bean
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input verifier
	 */
	public static InputValidator createIdValidator(final IdentifiableBean identifiableBean,
			final JTextComponent component, final ConfigurationHolder configurationHolder) {
		Preconditions.checkNotNull(identifiableBean, "IdentifiableBean is null");
		Preconditions.checkNotNull(component, "Component is null");
		Preconditions.checkNotNull(configurationHolder, "Configuration holder is null");

		final InputValidator inputValidator;
		if (identifiableBean instanceof FileSystemLocation) {
			inputValidator = createFileSystemLocationIdValidator((FileSystemLocation) identifiableBean, component,
					configurationHolder);
		}
		else if (identifiableBean instanceof HttpLocation) {
			inputValidator = createHttpLocationIdValidator((HttpLocation) identifiableBean, component,
					configurationHolder);
		}
		else if (identifiableBean instanceof Job) {
			inputValidator = createJobIdValidator((Job) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof Preference) {
			inputValidator = createPreferenceIdValidator((Preference) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof Property) {
			inputValidator = createPropertyIdValidator((Property) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof SubversionRepositoryLocation) {
			inputValidator = createSubversionRepositoryLocationIdValidator(
					(SubversionRepositoryLocation) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof Task) {
			inputValidator = createTaskIdValidator((Task) identifiableBean, component, configurationHolder);
		}
		else {
			throw new RuntimeException("Unsupported identifiable bean"); // TODO
		}

		return inputValidator;
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param job the job
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input validator
	 */
	private static InputValidator createJobIdValidator(final Job job, final JTextComponent component,
			final ConfigurationHolder configurationHolder) {
		final List<ValidationRule> rules = new ArrayList<ValidationRule>();

		rules.add(new RequiredTextValidationRule(ValidatorResourceType.JOB_ID_REQUIRED));
		rules.add(new JobIdAlreadyInUseValidationRule(job, configurationHolder));

		return new TextComponentValidator(component, rules.toArray(new ValidationRule[0]));
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param preference the preference
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input validator
	 */
	private static InputValidator createPreferenceIdValidator(final Preference preference,
			final JTextComponent component, final ConfigurationHolder configurationHolder) {
		final List<ValidationRule> rules = new ArrayList<ValidationRule>();

		rules.add(new RequiredTextValidationRule(ValidatorResourceType.PREFERENCE_ID_REQUIRED));
		rules.add(new PreferenceIdAlreadyInUseValidationRule(preference, configurationHolder));

		return new TextComponentValidator(component, rules.toArray(new ValidationRule[0]));
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param property the property
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input validator
	 */
	private static InputValidator createPropertyIdValidator(final Property property, final JTextComponent component,
			final ConfigurationHolder configurationHolder) {
		final List<ValidationRule> rules = new ArrayList<ValidationRule>();

		rules.add(new RequiredTextValidationRule(ValidatorResourceType.PROPERTY_ID_REQUIRED));
		rules.add(new PropertyIdAlreadyInUseValidationRule(property, configurationHolder));

		return new TextComponentValidator(component, rules.toArray(new ValidationRule[0]));
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param component the component
	 * @return the input verifier
	 */
	public static InputValidator createPropertyValueValidator(final JTextComponent component) {
		return new TextComponentValidator(component, new RequiredTextValidationRule(
				ValidatorResourceType.PROPERTY_VALUE_REQUIRED));
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input validator
	 */
	private static InputValidator createSubversionRepositoryLocationIdValidator(
			final SubversionRepositoryLocation subversionRepositoryLocation, final JTextComponent component,
			final ConfigurationHolder configurationHolder) {
		final List<ValidationRule> rules = new ArrayList<ValidationRule>();

		rules.add(new RequiredTextValidationRule(ValidatorResourceType.SUBVERSION_REPOSITORY_LOCATION_ID_REQUIRED));
		rules.add(new SubversionRepositoryLocationIdAlreadyInUseValidationRule(subversionRepositoryLocation,
				configurationHolder));

		return new TextComponentValidator(component, rules.toArray(new ValidationRule[0]));
	}

	/**
	 * Creates a new InputValidator object.
	 * 
	 * @param task the task
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 * @return the input validator
	 */
	private static InputValidator createTaskIdValidator(final Task task, final JTextComponent component,
			final ConfigurationHolder configurationHolder) {
		final List<ValidationRule> rules = new ArrayList<ValidationRule>();

		rules.add(new RequiredTextValidationRule(ValidatorResourceType.TASK_ID_REQUIRED));
		rules.add(new TaskIdAlreadyInUseValidationRule(task, configurationHolder));

		return new TextComponentValidator(component, rules.toArray(new ValidationRule[0]));
	}
}
