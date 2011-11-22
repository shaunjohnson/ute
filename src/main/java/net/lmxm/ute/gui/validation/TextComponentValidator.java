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

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import net.lmxm.ute.validation.rules.ValidationRule;

/**
 * The Class TextComponentValidator.
 */
public class TextComponentValidator extends AbstractInputValidator {
	/** The Constant TEXT_FIELD_BACKGROUND. */
	private static final Color TEXT_FIELD_BACKGROUND = (Color) UIManager.get("TextField.background");

	/**
	 * Instantiates a new text component validator.
	 * 
	 * @param textComponent the text component
	 */
	public TextComponentValidator(final JTextComponent textComponent) {
		super(textComponent);
	}

	/**
	 * Instantiates a new text component validator.
	 * 
	 * @param textComponent the input component
	 * @param validationRules the validation rules
	 */
	public TextComponentValidator(final JTextComponent textComponent, final ValidationRule... validationRules) {
		super(textComponent, validationRules);
	}

	@Override
	protected Object getCurrentValue(final JComponent component) {
		if (component instanceof JTextComponent) {
			return ((JTextComponent) component).getText();
		}
		else {
			throw new RuntimeException("The component instance is not a JTextComponent"); // TODO
		}
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.AbstractInputValidator#getDefaultBackgroundColor()
	 */
	@Override
	protected Color getDefaultBackgroundColor() {
		return TEXT_FIELD_BACKGROUND;
	}
}
