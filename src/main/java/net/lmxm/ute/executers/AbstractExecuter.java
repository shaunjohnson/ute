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
package net.lmxm.ute.executers;

import net.lmxm.ute.event.StatusChangeHelper;
import net.lmxm.ute.event.StatusChangeListener;

/**
 * The Class AbstractExecuter.
 */
public abstract class AbstractExecuter implements Executer {

	/** The status change helper. */
	private final StatusChangeHelper statusChangeHelper;

	/**
	 * Instantiates a new abstract executer.
	 */
	public AbstractExecuter() {
		super();

		statusChangeHelper = new StatusChangeHelper();
	}

	/**
	 * Instantiates a new abstract executer.
	 * 
	 * @param statusChangeHelper the status change helper
	 */
	public AbstractExecuter(final StatusChangeHelper statusChangeHelper) {
		super();

		this.statusChangeHelper = statusChangeHelper;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executers.Executer#addStatusChangeListener(net.lmxm.ute.listeners.StatusChangeListener)
	 */
	@Override
	public void addStatusChangeListener(final StatusChangeListener statusChangeListener) {
		statusChangeHelper.addStatusChangeListener(statusChangeListener);
	}

	/**
	 * Gets the status change helper.
	 * 
	 * @return the status change helper
	 */
	protected final StatusChangeHelper getStatusChangeHelper() {
		return statusChangeHelper;
	}
}
