/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executer.
 * 
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.resources;

import java.util.ResourceBundle;

import net.lmxm.ute.resources.types.ResourceType;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ResourcesUtils.
 */
public final class ResourcesUtils {

	/** The Constant ACCELERATOR_SUFFIX. */
	private static final String ACCELERATOR_SUFFIX = "accelerator";

	/** The Constant bundle. */
	private static final ResourceBundle bundle;

	/** The Constant MESSAGE_SUFFIX. */
	private static final String MESSAGE_SUFFIX = "message";

	/** The Constant TEXT_SUFFIX. */
	private static final String TEXT_SUFFIX = "text";

	/** The Constant TITLE_SUFFIX. */
	private static final String TITLE_SUFFIX = "title";

	/** The Constant TOOL_TIP_TEXT_SUFFIX. */
	private static final String TOOL_TIP_TEXT_SUFFIX = "toolTipText";

	/**
	 * Instantiates a new resources utils.
	 */
	static {
		bundle = ResourceBundle.getBundle("resources");
	}

	/**
	 * Builds the resource prefix.
	 * 
	 * @param resourceType the resource type
	 * @return the string
	 */
	private static String buildResourcePrefix(final ResourceType resourceType) {
		final StringBuilder builder = new StringBuilder();
		builder.append(resourceType.getResourceCategory().name());
		builder.append(".");
		builder.append(resourceType.name());
		builder.append(".");

		return builder.toString();
	}

	/**
	 * Gets the character.
	 * 
	 * @param key the key
	 * @return the character
	 */
	public static Character getCharacter(final String key) {
		final String string = StringUtils.trimToNull(bundle.getString(key));

		return string == null ? null : string.charAt(0);
	}

	/**
	 * Gets the resource accelerator.
	 * 
	 * @param resourceType the resource type
	 * @return the resource accelerator
	 */
	public static final Character getResourceAccelerator(final ResourceType resourceType) {
		return getResourceCharacter(resourceType, ACCELERATOR_SUFFIX);
	}

	/**
	 * Gets the resource character.
	 * 
	 * @param guiComponentType the gui component type
	 * @param suffix the suffix
	 * @return the resource character
	 */
	private static final Character getResourceCharacter(final ResourceType guiComponentType, final String suffix) {
		return getCharacter(buildResourcePrefix(guiComponentType) + suffix);
	}

	/**
	 * Gets the resource message.
	 * 
	 * @param resourceType the resource type
	 * @return the resource message
	 */
	public static final String getResourceMessage(final ResourceType resourceType) {
		return getResourceString(resourceType, MESSAGE_SUFFIX);
	}

	/**
	 * Gets the resource string.
	 * 
	 * @param resourceType the resource type
	 * @param suffix the suffix
	 * @return the resource string
	 */
	private static final String getResourceString(final ResourceType resourceType, final String suffix) {
		final String key = buildResourcePrefix(resourceType) + suffix;

		return StringUtils.trimToNull(bundle.getString(key));
	}

	/**
	 * Gets the resource text.
	 * 
	 * @param resourceType the resource type
	 * @return the resource text
	 */
	public static final String getResourceText(final ResourceType resourceType) {
		return getResourceString(resourceType, TEXT_SUFFIX);
	}

	/**
	 * Gets the resource title.
	 * 
	 * @param resourceType the resource type
	 * @return the resource title
	 */
	public static final String getResourceTitle(final ResourceType resourceType) {
		return getResourceString(resourceType, TITLE_SUFFIX);
	}

	/**
	 * Gets the resource tool tip text.
	 * 
	 * @param resourceType the resource type
	 * @return the resource tool tip text
	 */
	public static final String getResourceToolTipText(final ResourceType resourceType) {
		return getResourceString(resourceType, TOOL_TIP_TEXT_SUFFIX);
	}

	/**
	 * Instantiates a new resources utils.
	 */
	private ResourcesUtils() {
		throw new AssertionError();
	}
}
