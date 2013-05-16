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
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.utils.DomainBeanUtils;

/**
 * The Class Single Task Job.
 */
public final class SingleTaskJob extends AbstractJob {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1760138183959898349L;

	/** The task. */
	private final Task task;

	/** The tasks. */
	private final List<Task> tasks;

	/**
	 * Creates a single task job.
	 * 
	 * @param task Single Task object
	 */
	public SingleTaskJob(final Task task) {
		super();

		setId(task.getId());
		setDescription(task.getDescription());

		this.task = task;
		this.tasks = new ImmutableList.Builder<Task>().add(task).build();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableBean#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getId();
	}

	/**
	 * Gets the task.
	 * 
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.jobs.Job#getTasks()
	 */
	@Override
	public List<Task> getTasks() {
		return tasks;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && DomainBeanUtils.isEmpty(tasks);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		super.removeEmptyObjects();
		DomainBeanUtils.removeEmptyObjects(tasks);
	}
}
