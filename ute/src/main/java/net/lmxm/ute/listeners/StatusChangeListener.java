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
package net.lmxm.ute.listeners;

import java.util.EventListener;

/**
 * The listener interface for receiving statusChange events. The class that is interested in processing a statusChange
 * event implements this interface, and the object created with that class is registered with a component using the
 * component's <code>addStatusChangeListener<code> method. When
 * the statusChange event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see StatusChangeEvent
 */
public interface StatusChangeListener extends EventListener {

	/**
	 * Status change.
	 * 
	 * @param changeEvent the change event
	 */
	void statusChange(final StatusChangeEvent changeEvent);
}
