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
package net.lmxm.ute.resources;

import javax.swing.Icon;

import net.lmxm.ute.gui.ActionConstants;

/**
 * The Enum MenuItemResourceType.
 */
public enum MenuItemResourceType implements ResourceType {

	ABOUT(ImageUtil.ABOUT_ICON, ActionConstants.ABOUT),

	ADD_FILE_SYSTEM_DELETE_TASK(ImageUtil.ADD_FILE_SYSTEM_DELETE_TASK_ICON, ActionConstants.ADD_FILE_SYSTEM_DELETE_TASK),

	ADD_FILE_SYSTEM_LOCATION(ImageUtil.ADD_FILE_SYSTEM_LOCATION_ICON, ActionConstants.ADD_FILE_SYSTEM_LOCATION),

	ADD_FIND_REPLACE_TASK(ImageUtil.ADD_FIND_REPLACE_TASK_ICON, ActionConstants.ADD_FIND_REPLACE_TASK),

	ADD_GROOVY_TASK(ImageUtil.ADD_GROOVY_TASK_ICON, ActionConstants.ADD_GROOVY_TASK),

	ADD_HTTP_DOWNLOAD_TASK(ImageUtil.ADD_HTTP_DOWNLOAD_TASK_ICON, ActionConstants.ADD_HTTP_DOWNLOAD_TASK),

	ADD_HTTP_LOCATION(ImageUtil.ADD_HTTP_LOCATION_ICON, ActionConstants.ADD_HTTP_LOCATION),

	ADD_JOB(ImageUtil.ADD_JOB_ICON, ActionConstants.ADD_JOB),

	ADD_PREFERENCE(ImageUtil.ADD_PREFERENCE_ICON, ActionConstants.ADD_PREFERENCE),

	ADD_PROPERTY(ImageUtil.ADD_PROPERTY_ICON, ActionConstants.ADD_PROPERTY),

	ADD_SUBVERSION_EXPORT_TASK(ImageUtil.ADD_SUBVERSION_EXPORT_TASK_ICON, ActionConstants.ADD_SUBVERSION_EXPORT_TASK),

	ADD_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.ADD_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionConstants.ADD_SUBVERSION_REPOSITORY_LOCATION),

	ADD_SUBVERSION_UPDATE_TASK(ImageUtil.ADD_SUBVERSION_UPDATE_TASK_ICON, ActionConstants.ADD_SUBVERSION_UPDATE_TASK),

	ADD_TASK(null, null),

	CLONE_FILE_SYSTEM_LOCATION(null, ActionConstants.CLONE_FILE_SYSTEM_LOCATION),

	CLONE_HTTP_LOCATION(null, ActionConstants.CLONE_HTTP_LOCATION),

	CLONE_JOB(null, ActionConstants.CLONE_JOB),

	CLONE_PREFERENCE(null, ActionConstants.CLONE_PREFERENCE),

	CLONE_PROPERTY(null, ActionConstants.CLONE_PROPERTY),

	CLONE_SUBVERSION_REPOSITORY_LOCATION(null, ActionConstants.CLONE_SUBVERSION_REPOSITORY_LOCATION),

	CLONE_TASK(null, ActionConstants.CLONE_TASK),

	CLOSE_ALL_TABS(null, ActionConstants.CLOSE_ALL_TABS),

	DELETE_FILE_SYSTEM_LOCATION(ImageUtil.DELETE_FILE_SYSTEM_LOCATION_ICON, ActionConstants.DELETE_FILE_SYSTEM_LOCATION),

	DELETE_HTTP_LOCATION(ImageUtil.DELETE_HTTP_LOCATION_ICON, ActionConstants.DELETE_HTTP_LOCATION),

	DELETE_JOB(ImageUtil.DELETE_JOB_ICON, ActionConstants.DELETE_JOB),

	DELETE_PREFERENCE(ImageUtil.DELETE_PREFERENCE_ICON, ActionConstants.DELETE_PREFERENCE),

	DELETE_PROPERTY(ImageUtil.DELETE_PROPERTY_ICON, ActionConstants.DELETE_PROPERTY),

	DELETE_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.DELETE_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionConstants.DELETE_SUBVERSION_REPOSITORY_LOCATION),

	DELETE_TASK(null, ActionConstants.DELETE_TASK),

	EDIT_PREFERENCES(ImageUtil.EDIT_PREFERENCES_ICON, ActionConstants.EDIT_PREFERENCES),

	EXECUTE_JOB(ImageUtil.EXECUTE_ICON, ActionConstants.EXECUTE),

	EXECUTE_TASK(ImageUtil.EXECUTE_ICON, ActionConstants.EXECUTE),

	EXIT(ImageUtil.EXIT_ICON, ActionConstants.EXIT),

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
	 * Instantiates a new gui component menu item.
	 * 
	 * @param icon the icon
	 * @param actionCommand the action command
	 */
	MenuItemResourceType(final Icon icon, final String actionCommand) {
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getActionCommand()
	 */
	@Override
	public String getActionCommand() {
		return actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return icon;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getGuiComponentCategory()
	 */
	@Override
	public ResourceCategory getResourceCategory() {
		return ResourceCategory.MENU_ITEM;
	}
}
