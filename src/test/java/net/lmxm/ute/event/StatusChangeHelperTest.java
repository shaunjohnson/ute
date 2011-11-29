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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import net.lmxm.ute.resources.types.StatusChangeMessageResourceType;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class StatusChangeHelperTest.
 */
public class StatusChangeHelperTest {

	/**
	 * The Class StatusChangeCollector.
	 */
	private static class StatusChangeCollector implements StatusChangeListener {

		/** The change event. */
		private StatusChangeEvent changeEvent;

		/**
		 * Gets the change event.
		 * 
		 * @return the change event
		 */
		public StatusChangeEvent getChangeEvent() {
			return changeEvent;
		}

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.event.StatusChangeListener#statusChange(net.lmxm.ute.event.StatusChangeEvent)
		 */
		@Override
		public void statusChange(final StatusChangeEvent changeEvent) {
			this.changeEvent = changeEvent;
		}
	}

	/** The collector. */
	private StatusChangeCollector collector = null;

	/** The helper. */
	private StatusChangeHelper helper = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		helper = new StatusChangeHelper();
		collector = new StatusChangeCollector();
		helper.addStatusChangeListener(collector);
	}

	/**
	 * Test add status change listener.
	 */
	@Test
	public void testAddStatusChangeListener() {
		helper.addStatusChangeListener(new StatusChangeListener() {
			@Override
			public void statusChange(final StatusChangeEvent changeEvent) {
				// Not implemented
			}
		});
	}

	/**
	 * Test add status change listener null.
	 */
	@Test(expected = NullPointerException.class)
	public void testAddStatusChangeListenerNull() {
		helper.addStatusChangeListener(null);
	}

	/**
	 * Test error.
	 */
	@Test
	public void testError() {
		helper.error(this, StatusChangeMessageResourceType.JOB_FINISHED, "foobar");

		final StatusChangeEvent event = collector.getChangeEvent();
		assertNotNull(event);
		assertEquals(this, event.getSource());
		assertEquals(StatusChangeEventType.ERROR, event.getEventType());
		assertNotNull(event.getMessage());
	}

	/**
	 * Test fatal.
	 */
	@Test
	public void testFatal() {
		helper.fatal(this, StatusChangeMessageResourceType.JOB_FINISHED, "foobar");

		final StatusChangeEvent event = collector.getChangeEvent();
		assertNotNull(event);
		assertEquals(this, event.getSource());
		assertEquals(StatusChangeEventType.FATAL, event.getEventType());
		assertNotNull(event.getMessage());
	}

	/**
	 * Test fire event null event type.
	 */
	@Test(expected = NullPointerException.class)
	public void testFireEventNullEventType() {
		helper.fireEvent(this, null, StatusChangeMessageResourceType.JOB_FINISHED, "foobar");
	}

	/**
	 * Test fire event null resource type.
	 */
	@Test(expected = NullPointerException.class)
	public void testFireEventNullResourceType() {
		helper.fireEvent(this, StatusChangeEventType.ERROR, null, "foobar");
	}

	/**
	 * Test fire event null source.
	 */
	@Test(expected = NullPointerException.class)
	public void testFireEventNullSource() {
		helper.fireEvent(null, StatusChangeEventType.ERROR, StatusChangeMessageResourceType.JOB_FINISHED, "foobar");
	}

	/**
	 * Test heading.
	 */
	@Test
	public void testHeading() {
		helper.heading(this, StatusChangeMessageResourceType.JOB_FINISHED, "foobar");

		final StatusChangeEvent event = collector.getChangeEvent();
		assertNotNull(event);
		assertEquals(this, event.getSource());
		assertEquals(StatusChangeEventType.HEADING, event.getEventType());
		assertNotNull(event.getMessage());
	}

	/**
	 * Test important.
	 */
	@Test
	public void testImportant() {
		helper.important(this, StatusChangeMessageResourceType.JOB_FINISHED, "foobar");

		final StatusChangeEvent event = collector.getChangeEvent();
		assertNotNull(event);
		assertEquals(this, event.getSource());
		assertEquals(StatusChangeEventType.IMPORTANT, event.getEventType());
		assertNotNull(event.getMessage());
	}

	/**
	 * Test info.
	 */
	@Test
	public void testInfo() {
		helper.info(this, StatusChangeMessageResourceType.JOB_FINISHED, "foobar");

		final StatusChangeEvent event = collector.getChangeEvent();
		assertNotNull(event);
		assertEquals(this, event.getSource());
		assertEquals(StatusChangeEventType.INFO, event.getEventType());
		assertNotNull(event.getMessage());
	}
}
