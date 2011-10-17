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
package net.lmxm.ute.console;

import net.lmxm.ute.listeners.JobStatusListener;

/**
 * The listener interface for receiving consoleJobStatus events. The class that is interested in processing a
 * consoleJobStatus event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addConsoleJobStatusListener<code> method. When
 * the consoleJobStatus event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ConsoleJobStatusEvent
 */
public final class ConsoleJobStatusListener implements JobStatusListener {

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobAborted()
	 */
	@Override
	public void jobAborted() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobCompleted()
	 */
	@Override
	public void jobCompleted() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobStarted()
	 */
	@Override
	public void jobStarted() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobStopped()
	 */
	@Override
	public void jobStopped() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskCompleted()
	 */
	@Override
	public void jobTaskCompleted() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskSkipped()
	 */
	@Override
	public void jobTaskSkipped() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskStarted()
	 */
	@Override
	public void jobTaskStarted() {

	}
}
