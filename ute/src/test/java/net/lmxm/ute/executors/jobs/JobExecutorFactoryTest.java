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
package net.lmxm.ute.executors.jobs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import net.lmxm.ute.TestJob;
import net.lmxm.ute.TestJobStatusListener;
import net.lmxm.ute.TestStatusChangeListener;
import net.lmxm.ute.beans.Configuration;

import org.junit.Test;

/**
 * The Class JobExecutorFactoryTest.
 */
public class JobExecutorFactoryTest {

	/**
	 * Test create.
	 */
	@Test
	public void testCreate() {
		// Null job, properties holder, job listener and status listener
		try {
			JobExecutorFactory.create(null, null, null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null properties holder, job listener and status listener
		try {
			JobExecutorFactory.create(new TestJob(), null, null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null job listener and status listener
		try {
			JobExecutorFactory.create(new TestJob(), new Configuration(), null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null status listener
		try {
			JobExecutorFactory.create(new TestJob(), new Configuration(), new TestJobStatusListener(), null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null job
		try {
			JobExecutorFactory.create(null, new Configuration(), new TestJobStatusListener(),
					new TestStatusChangeListener());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Non-null job, properties holder, job listener and status listener
		JobExecutorFactory.create(new TestJob(), new Configuration(), new TestJobStatusListener(),
				new TestStatusChangeListener());
	}
}
