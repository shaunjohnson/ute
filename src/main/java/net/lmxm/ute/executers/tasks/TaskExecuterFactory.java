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
package net.lmxm.ute.executers.tasks;

import static com.google.common.base.Preconditions.checkNotNull;
import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.tasks.*;
import net.lmxm.ute.exceptions.TaskExecuterException;
import net.lmxm.ute.executers.Executer;
import net.lmxm.ute.executers.ExecuterFactory;
import net.lmxm.ute.resources.types.ExceptionResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A factory for creating TaskExecuter objects.
 */
public final class TaskExecuterFactory implements ExecuterFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskExecuterFactory.class);

	/**
	 * Creates the.
	 * 
	 * @param task the task
	 * @param propertiesHolder the properties holder
	 * @return the executer if
	 */
	public static Executer create(final Task task, final PropertiesHolder propertiesHolder) {
		final String prefix = "create(Task,StatusChangeHelper) :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} task={}", prefix, task);
		}

		checkNotNull(task, "Task may not be null");
		checkNotNull(propertiesHolder, "PropertiesHolder may not be null");

		final Executer executer;
		if (task instanceof FileSystemDeleteTask) {
			LOGGER.debug("{} task is FileSystemDeleteTask", prefix);

			executer = new FileSystemDeleteTaskExecuter((FileSystemDeleteTask) task);
		}
		else if (task instanceof FindReplaceTask) {
			LOGGER.debug("{} task is FindReplaceTask", prefix);

			executer = new FindReplaceTaskExecuter((FindReplaceTask) task);
		}
		else if (task instanceof GroovyTask) {
			LOGGER.debug("{} task is GroovyTask", prefix);

			executer = new GroovyTaskExecuter((GroovyTask) task, propertiesHolder);
		}
		else if (task instanceof HttpDownloadTask) {
			LOGGER.debug("{} task is HttpDownloadTask", prefix);

			executer = new HttpDownloadTaskExecuter((HttpDownloadTask) task);
		}
		else if (task instanceof MavenRepositoryDownloadTask) {
			LOGGER.debug("{} task is MavenRepositoryDownloadTask", prefix);

			executer = new MavenRepositoryDownloadTaskExecuter((MavenRepositoryDownloadTask) task);
		}
		else if (task instanceof SubversionExportTask) {
			LOGGER.debug("{} task is SubversionExportTask", prefix);

			executer = new SubversionExportTaskExecuter((SubversionExportTask) task);
		}
		else if (task instanceof SubversionUpdateTask) {
			LOGGER.debug("{} task is SubversionUpdateTask", prefix);

			executer = new SubversionUpdateTaskExecuter((SubversionUpdateTask) task);
		}
		else {
			LOGGER.error("{} unsupported task type {}", prefix, task.getClass());

			throw new TaskExecuterException(ExceptionResourceType.UNSUPPORTED_TASK_TYPE, task.getClass());
		}

		LOGGER.debug("{} returning {}", prefix, executer);

		return executer;
	}

	/**
	 * Instantiates a new task executer factory.
	 */
	private TaskExecuterFactory() {
		throw new AssertionError();
	}
}
