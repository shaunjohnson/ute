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
package net.lmxm.ute;

import net.lmxm.ute.beans.IdentifiableDomainBean;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.Task;

import java.util.List;

import org.junit.Ignore;

/**
 * The Class TestJob.
 */
@Ignore
public class TestJob extends IdentifiableDomainBean implements Job {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5768119536424092115L;

	/* (non-Javadoc)
	 * @see net.lmxm.ute.beans.DescribableBean#getDescription()
	 */
	@Override
	public String getDescription() {
		return null;
	}

	/* (non-Javadoc)
	 * @see net.lmxm.ute.beans.jobs.Job#getTasks()
	 */
	@Override
	public List<Task> getTasks() {
		return null;
	}

	/* (non-Javadoc)
	 * @see net.lmxm.ute.beans.DescribableBean#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(final String description) {

	}
}
