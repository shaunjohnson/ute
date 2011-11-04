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

import java.util.List;

import net.lmxm.ute.beans.DomainBean;

/**
 * The Class DomainBeanUtils.
 */
public class DomainBeanUtils {

	/**
	 * Checks if is empty.
	 * 
	 * @param domainBean the domain bean
	 * @return true, if is empty
	 */
	public static boolean isEmpty(final DomainBean domainBean) {
		if (domainBean == null) {
			return true;
		}
		else {
			return domainBean.isEmpty();
		}
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param domainBeans the domain beans
	 * @return true, if is empty
	 */
	public static boolean isEmpty(final List<? extends DomainBean> domainBeans) {
		if (domainBeans == null) {
			return true;
		}

		for (final DomainBean domainBean : domainBeans) {
			if (!domainBean.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Instantiates a new domain bean utils.
	 */
	private DomainBeanUtils() {
		throw new AssertionError();
	}
}
