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
package net.lmxm.ute.beans.locations;

import net.lmxm.ute.beans.IdentifiableDomainBean;
import org.apache.commons.lang3.StringUtils;

/**
 * The Class AbstractHttpLocation.
 */
public abstract class AbstractHttpLocation extends IdentifiableDomainBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6574126284651382086L;

	/** The url. */
	private String url;

	/**
	 * Instantiates a new abstract http location.
	 */
	public AbstractHttpLocation() {
		super();
	}

	/**
	 * Instantiates a new abstract http location.
	 * 
	 * @param id the id
	 */
	public AbstractHttpLocation(final String id) {
		super(id);
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public final String getUrl() {
		return url;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && StringUtils.isBlank(url);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		// Do nothing
	}

	/**
	 * Sets the url.
	 * 
	 * @param url the new url
	 */
	public final void setUrl(final String url) {
		this.url = url;
	}
}
