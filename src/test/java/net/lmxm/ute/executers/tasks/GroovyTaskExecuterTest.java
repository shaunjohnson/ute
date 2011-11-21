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

import net.lmxm.ute.TestJob;
import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.event.StatusChangeHelper;

import org.junit.Test;

/**
 * The Class GroovyTaskExecuterTest.
 */
public class GroovyTaskExecuterTest {

	/** The Constant HELLO_WORLD. */
	private static final String HELLO_WORLD = "println 'Hello World!'";

	/** The Constant PROPERTIES_HOLDER. */
	private static final PropertiesHolder PROPERTIES_HOLDER = new Configuration();

	/** The Constant STATUS_CHANGE_HELPER. */
	private static final StatusChangeHelper STATUS_CHANGE_HELPER = new StatusChangeHelper();

	/** The Constant EXECUTER. */
	private static final GroovyTaskExecuter TEST_EXECUTER = new GroovyTaskExecuter(new GroovyTask(new TestJob()),
			new Configuration(), STATUS_CHANGE_HELPER);

	/** The Constant TMP_DIR. */
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

	/**
	 * Test execute script.
	 */
	@Test
	public void testExecuteScript() {
		TEST_EXECUTER.executeScript(HELLO_WORLD, TMP_DIR, null, PROPERTIES_HOLDER);
	}

	/**
	 * Test execute script compilation failure.
	 */
	@Test(expected = RuntimeException.class)
	public void testExecuteScriptCompilationFailure() {
		TEST_EXECUTER.executeScript("this will not compile", TMP_DIR, null, PROPERTIES_HOLDER);
	}

	/**
	 * Test execute script execution failure.
	 */
	@Test(expected = RuntimeException.class)
	public void testExecuteScriptExecutionFailure() {
		TEST_EXECUTER.executeScript("throw new IllegalArgumentException()", TMP_DIR, null, PROPERTIES_HOLDER);
	}
}
