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
package net.lmxm.ute.event;

/**
 * The Enum StatusChangeEventType.
 */
public enum StatusChangeEventType {
	ERROR(StatusChangeEventTypeCategory.ERROR),

	FATAL(StatusChangeEventTypeCategory.ERROR),

	HEADING(StatusChangeEventTypeCategory.INFO),

	IMPORTANT(StatusChangeEventTypeCategory.INFO),

	INFO(StatusChangeEventTypeCategory.INFO);

	/**
	 * The Enum StatusChangeEventTypeCategory.
	 */
	private enum StatusChangeEventTypeCategory {
		ERROR,

		INFO;
	}

	/** The category. */
	private StatusChangeEventTypeCategory category;

	/**
	 * Instantiates a new status change event type.
	 * 
	 * @param category the category
	 */
	StatusChangeEventType(final StatusChangeEventTypeCategory category) {
		this.category = category;
	}

	/**
	 * Checks if is error type.
	 * 
	 * @return true, if is error type
	 */
	public boolean isErrorType() {
		return category == StatusChangeEventTypeCategory.ERROR;
	}
}
