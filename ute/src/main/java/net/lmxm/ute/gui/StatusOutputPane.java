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
package net.lmxm.ute.gui;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

/**
 * The Class StatusOutputPane.
 */
public class StatusOutputPane extends JEditorPane implements StatusChangeListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8105369333402380167L;

	/**
	 * Instantiates a new status output pane.
	 */
	public StatusOutputPane() {
		super();

		setContentType("text/html");
		setEditable(false);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.StatusChangeListener#statusChange(net.lmxm.ute.listeners .StatusChangeEvent)
	 */
	@Override
	public void statusChange(final StatusChangeEvent changeEvent) {
		final StatusChangeEventType eventType = changeEvent.getEventType();
		final HTMLEditorKit ek = (HTMLEditorKit) getEditorKit();
		final HTMLDocument doc = (HTMLDocument) getDocument();

		try {
			final StringBuilder builder = new StringBuilder();

			if (eventType == StatusChangeEventType.ERROR) {
				builder.append("<div style='color: red;'>");
				builder.append(changeEvent.getMessage());
				builder.append("</div>");
			}
			else if (eventType == StatusChangeEventType.FATAL) {
				builder.append("<div style='font-weight: bold;'>");
				builder.append(changeEvent.getMessage());
				builder.append("</div>");
			}
			else if (eventType == StatusChangeEventType.HEADING) {
				builder.append("<h2>");
				builder.append(changeEvent.getMessage());
				builder.append("</h2>");
			}
			else if (eventType == StatusChangeEventType.IMPORTANT) {
				builder.append("<div style='font-weight: bold;'>");
				builder.append(changeEvent.getMessage());
				builder.append("</div>");
			}
			else if (eventType == StatusChangeEventType.INFO) {
				builder.append("<div>");
				builder.append(changeEvent.getMessage());
				builder.append("</div>");
			}
			else {
				// TODO Handle unsupported StatusChangeEventType
			}

			ek.insertHTML(doc, doc.getLength(), builder.toString(), 1, 0, null);
		}
		catch (final BadLocationException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

		setCaretPosition(doc.getLength());
	}
}
