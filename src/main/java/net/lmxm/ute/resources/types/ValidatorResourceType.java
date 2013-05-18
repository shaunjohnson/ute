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

import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.resources.ResourceCategory;

import javax.swing.*;

import static net.lmxm.ute.resources.types.ResourceValueType.MESSAGE;

/**
 * The Enum ScopeResourceType.
 */
public enum ValidatorResourceType implements ResourceType {

	FILE_SYSTEM_LOCATION_ID_ALREADY_USED,
	FILE_SYSTEM_LOCATION_ID_REQUIRED,
	FILE_SYSTEM_LOCATION_PATH_REQUIRED,

	HTTP_LOCATION_ID_ALREADY_USED,
	HTTP_LOCATION_ID_REQUIRED,
	HTTP_LOCATION_URL_MALFORMED,
	HTTP_LOCATION_URL_REQUIRED,

	JOB_ID_ALREADY_USED,
	JOB_ID_REQUIRED,

    MAVEN_ARTIFACT_COORDINATES_MALFORMED,
    MAVEN_ARTIFACT_COORDINATES_REQUIRED,

    MAVEN_REPOSITORY_LOCATION_ID_ALREADY_USED,
    MAVEN_REPOSITORY_LOCATION_ID_REQUIRED,
    MAVEN_REPOSITORY_LOCATION_URL_MALFORMED,
    MAVEN_REPOSITORY_LOCATION_URL_REQUIRED,

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

    private final ResourceValueType[] types = {MESSAGE};

	public ActionCommand getActionCommand() {
		return null;
	}

	public Icon getIcon() {
		return null;
	}

	public ResourceCategory getResourceCategory() {
		return ResourceCategory.VALIDATOR;
	}

    public ResourceValueType[] getResourceValueTypes() {
        return types;
    }
}
