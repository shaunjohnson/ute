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

import java.util.List;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;


/**
 * The Class AbstractTextComponentValidationRule.
 */
public abstract class AbstractTextComponentValidationRule implements ValidationRule {

	/*
	 */
	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.ValidatorRule#validate(javax.swing.JComponent)
	 */
	@Override
	public final List<String> validate(final JComponent component) {
		if (component instanceof JTextComponent) {
			return validateText(((JTextComponent) component).getText());
		}
		else {
			throw new RuntimeException("The component instance is not a JTextComponent"); // TODO
		}
	}

	/**
	 * Validate text.
	 * 
	 * @param text the text
	 * @return the list
	 */
	public abstract List<String> validateText(String text);
}
