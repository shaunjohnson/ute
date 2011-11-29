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
		final StatusChangeEvent changeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(changeEvent.getEventType());
		assertEquals(StatusChangeEventType.ERROR, changeEvent.getEventType());
	}

	/**
	 * Test get message.
	 */
	@Test
	public void testGetMessage() {
		final StatusChangeEvent changeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(changeEvent.getMessage());
		assertEquals("Test", changeEvent.getMessage());
	}

	/**
	 * Test get source.
	 */
	@Test
	public void testGetSource() {
		final StatusChangeEvent changeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(changeEvent.getSource());
		assertSame(this, changeEvent.getSource());
	}

	/**
	 * Test status change event empty message.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStatusChangeEventEmptyMessage() {
		new StatusChangeEvent(this, StatusChangeEventType.ERROR, "");
	}

	/**
	 * Test status change event null event type.
	 */
	@Test(expected = NullPointerException.class)
	public void testStatusChangeEventNullEventType() {
		new StatusChangeEvent(this, null, "Test");
	}

	/**
	 * Test status change event null message.
	 */
	@Test(expected = NullPointerException.class)
	public void testStatusChangeEventNullMessage() {
		new StatusChangeEvent(this, StatusChangeEventType.ERROR, null);
	}

	/**
	 * Test status change event null source.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStatusChangeEventNullSource() {
		new StatusChangeEvent(null, StatusChangeEventType.ERROR, "Test");
	}

	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		final StatusChangeEvent changeEvent = new StatusChangeEvent(this, StatusChangeEventType.ERROR, "Test");

		assertNotNull(changeEvent.toString());
		assertTrue(changeEvent.toString().contains(StatusChangeEventType.ERROR.toString()));
		assertTrue(changeEvent.toString().contains("Test"));
	}
}
