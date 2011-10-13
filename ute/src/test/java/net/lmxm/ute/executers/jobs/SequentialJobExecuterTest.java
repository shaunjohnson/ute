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
package net.lmxm.ute.executers.jobs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import net.lmxm.ute.TestJobStatusListener;
import net.lmxm.ute.TestStatusChangeListener;
import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.jobs.SequentialJob;

import org.junit.Test;

/**
 * The Class SequentialJobExecuterTest.
 */
public class SequentialJobExecuterTest {

	/**
	 * Test execute.
	 */
	@Test
	public void testExecute() {
		new SequentialJobExecuter(new SequentialJob(), new Configuration(), new TestJobStatusListener(),
				new TestStatusChangeListener()).execute();
	}

	/**
	 * Test job executer.
	 */
	@Test
	public void testJobExecuter() {
		// Null job, properties holder, job listener and status listener
		try {
			new SequentialJobExecuter(null, null, null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null properties holder, job listener and status listener
		try {
			new SequentialJobExecuter(new SequentialJob(), null, null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null properties holder, job listener and status listener
		try {
			new SequentialJobExecuter(new SequentialJob(), new Configuration(), null, null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null status listener
		try {
			new SequentialJobExecuter(new SequentialJob(), new Configuration(), new TestJobStatusListener(), null);
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Null job
		try {
			new SequentialJobExecuter(null, new Configuration(), new TestJobStatusListener(),
					new TestStatusChangeListener());
			fail();
		}
		catch (final NullPointerException e) {
			assertNotNull(e.getMessage());
		}

		// Non-null job, properties holder and listener
		new SequentialJobExecuter(new SequentialJob(), new Configuration(), new TestJobStatusListener(),
				new TestStatusChangeListener());
	}
}
