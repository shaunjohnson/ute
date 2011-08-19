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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.lmxm.ute.TestStatusChangeListener;
import net.lmxm.ute.TestTask;
import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.executors.Executor;

import org.junit.Test;

/**
 * The Class TaskExecutorFactoryTest.
 */
public class TaskExecutorFactoryTest {

	/**
	 * Test create.
	 */
	@Test
	public void testCreate() {
		// Null task, properties holder and listener
		try {
			TaskExecutorFactory.create(null, null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null properties holder and listener
		try {
			TaskExecutorFactory.create(new TestTask(), null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null listener
		try {
			TaskExecutorFactory.create(new TestTask(), new Configuration(), null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null task
		try {
			TaskExecutorFactory.create(null, new Configuration(), new TestStatusChangeListener());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Unsupported task type
		try {
			TaskExecutorFactory.create(new TestTask(), new Configuration(), new TestStatusChangeListener());
			fail();
		}
		catch (final IllegalArgumentException e) {
			assertNotNull(e.getMessage());
		}

		// Test file system delete task
		final Executor fileSystemDeleteTaskExecutor = TaskExecutorFactory.create(new FileSystemDeleteTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(fileSystemDeleteTaskExecutor);
		assertTrue(fileSystemDeleteTaskExecutor instanceof FileSystemDeleteTaskExecutor);

		// Test find replace task
		final Executor findReplaceTaskExecutor = TaskExecutorFactory.create(new FindReplaceTask(), new Configuration(),
				new TestStatusChangeListener());

		assertNotNull(findReplaceTaskExecutor);
		assertTrue(findReplaceTaskExecutor instanceof FindReplaceTaskExecutor);

		// Test groovy task
		final Executor groovyTaskExecutor = TaskExecutorFactory.create(new GroovyTask(), new Configuration(),
				new TestStatusChangeListener());

		assertNotNull(groovyTaskExecutor);
		assertTrue(groovyTaskExecutor instanceof GroovyTaskExecutor);

		// Test HTTP download task
		final Executor httpDownloadTaskExecutor = TaskExecutorFactory.create(new HttpDownloadTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(httpDownloadTaskExecutor);
		assertTrue(httpDownloadTaskExecutor instanceof HttpDownloadTaskExecutor);

		// Test Subversion export task
		final Executor subversionExportTaskExecutor = TaskExecutorFactory.create(new SubversionExportTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(subversionExportTaskExecutor);
		assertTrue(subversionExportTaskExecutor instanceof SubversionExportTaskExecutor);

		// Test Subversion update task
		final Executor subversionUpdateTaskExecutor = TaskExecutorFactory.create(new SubversionUpdateTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(subversionUpdateTaskExecutor);
		assertTrue(subversionUpdateTaskExecutor instanceof SubversionUpdateTaskExecutor);
	}
}
