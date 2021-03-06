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

import net.lmxm.ute.TestJob;
import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The Class FileSystemDeleteTaskExecuterTest.
 */
public class FileSystemDeleteTaskExecuterTest {
	/** The Constant STOP_ON_ERROR. */
	private static final boolean STOP_ON_ERROR = false;

	/** The Constant TMP_DIR. */
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

	/** The executer. */
	private FileSystemDeleteTaskExecuter executer = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
        final Job job = new TestJob();
		final FileSystemDeleteTask task = new FileSystemDeleteTask(job);

		executer = new FileSystemDeleteTaskExecuter(job, task);
	}

	/**
	 * Test delete files blank path.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFilesBlankPath() {
		executer.deleteFiles("    ", null, STOP_ON_ERROR);
	}

	/**
	 * Test delete files directory contents.
	 */
	@Test
	public void testDeleteFilesDirectoryContents() {
		try {
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

			executer.deleteFiles(directory.getAbsolutePath(), files, STOP_ON_ERROR);

			assertFalse(file.exists());
		}
		catch (final IOException e) {
			fail();
		}
	}

	/**
	 * Test delete files file does not exist.
	 */
	public void testDeleteFilesFileDoesNotExist() {
		final File file = new File(TMP_DIR, "TESTFILE.TEST");
		file.deleteOnExit();

		assertFalse(file.exists());

		executer.deleteFiles(file.getAbsolutePath(), null, STOP_ON_ERROR);

		assertFalse(file.exists());
	}

	/**
	 * Test delete files.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFilesNullPath() {
		executer.deleteFiles(null, null, STOP_ON_ERROR);
	}

	/**
	 * Test delete files single directory.
	 */
	@Test
	public void testDeleteFilesSingleDirectory() {
		final File directory = new File(TMP_DIR, "TESTDIRECTORY");
		directory.deleteOnExit();
		directory.mkdir();

		assertTrue(directory.exists());

		executer.deleteFiles(directory.getAbsolutePath(), null, STOP_ON_ERROR);

		assertFalse(directory.exists());
	}

	/**
	 * Test delete files single file.
	 */
	@Test
	public void testDeleteFilesSingleFile() {
		try {
			final File file = File.createTempFile("UTE", ".TEST");
			file.deleteOnExit();
			FileUtils.touch(file);

			assertTrue(file.exists());

			executer.deleteFiles(file.getAbsolutePath(), null, STOP_ON_ERROR);

			assertFalse(file.exists());
		}
		catch (final IOException e) {
			fail();
		}
	}
}
