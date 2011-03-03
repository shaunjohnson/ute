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
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
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
		// Null task and listener
		try {
			TaskExecutorFactory.create(null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null listener
		try {
			TaskExecutorFactory.create(new TestTask(), null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null task
		try {
			TaskExecutorFactory.create(null, new TestStatusChangeListener());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Unsupported task type
		try {
			TaskExecutorFactory.create(new TestTask(), new TestStatusChangeListener());
			fail();
		}
		catch (final IllegalArgumentException e) {
			assertNotNull(e.getMessage());
		}

		// Test file system delete task
		final Executor fileSystemDeleteTaskExecutor = TaskExecutorFactory.create(new FileSystemDeleteTask(),
				new TestStatusChangeListener());

		assertNotNull(fileSystemDeleteTaskExecutor);
		assertTrue(fileSystemDeleteTaskExecutor instanceof FileSystemDeleteTaskExecutor);

		// Test Subversion export task
		final Executor subversionExportTaskExecutor = TaskExecutorFactory.create(new SubversionExportTask(),
				new TestStatusChangeListener());

		assertNotNull(subversionExportTaskExecutor);
		assertTrue(subversionExportTaskExecutor instanceof SubversionExportTaskExecutor);

		// Test Subversion update task
		final Executor subversionUpdateTaskExecutor = TaskExecutorFactory.create(new SubversionUpdateTask(),
				new TestStatusChangeListener());

		assertNotNull(subversionUpdateTaskExecutor);
		assertTrue(subversionUpdateTaskExecutor instanceof SubversionUpdateTaskExecutor);
	}
}
