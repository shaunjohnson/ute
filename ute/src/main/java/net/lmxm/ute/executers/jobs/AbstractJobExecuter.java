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
package net.lmxm.ute.executers.jobs;

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.executers.AbstractExecuter;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.resources.StatusChangeMessage;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractJobExecuter.
 */
public abstract class AbstractJobExecuter extends AbstractExecuter implements JobExecuter {

	/** The job. */
	private final Job job;

	/** The job status listener. */
	private final List<JobStatusListener> jobStatusListeners = new ArrayList<JobStatusListener>();

	/** The properties holder. */
	private final PropertiesHolder propertiesHolder;

	/**
	 * Instantiates a new abstract job executer.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 */
	public AbstractJobExecuter(final Job job, final PropertiesHolder propertiesHolder) {
		Preconditions.checkNotNull(job, "Job may not be null");
		Preconditions.checkNotNull(propertiesHolder, "PropertiesHolder may not be null");

		this.job = job;
		this.propertiesHolder = propertiesHolder;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executers.jobs.JobExecuter#addJobStatusListener(net.lmxm.ute.listeners.JobStatusListener)
	 */
	@Override
	public final void addJobStatusListener(final JobStatusListener jobStatusListener) {
		jobStatusListeners.add(jobStatusListener);
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
	 * Gets the properties holder.
	 * 
	 * @return the properties holder
	 */
	protected final PropertiesHolder getPropertiesHolder() {
		return propertiesHolder;
	}

	/**
	 * Job aborted.
	 */
	protected final void jobAborted() {
		getStatusChangeHelper().heading(this, StatusChangeMessage.JOB_ABORTED, job.getId());

		for (final JobStatusListener jobStatusListener : jobStatusListeners) {
			jobStatusListener.jobAborted();
		}
	}

	/**
	 * Job completed.
	 */
	protected final void jobCompleted() {
		getStatusChangeHelper().heading(this, StatusChangeMessage.JOB_FINISHED, job.getId());

		for (final JobStatusListener jobStatusListener : jobStatusListeners) {
			jobStatusListener.jobCompleted();
		}
	}

	/**
	 * Job started.
	 */
	protected final void jobStarted() {
		getStatusChangeHelper().heading(this, StatusChangeMessage.JOB_STARTED, job.getId());

		for (final JobStatusListener jobStatusListener : jobStatusListeners) {
			jobStatusListener.jobStarted();
		}
	}

	/**
	 * Task completed.
	 * 
	 * @param task the task
	 */
	protected final void taskCompleted(final Task task) {
		for (final JobStatusListener jobStatusListener : jobStatusListeners) {
			jobStatusListener.jobTaskCompleted();
		}
	}

	/**
	 * Task skipped.
	 * 
	 * @param task the task
	 */
	protected final void taskSkipped(final Task task) {
		getStatusChangeHelper().info(this, StatusChangeMessage.DISABLED_TASK_SKIPPED, task.getId());

		for (final JobStatusListener jobStatusListener : jobStatusListeners) {
			jobStatusListener.jobTaskSkipped();
		}
	}

	/**
	 * Task started.
	 * 
	 * @param task the task
	 */
	protected final void taskStarted(final Task task) {
		for (final JobStatusListener jobStatusListener : jobStatusListeners) {
			jobStatusListener.jobTaskStarted();
		}
	}
}
