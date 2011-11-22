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
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.event.StatusChangeHelper;
import net.lmxm.ute.subversion.utils.SubversionWorkingCopyUtils;
import net.lmxm.ute.utils.FileSystemTargetUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SubversionUpdateTaskExecuter.
 */
public final class SubversionUpdateTaskExecuter extends AbstractTaskExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionUpdateTaskExecuter.class);

	/** The task. */
	private final SubversionUpdateTask task;

	/**
	 * Instantiates a new subversion update task executer.
	 * 
	 * @param task the task
	 * @param statusChangeHelper the status change helper
	 */
	public SubversionUpdateTaskExecuter(final SubversionUpdateTask task, final StatusChangeHelper statusChangeHelper) {
		super(statusChangeHelper);

		checkNotNull(task, "Task may not be null");

		this.task = task;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executers.ExecuterIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final String path = FileSystemTargetUtils.getFullPath(task.getTarget());

		final SubversionRepositoryLocation location = task.getSource().getLocation();
		final String username = location.getUsername();
		final String password = location.getPassword();

		final SubversionWorkingCopyUtils subversionWorkingCopyUtils = new SubversionWorkingCopyUtils(username,
				password, getStatusChangeHelper());
		subversionWorkingCopyUtils.updateWorkingCopy(path);

		LOGGER.debug("{} returning", prefix);
	}
}