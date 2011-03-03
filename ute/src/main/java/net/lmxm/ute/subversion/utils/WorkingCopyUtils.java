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
package net.lmxm.ute.subversion.utils;

import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.google.common.base.Preconditions;

/**
 * The Class WorkingCopyUtils.
 */
public final class WorkingCopyUtils extends AbstractSubversionUtils {

	/** The Constant INSTANCE. */
	private static final WorkingCopyUtils INSTANCE = new WorkingCopyUtils();

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkingCopyUtils.class);

	/**
	 * Gets the single instance of WorkingCopyUtils.
	 * 
	 * @return single instance of WorkingCopyUtils
	 */
	public static WorkingCopyUtils getInstance() {
		return INSTANCE;
	}

	/**
	 * Checks if is working copy.
	 * 
	 * @param path the path
	 * 
	 * @return true, if is working copy
	 */
	private static boolean isWorkingCopy(final String path) {
		final String prefix = "isWorkingCopy() :";

		LOGGER.debug("{} entered, path={}", prefix, path);

		boolean isWorkingCopy;

		try {
			isWorkingCopy = SVNWCUtil.isWorkingCopyRoot(new File(path));
		}
		catch (final SVNException e) {
			LOGGER.debug(
					"{} SVNException caught calling SVNWCUtil.isWorkingCopyRoot(), assuming that the path is not a working copy",
					prefix, path);

			isWorkingCopy = false;
		}

		LOGGER.debug("{} returning {}", prefix, isWorkingCopy);

		return isWorkingCopy;
	}

	/**
	 * Instantiates a new working copy utils.
	 */
	private WorkingCopyUtils() {
		super();
	}

	/**
	 * Update working copy.
	 * 
	 * @param pathname the pathname
	 * @param statusChangeListener the status change listener
	 */
	public void updateWorkingCopy(final String path, final StatusChangeListener statusChangeListener) {
		final String prefix = "updateWorkingCopy() :";

		LOGGER.debug("{} entered, path={}", prefix, path);

		final String pathTrimmed = StringUtils.trimToNull(path);

		Preconditions.checkNotNull(pathTrimmed, "Path must not be blank");
		Preconditions.checkState(isWorkingCopy(pathTrimmed), "Path must be a working copy root");
		Preconditions.checkNotNull(statusChangeListener, "StatusChangeListener may not be null");

		try {
			LOGGER.debug("{} start updating working copy", prefix);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.IMPORTANT,
					"Started updating working copy (" + pathTrimmed + ")"));

			final DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
			final SVNClientManager clientManager = SVNClientManager.newInstance(options);

			clientManager.setAuthenticationManager(SVNWCUtil.createDefaultAuthenticationManager());

			final SVNUpdateClient updateClient = clientManager.getUpdateClient();

			updateClient.setEventHandler(new EventHandler(this, statusChangeListener));

			updateClient.doUpdate(new File(pathTrimmed), SVNRevision.HEAD, SVNDepth.INFINITY, true, false);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.IMPORTANT,
					"Finishing updating working copy (" + pathTrimmed + ")"));

			LOGGER.debug("{} finished updating working copy", prefix);
		}
		catch (final SVNException e) {
			LOGGER.error("SVNException caught while updating working copy", e);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.ERROR,
					"Error occurred updating working copy (" + pathTrimmed + ")"));

			throw new RuntimeException(e); // TODO Use appropriate exception
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
