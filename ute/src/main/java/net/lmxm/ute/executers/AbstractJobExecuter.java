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
package net.lmxm.ute.executers;

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractJobExecuter.
 */
public abstract class AbstractJobExecuter implements Executer {

	/** The job. */
	private final Job job;

	/** The job status listener. */
	private final JobStatusListener jobStatusListener;

	/** The properties holder. */
	private final PropertiesHolder propertiesHolder;

	/** The status change listener. */
	private final StatusChangeListener statusChangeListener;

	/**
	 * Instantiates a new abstract job executer.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 */
	public AbstractJobExecuter(final Job job, final PropertiesHolder propertiesHolder,
			final JobStatusListener jobStatusListener, final StatusChangeListener statusChangeListener) {
		Preconditions.checkNotNull(job, "Job may not be null");
		Preconditions.checkNotNull(propertiesHolder, "PropertiesHolder may not be null");
		Preconditions.checkNotNull(jobStatusListener, "JobStatusListener may not be null");
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		this.job = job;
		this.propertiesHolder = propertiesHolder;
		this.jobStatusListener = jobStatusListener;
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
	 * Fire heading status change.
	 * 
	 * @param message the message
	 */
	protected final void fireHeadingStatusChange(final String message) {
		getStatusChangeListener().statusChange(new StatusChangeEvent(this, StatusChangeEventType.HEADING, message));
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
	 * Gets the job.
	 * 
	 * @return the job
	 */
	protected Job getJob() {
		return job;
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
