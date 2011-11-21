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
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.utils.DomainBeanUtils;

/**
 * The Class FileSystemDeleteTask.
 */
public final class FileSystemDeleteTask extends AbstractFilesTask implements FileSystemTargetTask, StopOnErrorTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2208341570043553314L;

	/** The stop on error. */
	private boolean stopOnError;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new file system delete task.
	 * 
	 * @param job the job
	 */
	public FileSystemDeleteTask(final Job job) {
		super(job);

		stopOnError = false;
		target = new FileSystemTarget();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.StopOnErrorTask#getStopOnError()
	 */
	@Override
	public boolean getStopOnError() {
		return stopOnError;
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
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && DomainBeanUtils.isEmpty(target);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		super.removeEmptyObjects();
		DomainBeanUtils.removeEmptyObjects(target);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.StopOnErrorTask#setStopOnError(boolean)
	 */
	@Override
	public void setStopOnError(final boolean stopOnError) {
		this.stopOnError = stopOnError;
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
