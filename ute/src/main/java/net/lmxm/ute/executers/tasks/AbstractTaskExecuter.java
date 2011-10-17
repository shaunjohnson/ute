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
package net.lmxm.ute.executers.tasks;

import net.lmxm.ute.executers.Executer;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractTaskExecuter.
 */
public abstract class AbstractTaskExecuter implements Executer {

	/** The status change listener. */
	private final StatusChangeListener statusChangeListener;

	/**
	 * Instantiates a new abstract task executer.
	 * 
	 * @param statusChangeListener the status change listener
	 */
	public AbstractTaskExecuter(final StatusChangeListener statusChangeListener) {
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		this.statusChangeListener = statusChangeListener;
	}

	/**
	 * Fire error status change.
	 * 
	 * @param message the message
	 */
	protected final void fireErrorStatusChange(final String message) {
		getStatusChangeListener().statusChange(new StatusChangeEvent(this, StatusChangeEventType.ERROR, message));
	}

	/**
	 * Fire fatal status change.
	 * 
	 * @param message the message
	 */
	protected final void fireFatalStatusChange(final String message) {
		getStatusChangeListener().statusChange(new StatusChangeEvent(this, StatusChangeEventType.FATAL, message));
	}

	/**
	 * Fire important status change.
	 * 
	 * @param message the message
	 */
	protected final void fireImportantStatusChange(final String message) {
		getStatusChangeListener().statusChange(new StatusChangeEvent(this, StatusChangeEventType.IMPORTANT, message));
	}

	/**
	 * Fire info status change.
	 * 
	 * @param message the message
	 */
	protected final void fireInfoStatusChange(final String message) {
		getStatusChangeListener().statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO, message));
	}

	/**
	 * Gets the status change listener.
	 * 
	 * @return the status change listener
	 */
	protected final StatusChangeListener getStatusChangeListener() {
		return statusChangeListener;
	}
}
