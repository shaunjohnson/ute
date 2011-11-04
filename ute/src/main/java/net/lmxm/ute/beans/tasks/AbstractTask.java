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

import net.lmxm.ute.beans.IdentifiableDomainBean;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.utils.DomainBeanUtils;

import org.codehaus.plexus.util.StringUtils;

/**
 * The Class AbstractTask.
 */
public abstract class AbstractTask extends IdentifiableDomainBean implements Task {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7830292768049887798L;

	/** The description. */
	private String description;

	/** The enabled. */
	private boolean enabled;

	/** The job. */
	private final Job job;

	/**
	 * Instantiates a new abstract task.
	 * 
	 * @param job the job
	 */
	public AbstractTask(final Job job) {
		super();

		this.enabled = true;
		this.job = job;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#getDescription()
	 */
	@Override
	public final String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableBean#getDisplayText()
	 */
	@Override
	public final String getDisplayText() {
		return getId();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#getEnabled()
	 */
	@Override
	public final boolean getEnabled() {
		return enabled;
	}

	@Override
	public Job getJob() {
		return job;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && StringUtils.isBlank(description) && DomainBeanUtils.isEmpty(job);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#setDescription(java.lang.String)
	 */
	@Override
	public final void setDescription(final String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#setEnabled(boolean)
	 */
	@Override
	public final void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}
}
