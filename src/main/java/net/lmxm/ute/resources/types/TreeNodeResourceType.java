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

import static net.lmxm.ute.resources.types.ResourceValueType.TEXT;

import javax.swing.Icon;

import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.resources.ResourceCategory;

/**
 */
public enum TreeNodeResourceType implements ResourceType {

	FILE_SYSTEM_LOCATIONS,
	HTTP_LOCATIONS,
	JOBS,
    MAVEN_REPOSITORY_LOCATIONS,
	PREFERENCES,
	PROPERTIES,
	SUBVERSION_REPOSITORY_LOCATIONS;

    private final ResourceValueType[] types = {TEXT};

	public ActionCommand getActionCommand() {
		return null;
	}

	public Icon getIcon() {
		return null;
	}

	public ResourceCategory getResourceCategory() {
		return ResourceCategory.TREE_NODE;
	}

    public ResourceValueType[] getResourceValueTypes() {
        return types;
    }
}
