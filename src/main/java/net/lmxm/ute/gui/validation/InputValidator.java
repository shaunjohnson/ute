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

import net.lmxm.ute.validation.rules.ValidationRule;

import javax.swing.*;

/**
 * The Class InputValidator.
 */
public abstract class InputValidator extends InputVerifier {

	/**
	 * Adds the rule.
	 * 
	 * @param validationRule the validation rule
	 */
	public abstract void addRule(final ValidationRule validationRule);

	/**
	 * Clear.
	 */
	public abstract void clear();
}
