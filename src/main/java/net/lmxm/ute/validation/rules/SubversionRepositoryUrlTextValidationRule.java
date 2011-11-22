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

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;

import org.apache.commons.lang3.StringUtils;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;

/**
 * The Class SubversionRepositoryUrlTextValidationRule.
 */
public final class SubversionRepositoryUrlTextValidationRule extends AbstractStringValidationRule {

	/** The error message. */
	private final String errorMessage;

	/**
	 * Instantiates a new subversion repository url text validation rule.
	 */
	public SubversionRepositoryUrlTextValidationRule() {
		super();

		errorMessage = ResourcesUtils
				.getResourceMessage(ValidatorResourceType.SUBVERSION_REPOSITORY_LOCATION_URL_MALFORMED);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.validation.rules.AbstractStringValidationRule#validateString(java.lang.String)
	 */
	@Override
	protected List<String> validateString(final String string) {
		final List<String> messages = new ArrayList<String>();

		if (StringUtils.isNotBlank(string)) {
			try {
				SVNURL.parseURIDecoded(string);
			}
			catch (final SVNException e) {
				messages.add(errorMessage);
			}
		}

		return messages;
	}
}
