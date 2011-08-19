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
package net.lmxm.ute.executors.tasks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.utils.testimpl.TestStatusChangeListener;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class FileSystemDeleteTaskExecutorTest.
 */
public class FileSystemDeleteTaskExecutorTest {
	/** The Constant STATUS_CHANGE_LISTENER. */
	private static final TestStatusChangeListener STATUS_CHANGE_LISTENER = new TestStatusChangeListener();

	/** The Constant STOP_ON_ERROR. */
	private static final boolean STOP_ON_ERROR = false;

	/** The Constant TMP_DIR. */
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

	/** The executor. */
	private FileSystemDeleteTaskExecutor executor = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		final FileSystemDeleteTask task = new FileSystemDeleteTask();

		executor = new FileSystemDeleteTaskExecutor(task, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test delete files blank path.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFilesBlankPath() {
		executor.deleteFiles("    ", null, STOP_ON_ERROR);
	}

	/**
	 * Test delete files directory contents.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testDeleteFilesDirectoryContents() throws IOException {
		final File directory = new File(TMP_DIR, "TESTDIRECTORY");
		directory.deleteOnExit();
		directory.mkdir();

		final File file = new File(directory, "UTE.TEST");
		file.deleteOnExit();
		FileUtils.touch(file);

		assertTrue(file.exists());

		final FileReference fileReference = new FileReference();
		fileReference.setName("UTE.TEST");

		final List<FileReference> files = new ArrayList<FileReference>();
		files.add(fileReference);

		executor.deleteFiles(directory.getAbsolutePath(), files, STOP_ON_ERROR);

		assertFalse(file.exists());
	}

	/**
	 * Test delete files file does not exist.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void testDeleteFilesFileDoesNotExist() throws IOException {
		final File file = new File(TMP_DIR, "TESTFILE.TEST");
		file.deleteOnExit();

		assertFalse(file.exists());

		executor.deleteFiles(file.getAbsolutePath(), null, STOP_ON_ERROR);

		assertFalse(file.exists());
	}

	/**
	 * Test delete files.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFilesNullPath() {
		executor.deleteFiles(null, null, STOP_ON_ERROR);
	}

	/**
	 * Test delete files single directory.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testDeleteFilesSingleDirectory() throws IOException {
		final File directory = new File(TMP_DIR, "TESTDIRECTORY");
		directory.deleteOnExit();
		directory.mkdir();

		assertTrue(directory.exists());

		executor.deleteFiles(directory.getAbsolutePath(), null, STOP_ON_ERROR);

		assertFalse(directory.exists());
	}

	/**
	 * Test delete files single file.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testDeleteFilesSingleFile() throws IOException {
		final File file = File.createTempFile("UTE", ".TEST");
		file.deleteOnExit();
		FileUtils.touch(file);

		assertTrue(file.exists());

		executor.deleteFiles(file.getAbsolutePath(), null, STOP_ON_ERROR);

		assertFalse(file.exists());
	}
}
