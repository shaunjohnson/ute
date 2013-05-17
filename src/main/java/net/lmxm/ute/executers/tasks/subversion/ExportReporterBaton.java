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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.ISVNReporter;
import org.tmatesoft.svn.core.io.ISVNReporterBaton;

import static com.google.inject.internal.util.$Preconditions.checkNotNull;
import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.SUBVERSION_EXPORT_REPORT_ERROR;

/**
 * The Class ExportReporterBaton.
 */
public final class ExportReporterBaton implements ISVNReporterBaton {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportReporterBaton.class);

    /**
     * The job status change listener.
     */
    private final JobStatusChangeEventBus jobStatusChangeEventBus;

    /**
     * The export revision.
     */
    private final long revision;

    /**
     * Instantiates a new export reporter baton.
     *
     * @param jobStatusChangeEventBus the job status change listener
     * @param revision                the revision
     */
    public ExportReporterBaton(final JobStatusChangeEventBus jobStatusChangeEventBus, final long revision) {
        super();

        this.jobStatusChangeEventBus = checkNotNull(jobStatusChangeEventBus, "Job status change event listener");
        this.revision = revision;
    }

    /*
     * (non-Javadoc)
     * @see org.tmatesoft.svn.core.io.ISVNReporterBaton#report(org.tmatesoft. svn.core.io.ISVNReporter)
     */
    @Override
    public void report(final ISVNReporter reporter) throws SVNException {
        final String prefix = "report() :";

        LOGGER.debug("{} entered", prefix);

        try {
            /*
			 * Here empty working copy is reported. ISVNReporter includes methods that allows to report mixed-rev
			 * working copy and even let server know that some files or directories are locally missing or locked.
			 */
            reporter.setPath("", null, revision, SVNDepth.INFINITY, true);

            // Don't forget to finish the report!
            reporter.finishReport();
        }
        catch (final SVNException e) {
            LOGGER.error("report() : SVNException caught", e);

            reporter.abortReport();

            jobStatusChangeEventBus.error(SUBVERSION_EXPORT_REPORT_ERROR);
        }

        LOGGER.debug("{} leaving", prefix);
    }
}
