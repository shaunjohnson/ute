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
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;

/**
 * The Class SubversionUpdateTask.
 */
public final class SubversionUpdateTask extends AbstractRenameFilesTask implements FileSystemTargetTask,
		SubversionRepositorySourceTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3145702319174166685L;

	/** The source. */
	private SubversionRepositorySource source;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new subversion update task.
	 * 
	 * @param job the job
	 */
	public SubversionUpdateTask(final Job job) {
		super(job);

		source = new SubversionRepositorySource();
		target = new FileSystemTarget();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.SubversionRepositorySourceTask#getSource()
	 */
	@Override
	public SubversionRepositorySource getSource() {
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
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && source == null && target == null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.SubversionRepositorySourceTask#setSource(net.lmxm.ute.beans.sources.
	 * SubversionRepositorySource)
	 */
	@Override
	public void setSource(final SubversionRepositorySource source) {
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
