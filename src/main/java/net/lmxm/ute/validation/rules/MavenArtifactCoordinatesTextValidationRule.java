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

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ValidatorResourceType;
import org.apache.commons.lang3.StringUtils;
import org.sonatype.aether.util.artifact.DefaultArtifact;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class MavenArtifactCoordinatesTextValidationRule.
 */
public final class MavenArtifactCoordinatesTextValidationRule extends AbstractStringValidationRule {

	/** The error message. */
	private final String errorMessage;

	/**
	 * Instantiates a new Maven artifact coordinates text validation rule.
	 */
	public MavenArtifactCoordinatesTextValidationRule() {
		super();

		errorMessage = ResourcesUtils.getResourceMessage(ValidatorResourceType.MAVEN_ARTIFACT_COORDINATES_MALFORMED);
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
				new DefaultArtifact(string);
			}
			catch (final IllegalArgumentException e) {
				messages.add(errorMessage);
			}
		}

		return messages;
	}
}
