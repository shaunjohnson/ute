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

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.executors.AbstractJobExecutor;
import net.lmxm.ute.executors.tasks.TaskExecutorFactory;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class JobExecutor.
 */
public final class JobExecutor extends AbstractJobExecutor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(JobExecutor.class);

	/** The job. */
	private final Job job;

	/**
	 * Instantiates a new job executor.
	 *
	 * @param job the job
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 */
	protected JobExecutor(final Job job, final JobStatusListener jobStatusListener,
			final StatusChangeListener statusChangeListener) {
		super(jobStatusListener, statusChangeListener);

		Preconditions.checkNotNull(job, "Job may not be null");

		this.job = job;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.lmxm.ute.executors.ExecutorIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		try {
			getStatusChangeListener().statusChange(
					new StatusChangeEvent(this, StatusChangeEventType.HEADING, "Started Job (" + job.getId() + ")"));

			final List<Task> tasks = job.getTasks();

			if (tasks == null) {
				LOGGER.debug("{} there are no tasks to execute", prefix);
			}
			else {
				LOGGER.debug("{} executing {} tasks", prefix, tasks.size());

				for (final Task task : job.getTasks()) {
					if (Thread.currentThread().isInterrupted()) {
						LOGGER.debug("{} thread was interrupted, stopping job execution", prefix);

						throw new RuntimeException("Job is being stopped"); // TODO Use appropriate exception
					}

					TaskExecutorFactory.create(task, getStatusChangeListener()).execute();

					getJobStatusListener().jobTaskCompleted();
				}
			}

			getStatusChangeListener().statusChange(
					new StatusChangeEvent(this, StatusChangeEventType.HEADING, "Finished Job (" + job.getId() + ")"));
			getJobStatusListener().jobCompleted();
		}
		catch (final Exception e) {
			LOGGER.debug("Exception caught executing job", e);

			getStatusChangeListener().statusChange(
					new StatusChangeEvent(this, StatusChangeEventType.HEADING, "Job Aborted (" + job.getId() + ")"));
			getJobStatusListener().jobAborted();
		}

		LOGGER.debug("{} returning", prefix);
	}
}
