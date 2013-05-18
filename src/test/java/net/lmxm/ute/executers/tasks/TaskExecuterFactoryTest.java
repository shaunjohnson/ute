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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.lmxm.ute.TestJob;
import net.lmxm.ute.TestTask;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.exceptions.TaskExecuterException;
import net.lmxm.ute.executers.Executer;

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
		// Null task and properties holder
		try {
			TaskExecuterFactory.create(new TestJob(), null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null properties holder
		try {
			TaskExecuterFactory.create(new TestJob(), new TestTask(), null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null task
		try {
			TaskExecuterFactory.create(new TestJob(), null, new Configuration());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Unsupported task type
		try {
			TaskExecuterFactory.create(new TestJob(), new TestTask(), new Configuration());
			fail();
		}
		catch (final TaskExecuterException e) {
			assertNotNull(e);
		}
		catch (final Exception e) {
			fail();
		}

		// Test file system delete task
		final Executer fileSystemDeleteTaskExecuter = TaskExecuterFactory.create(
                new TestJob(), new FileSystemDeleteTask(new TestJob()), new Configuration());

		assertNotNull(fileSystemDeleteTaskExecuter);
		assertTrue(fileSystemDeleteTaskExecuter instanceof FileSystemDeleteTaskExecuter);

		// Test find replace task
		final Executer findReplaceTaskExecuter = TaskExecuterFactory.create(new TestJob(), new FindReplaceTask(new TestJob()),
				new Configuration());

		assertNotNull(findReplaceTaskExecuter);
		assertTrue(findReplaceTaskExecuter instanceof FindReplaceTaskExecuter);

		// Test groovy task
		final Executer groovyTaskExecuter = TaskExecuterFactory.create(new TestJob(), new GroovyTask(new TestJob()),
				new Configuration());

		assertNotNull(groovyTaskExecuter);
		assertTrue(groovyTaskExecuter instanceof GroovyTaskExecuter);

		// Test HTTP download task
		final Executer httpDownloadTaskExecuter = TaskExecuterFactory.create(new TestJob(), new HttpDownloadTask(new TestJob()),
				new Configuration());

		assertNotNull(httpDownloadTaskExecuter);
		assertTrue(httpDownloadTaskExecuter instanceof HttpDownloadTaskExecuter);

		// Test Subversion export task
		final Executer subversionExportTaskExecuter = TaskExecuterFactory.create(new TestJob(),
                new SubversionExportTask(new TestJob()), new Configuration());

		assertNotNull(subversionExportTaskExecuter);
		assertTrue(subversionExportTaskExecuter instanceof SubversionExportTaskExecuter);

		// Test Subversion update task
		final Executer subversionUpdateTaskExecuter = TaskExecuterFactory.create(new TestJob(),
                new SubversionUpdateTask(new TestJob()), new Configuration());

		assertNotNull(subversionUpdateTaskExecuter);
		assertTrue(subversionUpdateTaskExecuter instanceof SubversionUpdateTaskExecuter);
	}
}
