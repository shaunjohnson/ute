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
package net.lmxm.ute.executors;

import net.lmxm.ute.listeners.StatusChangeListener;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractTaskExecutor.
 */
public abstract class AbstractTaskExecutor implements Executor {

	/** The status change listener. */
	private final StatusChangeListener statusChangeListener;

	/**
	 * Instantiates a new abstract task executor.
	 *
	 * @param statusChangeListener the status change listener
	 */
	public AbstractTaskExecutor(final StatusChangeListener statusChangeListener) {
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		this.statusChangeListener = statusChangeListener;
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
