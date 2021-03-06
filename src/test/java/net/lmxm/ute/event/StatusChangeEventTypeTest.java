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

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The Class StatusChangeEventTypeTest.
 */
public class StatusChangeEventTypeTest {

	/**
	 * Test is error type.
	 */
	@Test
	public void testIsErrorType() {
		// Error types
		assertTrue(StatusChangeEventType.ERROR.isErrorType());
		assertTrue(StatusChangeEventType.FATAL.isErrorType());

		// Info types
		assertFalse(StatusChangeEventType.HEADING.isErrorType());
		assertFalse(StatusChangeEventType.IMPORTANT.isErrorType());
		assertFalse(StatusChangeEventType.INFO.isErrorType());
	}
}
