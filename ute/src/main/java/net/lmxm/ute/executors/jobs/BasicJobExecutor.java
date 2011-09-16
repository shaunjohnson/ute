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

import java.util.List;

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.executors.AbstractJobExecutor;
import net.lmxm.ute.executors.tasks.TaskExecutorFactory;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class BasicJobExecutor.
 */
public final class BasicJobExecutor extends AbstractJobExecutor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicJobExecutor.class);

	/** The job. */
	private final Job job;

	/**
	 * Instantiates a new job executor.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 */
	protected BasicJobExecutor(final Job job, final PropertiesHolder propertiesHolder,
			final JobStatusListener jobStatusListener, final StatusChangeListener statusChangeListener) {
		super(propertiesHolder, jobStatusListener, statusChangeListener);

		Preconditions.checkNotNull(job, "Job may not be null");

		this.job = job;
	}

	/*
	 * (non-Javadoc)
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

					if (task.getEnabled()) {
						TaskExecutorFactory.create(task, getPropertiesHolder(), getStatusChangeListener()).execute();

						getJobStatusListener().jobTaskCompleted();
					}
					else {
						LOGGER.debug("{} Task \"{}\" is disabled and will be skipped", prefix, task);

						getStatusChangeListener().statusChange(
								new StatusChangeEvent(this, StatusChangeEventType.INFO, "Skipping disabled task \""
										+ task.getId() + "\""));

						getJobStatusListener().jobTaskSkipped();
					}
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
