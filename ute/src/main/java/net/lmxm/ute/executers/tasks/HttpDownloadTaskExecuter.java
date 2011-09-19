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

import java.util.List;
import java.util.Map;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.executers.AbstractTaskExecuter;
import net.lmxm.ute.listeners.StatusChangeListener;
import net.lmxm.ute.utils.FileSystemTargetUtils;
import net.lmxm.ute.utils.HttpUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class HttpDownloadTaskExecuter.
 */
public final class HttpDownloadTaskExecuter extends AbstractTaskExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpDownloadTaskExecuter.class);

	/** The task. */
	private final HttpDownloadTask task;

	/**
	 * Instantiates a new http download task executer.
	 * 
	 * @param task the task
	 * @param statusChangeListener the status change listener
	 */
	public HttpDownloadTaskExecuter(final HttpDownloadTask task, final StatusChangeListener statusChangeListener) {
		super(statusChangeListener);

		Preconditions.checkNotNull(task, "Task may not be null");

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

		final String url = HttpUtils.getFullUrl(task.getSource());
		final Map<String, String> queryParams = task.getSource().getQueryParams();
		final String destinationPath = FileSystemTargetUtils.getFullPath(task.getTarget());
		final List<FileReference> files = task.getFiles();

		HttpUtils.getInstance().downloadFiles(url, queryParams, destinationPath, files, getStatusChangeListener());

		LOGGER.debug("{} returning", prefix);
	}
}
