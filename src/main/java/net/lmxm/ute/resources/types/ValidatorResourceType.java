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
import net.lmxm.ute.resources.ResourceCategory;

/**
 * The Enum ScopeResourceType.
 */
public enum ValidatorResourceType implements ResourceType {

	FILE_SYSTEM_LOCATION_ID_ALREADY_USED,

	FILE_SYSTEM_LOCATION_ID_REQUIRED,

	HTTP_LOCATION_ID_ALREADY_USED,

	HTTP_LOCATION_ID_REQUIRED,

	HTTP_LOCATION_URL_MALFORMED,

	HTTP_LOCATION_URL_REQUIRED,

	JOB_ID_ALREADY_USED,

	JOB_ID_REQUIRED,

	PREFERENCE_ID_ALREADY_USED,

	PREFERENCE_ID_REQUIRED,

	PROPERTY_ID_ALREADY_USED,

	PROPERTY_ID_REQUIRED,

	PROPERTY_VALUE_REQUIRED,

	SUBVERSION_REPOSITORY_LOCATION_ID_ALREADY_USED,

	SUBVERSION_REPOSITORY_LOCATION_ID_REQUIRED,

	SUBVERSION_REPOSITORY_LOCATION_URL_MALFORMED,

	SUBVERSION_REPOSITORY_LOCATION_URL_REQUIRED,

	TASK_ID_ALREADY_USED,

	TASK_ID_REQUIRED;

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getActionCommand()
	 */
	@Override
	public ActionCommand getActionCommand() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getGuiComponentCategory()
	 */
	@Override
	public ResourceCategory getResourceCategory() {
		return ResourceCategory.VALIDATOR;
	}
}
