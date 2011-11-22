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

import org.apache.commons.lang3.StringUtils;

/**
 * The Class GroovyTask.
 */
public final class GroovyTask extends AbstractFilesTask implements FileSystemTargetTask {

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

		target = new FileSystemTarget();
	}

	/**
	 * Gets the script.
	 * 
	 * @return the script
	 */
	public String getScript() {
		return script;
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
		return super.isEmpty() && StringUtils.isBlank(script) && DomainBeanUtils.isEmpty(target);
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

	/**
	 * Sets the script.
	 * 
	 * @param script the new script
	 */
	public void setScript(final String script) {
		this.script = script;
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
