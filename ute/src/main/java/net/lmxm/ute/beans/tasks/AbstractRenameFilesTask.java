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
import net.lmxm.ute.utils.DomainBeanUtils;

/**
 * The Class AbstractFilesTask.
 */
public abstract class AbstractRenameFilesTask extends AbstractTask implements RenameFilesTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2308238681775709817L;

	/** The files. */
	private List<FileReference> files;

	/**
	 * Instantiates a new files task.
	 * 
	 * @param job the job
	 */
	public AbstractRenameFilesTask(final Job job) {
		super(job);

		files = new ArrayList<FileReference>();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.FilesTask#getFiles()
	 */
	@Override
	public final List<FileReference> getFiles() {
		return files;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && DomainBeanUtils.isEmpty(files);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		super.removeEmptyObjects();
		DomainBeanUtils.removeEmptyObjects(files);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.FilesTask#setFiles(java.util.List)
	 */
	@Override
	public final void setFiles(final List<FileReference> files) {
		this.files = files;
	}
}
