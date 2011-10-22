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

	/** The ADD_JOB_BUTTON. */
	ADD_JOB_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.ADD_JOB_ICON, ActionConstants.ADD_JOB),
	
	/** The ADD_LOCATION_BUTTON. */
	ADD_LOCATION_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.ADD_LOCATION_ICON, ActionConstants.ADD_LOCATION),
	
	/** The ADD_PREFERENCE_BUTTON. */
	ADD_PREFERENCE_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.ADD_PREFERENCE_ICON, ActionConstants.ADD_PREFERENCE),
	
	/** The ADD_PROPERTY_BUTTON. */
	ADD_PROPERTY_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.ADD_PROPERTY_ICON, ActionConstants.ADD_PROPERTY),
	
	/** The EXECUTE_JOB_BUTTON. */
	EXECUTE_JOB_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.EXECUTE_ICON, ActionConstants.EXECUTE),
	
	/** The EXIT_BUTTON. */
	EXIT_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.EXIT_ICON, ActionConstants.EXIT),
	
	/** The CLOSE_DIALOG_BUTTON button. */
	CLOSE_DIALOG_BUTTON(GuiComponentCategory.BUTTON, null, null),

	/** The NEW_FILE_BUTTON. */
	NEW_FILE_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.NEW_FILE_ICON, ActionConstants.NEW_FILE),

	/** The OPEN_FILE_BUTTON. */
	OPEN_FILE_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.OPEN_FILE_ICON, ActionConstants.OPEN_FILE),
	
	/** The SAVE_FILE_AS_BUTTON. */
	SAVE_FILE_AS_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.SAVE_FILE_AS_ICON, ActionConstants.SAVE_FILE_AS),
	
	/** The SAVE_FILE_BUTTON. */
	SAVE_FILE_BUTTON(GuiComponentCategory.BUTTON, ImageUtil.SAVE_FILE_ICON, ActionConstants.SAVE_FILE);

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
