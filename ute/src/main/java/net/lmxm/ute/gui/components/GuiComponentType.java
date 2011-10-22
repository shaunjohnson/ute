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

import javax.swing.Icon;

import net.lmxm.ute.gui.ActionConstants;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Enum GuiComponentType.
 */
public enum GuiComponentType {

	/** The EXIT_BUTTON. */
	EXIT_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.EXIT_ICON, ActionConstants.EXIT),

	/** The NEW_FILE_BUTTON. */
	NEW_FILE_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.NEW_FILE_ICON, ActionConstants.NEW_FILE),

	/** The OPEN_FILE_BUTTON. */
	OPEN_FILE_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.OPEN_FILE_ICON, ActionConstants.OPEN_FILE);

	/**
	 * The Enum GuiComponentCategory.
	 */
	public enum GuiComponentCategory {
		/** The BUTTON. */
		BUTTON
	}

	/** The action command. */
	private final String actionCommand;

	/** The gui component category. */
	private final GuiComponentCategory guiComponentCategory;

	/** The icon. */
	private final Icon icon;

	/**
	 * Instantiates a new gui component type.
	 * 
	 * @param guiComponentCategory the gui component category
	 * @param icon the icon
	 * @param actionCommand the action command
	 */
	GuiComponentType(final GuiComponentCategory guiComponentCategory, final Icon icon, final String actionCommand) {
		this.guiComponentCategory = guiComponentCategory;
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	/**
	 * Gets the action command.
	 * 
	 * @return the action command
	 */
	public String getActionCommand() {
		return actionCommand;
	}

	/**
	 * Gets the gui component category.
	 * 
	 * @return the gui component category
	 */
	public GuiComponentCategory getGuiComponentCategory() {
		return guiComponentCategory;
	}

	/**
	 * Gets the icon.
	 * 
	 * @return the icon
	 */
	public Icon getIcon() {
		return icon;
	}
}
