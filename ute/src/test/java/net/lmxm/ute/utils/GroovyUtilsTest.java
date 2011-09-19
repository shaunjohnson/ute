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
package net.lmxm.ute.utils;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.utils.testimpl.TestStatusChangeListener;

import org.junit.Test;

/**
 * The Class GroovyUtilsTest.
 */
public class GroovyUtilsTest {

	/** The Constant GROOVY_UTILS. */
	private static final GroovyUtils GROOVY_UTILS = GroovyUtils.getInstance();

	/** The Constant HELLO_WORLD. */
	private static final String HELLO_WORLD = "println 'Hello World!'";

	/** The Constant PROPERTIES_HOLDER. */
	private static final PropertiesHolder PROPERTIES_HOLDER = new Configuration();

	/** The Constant STATUS_CHANGE_LISTENER. */
	private static final TestStatusChangeListener STATUS_CHANGE_LISTENER = new TestStatusChangeListener();

	/** The Constant TMP_DIR. */
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

	/**
	 * Test execute script.
	 */
	@Test
	public void testExecuteScript() {
		GROOVY_UTILS.executeScript(HELLO_WORLD, TMP_DIR, null, PROPERTIES_HOLDER, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test execute script compilation failure.
	 */
	@Test(expected = RuntimeException.class)
	public void testExecuteScriptCompilationFailure() {
		GROOVY_UTILS.executeScript("this will not compile", TMP_DIR, null, PROPERTIES_HOLDER, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test execute script execution failure.
	 */
	@Test(expected = RuntimeException.class)
	public void testExecuteScriptExecutionFailure() {
		GROOVY_UTILS.executeScript("throw new IllegalArgumentException()", TMP_DIR, null, PROPERTIES_HOLDER,
				STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test execute script null change listener.
	 */
	@Test(expected = NullPointerException.class)
	public void testExecuteScriptNullChangeListener() {
		GROOVY_UTILS.executeScript(HELLO_WORLD, TMP_DIR, null, PROPERTIES_HOLDER, null);
	}
}
