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
package net.lmxm.ute.beans;

/**
 * An Enabled State is a bean that has a state field named "enabled" that may be set or retrieved. An example of an
 * enabled state bean is a Task, which may be enabled or disabled.
 */
public interface EnabledStateBean {
	/**
	 * Gets the value of the enabled flag. A true value indicates that the object is enabled, otherwise it is disabled.
	 * 
	 * @return the current value of the enabled flag
	 */
	boolean getEnabled();

	/**
	 * Sets the enabled flag to the provided value. This allows a caller to enable/disable the object.
	 * 
	 * @param enabled the new value of enabled.
	 */
	void setEnabled(boolean enabled);
}
