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
package net.lmxm.ute.listeners;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * The Class StatusChangeEventTest.
 */
public class StatusChangeEventTest {

	/**
	 * Test get event type.
	 */
	@Test
	public void testGetEventType() {
		final StatusChangeEvent statusChangeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(statusChangeEvent.getEventType());
		assertEquals(StatusChangeEventType.ERROR, statusChangeEvent.getEventType());
	}

	/**
	 * Test get message.
	 */
	@Test
	public void testGetMessage() {
		final StatusChangeEvent statusChangeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(statusChangeEvent.getMessage());
		assertEquals("Test", statusChangeEvent.getMessage());
	}

	/**
	 * Test get source.
	 */
	@Test
	public void testGetSource() {
		final StatusChangeEvent statusChangeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(statusChangeEvent.getSource());
		assertSame(this, statusChangeEvent.getSource());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStatusChangeEventEmptyMessage() {
		new StatusChangeEvent(this, StatusChangeEventType.ERROR, "");
	}

	@Test(expected = NullPointerException.class)
	public void testStatusChangeEventNullEventType() {
		new StatusChangeEvent(this, null, "Test");
	}

	@Test(expected = NullPointerException.class)
	public void testStatusChangeEventNullMessage() {
		new StatusChangeEvent(this, StatusChangeEventType.ERROR, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStatusChangeEventNullSource() {
		new StatusChangeEvent(null, StatusChangeEventType.ERROR, "Test");
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		final StatusChangeEvent statusChangeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(statusChangeEvent.toString());
		assertTrue(statusChangeEvent.toString().contains(StatusChangeEventType.ERROR.toString()));
		assertTrue(statusChangeEvent.toString().contains("Test"));
	}
}
