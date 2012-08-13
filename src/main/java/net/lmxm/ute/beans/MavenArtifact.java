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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class FileReference.
 */
public final class MavenArtifact implements DomainBean {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8020836262393382648L;

	/** The coordinates. */
	private String coordinates;

	/** The target name. */
	private String targetName;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (object == null) {
			return false;
		}

		if (object == this) {
			return true;
		}

		if (object.getClass() != getClass()) {
			return false;
		}

		final MavenArtifact rhs = (MavenArtifact) object;

		return new EqualsBuilder().append(coordinates, rhs.coordinates).append(targetName, rhs.targetName).isEquals();
	}

	/**
	 * Gets the coordinates.
	 * 
	 * @return the coordinates
	 */
	public String getCoordinates() {
		return coordinates;
	}

	/**
	 * Gets the target name.
	 * 
	 * @return the target name
	 */
	public String getTargetName() {
		return targetName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(coordinates, targetName);
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	@Override
	public boolean isEmpty() {
		return StringUtils.isBlank(coordinates) && StringUtils.isBlank(targetName);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		// No children objects
	}

	/**
	 * Sets the coordinates.
	 * 
	 * @param coordinates the new coordinates
	 */
	public void setCoordinates(final String coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Sets the target name.
	 * 
	 * @param targetName the new target name
	 */
	public void setTargetName(final String targetName) {
		this.targetName = targetName;
	}

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("coordinates", coordinates).add("targetName", targetName).toString();
    }
}
