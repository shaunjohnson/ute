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
import net.lmxm.ute.beans.jobs.BasicJob;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SingleTaskJob;
import net.lmxm.ute.executors.Executor;
import net.lmxm.ute.executors.ExecutorFactory;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * A factory for creating JobExecutor objects.
 */
public final class JobExecutorFactory implements ExecutorFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(JobExecutorFactory.class);

	/**
	 * Creates the.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 * @return the executor if
	 */
	public static Executor create(final Job job, final PropertiesHolder propertiesHolder,
			final JobStatusListener jobStatusListener, final StatusChangeListener statusChangeListener) {
		final String prefix = "create(Job,PropertiesHolder,JobStatusListenerStatusChangeListener) :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} job={}", prefix, job);
			LOGGER.debug("{} jobStatusListener={}", prefix, jobStatusListener);
			LOGGER.debug("{} statusChangeListener={}", prefix, statusChangeListener);
		}

		Preconditions.checkNotNull(job, "Job may not be null");
		Preconditions.checkNotNull(propertiesHolder, "PropertiesHolder may not be null");
		Preconditions.checkNotNull(jobStatusListener, "JobStatusListener may not be null");
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		Executor executor = null;

		if (job instanceof BasicJob) {
			executor = new BasicJobExecutor(job, propertiesHolder, jobStatusListener, statusChangeListener);
		}
		else if (job instanceof SingleTaskJob) {
			executor = new SingleTaskJobExecutor(job, propertiesHolder, jobStatusListener, statusChangeListener);
		}
		else {
			throw new IllegalArgumentException("Unsupported job type");
		}

		LOGGER.debug("{} returning {}", prefix, executor);

		return executor;
	}

	/**
	 * Instantiates a new job executor factory.
	 */
	private JobExecutorFactory() {
		throw new AssertionError();
	}
}
