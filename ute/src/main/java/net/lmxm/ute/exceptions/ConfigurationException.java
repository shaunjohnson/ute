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

/**
 * The Class ConfigurationException.
 */
public final class ConfigurationException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2160059616539742552L;

	/**
	 * Instantiates a new configuration exception.
	 */
	public ConfigurationException() {
		super();
	}

	/**
	 * Instantiates a new configuration exception.
	 *
	 * @param message the message
	 */
	public ConfigurationException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new configuration exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ConfigurationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new configuration exception.
	 *
	 * @param cause the cause
	 */
	public ConfigurationException(final Throwable cause) {
		super(cause);
	}
}
