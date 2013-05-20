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
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.enums.SubversionDepth;
import net.lmxm.ute.enums.SubversionRevision;
import net.lmxm.ute.utils.DomainBeanUtils;

import java.util.Date;

/**
 * The Class SubversionExportTask.
 */
public final class SubversionExportTask extends AbstractRenameFilesTask implements FileSystemTargetTask,
		SubversionRepositorySourceTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2831925595307147878L;

	/** The depth. */
	private SubversionDepth depth;

	/** The revision. */
	private SubversionRevision revision;

	/** The revision date. */
	private Date revisionDate;

	/** The revision number. */
	private Long revisionNumber;

	/** The source. */
	private SubversionRepositorySource source;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new subversion export task.
	 * 
	 * @param job the job
	 */
	public SubversionExportTask(final Job job) {
		super(job);

		source = new SubversionRepositorySource();
		target = new FileSystemTarget();
	}

	public SubversionDepth getDepth() {
		return depth;
	}

	/**
	 * Gets the revision.
	 * 
	 * @return the revision
	 */
	public SubversionRevision getRevision() {
		return revision;
	}

	/**
	 * Gets the revision date.
	 * 
	 * @return the revision date
	 */
	public Date getRevisionDate() {
		return revisionDate == null ? null : (Date)revisionDate.clone();
	}

	/**
	 * Gets the revision number.
	 * 
	 * @return the revision number
	 */
	public Long getRevisionNumber() {
		return revisionNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.SubversionRepositorySourceTask#getSource()
	 */
	@Override
	public SubversionRepositorySource getSource() {
		return source;
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
		return super.isEmpty() && DomainBeanUtils.isEmpty(source) && DomainBeanUtils.isEmpty(target);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		super.removeEmptyObjects();
		DomainBeanUtils.removeEmptyObjects(source, target);
	}

	public void setDepth(final SubversionDepth depth) {
		this.depth = depth;
	}

	/**
	 * Sets the revision.
	 * 
	 * @param revision the new revision
	 */
	public void setRevision(final SubversionRevision revision) {
		this.revision = revision;
	}

	/**
	 * Sets the revision date.
	 * 
	 * @param revisionDate the new revision date
	 */
	public void setRevisionDate(final Date revisionDate) {
		this.revisionDate = revisionDate == null ? null : (Date)revisionDate.clone();
	}

	/**
	 * Sets the revision number.
	 * 
	 * @param revisionNumber the new revision number
	 */
	public void setRevisionNumber(final Long revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.SubversionRepositorySourceTask#setSource(net.lmxm.ute.beans.sources.
	 * SubversionRepositorySource)
	 */
	@Override
	public void setSource(final SubversionRepositorySource source) {
		this.source = source;
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
