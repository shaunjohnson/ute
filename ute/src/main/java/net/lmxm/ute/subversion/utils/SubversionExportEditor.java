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
import java.io.IOException;
import java.io.OutputStream;

import net.lmxm.ute.listeners.StatusChangeHelper;
import net.lmxm.ute.resources.StatusChangeMessage;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.diff.SVNDeltaProcessor;
import org.tmatesoft.svn.core.io.diff.SVNDiffWindow;

/**
 * The Class SubversionExportEditor.
 */
public class SubversionExportEditor implements ISVNEditor {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionExportEditor.class);

	/** The my delta processor. */
	private final SVNDeltaProcessor deltaProcessor;

	/** the local directory where the node tree is to be exported into. */
	private final File root;

	/** The status change helper. */
	private final StatusChangeHelper statusChangeHelper;

	/**
	 * Instantiates a new subversion export editor.
	 * 
	 * @param root the root
	 * @param statusChangeHelper the status change helper
	 */
	public SubversionExportEditor(final File root, final StatusChangeHelper statusChangeHelper) {
		super();

		this.root = root;
		this.deltaProcessor = new SVNDeltaProcessor();
		this.statusChangeHelper = statusChangeHelper;
	}

	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#abortEdit()
	 */
	@Override
	public void abortEdit() throws SVNException {

	}

	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#absentDir(java.lang.String)
	 */
	@Override
	public void absentDir(final String path) throws SVNException {

	}

	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#absentFile(java.lang.String)
	 */
	@Override
	public void absentFile(final String path) throws SVNException {

	}

	/**
	 * Called when a new directory has to be added.
	 * 
	 * For each 'addDir' call server will call 'closeDir' method after all children of the added directory are added.
	 * 
	 * This implementation creates corresponding directory below root directory.
	 * 
	 * @param path the path
	 * @param copyFromPath the copy from path
	 * @param copyFromRevision the copy from revision
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public void addDir(final String path, final String copyFromPath, final long copyFromRevision) throws SVNException {
		final String prefix = "addDir() :";

		LOGGER.debug("{} entered", prefix);

		final File newDirectory = new File(root, path);

		if (newDirectory.exists()) {
			LOGGER.debug("{} directory already exists", prefix);
		}
		else {
			if (newDirectory.mkdirs()) {
				LOGGER.debug("{} successfully created directory", prefix);
			}
			else {
				LOGGER.error("{} unable to create directory", prefix);

				final SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.IO_ERROR,
						"error: failed to add the directory ''{0}''.", newDirectory);
				throw new SVNException(err);
			}
		}

		statusChangeHelper.info(this, StatusChangeMessage.SUBVERSION_EXPORT_DIRECTORY_ADDED, path);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Called when a new file has to be created.
	 * 
	 * For each 'addFile' call server will call 'closeFile' method after sending file properties and contents.
	 * 
	 * This implementation creates empty file below root directory, file contents will be updated later, and for empty
	 * files may not be sent at all.
	 * 
	 * @param path the path
	 * @param copyFromPath the copy from path
	 * @param copyFromRevision the copy from revision
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public void addFile(final String path, final String copyFromPath, final long copyFromRevision) throws SVNException {
		final String prefix = "addFile() :";

		LOGGER.debug("{} entered", prefix);

		final File file = new File(root, path);

		if (file.exists()) {
			LOGGER.debug("{} file {} already exists, deleting it before continuing", prefix, file.getAbsolutePath());

			FileUtils.deleteQuietly(file);
		}

		try {
			if (file.createNewFile()) {
				LOGGER.debug("{} created new file", prefix);
			}
			else {
				LOGGER.error("{} unable to create file {}", prefix, file.getAbsolutePath());

				final SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.IO_ERROR,
						"error: cannot create new  file ''{0}''", file);
				throw new SVNException(err);
			}
		}
		catch (final IOException e) {
			LOGGER.error("{} unable to create file {}", prefix, file.getAbsolutePath());

			final SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.IO_ERROR,
					"error: cannot create new  file ''{0}''", file);
			throw new SVNException(err, e);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/*
	 * Instructs to change opened or added directory property. This method is called to update properties set by the
	 * user as well as those created automatically, like "svn:committed-rev". See SVNProperty class for default property
	 * names. When property has to be deleted value will be 'null'.
	 */

	/**
	 * Called before sending 'delta' for a file. Delta may include instructions on how to create a file or how to modify
	 * existing file. In this example delta will always contain instructions on how to create a new file and so we set
	 * up deltaProcessor with 'null' base file and target file to which we would like to store the result of delta
	 * application.
	 * 
	 * @param path the path
	 * @param baseChecksum the base checksum
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public void applyTextDelta(final String path, final String baseChecksum) throws SVNException {
		deltaProcessor.applyTextDelta((File) null, new File(root, path), false);
	}

	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#changeDirProperty(java.lang. String,
	 * org.tmatesoft.svn.core.SVNPropertyValue)
	 */
	@Override
	public void changeDirProperty(final String name, final SVNPropertyValue property) throws SVNException {

	}

	/**
	 * Instructs to add, modify or delete file property. In this example we skip this instruction, but 'real' export
	 * operation may inspect 'svn:eol-style' or 'svn:mime-type' property values to transfor file contents propertly
	 * after receiving.
	 * 
	 * @param path the path
	 * @param name the name
	 * @param property the property
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public void changeFileProperty(final String path, final String name, final SVNPropertyValue property)
			throws SVNException {

	}

	/**
	 * Called when all child files and directories are processed. This call always matches addDir, openDir or openRoot
	 * call.
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public void closeDir() throws SVNException {

	}

	/**
	 * Called when update is completed.
	 * 
	 * @return the SVN commit info
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public SVNCommitInfo closeEdit() throws SVNException {
		return null;
	}

	/**
	 * Called when file update is completed. This call always matches addFile or openFile call.
	 * 
	 * @param path the path
	 * @param textChecksum the text checksum
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public void closeFile(final String path, final String textChecksum) throws SVNException {
		statusChangeHelper.info(this, StatusChangeMessage.SUBVERSION_EXPORT_FILE_ADDED, path);
	}

	/**
	 * Instructs to delete an entry in the 'working copy'. Of course will not be called during export operation.
	 * 
	 * @param path the path
	 * @param revision the revision
	 * 
	 * @throws SVNException the SVN exception
	 */
	@Override
	public void deleteEntry(final String path, final long revision) throws SVNException {

	}

	/*
	 * Called when there is an existing directory that has to be 'opened' either to modify this directory properties or
	 * to process other files and directories inside this directory. In case of export this method will never be called
	 * because we reported that our 'working copy' is empty and so server knows that there are no 'existing'
	 * directories.
	 */
	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#openDir(java.lang.String, long)
	 */
	@Override
	public void openDir(final String path, final long revision) throws SVNException {

	}

	/*
	 * Called when there is an existing files that has to be 'opened' either to modify file contents or properties. In
	 * case of export this method will never be called because we reported that our 'working copy' is empty and so
	 * server knows that there are no 'existing' files.
	 */
	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#openFile(java.lang.String, long)
	 */
	@Override
	public void openFile(final String path, final long revision) throws SVNException {

	}

	/*
	 * Called before sending other instructions.
	 */
	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#openRoot(long)
	 */
	@Override
	public void openRoot(final long revision) throws SVNException {

	}

	/*
	 * Server reports revision to which application of the further instructions will update working copy to.
	 */
	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNEditor#targetRevision(long)
	 */
	@Override
	public void targetRevision(final long revision) throws SVNException {

	}

	/*
	 * Server sends deltas in form of 'diff windows'. Depending on the file size there may be several diff windows.
	 * Utility class SVNDeltaProcessor processes these windows for us.
	 */
	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNDeltaConsumer#textDeltaChunk(java.lang .String,
	 * org.tmatesoft.svn.core.io.diff.SVNDiffWindow)
	 */
	@Override
	public OutputStream textDeltaChunk(final String path, final SVNDiffWindow diffWindow) throws SVNException {
		return deltaProcessor.textDeltaChunk(diffWindow);
	}

	/*
	 * Called when all diff windows (delta) is transferred.
	 */
	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.io.ISVNDeltaConsumer#textDeltaEnd(java.lang .String)
	 */
	@Override
	public void textDeltaEnd(final String path) throws SVNException {
		deltaProcessor.textDeltaEnd();
	}
}
