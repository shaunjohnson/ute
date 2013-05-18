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
package net.lmxm.ute.exceptions;

import net.lmxm.ute.resources.types.ExceptionResourceType;

/**
 * GUI related exception.
 */
public final class GuiException extends AbstractRuntimeException {

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6964127659003282685L;

	/**
	 * Instantiates a new task executer exception.
	 *
	 * @param type the type
	 */
	public GuiException(final ExceptionResourceType type) {
		super(type);
	}

	/**
	 * Instantiates a new task executer exception.
	 *
	 * @param type the type
	 * @param args the args
	 */
	public GuiException(final ExceptionResourceType type, final Object... args) {
		super(type, args);
	}

	/**
	 * Instantiates a new task executer exception.
	 *
	 * @param type the type
	 * @param cause the cause
	 */
	public GuiException(final ExceptionResourceType type, final Throwable cause) {
		super(type, cause);
	}

	/**
	 * Instantiates a new task executer exception.
	 *
	 * @param type the type
	 * @param cause the cause
     * @param args the args
	 */
	public GuiException(final ExceptionResourceType type, final Throwable cause, final Object... args) {
		super(type, cause, args);
	}
}
