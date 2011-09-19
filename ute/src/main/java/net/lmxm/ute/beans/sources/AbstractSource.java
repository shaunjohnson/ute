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
package net.lmxm.ute.beans.sources;

/**
 * The Class AbstractSource.
 */
public abstract class AbstractSource implements Source {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5365100899115539132L;

	/** The relative path. */
	private String relativePath;

	/**
	 * Gets the relative path.
	 * 
	 * @return the relative path
	 */
	public final String getRelativePath() {
		return relativePath;
	}

	/**
	 * Sets the relative path.
	 * 
	 * @param relativePath the new relative path
	 */
	public final void setRelativePath(final String relativePath) {
		this.relativePath = relativePath;
	}
}
