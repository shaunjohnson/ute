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
import net.lmxm.ute.executors.Executer;

import org.junit.Test;

/**
 * The Class TaskExecuterFactoryTest.
 */
public class TaskExecuterFactoryTest {

	/**
	 * Test create.
	 */
	@Test
	public void testCreate() {
		// Null task, properties holder and listener
		try {
			TaskExecuterFactory.create(null, null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null properties holder and listener
		try {
			TaskExecuterFactory.create(new TestTask(), null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null listener
		try {
			TaskExecuterFactory.create(new TestTask(), new Configuration(), null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null task
		try {
			TaskExecuterFactory.create(null, new Configuration(), new TestStatusChangeListener());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Unsupported task type
		try {
			TaskExecuterFactory.create(new TestTask(), new Configuration(), new TestStatusChangeListener());
			fail();
		}
		catch (final IllegalArgumentException e) {
			assertNotNull(e.getMessage());
		}

		// Test file system delete task
		final Executer fileSystemDeleteTaskExecuter = TaskExecuterFactory.create(new FileSystemDeleteTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(fileSystemDeleteTaskExecuter);
		assertTrue(fileSystemDeleteTaskExecuter instanceof FileSystemDeleteTaskExecuter);

		// Test find replace task
		final Executer findReplaceTaskExecuter = TaskExecuterFactory.create(new FindReplaceTask(), new Configuration(),
				new TestStatusChangeListener());

		assertNotNull(findReplaceTaskExecuter);
		assertTrue(findReplaceTaskExecuter instanceof FindReplaceTaskExecuter);

		// Test groovy task
		final Executer groovyTaskExecuter = TaskExecuterFactory.create(new GroovyTask(), new Configuration(),
				new TestStatusChangeListener());

		assertNotNull(groovyTaskExecuter);
		assertTrue(groovyTaskExecuter instanceof GroovyTaskExecuter);

		// Test HTTP download task
		final Executer httpDownloadTaskExecuter = TaskExecuterFactory.create(new HttpDownloadTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(httpDownloadTaskExecuter);
		assertTrue(httpDownloadTaskExecuter instanceof HttpDownloadTaskExecuter);

		// Test Subversion export task
		final Executer subversionExportTaskExecuter = TaskExecuterFactory.create(new SubversionExportTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(subversionExportTaskExecuter);
		assertTrue(subversionExportTaskExecuter instanceof SubversionExportTaskExecuter);

		// Test Subversion update task
		final Executer subversionUpdateTaskExecuter = TaskExecuterFactory.create(new SubversionUpdateTask(),
				new Configuration(), new TestStatusChangeListener());

		assertNotNull(subversionUpdateTaskExecuter);
		assertTrue(subversionUpdateTaskExecuter instanceof SubversionUpdateTaskExecuter);
	}
}
