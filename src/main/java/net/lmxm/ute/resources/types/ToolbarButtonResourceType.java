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
 * The Enum ToolbarButtonResourceType.
 */
public enum ToolbarButtonResourceType implements ResourceType {

	ADD_FILE_SYSTEM_LOCATION(ImageUtil.ADD_FILE_SYSTEM_LOCATION_ICON, ActionCommand.ADD_FILE_SYSTEM_LOCATION),

	ADD_HTTP_LOCATION(ImageUtil.ADD_HTTP_LOCATION_ICON, ActionCommand.ADD_HTTP_LOCATION),

	ADD_JOB(ImageUtil.ADD_JOB_ICON, ActionCommand.ADD_JOB),

	ADD_PREFERENCE(ImageUtil.ADD_PREFERENCE_ICON, ActionCommand.ADD_PREFERENCE),

	ADD_PROPERTY(ImageUtil.ADD_PROPERTY_ICON, ActionCommand.ADD_PROPERTY),

	ADD_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.ADD_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionCommand.ADD_SUBVERSION_REPOSITORY_LOCATION),

	ADD_TASK(null, ActionCommand.ADD_TASK),

	COLLAPSE(ImageUtil.COLLAPSE_ICON, ActionCommand.COLLAPSE),

	DELETE_FILE_SYSTEM_LOCATION(ImageUtil.DELETE_FILE_SYSTEM_LOCATION_ICON, ActionCommand.DELETE_FILE_SYSTEM_LOCATION),

	DELETE_HTTP_LOCATION(ImageUtil.DELETE_HTTP_LOCATION_ICON, ActionCommand.DELETE_HTTP_LOCATION),

	DELETE_JOB(ImageUtil.DELETE_JOB_ICON, ActionCommand.DELETE_JOB),

	DELETE_PREFERENCE(ImageUtil.DELETE_PREFERENCE_ICON, ActionCommand.DELETE_PREFERENCE),

	DELETE_PROPERTY(ImageUtil.DELETE_PROPERTY_ICON, ActionCommand.DELETE_PROPERTY),

	DELETE_SUBVERSION_REPOSITORY_LOCATION(ImageUtil.DELETE_SUBVERSION_REPOSITORY_LOCATION_ICON,
			ActionCommand.DELETE_SUBVERSION_REPOSITORY_LOCATION),

	DELETE_TASK(null, ActionCommand.DELETE_TASK),

	EXECUTE_JOB(ImageUtil.EXECUTE_ICON, ActionCommand.EXECUTE),

	EXECUTE_TASK(ImageUtil.EXECUTE_ICON, ActionCommand.EXECUTE),

	EXIT(ImageUtil.EXIT_ICON, ActionCommand.EXIT),

	EXPAND(ImageUtil.EXPAND_ICON, ActionCommand.EXPAND),

	NEW_FILE(ImageUtil.NEW_FILE_ICON, ActionCommand.NEW_FILE),

	OPEN_FILE(ImageUtil.OPEN_FILE_ICON, ActionCommand.OPEN_FILE),

	RELOAD_FILE(ImageUtil.RELOAD_FILE_ICON, ActionCommand.RELOAD_FILE),

	SAVE_FILE(ImageUtil.SAVE_FILE_ICON, ActionCommand.SAVE_FILE),

	SAVE_FILE_AS(ImageUtil.SAVE_FILE_AS_ICON, ActionCommand.SAVE_FILE_AS);

	/** The action command. */
	private final ActionCommand actionCommand;

	/** The icon. */
	private final Icon icon;

	/**
	 * Instantiates a new gui component button.
	 * 
	 * @param icon the icon
	 * @param actionCommand the action command
	 */
	ToolbarButtonResourceType(final Icon icon, final ActionCommand actionCommand) {
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getActionCommand()
	 */
	@Override
	public ActionCommand getActionCommand() {
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
