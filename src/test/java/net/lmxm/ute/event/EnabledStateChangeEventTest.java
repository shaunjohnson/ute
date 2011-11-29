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

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import net.lmxm.ute.TestTask;
import net.lmxm.ute.beans.EnabledStateBean;

import org.junit.Test;

/**
 */
public class EnabledStateChangeEventTest {

	@Test(expected = NullPointerException.class)
	public void testEnabledStateChangeEventNullIdentifiableBean() {
		new EnabledStateChangeEvent(this, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnabledStateChangeEventNullSource() {
		new EnabledStateChangeEvent(null, new TestTask());
	}

	@Test
	public void testGetEnabledStateBean() {
		final EnabledStateBean bean = new TestTask();

		final EnabledStateChangeEvent changeEvent = new EnabledStateChangeEvent(this, bean);

		assertNotNull(changeEvent.getSource());
		assertEquals(bean, changeEvent.getEnabledStateBean());
	}

	@Test
	public void testGetSource() {
		final EnabledStateChangeEvent changeEvent = new EnabledStateChangeEvent(this, new TestTask());

		assertNotNull(changeEvent.getSource());
		assertSame(this, changeEvent.getSource());
	}
}
