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

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.executers.jobs.JobExecuter;
import net.lmxm.ute.executers.jobs.JobExecuterFactory;
import net.lmxm.ute.executers.jobs.JobStatusEvent;
import net.lmxm.ute.executers.jobs.JobStatusEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.JOB_STOPPED;

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

	/**
	 * Instantiates a new execute job worker.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 */
	public ExecuteJobWorker(final Job job, final PropertiesHolder propertiesHolder) {
		super();

		checkNotNull(job, "Job may not be null");

		this.job = job;
		this.jobExecuter = JobExecuterFactory.create(job, propertiesHolder);
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
            StatusChangeEventBus.heading(JOB_STOPPED, job.getId());
		}

        JobStatusEventBus.post(new JobStatusEvent(JobStatusEvent.JobStatusEventType.JobStopped, job));

		LOGGER.debug("{} leaving", prefix);

		super.done();
	}
}
