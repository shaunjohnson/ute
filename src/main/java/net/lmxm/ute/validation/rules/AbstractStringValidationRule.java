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

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Class AbstractStringValidationRule.
 */
public abstract class AbstractStringValidationRule implements ValidationRule {

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.rules.ValidationRule#validate(java.lang.Object)
	 */
	@Override
	public final List<String> validate(final Object value) {
		checkNotNull(value, "Value is null");
		checkArgument(value instanceof String, "Value %s is not a string", value);

		return validateString((String) value);
	}

	/**
	 * Validate string.
	 * 
	 * @param string the string
	 * @return the list
	 */
	protected abstract List<String> validateString(String string);
}
