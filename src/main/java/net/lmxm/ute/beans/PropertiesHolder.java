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

import java.util.List;

/**
 * A properties holder is an object that contains retrievable lists of preferences and properties.
 */
public interface PropertiesHolder {

	/**
	 * Gets a List of Preference objects.
	 * 
	 * @return the preferences
	 */
	List<Preference> getPreferences();

	/**
	 * Gets a List of Property objects.
	 * 
	 * @return the properties
	 */
	List<Property> getProperties();
}
