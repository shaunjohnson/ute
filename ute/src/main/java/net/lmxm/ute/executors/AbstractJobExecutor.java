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

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeListener;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractJobExecutor.
 */
public abstract class AbstractJobExecutor implements Executor {

	/** The job status listener. */
	private final JobStatusListener jobStatusListener;

	/** The properties holder. */
	private final PropertiesHolder propertiesHolder;

	/** The status change listener. */
	private final StatusChangeListener statusChangeListener;

	/**
	 * Instantiates a new abstract job executor.
	 * 
	 * @param propertiesHolder the properties holder
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 */
	public AbstractJobExecutor(final PropertiesHolder propertiesHolder, final JobStatusListener jobStatusListener,
			final StatusChangeListener statusChangeListener) {
		Preconditions.checkNotNull(propertiesHolder, "PropertiesHolder may not be null");
		Preconditions.checkNotNull(jobStatusListener, "JobStatusListener may not be null");
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		this.propertiesHolder = propertiesHolder;
		this.jobStatusListener = jobStatusListener;
		this.statusChangeListener = statusChangeListener;
	}

	/**
	 * Gets the job status listener.
	 * 
	 * @return the job status listener
	 */
	protected final JobStatusListener getJobStatusListener() {
		return jobStatusListener;
	}

	/**
	 * Gets the properties holder.
	 * 
	 * @return the properties holder
	 */
	protected final PropertiesHolder getPropertiesHolder() {
		return propertiesHolder;
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
