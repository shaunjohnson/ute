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
package net.lmxm.ute.gui.workers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.event.JobStatusListener;
import net.lmxm.ute.event.StatusChangeHelper;
import net.lmxm.ute.event.StatusChangeListener;
import net.lmxm.ute.executers.jobs.JobExecuter;
import net.lmxm.ute.executers.jobs.JobExecuterFactory;
import net.lmxm.ute.resources.StatusChangeMessageResourceType;

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

	/** The job executer. */
	private final JobExecuter jobExecuter;

	/** The job status listeners. */
	private final List<JobStatusListener> jobStatusListeners = new ArrayList<JobStatusListener>();

	/** The status change helper. */
	private final StatusChangeHelper statusChangeHelper = new StatusChangeHelper();

	/**
	 * Instantiates a new execute job worker.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 */
	public ExecuteJobWorker(final Job job, final PropertiesHolder propertiesHolder) {
		super();

		Preconditions.checkNotNull(job, "Job may not be null");

		this.job = job;
		this.jobExecuter = JobExecuterFactory.create(job, propertiesHolder);
	}

	/**
	 * Adds the job status listener.
	 * 
	 * @param jobStatusListener the job status listener
	 */
	public void addJobStatusListener(final JobStatusListener jobStatusListener) {
		jobExecuter.addJobStatusListener(jobStatusListener);
		jobStatusListeners.add(jobStatusListener);
	}

	/**
	 * Adds the status change listener.
	 * 
	 * @param statusChangeListener the status change listener
	 */
	public void addStatusChangeListener(final StatusChangeListener statusChangeListener) {
		jobExecuter.addStatusChangeListener(statusChangeListener);
		statusChangeHelper.addStatusChangeListener(statusChangeListener);
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

		jobExecuter.execute();

		LOGGER.debug("{} leaving", prefix);

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		final String prefix = "done() :";

		LOGGER.debug("{} entered", prefix);

		if (isCancelled()) {
			statusChangeHelper.heading(this, StatusChangeMessageResourceType.JOB_STOPPED, job.getId());
		}

		for (final JobStatusListener jobStatusListener : jobStatusListeners) {
			jobStatusListener.jobStopped();
		}

		LOGGER.debug("{} leaving", prefix);

		super.done();
	}
}
