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
package net.lmxm.ute.utils;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.codehaus.groovy.control.CompilationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GroovyUtils.
 */
public final class GroovyUtils {

	/** The Constant INSTANCE. */
	private static final GroovyUtils INSTANCE = new GroovyUtils();

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GroovyUtils.class);

	/**
	 * Gets the single instance of GroovyUtils.
	 * 
	 * @return single instance of GroovyUtils
	 */
	public static final GroovyUtils getInstance() {
		return INSTANCE;
	}

	/**
	 * Instantiates a new groovy utils.
	 */
	private GroovyUtils() {
		super();
	}

	/**
	 * Execute script.
	 * 
	 * @param script the script
	 * @param path the path
	 * @param files the files
	 * @param statusChangeListener the status change listener
	 */
	public void executeScript(final String script, final String path, final List<FileReference> files,
			final StatusChangeListener statusChangeListener) {
		final String prefix = "executeScript() :";

		LOGGER.debug("{} entered", prefix);

		final Binding binding = new Binding();
		binding.setVariable("path", path);
		binding.setVariable("files", FileSystemUtils.convertToFileObjects(path, files));

		statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO,
				"Starting execution of script"));

		try {
			final Object returnValue = new GroovyShell(binding).evaluate(script);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.INFO,
					"Script execution completed; Script returned \"" + returnValue + "\""));
		}
		catch (final CompilationFailedException e) {
			LOGGER.error(prefix + " Script compilation failed", e);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.ERROR,
					"Script compilation failed"));

			throw new RuntimeException("Script compilation failed");
		}
		catch (final Exception e) {
			LOGGER.error(prefix + " Script execution failed", e);

			statusChangeListener.statusChange(new StatusChangeEvent(this, StatusChangeEventType.ERROR,
					"Script execution failed"));

			throw new RuntimeException("Script execution failed");
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
