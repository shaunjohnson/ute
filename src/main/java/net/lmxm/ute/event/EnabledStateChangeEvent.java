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
package net.lmxm.ute.event;

import java.util.EventObject;

import net.lmxm.ute.beans.EnabledStateBean;

/**
 * The Class EnabledStateChangeEvent.
 */
public class EnabledStateChangeEvent extends EventObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8885994939324423499L;

	/** The enabled state bean. */
	private final EnabledStateBean enabledStateBean;

	/**
	 * Instantiates a new enabled state change event.
	 * 
	 * @param source the source
	 * @param enabledStateBean the enabled state bean
	 */
	public EnabledStateChangeEvent(final Object source, final EnabledStateBean enabledStateBean) {
		super(source);

		this.enabledStateBean = enabledStateBean;
	}

	/**
	 * Gets the enabled state bean.
	 * 
	 * @return the enabled state bean
	 */
	public EnabledStateBean getEnabledStateBean() {
		return enabledStateBean;
	}

}
