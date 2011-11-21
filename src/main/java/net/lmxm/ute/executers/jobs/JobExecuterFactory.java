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
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.jobs.SingleTaskJob;
import net.lmxm.ute.exceptions.JobExecuterException;
import net.lmxm.ute.executers.ExecuterFactory;
import net.lmxm.ute.resources.types.ExceptionResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * A factory for creating JobExecuter objects.
 */
public final class JobExecuterFactory implements ExecuterFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(JobExecuterFactory.class);

	/**
	 * Creates the.
	 * 
	 * @param job the job
	 * @param propertiesHolder the properties holder
	 * @return the executer if
	 */
	public static JobExecuter create(final Job job, final PropertiesHolder propertiesHolder) {
		final String prefix = "create() :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} job={}", prefix, job);
		}

		Preconditions.checkNotNull(job, "Job may not be null");
		Preconditions.checkNotNull(propertiesHolder, "PropertiesHolder may not be null");

		JobExecuter executer = null;

		if (job instanceof SequentialJob) {
			executer = new SequentialJobExecuter(job, propertiesHolder);
		}
		else if (job instanceof SingleTaskJob) {
			executer = new SingleTaskJobExecuter(job, propertiesHolder);
		}
		else {
			LOGGER.error("{} unsupported job type {}", prefix, job);

			throw new JobExecuterException(ExceptionResourceType.UNSUPPORTED_JOB_TYPE, job);
		}

		LOGGER.debug("{} returning {}", prefix, executer);

		return executer;
	}

	/**
	 * Instantiates a new job executer factory.
	 */
	private JobExecuterFactory() {
		throw new AssertionError();
	}
}
