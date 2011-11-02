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
package net.lmxm.ute.beans.tasks;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.targets.FileSystemTarget;

/**
 * The Class HttpDownloadTask.
 */
public class HttpDownloadTask extends AbstractFilesTask implements FileSystemTargetTask, HttpSourceTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7304138948723618917L;

	/** The source. */
	private HttpSource source;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new http download task.
	 * 
	 * @param job the job
	 */
	public HttpDownloadTask(final Job job) {
		super(job);

		source = new HttpSource();
		target = new FileSystemTarget();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.HttpSourceTask#getSource()
	 */
	@Override
	public HttpSource getSource() {
		return source;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.FileSystemTargetTask#getTarget()
	 */
	@Override
	public FileSystemTarget getTarget() {
		return target;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.HttpSourceTask#setSource(net.lmxm.ute.beans.sources.HttpSource)
	 */
	@Override
	public void setSource(final HttpSource source) {
		this.source = source;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.FileSystemTargetTask#setTarget(net.lmxm.ute.beans.targets.FileSystemTarget)
	 */
	@Override
	public void setTarget(final FileSystemTarget target) {
		this.target = target;
	}
}
