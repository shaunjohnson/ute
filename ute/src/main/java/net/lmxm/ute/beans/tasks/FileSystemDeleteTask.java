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

import net.lmxm.ute.beans.targets.FileSystemTarget;

/**
 * The Class FileSystemDeleteTask.
 */
public final class FileSystemDeleteTask extends AbstractFilesTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2208341570043553314L;

	/** The stop on error. */
	private boolean stopOnError;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new file system delete task.
	 */
	public FileSystemDeleteTask() {
		super();
	}

	/**
	 * Gets the stop on error.
	 * 
	 * @return the stop on error
	 */
	public boolean getStopOnError() {
		return stopOnError;
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
	 * Sets the stop on error.
	 * 
	 * @param stopOnError the new stop on error
	 */
	public void setStopOnError(final boolean stopOnError) {
		this.stopOnError = stopOnError;
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
