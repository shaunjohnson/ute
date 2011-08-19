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

import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.executors.AbstractTaskExecutor;
import net.lmxm.ute.listeners.StatusChangeListener;
import net.lmxm.ute.utils.FileSystemTargetUtils;
import net.lmxm.ute.utils.FindReplaceUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class FindReplaceTaskExecutor.
 */
public final class FindReplaceTaskExecutor extends AbstractTaskExecutor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FindReplaceTaskExecutor.class);

	/** The task. */
	private final FindReplaceTask task;

	/**
	 * Instantiates a new find replace task executor.
	 * 
	 * @param task the task
	 * @param statusChangeListener the status change listener
	 */
	public FindReplaceTaskExecutor(final FindReplaceTask task, final StatusChangeListener statusChangeListener) {
		super(statusChangeListener);

		Preconditions.checkNotNull(task, "Task may not be null");

		this.task = task;
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
		final List<FindReplacePattern> patterns = task.getPatterns();
		final Scope scope = task.getScope();

		FindReplaceUtils.getInstance().findReplaceInFiles(path, files, patterns, scope, getStatusChangeListener());

		LOGGER.debug("{} returning", prefix);
	}
}
