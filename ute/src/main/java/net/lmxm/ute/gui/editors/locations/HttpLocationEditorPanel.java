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
package net.lmxm.ute.gui.editors.locations;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

import net.lmxm.ute.beans.locations.HttpLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class HttpLocationEditorPanel.
 */
public final class HttpLocationEditorPanel extends AbstractLocationEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpLocationEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4152865192476292446L;

	/** The url text field. */
	private JTextField urlTextField = null;

	/**
	 * Instantiates a new http location editor panel.
	 */
	public HttpLocationEditorPanel() {
		super("HTTP Location");

		final JPanel contentPanel = getContentPanel();

		addLocationCommonFields();

		addLabel(contentPanel, "URL");
		contentPanel.add(getUrlTextField());
	}

	/**
	 * Gets the url text field.
	 * 
	 * @return the url text field
	 */
	private JTextField getUrlTextField() {
		if (urlTextField == null) {
			urlTextField = new JTextField();
			urlTextField.setMinimumSize(new Dimension(400, (int) urlTextField.getSize().getHeight()));
		}
		return urlTextField;
	}

	/**
	 * Load data.
	 * 
	 * @param httpLocation the http location
	 */
	public void loadData(final HttpLocation httpLocation) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, httpLocation={}", prefix, httpLocation);

		loadIdCommonFieldData(httpLocation);

		if (httpLocation == null) {
			getUrlTextField().setText("");
		}
		else {
			getUrlTextField().setText(httpLocation.getUrl());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
