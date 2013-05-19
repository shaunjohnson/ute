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

import net.lmxm.ute.TestJob;
import net.lmxm.ute.beans.IdentifiableBean;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * The Class IdChangeEventTest.
 */
public class IdChangeEventTest {

	/**
	 * Test get identifiable bean.
	 */
	@Test
	public void testGetIdentifiableBean() {
		final IdentifiableBean bean = new TestJob();

		final IdChangeEvent changeEvent = new IdChangeEvent(bean);

		assertEquals(bean, changeEvent.getIdentifiableBean());
	}

	/**
	 * Test id change event null identifiable bean.
	 */
	@Test(expected = NullPointerException.class)
	public void testIdChangeEventNullIdentifiableBean() {
		new IdChangeEvent(null);
	}
}
