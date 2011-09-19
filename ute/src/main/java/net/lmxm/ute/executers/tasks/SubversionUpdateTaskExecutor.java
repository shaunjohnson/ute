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

import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.executors.AbstractTaskExecuter;
import net.lmxm.ute.listeners.StatusChangeListener;
import net.lmxm.ute.subversion.utils.WorkingCopyUtils;
import net.lmxm.ute.utils.FileSystemTargetUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class SubversionUpdateTaskExecuter.
 */
public final class SubversionUpdateTaskExecuter extends AbstractTaskExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionUpdateTaskExecuter.class);

	/** The task. */
	private final SubversionUpdateTask task;

	/**
	 * Instantiates a new subversion update task executor.
	 *
	 * @param task the task
	 * @param statusChangeListener the status change listener
	 */
	public SubversionUpdateTaskExecuter(final SubversionUpdateTask task, final StatusChangeListener statusChangeListener) {
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

		final String path = FileSystemTargetUtils.getFullPath(task.getTarget());

		WorkingCopyUtils.getInstance().updateWorkingCopy(path, getStatusChangeListener());

		LOGGER.debug("{} returning", prefix);
	}
}
