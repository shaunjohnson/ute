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

import net.lmxm.ute.resources.types.ResourceType;
import net.lmxm.ute.resources.types.ResourceValueType;
import org.apache.commons.lang3.StringUtils;

import java.util.ResourceBundle;

/**
 * The Class ResourcesUtils.
 */
public final class ResourcesUtils {

	/** The Constant ACCELERATOR_SUFFIX. */
	private static final String ACCELERATOR_SUFFIX = "accelerator";

	/** The Constant RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE;

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
		RESOURCE_BUNDLE = ResourceBundle.getBundle("resources");
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
		final String string = StringUtils.trimToNull(RESOURCE_BUNDLE.getString(key));

		return string == null ? null : string.charAt(0);
	}

    /**
     * Gets the resource value of the provided value type.
     *
     * @param resourceType the resource type to load
     * @param resourceValueType the resource value type to load
     * @return the resource value
     */
    public static String getResource(final ResourceType resourceType, ResourceValueType resourceValueType) {
        if (resourceValueType == ResourceValueType.ACCELERATOR) {
            final Character accelerator = getResourceAccelerator(resourceType);
            return accelerator == null ? null : accelerator.toString();
        }
        else if (resourceValueType == ResourceValueType.MESSAGE) {
            return getResourceMessage(resourceType);
        }
        else if (resourceValueType == ResourceValueType.TEXT) {
            return getResourceText(resourceType);
        }
        else if (resourceValueType == ResourceValueType.TITLE) {
            return getResourceTitle(resourceType);
        }
        else if (resourceValueType == ResourceValueType.TOOLTIP_TEXT) {
            return getResourceToolTipText(resourceType);
        }
        else {
            throw new IllegalArgumentException("Unsupported resource value type: " + resourceValueType);
        }
    }

	/**
	 * Gets the resource accelerator.
	 * 
	 * @param resourceType the resource type
	 * @return the resource accelerator
	 */
	public static Character getResourceAccelerator(final ResourceType resourceType) {
		return getResourceCharacter(resourceType, ACCELERATOR_SUFFIX);
	}

	/**
	 * Gets the resource character.
	 * 
	 * @param guiComponentType the gui component type
	 * @param suffix the suffix
	 * @return the resource character
	 */
	private static Character getResourceCharacter(final ResourceType guiComponentType, final String suffix) {
		return getCharacter(buildResourcePrefix(guiComponentType) + suffix);
	}

	/**
	 * Gets the resource message.
	 * 
	 * @param resourceType the resource type
	 * @return the resource message
	 */
	public static String getResourceMessage(final ResourceType resourceType) {
		return getResourceString(resourceType, MESSAGE_SUFFIX);
	}

	/**
	 * Gets the resource string.
	 * 
	 * @param resourceType the resource type
	 * @param suffix the suffix
	 * @return the resource string
	 */
	private static String getResourceString(final ResourceType resourceType, final String suffix) {
		final String key = buildResourcePrefix(resourceType) + suffix;

		return StringUtils.trimToNull(RESOURCE_BUNDLE.getString(key));
	}

	/**
	 * Gets the resource text.
	 * 
	 * @param resourceType the resource type
	 * @return the resource text
	 */
	public static String getResourceText(final ResourceType resourceType) {
		return getResourceString(resourceType, TEXT_SUFFIX);
	}

	/**
	 * Gets the resource title.
	 * 
	 * @param resourceType the resource type
	 * @return the resource title
	 */
	public static String getResourceTitle(final ResourceType resourceType) {
		return getResourceString(resourceType, TITLE_SUFFIX);
	}

	/**
	 * Gets the resource tool tip text.
	 * 
	 * @param resourceType the resource type
	 * @return the resource tool tip text
	 */
	public static String getResourceToolTipText(final ResourceType resourceType) {
		return getResourceString(resourceType, TOOL_TIP_TEXT_SUFFIX);
	}

	/**
	 * Instantiates a new resources utils.
	 */
	private ResourcesUtils() {
		throw new AssertionError();
	}
}
