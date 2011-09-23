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
package net.lmxm.ute.beans.jobs;

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.tasks.Task;

/**
 * The Class SequentialJob.
 */
public final class SequentialJob extends AbstractJob {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7969957264452664969L;

	/** The tasks. */
	private List<Task> tasks;

	/**
	 * Instantiates a new sequential job.
	 */
	public SequentialJob() {
		super();

		tasks = new ArrayList<Task>();
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
	 * Sets the tasks.
	 * 
	 * @param tasks the new tasks
	 */
	public final void setTasks(final List<Task> tasks) {
		this.tasks = tasks;
	}
}
