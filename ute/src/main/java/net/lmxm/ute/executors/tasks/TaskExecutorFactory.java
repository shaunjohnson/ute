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
package net.lmxm.ute.executors.tasks;

import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.executors.Executor;
import net.lmxm.ute.executors.ExecutorFactory;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * A factory for creating TaskExecutor objects.
 */
public final class TaskExecutorFactory implements ExecutorFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskExecutorFactory.class);

	/**
	 * Creates the.
	 * 
	 * @param task the task
	 * @param propertiesHolder the properties holder
	 * @param statusChangeListener the status change listener
	 * @return the executor if
	 */
	public static Executor create(final Task task, final PropertiesHolder propertiesHolder,
			final StatusChangeListener statusChangeListener) {
		final String prefix = "create(Task,StatusChangeListener) :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} task={}", prefix, task);
			LOGGER.debug("{} statusChangeListener={}", prefix, statusChangeListener);
		}

		Preconditions.checkNotNull(task, "Task may not be null");
		Preconditions.checkNotNull(propertiesHolder, "PropertiesHolder may not be null");
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		Executor executor;

		if (task instanceof GroovyTask) {
			LOGGER.debug("{} task is GroovyTask", prefix);

			executor = new GroovyTaskExecutor((GroovyTask) task, propertiesHolder, statusChangeListener);
		}
		else if (task instanceof FileSystemDeleteTask) {
			LOGGER.debug("{} task is FileSystemDeleteTask", prefix);

			executor = new FileSystemDeleteTaskExecutor((FileSystemDeleteTask) task, statusChangeListener);
		}
		else if (task instanceof HttpDownloadTask) {
			LOGGER.debug("{} task is HttpDownloadTask", prefix);

			executor = new HttpDownloadTaskExecutor((HttpDownloadTask) task, statusChangeListener);
		}
		else if (task instanceof SubversionExportTask) {
			LOGGER.debug("{} task is SubversionExportTask", prefix);

			executor = new SubversionExportTaskExecutor((SubversionExportTask) task, statusChangeListener);
		}
		else if (task instanceof SubversionUpdateTask) {
			LOGGER.debug("{} task is SubversionUpdateTask", prefix);

			executor = new SubversionUpdateTaskExecutor((SubversionUpdateTask) task, statusChangeListener);
		}
		else {
			LOGGER.error("{} unsupported task type {}", prefix, task);

			throw new IllegalArgumentException("Unsupported task type");
		}

		LOGGER.debug("{} returning {}", prefix, executor);

		return executor;
	}

	/**
	 * Instantiates a new task executor factory.
	 */
	private TaskExecutorFactory() {
		throw new AssertionError();
	}
}
