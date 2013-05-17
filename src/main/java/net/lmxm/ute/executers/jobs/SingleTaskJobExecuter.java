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

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SingleTaskJob;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.executers.tasks.TaskExecuterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SingleTaskJobExecuter.
 */
public final class SingleTaskJobExecuter extends AbstractJobExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SingleTaskJobExecuter.class);

	/**
	 * Instantiates a new single task job executer.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 */
	protected SingleTaskJobExecuter(final Job job, final PropertiesHolder propertiesHolder) {
		super(job, propertiesHolder);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executers.ExecuterIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final SingleTaskJob job = (SingleTaskJob) getJob();

		try {
			jobStarted();

			final Task task = job.getTask();

			LOGGER.debug("{} executing task={}", prefix, task);

			if (task.getEnabled()) {
				taskStarted(task);
				TaskExecuterFactory.create(job, task, getPropertiesHolder()).execute();
				taskCompleted(task);
			}
			else {
				LOGGER.debug("{} Task \"{}\" is disabled and will be skipped", prefix, task);
				taskSkipped(task);
			}

			jobCompleted();
		}
		catch (final Exception e) {
			LOGGER.debug("Exception caught executing job", e);
			jobAborted();
		}

		LOGGER.debug("{} returning", prefix);
	}
}
