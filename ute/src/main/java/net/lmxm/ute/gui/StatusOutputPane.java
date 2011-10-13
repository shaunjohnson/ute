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

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeListener;

/**
 * The Class StatusOutputPane.
 */
public class StatusOutputPane extends JTextPane implements StatusChangeListener {

	/** The Constant ERROR. */
	private static final String ERROR = "ERROR";

	/** The Constant FATAL. */
	private static final String FATAL = "FATAL";

	/** The Constant HEADING. */
	private static final String HEADING = "HEADING";

	/** The Constant IMPORTANT. */
	private static final String IMPORTANT = "IMPORTANT";

	/** The Constant INFO. */
	private static final String INFO = "INFO";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8105369333402380167L;

	/** The style context. */
	private final StyleContext styleContext;

	/**
	 * Instantiates a new status output pane.
	 */
	public StatusOutputPane() {
		super();

		styleContext = createStyleContext();
		setStyledDocument(new DefaultStyledDocument(styleContext));
		setEditable(false);
	}

	/**
	 * Creates the style context.
	 * 
	 * @return the style context
	 */
	private StyleContext createStyleContext() {
		final StyleContext styleContext = new StyleContext();
		final Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);

		final Style errorStyle = styleContext.addStyle(ERROR, defaultStyle);
		StyleConstants.setForeground(errorStyle, Color.RED);

		final Style fatalStyle = styleContext.addStyle(FATAL, defaultStyle);
		StyleConstants.setBold(fatalStyle, true);
		StyleConstants.setForeground(fatalStyle, Color.RED);

		final Style headingStyle = styleContext.addStyle(HEADING, defaultStyle);
		StyleConstants.setBold(headingStyle, true);
		StyleConstants.setFontSize(headingStyle, 16);

		final Style importantStyle = styleContext.addStyle(IMPORTANT, defaultStyle);
		StyleConstants.setBold(importantStyle, true);

		final Style infoStyle = styleContext.addStyle(INFO, defaultStyle);
		StyleConstants.setLeftIndent(infoStyle, 10);

		return styleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.StatusChangeListener#statusChange(net.lmxm.ute.listeners .StatusChangeEvent)
	 */
	@Override
	public void statusChange(final StatusChangeEvent statusChangeEvent) {
		final String styleName;

		switch (statusChangeEvent.getEventType()) {
			case ERROR:
				styleName = ERROR;
				break;
			case FATAL:
				styleName = FATAL;
				break;
			case HEADING:
				styleName = HEADING;
				break;
			case IMPORTANT:
				styleName = IMPORTANT;
				break;
			case INFO:
				styleName = INFO;
				break;
			default:
				throw new IllegalArgumentException("Unsupported event type " + statusChangeEvent.getEventType());
		}

		try {
			final Document document = getDocument();
			document.insertString(document.getLength(), statusChangeEvent.getMessage() + "\n",
					styleContext.getStyle(styleName));
		}
		catch (final BadLocationException e) {
			e.printStackTrace();
		}

		setCaretPosition(getDocument().getLength());
	}
}
