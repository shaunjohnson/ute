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

/**
 * The Class GroovyTask.
 */
public final class GroovyTask extends AbstractFilesTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5773262602501136737L;

	/** The script. */
	private String script;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new bean shell task.
	 * 
	 * @param job the job
	 */
	public GroovyTask(final Job job) {
		super(job);
	}

	/**
	 * Gets the script.
	 * 
	 * @return the script
	 */
	public String getScript() {
		return script;
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
	 * Sets the script.
	 * 
	 * @param script the new script
	 */
	public void setScript(final String script) {
		this.script = script;
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
