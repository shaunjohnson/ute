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
package net.lmxm.ute;

import static junit.framework.Assert.assertTrue;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class UteTestAssert.
 */
public final class UteTestAssert {

	/**
	 * Assert empty.
	 * 
	 * @param <E> the element type
	 * @param collection the collection
	 */
	public static <E> void assertEmpty(final Collection<E> collection) {
		assertEmpty(null, collection);
	}

	/**
	 * Assert empty.
	 * 
	 * @param <E> the element type
	 * @param message the message
	 * @param collection the collection
	 */
	public static <E> void assertEmpty(final String message, final Collection<E> collection) {
		assertTrue(message, collection.isEmpty());
	}

	/**
	 * Assert not blank.
	 * 
	 * @param value the value
	 */
	public static void assertNotBlank(final String value) {
		assertNotBlank(null, value);
	}

	/**
	 * Assert not blank.
	 * 
	 * @param Message the message
	 * @param value the value
	 */
	public static void assertNotBlank(final String Message, final String value) {
		assertTrue(StringUtils.isNotBlank(value));
	}

	/**
	 * Assert not empty.
	 * 
	 * @param <E> the element type
	 * @param collection the collection
	 */
	public static <E> void assertNotEmpty(final Collection<E> collection) {
		assertNotEmpty(null, collection);
	}

	/**
	 * Assert not empty.
	 * 
	 * @param <E> the element type
	 * @param message the message
	 * @param collection the collection
	 */
	public static <E> void assertNotEmpty(final String message, final Collection<E> collection) {
		assertTrue(message, !collection.isEmpty());
	}

	/**
	 * Instantiates a new ute test assert.
	 */
	private UteTestAssert() {
		throw new AssertionError();
	}
}
