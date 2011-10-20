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
package net.lmxm.ute.beans.tasks;

import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.targets.FileSystemTarget;

/**
 * The Class HttpDownloadTask.
 */
public class HttpDownloadTask extends AbstractFilesTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7304138948723618917L;

	/** The source. */
	private HttpSource source;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Gets the source.
	 * 
	 * @return the source
	 */
	public HttpSource getSource() {
		return source;
	}

	/**
	 * Gets the target.
	 * 
	 * @return the target
	 */
	public FileSystemTarget getTarget() {
		return target;
	}

	/**
	 * Sets the source.
	 * 
	 * @param source the new source
	 */
	public void setSource(final HttpSource source) {
		this.source = source;
	}

	/**
	 * Sets the target.
	 * 
	 * @param target the new target
	 */
	public void setTarget(final FileSystemTarget target) {
		this.target = target;
	}
}
