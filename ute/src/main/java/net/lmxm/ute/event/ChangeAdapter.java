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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * The Class ChangeAdapter.
 */
public abstract class ChangeAdapter implements DocumentListener {

	/*
	 * (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(final DocumentEvent documentEvent) {
		valueChanged(getText(documentEvent));
	}

	/**
	 * Gets the text.
	 * 
	 * @param documentEvent the document event
	 * @return the text
	 */
	private String getText(final DocumentEvent documentEvent) {
		String text = null;

		try {
			final Document document = documentEvent.getDocument();
			text = document.getText(0, document.getLength());
		}
		catch (final BadLocationException e) {
			text = null;
		}

		return text;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(final DocumentEvent documentEvent) {
		valueChanged(getText(documentEvent));
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(final DocumentEvent documentEvent) {
		valueChanged(getText(documentEvent));
	}

	/**
	 * Value changed.
	 * 
	 * @param newValue the new value
	 */
	public abstract void valueChanged(String newValue);
}
