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
package net.lmxm.ute.enums;

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.SubversionRevisionResourceType;

/**
 * The Enum SubversionRevision.
 */
public enum SubversionRevision {

	DATE(ResourcesUtils.getResourceText(SubversionRevisionResourceType.DATE)),

	HEAD(ResourcesUtils.getResourceText(SubversionRevisionResourceType.HEAD)),

	NUMBERED(ResourcesUtils.getResourceText(SubversionRevisionResourceType.NUMBERED));

	/** The name. */
	private final String name;

	/**
	 * Instantiates a new subversion revision.
	 * 
	 * @param name the name
	 */
	SubversionRevision(final String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}
