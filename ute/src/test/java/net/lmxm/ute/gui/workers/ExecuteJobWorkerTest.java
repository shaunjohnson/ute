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
package net.lmxm.ute.gui.workers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import net.lmxm.ute.TestJob;
import net.lmxm.ute.TestJobStatusListener;
import net.lmxm.ute.TestStatusChangeListener;

import org.junit.Test;

/**
 * The Class ExecuteJobWorkerTest.
 */
public class ExecuteJobWorkerTest {

	/**
	 * Test do in background.
	 */
	@Test
	public void testDoInBackground() {
		new ExecuteJobWorker(new TestJob(), new TestJobStatusListener(), new TestStatusChangeListener())
				.doInBackground();
	}

	/**
	 * Test execute job worker.
	 */
	@Test
	public void testExecuteJobWorker() {
		// Null job, job listener and status listener
		try {
			new ExecuteJobWorker(null, null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null job listener and status listener
		try {
			new ExecuteJobWorker(new TestJob(), null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null status listener
		try {
			new ExecuteJobWorker(new TestJob(), new TestJobStatusListener(), null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null job
		try {
			new ExecuteJobWorker(null, new TestJobStatusListener(), new TestStatusChangeListener());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Non-null job, job listener and status listener
		new ExecuteJobWorker(new TestJob(), new TestJobStatusListener(), new TestStatusChangeListener());
	}

}
