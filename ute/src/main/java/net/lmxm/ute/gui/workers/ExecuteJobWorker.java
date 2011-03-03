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
package net.lmxm.ute.gui.workers;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.executors.jobs.JobExecutorFactory;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class ExecuteJobWorker.
 */
public final class ExecuteJobWorker extends SwingWorker<Void, Void> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteJobWorker.class);

	/** The job. */
	private final Job job;

	/** The job status listener. */
	private final JobStatusListener jobStatusListener;

	/** The status change listener. */
	private final StatusChangeListener statusChangeListener;

	/**
	 * Instantiates a new execute job worker.
	 *
	 * @param job the job
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 */
	public ExecuteJobWorker(final Job job, final JobStatusListener jobStatusListener,
			final StatusChangeListener statusChangeListener) {
		super();

		Preconditions.checkNotNull(job, "Job may not be null");
		Preconditions.checkNotNull(jobStatusListener, "JobStatusListener may not be null");
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		this.job = job;
		this.jobStatusListener = jobStatusListener;
		this.statusChangeListener = statusChangeListener;
	}

	/**
	 * Performs action for this worker thread. This worker thread executes the report generator.
	 * 
	 * @return the void
	 */
	@Override
	public Void doInBackground() {
		final String prefix = "doInBackground() :";

		LOGGER.debug("{} entered", prefix);

		JobExecutorFactory.create(job, jobStatusListener, statusChangeListener).execute();

		LOGGER.debug("{} leaving", prefix);

		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		final String prefix = "done() :";

		LOGGER.debug("{} entered", prefix);

		if (isCancelled()) {
			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.HEADING,
					"Job Stopped (" + job.getId() + ")"));
		}

		jobStatusListener.jobStopped();

		LOGGER.debug("{} leaving", prefix);

		super.done();
	}

}
