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

import static net.lmxm.ute.resources.types.ResourceValueType.TEXT;
import static net.lmxm.ute.resources.types.ResourceValueType.TOOLTIP_TEXT;

/**
 * The Enum LabelResourceType.
 */
public enum LabelResourceType implements ResourceType {

    ARTIFACT_COORDINATES,
    ARTIFACTS,
    CURRENT_VALUE,
    DEPTH,
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
    MAVEN_REPOSITORY_DOWNLOAD_TASK,
    MAVEN_REPOSITORY_LOCATION,
    MAVEN_REPOSITORY_LOCATIONS,
    OPTIONS,
    PASSWORD,
    PATH,
    PATTERNS,
    PREFERENCE,
    PREFERENCES,
    PROPERTIES,
    PROPERTY,
    REVISION,
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

    private final ResourceValueType[] types = {TEXT, TOOLTIP_TEXT};

    public ActionCommand getActionCommand() {
        return null;
    }

    public Icon getIcon() {
        return null;
    }

    public ResourceCategory getResourceCategory() {
        return ResourceCategory.LABEL;
    }

    public ResourceValueType[] getResourceValueTypes() {
        return types;
    }
}
