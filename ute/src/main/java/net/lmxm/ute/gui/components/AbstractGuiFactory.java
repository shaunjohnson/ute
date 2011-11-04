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
package net.lmxm.ute.gui.components;

import net.lmxm.ute.resources.ResourcesUtils;

/**
 * A factory for creating AbstractGui objects.
 */
public abstract class AbstractGuiFactory {
	/** The Constant ACCELERATOR_SUFFIX. */
	private static final String ACCELERATOR_SUFFIX = "accelerator";

	/** The Constant MESSAGE_SUFFIX. */
	private static final String MESSAGE_SUFFIX = "message";

	/** The Constant TEXT_SUFFIX. */
	private static final String TEXT_SUFFIX = "text";

	/** The Constant TITLE_SUFFIX. */
	private static final String TITLE_SUFFIX = "title";

	/** The Constant TOOL_TIP_TEXT_SUFFIX. */
	private static final String TOOL_TIP_TEXT_SUFFIX = "toolTipText";

	/**
	 * Builds the resource prefix.
	 * 
	 * @param guiComponentType the gui component type
	 * @return the string
	 */
	private static String buildResourcePrefix(final GuiComponentType guiComponentType) {
		final StringBuilder builder = new StringBuilder();
		builder.append(guiComponentType.getGuiComponentCategory().name());
		builder.append(".");
		builder.append(guiComponentType.name());
		builder.append(".");

		return builder.toString();
	}

	/**
	 * Gets the accelerator.
	 * 
	 * @param guiComponentType the gui component type
	 * @return the accelerator
	 */
	protected static final Character getAccelerator(final GuiComponentType guiComponentType) {
		return getResourceCharacter(guiComponentType, ACCELERATOR_SUFFIX);
	}

	/**
	 * Gets the message.
	 * 
	 * @param guiComponentType the gui component type
	 * @return the message
	 */
	protected static final String getMessage(final GuiComponentType guiComponentType) {
		return getResourceString(guiComponentType, MESSAGE_SUFFIX);
	}

	/**
	 * Gets the resource character.
	 * 
	 * @param guiComponentType the gui component type
	 * @param suffix the suffix
	 * @return the resource character
	 */
	private static final Character getResourceCharacter(final GuiComponentType guiComponentType, final String suffix) {
		final String resourcePrefix = buildResourcePrefix(guiComponentType);

		return ResourcesUtils.getCharacter(resourcePrefix + suffix);
	}

	/**
	 * Gets the resource string.
	 * 
	 * @param guiComponentType the gui component type
	 * @param suffix the suffix
	 * @return the resource string
	 */
	private static final String getResourceString(final GuiComponentType guiComponentType, final String suffix) {
		final String resourcePrefix = buildResourcePrefix(guiComponentType);

		return ResourcesUtils.getString(resourcePrefix + suffix);
	}

	/**
	 * Gets the text.
	 * 
	 * @param guiComponentType the gui component type
	 * @return the text
	 */
	protected static final String getText(final GuiComponentType guiComponentType) {
		return getResourceString(guiComponentType, TEXT_SUFFIX);
	}

	/**
	 * Gets the title.
	 * 
	 * @param guiComponentType the gui component type
	 * @return the title
	 */
	protected static final String getTitle(final GuiComponentType guiComponentType) {
		return getResourceString(guiComponentType, TITLE_SUFFIX);
	}

	/**
	 * Gets the tool tip text.
	 * 
	 * @param guiComponentType the gui component type
	 * @return the tool tip text
	 */
	protected static final String getToolTipText(final GuiComponentType guiComponentType) {
		return getResourceString(guiComponentType, TOOL_TIP_TEXT_SUFFIX);
	}
}
