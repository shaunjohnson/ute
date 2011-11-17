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
package net.lmxm.ute.resources.types;

import javax.swing.Icon;

import net.lmxm.ute.gui.ActionConstants;
import net.lmxm.ute.resources.ImageUtil;
import net.lmxm.ute.resources.ResourceCategory;

/**
 * The Enum ToolbarButtonResourceType.
 */
public enum ToolbarButtonResourceType implements ResourceType {

	ADD_FILE_SYSTEM_LOCATION(ImageUtil.ADD_FILE_SYSTEM_LOCATION_ICON, ActionConstants.ADD_FILE_SYSTEM_LOCATION),

	ADD_HTTP_LOCATION(ImageUtil.ADD_HTTP_LOCATION_ICON, ActionConstants.ADD_HTTP_LOCATION),

	ADD_JOB(ImageUtil.ADD_JOB_ICON, ActionConstants.ADD_JOB),

	ADD_PREFERENCE(ImageUtil.ADD_PREFERENCE_ICON, ActionConstants.ADD_PREFERENCE),

	ADD_PROPERTY(ImageUtil.ADD_PROPERTY_ICON, ActionConstants.ADD_PROPERTY),

	ADD_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.ADD_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionConstants.ADD_SUBVERSION_REPOSITORY_LOCATION),

	ADD_TASK(null, null),

	COLLAPSE(ImageUtil.COLLAPSE_ICON, ActionConstants.COLLAPSE),

	DELETE_FILE_SYSTEM_LOCATION(ImageUtil.DELETE_FILE_SYSTEM_LOCATION_ICON, ActionConstants.DELETE_FILE_SYSTEM_LOCATION),

	DELETE_HTTP_LOCATION(ImageUtil.DELETE_HTTP_LOCATION_ICON, ActionConstants.DELETE_HTTP_LOCATION),

	DELETE_JOB(ImageUtil.DELETE_JOB_ICON, ActionConstants.DELETE_JOB),

	DELETE_PREFERENCE(ImageUtil.DELETE_PREFERENCE_ICON, ActionConstants.DELETE_PREFERENCE),

	DELETE_PROPERTY(ImageUtil.DELETE_PROPERTY_ICON, ActionConstants.DELETE_PROPERTY),

	DELETE_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.DELETE_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionConstants.DELETE_SUBVERSION_REPOSITORY_LOCATION),

	DELETE_TASK(null, ActionConstants.DELETE_TASK),

	EXECUTE_JOB(ImageUtil.EXECUTE_ICON, ActionConstants.EXECUTE),

	EXECUTE_TASK(ImageUtil.EXECUTE_ICON, ActionConstants.EXECUTE),

	EXIT(ImageUtil.EXIT_ICON, ActionConstants.EXIT),

	EXPAND(ImageUtil.EXPAND_ICON, ActionConstants.EXPAND),

	NEW_FILE(ImageUtil.NEW_FILE_ICON, ActionConstants.NEW_FILE),

	OPEN_FILE(ImageUtil.OPEN_FILE_ICON, ActionConstants.OPEN_FILE),

	RELOAD_FILE(ImageUtil.RELOAD_FILE_ICON, ActionConstants.RELOAD_FILE),

	SAVE_FILE(ImageUtil.SAVE_FILE_ICON, ActionConstants.SAVE_FILE),

	SAVE_FILE_AS(ImageUtil.SAVE_FILE_AS_ICON, ActionConstants.SAVE_FILE_AS);

	/** The action command. */
	private final String actionCommand;

	/** The icon. */
	private final Icon icon;

	/**
	 * Instantiates a new gui component button.
	 * 
	 * @param icon the icon
	 * @param actionCommand the action command
	 */
	ToolbarButtonResourceType(final Icon icon, final String actionCommand) {
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getActionCommand()
	 */
	@Override
	public String getActionCommand() {
		return actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return icon;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getGuiComponentCategory()
	 */
	@Override
	public ResourceCategory getResourceCategory() {
		return ResourceCategory.TOOLBAR_BUTTON;
	}
}
