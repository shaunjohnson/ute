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

/**
 * The Class AbstractRequiredTextComponentValidator.
 */
public abstract class AbstractRequiredTextComponentValidator extends AbstractTextFieldValidator {

	/**
	 * Instantiates a new abstract required text component validator.
	 * 
	 * @param inputComponent the input component
	 */
	public AbstractRequiredTextComponentValidator(final JComponent inputComponent) {
		super(inputComponent);
	}

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
	// @Override
	// protected final List<String> validate(final JComponent component) {
	// final List<String> messages = new ArrayList<String>();
	//
	// if (component instanceof JTextField) {
	// final String value = ((JTextField) component).getText();
	//
	// if (StringUtils.isBlank(value)) {
	// messages.add(getObjectRequiredMessage());
	// }
	// }
	// else {
	// messages.add("Error occurred validating input"); // TODO
	// }
	//
	// return messages;
	// }
}
