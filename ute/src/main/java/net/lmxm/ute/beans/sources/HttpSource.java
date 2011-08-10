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
package net.lmxm.ute.beans.sources;

import java.util.Map;

import net.lmxm.ute.beans.locations.HttpLocation;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class HttpSource.
 */
public class HttpSource extends AbstractSource {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7812595176947817364L;

	/** The location. */
	private HttpLocation location;

	/** The query params. */
	private Map<String, String> queryParams;

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public HttpLocation getLocation() {
		return location;
	}

	/**
	 * Gets the query params.
	 * 
	 * @return the query params
	 */
	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location the new location
	 */
	public void setLocation(final HttpLocation location) {
		this.location = location;
	}

	/**
	 * Sets the query params.
	 * 
	 * @param queryParams the query params
	 */
	public void setQueryParams(final Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("location", location).toString();
	}
}
