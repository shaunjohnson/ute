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
package net.lmxm.ute.executors.tasks;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.executors.AbstractTaskExecuter;
import net.lmxm.ute.listeners.StatusChangeListener;
import net.lmxm.ute.subversion.utils.SubversionRepositoryLocationUtils;
import net.lmxm.ute.subversion.utils.SubversionRepositoryUtils;
import net.lmxm.ute.utils.FileSystemTargetUtils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class SubversionExportTaskExecuter.
 */
public final class SubversionExportTaskExecuter extends AbstractTaskExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionExportTaskExecuter.class);

	/** The task. */
	private final SubversionExportTask task;

	/**
	 * Instantiates a new subversion export task executor.
	 *
	 * @param task the task
	 * @param statusChangeListener the status change listener
	 */
	public SubversionExportTaskExecuter(final SubversionExportTask task, final StatusChangeListener statusChangeListener) {
		super(statusChangeListener);

		Preconditions.checkNotNull(task, "Task may not be null");

		this.task = task;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.lmxm.ute.executors.ExecuterIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final String url = SubversionRepositoryLocationUtils.getFullUrl(task.getSource());
		final String path = FileSystemTargetUtils.getFullPath(task.getTarget());
		final List<FileReference> files = task.getFiles();

		SubversionRepositoryUtils.getInstance().exportFiles(url, path, files, getStatusChangeListener());

		LOGGER.debug("{} returning", prefix);
	}
}