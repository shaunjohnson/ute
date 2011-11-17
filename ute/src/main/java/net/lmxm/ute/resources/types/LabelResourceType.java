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

import net.lmxm.ute.resources.ResourceCategory;

/**
 * The Enum LabelResourceType.
 */
public enum LabelResourceType implements ResourceType {

	CURRENT_VALUE,

	DESCRIPTION,

	ENABLED,

	FILE_SYSTEM_DELETE_TASK,

	FILE_SYSTEM_LOCATION,

	FILE_SYSTEM_LOCATIONS,

	FILES,

	FIND_AND_REPLACE,

	FIND_AND_REPLACE_TASK,

	GROOVY_SCRIPT,

	GROOVY_TASK,

	HTTP_DOWNLOAD_TASK,

	HTTP_LOCATION,

	HTTP_LOCATIONS,

	ID,

	JOB,

	JOBS,

	LOCATION,

	PASSWORD,

	PATH,

	PATTERNS,

	PREFERENCE,

	PREFERENCES,

	PROPERTIES,

	PROPERTY,

	SCOPE,

	SCRIPT,

	SEQUENTIAL_JOB,

	SERVER,

	SOURCE,

	STOP_ON_ERROR,

	SUBVERSION_EXPORT_TASK,

	SUBVERSION_REPOSITORY_LOCATION,

	SUBVERSION_REPOSITORY_LOCATIONS,

	SUBVERSION_UPDATE_TASK,

	TARGET,

	TASK,

	URL,

	USERNAME,

	VALUE;

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getActionCommand()
	 */
	@Override
	public String getActionCommand() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getGuiComponentCategory()
	 */
	@Override
	public ResourceCategory getResourceCategory() {
		return ResourceCategory.LABEL;
	}
}
