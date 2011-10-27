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

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.jobs.Job;

/**
 * The Class AbstractFilesTask.
 */
public abstract class AbstractFilesTask extends AbstractTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2308238681775709817L;

	/** The files. */
	private List<FileReference> files;

	/**
	 * Instantiates a new files task.
	 * 
	 * @param job the job
	 */
	public AbstractFilesTask(final Job job) {
		super(job);

		files = new ArrayList<FileReference>();
	}

	/**
	 * Gets the files.
	 * 
	 * @return the files
	 */
	public final List<FileReference> getFiles() {
		return files;
	}

	/**
	 * Sets the files.
	 * 
	 * @param files the new files
	 */
	public final void setFiles(final List<FileReference> files) {
		this.files = files;
	}
}
