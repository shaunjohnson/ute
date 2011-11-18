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

import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.resources.ImageUtil;
import net.lmxm.ute.resources.ResourceCategory;

/**
 * The Enum MenuItemResourceType.
 */
public enum MenuItemResourceType implements ResourceType {

	ABOUT(ImageUtil.ABOUT_ICON, ActionCommand.ABOUT),

	ADD_FILE_SYSTEM_DELETE_TASK(ImageUtil.ADD_FILE_SYSTEM_DELETE_TASK_ICON, ActionCommand.ADD_FILE_SYSTEM_DELETE_TASK),

	ADD_FILE_SYSTEM_LOCATION(ImageUtil.ADD_FILE_SYSTEM_LOCATION_ICON, ActionCommand.ADD_FILE_SYSTEM_LOCATION),

	ADD_FIND_REPLACE_TASK(ImageUtil.ADD_FIND_REPLACE_TASK_ICON, ActionCommand.ADD_FIND_REPLACE_TASK),

	ADD_GROOVY_TASK(ImageUtil.ADD_GROOVY_TASK_ICON, ActionCommand.ADD_GROOVY_TASK),

	ADD_HTTP_DOWNLOAD_TASK(ImageUtil.ADD_HTTP_DOWNLOAD_TASK_ICON, ActionCommand.ADD_HTTP_DOWNLOAD_TASK),

	ADD_HTTP_LOCATION(ImageUtil.ADD_HTTP_LOCATION_ICON, ActionCommand.ADD_HTTP_LOCATION),

	ADD_JOB(ImageUtil.ADD_JOB_ICON, ActionCommand.ADD_JOB),

	ADD_PREFERENCE(ImageUtil.ADD_PREFERENCE_ICON, ActionCommand.ADD_PREFERENCE),

	ADD_PROPERTY(ImageUtil.ADD_PROPERTY_ICON, ActionCommand.ADD_PROPERTY),

	ADD_SUBVERSION_EXPORT_TASK(ImageUtil.ADD_SUBVERSION_EXPORT_TASK_ICON, ActionCommand.ADD_SUBVERSION_EXPORT_TASK),

	ADD_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.ADD_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionCommand.ADD_SUBVERSION_REPOSITORY_LOCATION),

	ADD_SUBVERSION_UPDATE_TASK(ImageUtil.ADD_SUBVERSION_UPDATE_TASK_ICON, ActionCommand.ADD_SUBVERSION_UPDATE_TASK),

	ADD_TASK(null, null),

	CLONE_FILE_SYSTEM_LOCATION(null, ActionCommand.CLONE_FILE_SYSTEM_LOCATION),

	CLONE_HTTP_LOCATION(null, ActionCommand.CLONE_HTTP_LOCATION),

	CLONE_JOB(null, ActionCommand.CLONE_JOB),

	CLONE_PREFERENCE(null, ActionCommand.CLONE_PREFERENCE),

	CLONE_PROPERTY(null, ActionCommand.CLONE_PROPERTY),

	CLONE_SUBVERSION_REPOSITORY_LOCATION(null, ActionCommand.CLONE_SUBVERSION_REPOSITORY_LOCATION),

	CLONE_TASK(null, ActionCommand.CLONE_TASK),

	CLOSE_ALL_TABS(null, ActionCommand.CLOSE_ALL_TABS),

	DELETE_FILE_SYSTEM_LOCATION(ImageUtil.DELETE_FILE_SYSTEM_LOCATION_ICON, ActionCommand.DELETE_FILE_SYSTEM_LOCATION),

	DELETE_HTTP_LOCATION(ImageUtil.DELETE_HTTP_LOCATION_ICON, ActionCommand.DELETE_HTTP_LOCATION),

	DELETE_JOB(ImageUtil.DELETE_JOB_ICON, ActionCommand.DELETE_JOB),

	DELETE_PREFERENCE(ImageUtil.DELETE_PREFERENCE_ICON, ActionCommand.DELETE_PREFERENCE),

	DELETE_PROPERTY(ImageUtil.DELETE_PROPERTY_ICON, ActionCommand.DELETE_PROPERTY),

	DELETE_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.DELETE_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionCommand.DELETE_SUBVERSION_REPOSITORY_LOCATION),

	DELETE_TASK(null, ActionCommand.DELETE_TASK),

	EDIT_PREFERENCES(ImageUtil.EDIT_PREFERENCES_ICON, ActionCommand.EDIT_PREFERENCES),

	EXECUTE_JOB(ImageUtil.EXECUTE_ICON, ActionCommand.EXECUTE),

	EXECUTE_TASK(ImageUtil.EXECUTE_ICON, ActionCommand.EXECUTE),

	EXIT(ImageUtil.EXIT_ICON, ActionCommand.EXIT),

	NEW_FILE(ImageUtil.NEW_FILE_ICON, ActionCommand.NEW_FILE),

	OPEN_FILE(ImageUtil.OPEN_FILE_ICON, ActionCommand.OPEN_FILE),

	RELOAD_FILE(ImageUtil.RELOAD_FILE_ICON, ActionCommand.RELOAD_FILE),

	SAVE_FILE(ImageUtil.SAVE_FILE_ICON, ActionCommand.SAVE_FILE),

	SAVE_FILE_AS(ImageUtil.SAVE_FILE_AS_ICON, ActionCommand.SAVE_FILE_AS);

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
