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
 * The Class SubversionExportTask.
 */
public final class SubversionExportTask extends AbstractFilesTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2831925595307147878L;

	/** The source. */
	private SubversionRepositorySource source;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new subversion export task.
	 * 
	 * @param job the job
	 */
	public SubversionExportTask(final Job job) {
		super(job);
	}

	/**
	 * Gets the source.
	 * 
	 * @return the source
	 */
	public SubversionRepositorySource getSource() {
		return source;
	}

	/**
	 * Gets the target.
	 * 
	 * @return the target
	 */
	public FileSystemTarget getTarget() {
		return target;
	}

	/**
	 * Sets the source.
	 * 
	 * @param source the new source
	 */
	public void setSource(final SubversionRepositorySource source) {
		this.source = source;
	}

	/**
	 * Sets the target.
	 * 
	 * @param target the new target
	 */
	public void setTarget(final FileSystemTarget target) {
		this.target = target;
	}
}
