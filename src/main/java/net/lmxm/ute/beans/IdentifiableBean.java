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

import java.io.Serializable;

/**
 * An identifiable bean is a serializable bean that contains an ID field that may be set, retrieved, as well as
 * include a method for generating user friendly text that may be used in a GUI to present to the user.
 */
public interface IdentifiableBean extends Comparable<IdentifiableBean>, Serializable, TypeBean {

	/**
     * Generates user friendly display text that may be used in the GUI.
	 *
     * @return User friendly text
	 */
	String getDisplayText();

	/**
	 * Gets the current ID of the object.
	 * 
	 * @return the ID
	 */
	String getId();

	/**
	 * Sets the ID of the object.
	 * 
	 * @param id the new ID
	 */
	void setId(String id);
}
