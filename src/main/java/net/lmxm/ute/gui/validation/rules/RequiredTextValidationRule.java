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

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class RequiredTextValidationRule.
 */
public final class RequiredTextValidationRule extends AbstractStringValidationRule {

	/** The error message. */
	private final String errorMessage;

	/**
	 * Instantiates a new required text validation rule.
	 * 
	 * @param validatorResourceType the validator resource type
	 */
	public RequiredTextValidationRule(final ValidatorResourceType validatorResourceType) {
		super();

		errorMessage = ResourcesUtils.getResourceMessage(validatorResourceType);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.rules.AbstractStringValidationRule#validateString(java.lang.String)
	 */
	@Override
	public List<String> validateString(final String string) {
		final List<String> messages = new ArrayList<String>();

		if (StringUtils.isBlank(string)) {
			messages.add(errorMessage);
		}

		return messages;
	}
}
