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

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.configuration.ConfigurationHolder;

import org.codehaus.plexus.util.StringUtils;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractIdValidator.
 */
public abstract class AbstractIdValidator extends AbstractInputValidator {

	/**
	 * Adds the input validator.
	 * 
	 * @param identifiableBean the identifiable bean
	 * @param component the component
	 * @param configurationHolder the configuration holder
	 */
	public static final void addInputValidator(final IdentifiableBean identifiableBean, final JComponent component,
			final ConfigurationHolder configurationHolder) {
		Preconditions.checkNotNull(identifiableBean, "IdentifiableBean is null");
		Preconditions.checkNotNull(component, "Component is null");
		Preconditions.checkNotNull(configurationHolder, "Configuration holder is null");

		final InputVerifier inputVerifier;
		if (identifiableBean instanceof FileSystemLocation) {
			inputVerifier = new FileSystemLocationIdValidator((FileSystemLocation) identifiableBean, component,
					configurationHolder);
		}
		else if (identifiableBean instanceof HttpLocation) {
			inputVerifier = new HttpLocationIdValidator((HttpLocation) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof Job) {
			inputVerifier = new JobIdValidator((Job) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof Preference) {
			inputVerifier = new PreferenceIdValidator((Preference) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof Property) {
			inputVerifier = new PropertyIdValidator((Property) identifiableBean, component, configurationHolder);
		}
		else if (identifiableBean instanceof SubversionRepositoryLocation) {
			inputVerifier = new SubversionRepositoryLocationIdValidator(
					(SubversionRepositoryLocation) identifiableBean, component, configurationHolder);
		}
		else {
			throw new RuntimeException("Unsupported identifiable bean"); // TODO
		}

		component.setInputVerifier(inputVerifier);
	}

	/** The object. */
	private final IdentifiableBean object;

	/**
	 * Instantiates a new abstract id validator.
	 * 
	 * @param object the object
	 * @param component the component
	 */
	public AbstractIdValidator(final IdentifiableBean object, final JComponent component) {
		super(component);

		this.object = object;
	}

	/**
	 * Gets the existing object.
	 * 
	 * @param id the id
	 * @return the existing object
	 */
	protected abstract IdentifiableBean getExistingObject(String id);

	/**
	 * Gets the object in use message.
	 * 
	 * @return the object in use message
	 */
	protected abstract String getObjectInUseMessage();

	/**
	 * Gets the object required message.
	 * 
	 * @return the object required message
	 */
	protected abstract String getObjectRequiredMessage();

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.AbstractInputValidator#validate(javax.swing.JComponent)
	 */
	@Override
	protected final List<String> validate(final JComponent component) {
		final List<String> messages = new ArrayList<String>();

		if (component instanceof JTextField) {
			final String id = ((JTextField) component).getText();

			if (StringUtils.isBlank(id)) {
				messages.add(getObjectRequiredMessage());
			}
			else {
				final IdentifiableBean existingObject = getExistingObject(id);

				if (existingObject != null && object != existingObject) {
					messages.add(getObjectInUseMessage());
				}
			}
		}
		else {
			messages.add("Error occurred validating input"); // TODO
		}

		return messages;
	}
}
