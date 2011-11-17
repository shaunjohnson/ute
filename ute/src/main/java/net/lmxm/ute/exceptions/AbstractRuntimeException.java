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
package net.lmxm.ute.exceptions;

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ExceptionResourceType;

/**
 * The Class AbstractRuntimeException.
 */
public class AbstractRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5753912678285938064L;

	/**
	 * Instantiates a new abstract runtime exception.
	 * 
	 * @param resourceType the resource type
	 */
	public AbstractRuntimeException(final ExceptionResourceType resourceType) {
		super(ResourcesUtils.getResourceMessage(resourceType));
	}

	/**
	 * Instantiates a new abstract runtime exception.
	 * 
	 * @param resourceType the resource type
	 * @param args the args
	 */
	public AbstractRuntimeException(final ExceptionResourceType resourceType, final Object... args) {
		super(String.format(ResourcesUtils.getResourceMessage(resourceType), args));
	}

	/**
	 * Instantiates a new abstract runtime exception.
	 * 
	 * @param resourceType the resource type
	 * @param cause the cause
	 */
	public AbstractRuntimeException(final ExceptionResourceType resourceType, final Throwable cause) {
		super(ResourcesUtils.getResourceMessage(resourceType), cause);
	}
}
