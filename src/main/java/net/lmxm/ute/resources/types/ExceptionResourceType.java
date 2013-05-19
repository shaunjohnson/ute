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

import com.google.common.collect.ImmutableSet;
import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.resources.ResourceCategory;

import javax.swing.*;

import java.util.Set;

import static net.lmxm.ute.resources.types.ResourceValueType.MESSAGE;

public enum ExceptionResourceType implements ResourceType {
    DIRECTORY_ALREADY_EXISTS,
    DRAG_AND_DROP_ERROR,
	ERROR_CREATING_PREFERENCES_FILE,
	ERROR_LOADING_CONFIGURATION_FILE,
	ERROR_LOADING_PREFERENCES_FILE,
	ERROR_SAVING_CONFIGURATION_FILE,
    FILE_DELETE_ERROR,
    FILE_NOT_FOUND,
    FILE_READ_WRITE_ERROR,
    FILE_SYSTEM_LOCATION_NODE_MISSING,
    GROOVY_SCRIPT_COMPILATION_FAILED,
    GROOVY_SCRIPT_EXECUTION_FAILED,
    HTTP_LOCATION_NODE_MISSING,
    HTTP_DOWNLOAD_ERROR,
    HTTP_DOWNLOAD_FILE_LIST_EMPTY,
    HTTP_DOWNLOAD_NO_RESPONSE,
    HTTP_DOWNLOAD_STATUS_ERROR,
	INVALID_CONFIGURATION_FILE,
	INVALID_PATTERN,
    INVALID_SUBVERSION_REVISION_VALUE,
    JOB_NODE_MISSING,
    JOB_NOT_FOUND,
    MAVEN_REPOSITORY_LOCATION_NODE_MISSING,
    PREFERENCE_NODE_MISSING,
	PREFERENCES_FILE_ALREADY_EXISTS,
    PREFERENCES_MUST_BE_ASSIGNED_VALUES,
    PROPERTY_NODE_MISSING,
    SUBVERSION_AUTHENTICATION_FAILED,
    SUBVERSION_EXPORT_ERROR,
    SUBVERSION_REPOSITORY_LOCATION_NODE_MISSING,
    SUBVERSION_UPDATE_ERROR,
    TASK_NODE_MISSING,
    TASK_NOT_FOUND,
    UNABLE_TO_CREATE_DIRECTORY,
    UNDEFINED_PROPERTY,
    UNEXPECTED_ERROR,
    UNSUPPORTED_HTTP_LOCATION_TYPE,
	UNSUPPORTED_JOB_TYPE,
	UNSUPPORTED_SCOPE,
	UNSUPPORTED_SCOPE_TYPE,
	UNSUPPORTED_SUBVERSION_DEPTH,
	UNSUPPORTED_SUBVERSION_DEPTH_TYPE,
    UNSUPPORTED_SUBVERSION_REVISION,
	UNSUPPORTED_TASK_TYPE;

    private final Set<ResourceValueType> types = ImmutableSet.of(MESSAGE);

	public ActionCommand getActionCommand() {
		return null;
	}

	public Icon getIcon() {
		return null;
	}

	public ResourceCategory getResourceCategory() {
		return ResourceCategory.EXCEPTION;
	}

    public Set<ResourceValueType> getResourceValueTypes() {
        return types;
    }
}
