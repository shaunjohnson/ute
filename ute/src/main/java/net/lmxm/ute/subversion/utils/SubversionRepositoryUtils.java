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
package net.lmxm.ute.subversion.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.listeners.StatusChangeHelper;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.io.ISVNReporterBaton;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

import com.google.common.base.Preconditions;

/**
 * The Class SubversionUtils.
 */
public final class SubversionRepositoryUtils extends AbstractSubversionUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionRepositoryUtils.class);

	/**
	 * Instantiates a new subversion repository utils.
	 * 
	 * @param statusChangeHelper the status change helper
	 */
	public SubversionRepositoryUtils(final StatusChangeHelper statusChangeHelper) {
		super(statusChangeHelper);
	}

	/**
	 * Instantiates a new subversion repository utils.
	 * 
	 * @param username the username
	 * @param password the password
	 * @param statusChangeHelper the status change helper
	 */
	public SubversionRepositoryUtils(final String username, final String password,
			final StatusChangeHelper statusChangeHelper) {
		super(username, password, statusChangeHelper);
	}

	/**
	 * Export files.
	 * 
	 * @param urlString the url
	 * @param destinationPath the path
	 * @param files the files
	 */
	public void exportFiles(final String urlString, final String destinationPath, final List<FileReference> files) {
		final String prefix = "exportFiles() :";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("{} entered", prefix);
			LOGGER.debug("{} urlString={}", prefix, urlString);
			LOGGER.debug("{} destinationPath={}", prefix, destinationPath);
			LOGGER.debug("{} files={}", prefix, files);
		}

		Preconditions.checkNotNull(urlString, "URL must not be null");
		Preconditions.checkNotNull(destinationPath, "Destination path must not be null");

		try {
			getStatusChangeHelper().important(this, "Starting file export");

			getStatusChangeHelper().info(this,
					"Exporting files from \"" + urlString + "\" to \"" + destinationPath + "\"");

			final SVNURL url = SVNURL.parseURIEncoded(urlString);
			final File exportDirectory = new File(destinationPath);

			if (exportDirectory.exists()) {
				LOGGER.debug("{} export directory already exists", prefix);
			}
			else {
				if (exportDirectory.mkdirs()) {
					LOGGER.debug("{} successfully created export directories", prefix);
				}
				else {
					LOGGER.error("{} unable to create export directories", prefix);

					throw new RuntimeException("Unable to create export directories"); // TODO Use appropriate exception
				}
			}

			final SVNRepository repository = SVNRepositoryFactory.create(url);

			repository.setAuthenticationManager(getAuthenticationManager());

			final SVNNodeKind nodeKind = repository.checkPath("", -1);
			final long latestRevision = repository.getLatestRevision();

			if (nodeKind == SVNNodeKind.NONE) {
				LOGGER.error("{} No entry at URL {}", prefix, url);

				final SVNErrorMessage err = SVNErrorMessage
						.create(SVNErrorCode.UNKNOWN, "No entry at URL ''{0}''", url);
				throw new SVNException(err);
			}
			else if (nodeKind == SVNNodeKind.FILE) {
				LOGGER.error("{} Entry at URL {} is a file while directory was expected", prefix, url);

				final SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.UNKNOWN,
						"Entry at URL ''{0}'' is a file while directory was expected", url);
				throw new SVNException(err);
			}

			if (CollectionUtils.isEmpty(files)) {
				LOGGER.debug("{} files list is empty, exporting entire directory", prefix);

				final ISVNReporterBaton reporterBaton = new ExportReporterBaton(latestRevision, getStatusChangeHelper());
				final SubversionExportEditor exportEditor = new SubversionExportEditor(exportDirectory,
						getStatusChangeHelper());

				repository.update(latestRevision, null, true, reporterBaton, exportEditor);
			}
			else {
				LOGGER.debug("{} files list contains entries, exporting {} individual files", prefix, files.size());

				final File directory = new File(destinationPath);

				for (final FileReference fileReference : files) {
					final String fileName = fileReference.getName();
					final String targetName = fileReference.getTargetName();
					final String targetFileName = StringUtils.isBlank(targetName) ? fileName : targetName;

					LOGGER.debug("{} exporting file {}", prefix, fileName);

					FileOutputStream contents = null;

					try {
						final File destinationFile = new File(directory, targetFileName);

						if (destinationFile.exists()) {
							LOGGER.debug("{} destination file {} exists, deleting", prefix, targetFileName);
						}

						contents = new FileOutputStream(destinationFile);
						repository.getFile(fileName, latestRevision, null, contents);
						contents.close();

						getStatusChangeHelper().info(this, "Added file \"" + fileName + "\"");
					}
					catch (final FileNotFoundException e) {
						LOGGER.error("FileNotFoundException caught exporting a file", e);

						throw new RuntimeException(e);
					}
					catch (final IOException e) {
						LOGGER.error("IOException caught exporting a file", e);

						throw new RuntimeException(e);
					}
					finally {
						if (contents != null) {
							try {
								contents.close();
							}
							catch (final Exception e) {
								LOGGER.error("Exception caught closing the destination file", e);
							}
						}
					}
				}
			}

			LOGGER.debug("{} finished exporting files", prefix);

			getStatusChangeHelper().important(this, "Finished exporting files. revision=" + latestRevision);
		}
		catch (final SVNException e) {
			LOGGER.error("SVNException caught exporting a file", e);
			getStatusChangeHelper().error(this, "Error occurred exporting files");
			throw new RuntimeException(e); // TODO Use appropriate exception
		}
	}
}
