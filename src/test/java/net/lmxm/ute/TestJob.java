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
package net.lmxm.ute;

import java.util.List;

import net.lmxm.ute.beans.IdentifiableDomainBean;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.Task;

import org.junit.Ignore;

/**
 * The Class TestJob.
 */
@Ignore
public class TestJob extends IdentifiableDomainBean implements Job {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5768119536424092115L;

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DescribableBean#getDescription()
	 */
	@Override
	public String getDescription() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableBean#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.jobs.Job#getTasks()
	 */
	@Override
	public List<Task> getTasks() {
		return null;
	}

	@Override
	public void removeEmptyObjects() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DescribableBean#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(final String description) {

	}
}
