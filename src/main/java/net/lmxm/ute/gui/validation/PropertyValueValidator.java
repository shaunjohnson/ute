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

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;

/**
 */
public final class PropertyValueValidator extends AbstractRequiredTextComponentValidator {

	/**
	 * Instantiates a new property value validator.
	 * 
	 * @param component the component
	 */
	public PropertyValueValidator(final JComponent component) {
		super(component);
	}

	/**
	 * Gets the object required message.
	 * 
	 * @return the object required message
	 */
	@Override
	protected String getObjectRequiredMessage() {
		return ResourcesUtils.getResourceMessage(ValidatorResourceType.PROPERTY_VALUE_REQUIRED);
	}
}
