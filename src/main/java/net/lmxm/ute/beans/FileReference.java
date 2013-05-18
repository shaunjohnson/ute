/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executer.
 * 
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.beans;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * A File Reference refers to a single file by name. A file consists of its current name and an optional target name
 * field, which is used to change the name of the file upon completion of some task. For example, targetName is used
 * by a Subversion Export Task so that a file named A may be exported and named B upon completion of the task.
 */
public final class FileReference implements DomainBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1693987931796968024L;

	/** The name of the referenced file. */
	private String name;

	/** The target name of the referenced file. */
	private String targetName;

	@Override
	public boolean equals(final Object object) {
        if (object instanceof FileReference) {
            final FileReference that = (FileReference) object;

            return Objects.equal(name, that.name) && Objects.equal(targetName, that.targetName);
        }
        else {
            return false;
        }
	}

	/**
	 * Gets the name of the referenced file.
	 * 
	 * @return the name of the file
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the target name of the referenced file.
	 * 
	 * @return the target name of the file
	 */
	public String getTargetName() {
		return targetName;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, targetName);
	}

    /**
     * Checks if the domain object is empty.
     *
     * @return true, if is empty
     */
	public boolean isEmpty() {
		return StringUtils.isBlank(name) && StringUtils.isBlank(targetName);
	}

    /**
     * Removes all empty child objects.
     */
	public void removeEmptyObjects() {
		// No children objects
	}

	/**
	 * Sets the name of the referenced file.
	 * 
	 * @param name the new name of the file
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Sets the target name of the referenced file.
	 * 
	 * @param targetName the new target name of the referenced file
	 */
	public void setTargetName(final String targetName) {
		this.targetName = targetName;
	}

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", name).add("targetName", targetName).toString();
    }
}
