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
package net.lmxm.ute.beans.tasks;

import net.lmxm.ute.beans.IdentifiableDomainBean;

/**
 * The Class AbstractTask.
 */
public abstract class AbstractTask extends IdentifiableDomainBean implements Task {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7830292768049887798L;

	/** The description. */
	private String description;

	/** The enabled. */
	private boolean enabled;

	/**
	 * Instantiates a new abstract task.
	 */
	public AbstractTask() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#getDescription()
	 */
	@Override
	public final String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#getEnabled()
	 */
	@Override
	public final boolean getEnabled() {
		return enabled;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#setDescription(java.lang.String)
	 */
	@Override
	public final void setDescription(final String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.Task#setEnabled(boolean)
	 */
	@Override
	public final void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}
}
