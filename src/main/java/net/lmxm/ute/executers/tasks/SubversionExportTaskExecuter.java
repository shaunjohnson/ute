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
package net.lmxm.ute.executers.tasks;

import java.util.Date;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.enums.SubversionDepth;
import net.lmxm.ute.enums.SubversionRevision;
import net.lmxm.ute.event.StatusChangeHelper;
import net.lmxm.ute.subversion.utils.SubversionRepositoryLocationUtils;
import net.lmxm.ute.subversion.utils.SubversionRepositoryUtils;
import net.lmxm.ute.utils.FileSystemTargetUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class SubversionExportTaskExecuter.
 */
public final class SubversionExportTaskExecuter extends AbstractTaskExecuter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionExportTaskExecuter.class);

	/** The task. */
	private final SubversionExportTask task;

	/**
	 * Instantiates a new subversion export task executer.
	 * 
	 * @param task the task
	 * @param statusChangeHelper the status change helper
	 */
	public SubversionExportTaskExecuter(final SubversionExportTask task, final StatusChangeHelper statusChangeHelper) {
		super(statusChangeHelper);

		Preconditions.checkNotNull(task, "Task may not be null");

		this.task = task;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.executers.ExecuterIF#execute()
	 */
	@Override
	public void execute() {
		final String prefix = "execute() :";

		LOGGER.debug("{} entered", prefix);

		final SubversionRepositorySource source = task.getSource();
		final SubversionRepositoryLocation location = source.getLocation();
		final String username = location.getUsername();
		final String password = location.getPassword();

		final SubversionRepositoryUtils subversionRepositoryUtils = new SubversionRepositoryUtils(username, password,
				getStatusChangeHelper());

		final String url = SubversionRepositoryLocationUtils.getFullUrl(source);
		final String path = FileSystemTargetUtils.getFullPath(task.getTarget());
		final List<FileReference> files = task.getFiles();
		final SubversionDepth depth = task.getDepth();
		final SubversionRevision revision = task.getRevision();
		final Date revisionDate = task.getRevisionDate();
		final Long revisionNumber = task.getRevisionNumber();

		subversionRepositoryUtils.exportFiles(url, path, files, depth, revision, revisionDate, revisionNumber);

		LOGGER.debug("{} returning", prefix);
	}
}
