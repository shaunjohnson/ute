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
import static junit.framework.Assert.assertNull;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class DocumentAdapterTest.
 */
public class DocumentAdapterTest {

	/**
	 * The Class TestDocument.
	 */
	private static class TestDocument implements Document {

		/** The text. */
		private final String text;

		/**
		 * Instantiates a new test document.
		 * 
		 * @param text the text
		 */
		public TestDocument(final String text) {
			super();

			this.text = text;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#addDocumentListener(javax.swing.event.DocumentListener)
		 */
		@Override
		public void addDocumentListener(final DocumentListener listener) {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#addUndoableEditListener(javax.swing.event.UndoableEditListener)
		 */
		@Override
		public void addUndoableEditListener(final UndoableEditListener listener) {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#createPosition(int)
		 */
		@Override
		public Position createPosition(final int offs) throws BadLocationException {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getDefaultRootElement()
		 */
		@Override
		public Element getDefaultRootElement() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getEndPosition()
		 */
		@Override
		public Position getEndPosition() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getLength()
		 */
		@Override
		public int getLength() {
			return StringUtils.length(text);
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getProperty(java.lang.Object)
		 */
		@Override
		public Object getProperty(final Object key) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getRootElements()
		 */
		@Override
		public Element[] getRootElements() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getStartPosition()
		 */
		@Override
		public Position getStartPosition() {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getText(int, int)
		 */
		@Override
		public String getText(final int offset, final int length) throws BadLocationException {
			return text.substring(offset, offset + length);
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#getText(int, int, javax.swing.text.Segment)
		 */
		@Override
		public void getText(final int offset, final int length, final Segment txt) throws BadLocationException {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#insertString(int, java.lang.String, javax.swing.text.AttributeSet)
		 */
		@Override
		public void insertString(final int offset, final String str, final AttributeSet a) throws BadLocationException {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#putProperty(java.lang.Object, java.lang.Object)
		 */
		@Override
		public void putProperty(final Object key, final Object value) {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#remove(int, int)
		 */
		@Override
		public void remove(final int offs, final int len) throws BadLocationException {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#removeDocumentListener(javax.swing.event.DocumentListener)
		 */
		@Override
		public void removeDocumentListener(final DocumentListener listener) {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#removeUndoableEditListener(javax.swing.event.UndoableEditListener)
		 */
		@Override
		public void removeUndoableEditListener(final UndoableEditListener listener) {

		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.text.Document#render(java.lang.Runnable)
		 */
		@Override
		public void render(final Runnable r) {

		}
	}

	/**
	 * The Class TestDocumentAdapter.
	 */
	private static class TestDocumentAdapter extends DocumentAdapter {

		/** The new value. */
		private String newValue = null;

		/**
		 * Gets the new value.
		 * 
		 * @return the new value
		 */
		public String getNewValue() {
			return newValue;
		}

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.event.DocumentAdapter#valueChanged(java.lang.String)
		 */
		@Override
		public void valueChanged(final String newValue) {
			this.newValue = newValue;
		}
	}

	/**
	 * The Class TestDocumentBadLocation.
	 */
	private static class TestDocumentBadLocation extends TestDocument {

		/**
		 * Instantiates a new test document bad location.
		 */
		public TestDocumentBadLocation() {
			super("foobar");
		}

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.event.DocumentAdapterTest.TestDocument#getText(int, int)
		 */
		@Override
		public String getText(final int offset, final int length) throws BadLocationException {
			throw new BadLocationException("boom", offset);
		}
	}

	/**
	 * The Class TestDocumentEvent.
	 */
	private static class TestDocumentEvent implements DocumentEvent {

		/** The document. */
		private final Document document;

		/** The text. */
		private final String text;

		/**
		 * Instantiates a new test document event.
		 * 
		 * @param text the text
		 */
		public TestDocumentEvent(final String text) {
			super();

			this.text = text;
			this.document = new TestDocument(text);
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentEvent#getChange(javax.swing.text.Element)
		 */
		@Override
		public ElementChange getChange(final Element element) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentEvent#getDocument()
		 */
		@Override
		public Document getDocument() {
			return document;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentEvent#getLength()
		 */
		@Override
		public int getLength() {
			return StringUtils.length(text);
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentEvent#getOffset()
		 */
		@Override
		public int getOffset() {
			return 0;
		}

		/*
		 * (non-Javadoc)
		 * @see javax.swing.event.DocumentEvent#getType()
		 */
		@Override
		public EventType getType() {
			return null;
		}
	}

	/**
	 * The Class TestDocumentEventBadLocation.
	 */
	private static class TestDocumentEventBadLocation extends TestDocumentEvent {

		/** The document. */
		private final Document document;

		/**
		 * Instantiates a new test document event bad location.
		 */
		public TestDocumentEventBadLocation() {
			super("foobar");

			this.document = new TestDocumentBadLocation();
		}

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.event.DocumentAdapterTest.TestDocumentEvent#getDocument()
		 */
		@Override
		public Document getDocument() {
			return document;
		}
	}

	/**
	 * The Class TestDocumentEventNullDocument.
	 */
	private static class TestDocumentEventNullDocument extends TestDocumentEvent {

		/**
		 * Instantiates a new test document event null document.
		 */
		public TestDocumentEventNullDocument() {
			super("foobar");
		}

		/*
		 * (non-Javadoc)
		 * @see net.lmxm.ute.event.DocumentAdapterTest.TestDocumentEvent#getDocument()
		 */
		@Override
		public Document getDocument() {
			return null;
		}
	}

	/** The listener. */
	private TestDocumentAdapter listener = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		listener = new TestDocumentAdapter();
	}

	/**
	 * Test changed update.
	 */
	@Test
	public void testChangedUpdate() {
		assertNull(listener.getNewValue());

		listener.changedUpdate(new TestDocumentEvent("foobar"));

		assertNotNull(listener.getNewValue());
		assertEquals("foobar", listener.getNewValue());
	}

	/**
	 * Test get text.
	 */
	@Test
	public void testGetText() {
		assertEquals("foobar", listener.getText(new TestDocumentEvent("foobar")));
	}

	/**
	 * Test get text bad location.
	 */
	@Test
	public void testGetTextBadLocation() {
		assertNull(listener.getText(new TestDocumentEventBadLocation()));
	}

	/**
	 * Test get text document event null.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetTextDocumentEventNull() {
		listener.getText(null);
	}

	/**
	 * Test get text null document.
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetTextNullDocument() {
		listener.getText(new TestDocumentEventNullDocument());
	}

	/**
	 * Test insert update.
	 */
	@Test
	public void testInsertUpdate() {
		assertNull(listener.getNewValue());

		listener.insertUpdate(new TestDocumentEvent("foobar"));

		assertNotNull(listener.getNewValue());
		assertEquals("foobar", listener.getNewValue());
	}

	/**
	 * Test remove update.
	 */
	@Test
	public void testRemoveUpdate() {
		assertNull(listener.getNewValue());

		listener.removeUpdate(new TestDocumentEvent("foobar"));

		assertNotNull(listener.getNewValue());
		assertEquals("foobar", listener.getNewValue());
	}

	/**
	 * Test value changed.
	 */
	@Test
	public void testValueChanged() {
		assertNull(listener.getNewValue());

		listener.valueChanged("foobar");

		assertNotNull(listener.getNewValue());
		assertEquals("foobar", listener.getNewValue());
	}
}
