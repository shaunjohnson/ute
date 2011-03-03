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
package net.lmxm.ute.beans.jobs;

import net.lmxm.ute.beans.IdentifiableDomainBean;
import net.lmxm.ute.beans.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class AbstractJob.
 */
public abstract class AbstractJob extends IdentifiableDomainBean implements Job {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7974535917838830967L;

	/** The description. */
	private String description;

	/** The tasks. */
	private List<Task> tasks;

	/**
	 * Instantiates a new job.
	 */
	public AbstractJob() {
		super();

		tasks = new ArrayList<Task>();
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	@Override
	public final String getDescription() {
		return description;
	}

	/**
	 * Gets the tasks.
	 * 
	 * @return the tasks
	 */
	@Override
	public final List<Task> getTasks() {
		return tasks;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	@Override
	public final void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the tasks.
	 * 
	 * @param tasks the new tasks
	 */
	public final void setTasks(final List<Task> tasks) {
		this.tasks = tasks;
	}
}
