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
package net.lmxm.ute.executers.tasks.subversion;

import net.lmxm.ute.event.JobStatusChangeEventBus;
import org.apache.commons.lang3.StringUtils;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import static com.google.inject.internal.util.$Preconditions.checkNotNull;

/**
 * The Class AbstractSubversionUtils.
 */
public abstract class AbstractSubversionUtils {

	/** The authentication manager. */
	private final ISVNAuthenticationManager authenticationManager;

    /** The job status change listener. */
    private final JobStatusChangeEventBus jobStatusChangeEventBus;

	/**
	 * Instantiates a new abstract subversion utils.
	 * 
	 * @param username the username
	 * @param password the password
	 */
	public AbstractSubversionUtils(final JobStatusChangeEventBus jobStatusChangeEventBus, final String username, final String password) {
		super();

        this.jobStatusChangeEventBus = checkNotNull(jobStatusChangeEventBus, "Job status change listener may not be null");

		initialize();

		if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
			authenticationManager = SVNWCUtil.createDefaultAuthenticationManager();
		}
		else {
			authenticationManager = new BasicAuthenticationManager(username, password);
		}
	}

	/**
	 * Gets the authentication manager.
	 * 
	 * @return the authentication manager
	 */
	protected final ISVNAuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	/**
	 * Instantiates a new abstract subversion utils.
	 */
	private void initialize() {
		// For using over http:// and https://
		DAVRepositoryFactory.setup();

		// For using over svn:// and svn+xxx://
		SVNRepositoryFactoryImpl.setup();

		// For using over file:///
		FSRepositoryFactory.setup();
	}

    /**
     * Gets the associated job status change listener.
     *
     * @return The associated job statu change listener
     */
    protected final JobStatusChangeEventBus getJobStatusChangeEventBus() {
        return jobStatusChangeEventBus;
    }
}
