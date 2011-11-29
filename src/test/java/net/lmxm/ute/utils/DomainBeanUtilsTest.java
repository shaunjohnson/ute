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
package net.lmxm.ute.utils;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static net.lmxm.ute.UteTestAssert.assertEmpty;
import static net.lmxm.ute.UteTestAssert.assertNotEmpty;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.DomainBean;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.locations.FileSystemLocation;

import org.junit.Test;

/**
 * The Class DomainBeanUtilsTest.
 */
public class DomainBeanUtilsTest {

	/**
	 * The Class TestDomainBean.
	 */
	private static class TestDomainBean implements DomainBean {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -236685527245368901L;

		/** The is empty. */
		private final boolean isEmpty;

		/**
		 * Instantiates a new test domain bean.
		 * 
		 * @param isEmpty the is empty
		 */
		public TestDomainBean(final boolean isEmpty) {
			super();

			this.isEmpty = isEmpty;
		}

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.beans.DomainBean#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return isEmpty;
		}

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
		 */
		@Override
		public void removeEmptyObjects() {

		}
	}

	/**
	 * Test is empty domain bean.
	 */
	@Test
	public void testIsEmptyDomainBean() {
		assertTrue(DomainBeanUtils.isEmpty((DomainBean) null));
		assertTrue(DomainBeanUtils.isEmpty(new TestDomainBean(true)));
		assertFalse(DomainBeanUtils.isEmpty(new TestDomainBean(false)));
	}

	/**
	 * Test is empty list of qextends domain bean.
	 */
	@Test
	public void testIsEmptyListOfQextendsDomainBean() {
		assertTrue(DomainBeanUtils.isEmpty((List<? extends DomainBean>) null));

		final List<DomainBean> emptyList = new ArrayList<DomainBean>(0);
		assertTrue(DomainBeanUtils.isEmpty(emptyList));

		final List<DomainBean> listWithEmptyBean = new ArrayList<DomainBean>(1);
		listWithEmptyBean.add(new TestDomainBean(true));
		assertTrue(DomainBeanUtils.isEmpty(listWithEmptyBean));

		final List<DomainBean> listWithNonEmptyBean = new ArrayList<DomainBean>(1);
		listWithNonEmptyBean.add(new TestDomainBean(false));
		assertFalse(DomainBeanUtils.isEmpty(listWithNonEmptyBean));
	}

	/**
	 * Test remove empty objects domain bean array.
	 */
	@Test
	public void testRemoveEmptyObjectsDomainBeanArray() {
		DomainBeanUtils.removeEmptyObjects((DomainBean) null);

		final FileSystemLocation location = new FileSystemLocation();
		assertTrue(location.isEmpty());

		final Configuration configuration = new Configuration();
		configuration.getFileSystemLocations().add(location);
		assertNotEmpty(configuration.getFileSystemLocations());

		DomainBeanUtils.removeEmptyObjects(configuration);

		assertNotNull(configuration.getFileSystemLocations());
		assertEmpty(configuration.getFileSystemLocations());
	}

	/**
	 * Test remove empty objects list of qextends domain bean.
	 */
	@Test
	public void testRemoveEmptyObjectsListOfQextendsDomainBean() {
		DomainBeanUtils.removeEmptyObjects((List<? extends DomainBean>) null);

		final FileSystemLocation location = new FileSystemLocation();
		assertTrue(location.isEmpty());

		final Configuration configuration = new Configuration();
		configuration.getFileSystemLocations().add(location);
		assertNotEmpty(configuration.getFileSystemLocations());

		final List<DomainBean> list = new ArrayList<DomainBean>(1);
		list.add(configuration);
		DomainBeanUtils.removeEmptyObjects(list);

		assertNotNull(list);
		assertEmpty(list);
	}

	/**
	 * Test remove empty objects list of qextends domain bean children.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testRemoveEmptyObjectsListOfQextendsDomainBeanChildren() throws IOException {
		DomainBeanUtils.removeEmptyObjects((List<? extends DomainBean>) null);

		final FileSystemLocation location = new FileSystemLocation();
		assertTrue(location.isEmpty());

		final File tempFile = File.createTempFile("ute_", ".test");
		tempFile.deleteOnExit();

		final Configuration configuration = new Configuration();
		configuration.setConfigurationFile(tempFile);
		configuration.getFileSystemLocations().add(location);
		assertNotEmpty(configuration.getFileSystemLocations());

		final List<DomainBean> list = new ArrayList<DomainBean>(1);
		list.add(configuration);
		DomainBeanUtils.removeEmptyObjects(list);

		assertNotNull(list);
		assertNotEmpty(list);
		assertEmpty(configuration.getFileSystemLocations());
	}
}
