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
package net.lmxm.ute.gui.editors;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.locations.FileSystemLocation;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FileSystemLocationEditorPanel.
 */
public final class FileSystemLocationEditorPanel extends AbstractIdEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemLocationEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7086761608623968446L;

	/** The path text field. */
	private JTextField pathTextField = null;

	/**
	 * Instantiates a new file system location editor panel.
	 *
	 * @param configuration the configuration
	 */
	public FileSystemLocationEditorPanel(final Configuration configuration) {
		super(configuration, "File System Location");

		addIdCommonFields();

		final JPanel contentPanel = getContentPanel();

		contentPanel.add(new JLabel("Path:"));
		contentPanel.add(getPathTextField());
	}

	/**
	 * Gets the path text field.
	 *
	 * @return the path text field
	 */
	private JTextField getPathTextField() {
		if (pathTextField == null) {
			pathTextField = new JTextField();
			pathTextField.setMinimumSize(new Dimension(400, (int)pathTextField.getSize().getHeight()));
		}
		return pathTextField;
	}

	/**
	 * Load data.
	 *
	 * @param fileSystemLocation the property
	 */
	public void loadData(final FileSystemLocation fileSystemLocation) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, fileSystemLocation={}", prefix, fileSystemLocation);

		loadIdCommonFieldData(fileSystemLocation);

		if (fileSystemLocation == null) {
			getPathTextField().setText("");
		}
		else {
			getPathTextField().setText(fileSystemLocation.getPath());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
