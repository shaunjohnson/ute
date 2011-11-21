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
package net.lmxm.ute.executers.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lmxm.ute.TestJob;
import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.event.StatusChangeHelper;

import org.junit.Test;

/**
 * The Class HttpDownloadTaskExecuterTest.
 */
public class HttpDownloadTaskExecuterTest {
	/** The Constant EMPTY_FILE_LIST. */
	private static final List<FileReference> EMPTY_FILE_LIST = new ArrayList<FileReference>();
	/** The Constant EXISTING_DIRECTORY. */
	private static final String EXISTING_DIRECTORY = System.getProperty("java.io.tmpdir");

	/** The Constant FILE_LIST. */
	private static final List<FileReference> FILE_LIST = new ArrayList<FileReference>(1);

	/** The Constant QUERY_PARAMS. */
	private static final Map<String, String> QUERY_PARAMS = new HashMap<String, String>();

	/** The Constant STATUS_CHANGE_HELPER. */
	private static final StatusChangeHelper STATUS_CHANGE_HELPER = new StatusChangeHelper();

	/** The Constant TEST_EXECUTER. */
	private static final HttpDownloadTaskExecuter TEST_EXECUTER = new HttpDownloadTaskExecuter(new HttpDownloadTask(
			new TestJob()), STATUS_CHANGE_HELPER);

	/** The Constant URL. */
	private static final String URL = "http://google.com";

	static {
		FILE_LIST.add(new FileReference());
	}

	/**
	 * Test download files empty files.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesEmptyFiles() {
		TEST_EXECUTER.downloadFiles(URL, QUERY_PARAMS, EXISTING_DIRECTORY, EMPTY_FILE_LIST);
	}

	/**
	 * Test download files null destination path.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesNullDestinationPath() {
		TEST_EXECUTER.downloadFiles(URL, QUERY_PARAMS, null, FILE_LIST);
	}

	/**
	 * Test download files null files.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesNullFiles() {
		TEST_EXECUTER.downloadFiles(URL, QUERY_PARAMS, EXISTING_DIRECTORY, null);
	}

	/**
	 * Test download files null url.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDownloadFilesNullUrl() {
		TEST_EXECUTER.downloadFiles(null, QUERY_PARAMS, EXISTING_DIRECTORY, FILE_LIST);
	}
}
