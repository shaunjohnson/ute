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


import net.lmxm.ute.beans.tasks.Task;

/**
 * The Class Single Task Job.
 */
public final class SingleTaskJob extends AbstractJob {

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1760138183959898349L;

    /**
     * Creates a single task job.
     *
     * @param task Single Task object
     */
    public SingleTaskJob(Task task) {
        super();

        setId(task.getId());
        setDescription(task.getDescription());
        getTasks().add(task);
    }
}
