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
package net.lmxm.ute.gui.editors;

import net.lmxm.ute.beans.Configuration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AbstractEditor.
 */
public abstract class AbstractEditorPanel extends JPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2931881275263682418L;

	/** The configuration. */
	private final Configuration configuration;

	/** The content panel. */
	private final JPanel contentPanel;

	/** The description pane. */
	private JScrollPane descriptionPane = null;

	/** The description text area. */
	private JTextArea descriptionTextArea = null;

	/** The file system location target combo box. */
	private JComboBox fileSystemLocationTargetComboBox = null;

	/** The http location target combo box. */
	private JComboBox httpLocationTargetComboBox = null;

	/** The id text field. */
	private JTextField idTextField = null;

	/** The subversion repository location target combo box. */
	private JComboBox subversionRepositoryLocationTargetComboBox = null;

	/**
	 * Instantiates a new abstract editor panel.
	 *
	 * @param configuration the configuration
	 * @param titleText the title text
	 */
	public AbstractEditorPanel(final Configuration configuration, final String titleText) {
		super();

		this.configuration = configuration;

		// Setup title panel
		final JPanel titlePanel = new JPanel(new MigLayout("wrap 1", "[center]"));
		final JLabel titleLabel = new JLabel(titleText);
		titleLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

		titlePanel.add(titleLabel);

		// Setup content panel
		contentPanel = new JPanel(new MigLayout("wrap 2", "[right][fill]"));

		// Setup this panel
		setLayout(new BorderLayout());
		add(titlePanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
	}

	/**
	 * Gets the content panel.
	 *
	 * @return the content panel
	 */
	protected JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * Gets the description pane.
	 *
	 * @return the description pane
	 */
	protected final JScrollPane getDescriptionPane() {
		if (descriptionPane == null) {
			descriptionPane = new JScrollPane(getDescriptionTextArea());
		}

		return descriptionPane;
	}

	/**
	 * Gets the description text area.
	 *
	 * @return the description text area
	 */
	protected final JTextArea getDescriptionTextArea() {
		if (descriptionTextArea == null) {
			descriptionTextArea = new JTextArea();
			descriptionTextArea.setColumns(40);
			descriptionTextArea.setRows(5);
			descriptionTextArea.setLineWrap(true);
		}
		return descriptionTextArea;
	}

	/**
	 * Gets the file system location target combo box.
	 *
	 * @return the file system location target combo box
	 */
	protected final JComboBox getFileSystemLocationTargetComboBox() {
		if (fileSystemLocationTargetComboBox == null) {
			fileSystemLocationTargetComboBox = new JComboBox(configuration.getFileSystemLocations().toArray());
		}

		return fileSystemLocationTargetComboBox;
	}

	/**
	 * Gets the http location source combo box.
	 *
	 * @return the http location source combo box
	 */
	protected final JComboBox getHttpLocationSourceComboBox() {
		if (httpLocationTargetComboBox == null) {
			httpLocationTargetComboBox = new JComboBox(configuration.getHttpLocations().toArray());
		}

		return httpLocationTargetComboBox;
	}

	/**
	 * Gets the id text field.
	 *
	 * @return the id text field
	 */
	protected final JTextField getIdTextField() {
		if (idTextField == null) {
			idTextField = new JTextField();
			idTextField.setMinimumSize(new Dimension(400, (int)idTextField.getSize().getHeight()));
		}

		return idTextField;
	}

	/**
	 * Gets the subversion repository location source combo box.
	 *
	 * @return the subversion repository location source combo box
	 */
	protected final JComboBox getSubversionRepositoryLocationSourceComboBox() {
		if (subversionRepositoryLocationTargetComboBox == null) {
			subversionRepositoryLocationTargetComboBox = new JComboBox(configuration.getSubversionRepositoryLocations()
					.toArray());
		}

		return subversionRepositoryLocationTargetComboBox;
	}

	/**
	 * Sets the selected index.
	 *
	 * @param comboBox the combo box
	 * @param value the value
	 */
	protected final void setSelectedIndex(final JComboBox comboBox, final Object value) {
		final String prefix = "setSelectedIndex() :";

		LOGGER.debug("{} entered", prefix);

		final int itemCount = comboBox.getItemCount();
		int selectedIndex = -1;

		if (value == null) {
			LOGGER.debug("{} value to select is null, setting index to -1", prefix);
		}
		else {
			LOGGER.debug("{} value={}", prefix, value);

			for (int i = 0; i < itemCount; i++) {
				if (comboBox.getItemAt(i).toString().equals(value.toString())) {
					selectedIndex = i;

					break;
				}
			}
		}

		LOGGER.debug("{} setting index to {}", prefix, selectedIndex);

		comboBox.setSelectedIndex(selectedIndex);

		LOGGER.debug("{} leaving", prefix);
	}
}
