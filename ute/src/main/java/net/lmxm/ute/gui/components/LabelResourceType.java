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

/**
 * The Enum GuiComponentLabel.
 */
public enum LabelResourceType implements ResourceType {

	/** The CURRENT_VALUE. */
	CURRENT_VALUE,

	/** The DESCRIPTION. */
	DESCRIPTION,

	/** The ENABLED. */
	ENABLED,

	/** The FILE_SYSTEM_DELETE_TASK. */
	FILE_SYSTEM_DELETE_TASK,

	/** The FILE_SYSTEM_LOCATION. */
	FILE_SYSTEM_LOCATION,

	/** The FILE_SYSTEM_LOCATIONS. */
	FILE_SYSTEM_LOCATIONS,

	/** The FILES. */
	FILES,

	/** The FIND_AND_REPLACE. */
	FIND_AND_REPLACE,

	/** The FIND_AND_REPLACE_TASK. */
	FIND_AND_REPLACE_TASK,

	/** The GROOVY_SCRIPT. */
	GROOVY_SCRIPT,

	/** The GROOVY_TASK. */
	GROOVY_TASK,

	/** The HTTP_DOWNLOAD_TASK. */
	HTTP_DOWNLOAD_TASK,

	/** The HTTP_LOCATION. */
	HTTP_LOCATION,

	/** The HTTP_LOCATIONS. */
	HTTP_LOCATIONS,

	/** The ID. */
	ID,

	/** The JOB. */
	JOB,

	/** The JOBS. */
	JOBS,

	/** The LOCATION. */
	LOCATION,

	/** The PASSWORD. */
	PASSWORD,

	/** The PATH. */
	PATH,

	/** The PATTERNS. */
	PATTERNS,

	/** The PREFERENCE. */
	PREFERENCE,

	/** The PREFERENCES. */
	PREFERENCES,

	/** The PROPERTIES. */
	PROPERTIES,

	/** The PROPERTY. */
	PROPERTY,

	/** The SCOPE. */
	SCOPE,

	/** The SCRIPT. */
	SCRIPT,

	/** The SEQUENTIAL_JOB. */
	SEQUENTIAL_JOB,

	/** The SERVER. */
	SERVER,

	/** The SOURCE. */
	SOURCE,

	/** The STOP_ON_ERROR. */
	STOP_ON_ERROR,

	/** The SUBVERSION_EXPORT_TASK. */
	SUBVERSION_EXPORT_TASK,

	/** The SUBVERSION_REPOSITORY_LOCATION. */
	SUBVERSION_REPOSITORY_LOCATION,

	/** The SUBVERSION_REPOSITORY_LOCATIONS. */
	SUBVERSION_REPOSITORY_LOCATIONS,

	/** The SUBVERSION_UPDATE_TASK. */
	SUBVERSION_UPDATE_TASK,

	/** The TARGET. */
	TARGET,

	/** The TASK. */
	TASK,

	/** The URL. */
	URL,

	/** The USERNAME. */
	USERNAME,

	/** The VALUE. */
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
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getGuiComponentCategory()
	 */
	@Override
	public ResourceCategory getGuiComponentCategory() {
		return ResourceCategory.LABEL;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return null;
	}
}
