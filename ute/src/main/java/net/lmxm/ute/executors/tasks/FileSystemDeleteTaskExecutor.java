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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.executors.AbstractTaskExecutor;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;
import net.lmxm.ute.utils.FileSystemTargetUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class FileSystemDeleteTaskExecutor.
 */
public final class FileSystemDeleteTaskExecutor extends AbstractTaskExecutor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemDeleteTaskExecutor.class);

	/** The task. */
	private final FileSystemDeleteTask task;

	/**
	 * Instantiates a new file system delete task executor.
	 * 
	 * @param task the task
	 * @param jobStatusListener the job status listener
	 * @param statusChangeListener the status change listener
	 */
	public FileSystemDeleteTaskExecutor(final FileSystemDeleteTask task, final StatusChangeListener statusChangeListener) {
		super(statusChangeListener);

		Preconditions.checkNotNull(task, "Task may not be null");

		this.task = task;
	}

	/**
	 * Delete files.
	 * 
	 * @param path the path
	 * @param files the files
	 * @param stopOnError the stop on error
	 * @param statusChangeListener the status change listener
	 */
	protected void deleteFiles(final String path, final List<FileReference> files, final boolean stopOnError) {
		final String prefix = "execute() :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} path={}", prefix, path);
			LOGGER.debug("{} files={}", prefix, files);
			LOGGER.debug("{} stopOnError={}", prefix, stopOnError);
		}

		final String pathTrimmed = StringUtils.trimToNull(path);

		Preconditions.checkArgument(pathTrimmed != null, "Path may not be blank");

		final File pathFile = new File(pathTrimmed);

		if (!pathFile.exists()) {
			LOGGER.debug("{} path does not exist, returning", prefix);

			getStatusChangeListener().statusChange(
					new StatusChangeEvent(this, StatusChangeEventType.INFO, "Path \"" + pathFile + "\" do not exist"));

			return;
		}

		if (pathFile.isFile()) {
			LOGGER.debug("{} deleting file {}", prefix, pathFile.getName());

			if (forceDelete(pathFile, stopOnError)) {
				getStatusChangeListener().statusChange(
						new StatusChangeEvent(this, StatusChangeEventType.INFO, "Deleted file \"" + pathFile + "\""));
			}
		}
		else if (pathFile.isDirectory()) {
			LOGGER.debug("{} path is a directory", prefix);

			if (CollectionUtils.isEmpty(files)) {
				LOGGER.debug("{} deleting directory {}", prefix, pathFile.getName());

				if (forceDelete(pathFile, stopOnError)) {
					getStatusChangeListener().statusChange(
							new StatusChangeEvent(this, StatusChangeEventType.INFO, "Deleted directory \"" + pathFile
									+ "\""));
				}
			}
			else {
				LOGGER.debug("{} deleting {} files in a directory", prefix, prefix);

				for (final FileReference file : files) {
					final String fileName = file.getName();

					LOGGER.debug("{} deleting file {}", prefix, fileName);

					if (forceDelete(new File(pathFile, fileName), stopOnError)) {
						getStatusChangeListener().statusChange(
								new StatusChangeEvent(this, StatusChangeEventType.INFO, "Deleted file \"" + fileName
										+ "\""));
					}
				}
			}
		}
		else {
			LOGGER.error("{} path is not a file or directory, returning", prefix);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executors.ExecutorIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final String path = FileSystemTargetUtils.getFullPath(task.getTarget());
		final List<FileReference> files = task.getFiles();
		final boolean stopOnError = task.getStopOnError();

		deleteFiles(path, files, stopOnError);

		LOGGER.debug("{} returning", prefix);
	}

	/**
	 * Force delete.
	 * 
	 * @param pathFile the path file
	 */
	protected boolean forceDelete(final File pathFile, final boolean stopOnError) {
		final String prefix = "forceDelete() :";

		boolean successful = false;

		try {
			FileUtils.forceDelete(pathFile);

			successful = true;
		}
		catch (final FileNotFoundException e) {
			if (stopOnError) {
				LOGGER.error(prefix + " file not found " + pathFile.getName(), e);

				getStatusChangeListener().statusChange(
						new StatusChangeEvent(this, StatusChangeEventType.ERROR,
								"Error deleting file, file not found \"" + pathFile + "\""));

				throw new RuntimeException();
			}
			else {
				LOGGER.debug("{} ignoring error deleting file", prefix);

				getStatusChangeListener().statusChange(
						new StatusChangeEvent(this, StatusChangeEventType.INFO,
								"Error deleting file, file not found \"" + pathFile + "\""));
			}
		}
		catch (final IOException e) {
			if (stopOnError) {
				LOGGER.error(prefix + " error deleting file " + pathFile.getName(), e);

				getStatusChangeListener().statusChange(
						new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Error deleting file \"" + pathFile
								+ "\""));

				throw new RuntimeException();
			}
			else {
				LOGGER.debug("{} ignoring error deleting file", prefix);

				getStatusChangeListener().statusChange(
						new StatusChangeEvent(this, StatusChangeEventType.INFO, "Error deleting file \"" + pathFile
								+ "\""));
			}
		}

		return successful;
	}
}
