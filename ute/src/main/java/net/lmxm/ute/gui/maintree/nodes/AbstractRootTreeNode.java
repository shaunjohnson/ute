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
package net.lmxm.ute.gui.maintree.nodes;

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.configuration.ConfigurationHolder;

/**
 * The Class AbstractRootTreeNode.
 */
public abstract class AbstractRootTreeNode implements RootTreeNode {

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/**
	 * Instantiates a new abstract root tree node.
	 * 
	 * @param configurationHolder the configuration holder
	 */
	public AbstractRootTreeNode(final ConfigurationHolder configurationHolder) {
		super();

		this.configurationHolder = configurationHolder;
	}

	/**
	 * Convert to string.
	 * 
	 * @param configuration the configuration
	 * @return the string
	 */
	protected abstract String convertToString(Configuration configuration);

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return convertToString(configurationHolder.getConfiguration());
	}
}
