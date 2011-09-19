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
package net.lmxm.ute.executors.jobs;

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SingleTaskJob;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.executors.AbstractJobExecutor;
import net.lmxm.ute.executors.tasks.TaskExecutorFactory;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SingleTaskJobExecutor.
 */
public final class SingleTaskJobExecutor extends AbstractJobExecutor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SingleTaskJobExecutor.class);

	/**
	 * Instantiates a new single task job executor.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 */
	protected SingleTaskJobExecutor(final Job job, final PropertiesHolder propertiesHolder,
			final JobStatusListener jobStatusListener, final StatusChangeListener statusChangeListener) {
		super(job, propertiesHolder, jobStatusListener, statusChangeListener);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executors.ExecutorIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final SingleTaskJob job = (SingleTaskJob) getJob();

		try {
			fireHeadingStatusChange("Started Task (" + job.getId() + ")");

			final Task task = job.getTask();

			LOGGER.debug("{} executing task={}", prefix, task);

			if (task.getEnabled()) {
				TaskExecutorFactory.create(task, getPropertiesHolder(), getStatusChangeListener()).execute();

				getJobStatusListener().jobTaskCompleted();
			}
			else {
				LOGGER.debug("{} Task \"{}\" is disabled and will be skipped", prefix, task);

				fireInfoStatusChange("Skipping disabled task \"" + task.getId() + "\"");

				getJobStatusListener().jobTaskSkipped();
			}

			fireHeadingStatusChange("Finished Task (" + job.getId() + ")");
			getJobStatusListener().jobCompleted();
		}
		catch (final Exception e) {
			LOGGER.debug("Exception caught executing job", e);

			fireHeadingStatusChange("Task Aborted (" + job.getId() + ")");
			getJobStatusListener().jobAborted();
		}

		LOGGER.debug("{} returning", prefix);
	}
}
