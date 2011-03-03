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
package net.lmxm.ute.beans.tasks;

import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;

/**
 * The Class SubversionUpdateTask.
 */
public final class SubversionUpdateTask extends AbstractFilesTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3145702319174166685L;

	/** The source. */
	private SubversionRepositorySource source;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new subversion update task.
	 */
	public SubversionUpdateTask() {
		super();
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
