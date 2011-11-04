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
package net.lmxm.ute.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.resources.ImageUtil;

/**
 * The Class EditPreferencesDialog.
 */
public class EditPreferencesDialog extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4775245952490720963L;

	/** The button panel. */
	private JPanel buttonPanel = null;

	/** The cancel button. */
	private JButton cancelButton = null;

	/** The main layout panel. */
	private JPanel mainLayoutPanel = null;

	/** The preferences scroll pane. */
	private JScrollPane preferencesScrollPane = null;

	/** The preferences table. */
	private JTable preferencesTable = null;

	/** The save button. */
	private JButton saveButton = null;

	/** The title label. */
	private JLabel titleLabel = null;

	/** The title panel. */
	private JPanel titlePanel = null;

	/**
	 * Instantiates a new edits the preferences dialog.
	 * 
	 * @param configuration the configuration
	 */
	public EditPreferencesDialog(final Configuration configuration) {
		super();

		initialize();
		loadPreferencesData(configuration);
	}

	/**
	 * Creates the empty preferences table model.
	 * 
	 * @return the default table model
	 */
	protected final DefaultTableModel createEmptyPreferencesTableModel() {
		final DefaultTableModel tableModel = new DefaultTableModel();

		tableModel.addColumn("Name");
		tableModel.addColumn("Value");

		return tableModel;
	}

	/**
	 * Gets the button panel.
	 * 
	 * @return the button panel
	 */
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getSaveButton());
			buttonPanel.add(getCancelButton());
		}
		return buttonPanel;
	}

	/**
	 * Gets the cancel button.
	 * 
	 * @return the cancel button
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelButton;
	}

	/**
	 * Gets the main layout panel.
	 * 
	 * @return the main layout panel
	 */
	private JPanel getMainLayoutPanel() {
		if (mainLayoutPanel == null) {
			mainLayoutPanel = new JPanel();
			mainLayoutPanel.setLayout(new BorderLayout());
			mainLayoutPanel.add(getTitlePanel(), BorderLayout.NORTH);
			mainLayoutPanel.add(getPreferencesScrollPane(), BorderLayout.CENTER);
			mainLayoutPanel.add(getButtonPanel(), BorderLayout.SOUTH);
		}
		return mainLayoutPanel;
	}

	/**
	 * Gets the preferences scroll pane.
	 * 
	 * @return the preferences scroll pane
	 */
	protected final JScrollPane getPreferencesScrollPane() {
		if (preferencesScrollPane == null) {
			preferencesScrollPane = new JScrollPane(getPreferencesTable());
			preferencesScrollPane.setMaximumSize(new Dimension(400, 100));
		}

		return preferencesScrollPane;
	}

	/**
	 * Gets the preferences table.
	 * 
	 * @return the preferences table
	 */
	protected final JTable getPreferencesTable() {
		if (preferencesTable == null) {
			preferencesTable = new JTable(createEmptyPreferencesTableModel());
			preferencesTable.setFillsViewportHeight(true);
		}

		return preferencesTable;
	}

	/**
	 * Gets the save button.
	 * 
	 * @return the save button
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("Save");
			saveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					dispose();
				}
			});
		}
		return saveButton;
	}

	/**
	 * Gets the title panel.
	 * 
	 * @return the title panel
	 */
	private JPanel getTitlePanel() {
		if (titlePanel == null) {
			final GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setColumns(1);

			titleLabel = new JLabel();
			titleLabel.setText("Edit Preferences");
			titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

			titlePanel = new JPanel();
			titlePanel.setLayout(gridLayout);
			titlePanel.add(titleLabel, null);
		}
		return titlePanel;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		setIconImage(ImageUtil.APPLICATION_ICON_IMAGE);
		setMinimumSize(new Dimension(600, 400));
		setPreferredSize(new Dimension(600, 400));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setContentPane(getMainLayoutPanel());
		setModal(true);
		setTitle("Edit Preferences");

		pack();

		getCancelButton().requestFocus();
	}

	/**
	 * Load preferences data.
	 * 
	 * @param configuration the configuration
	 */
	private void loadPreferencesData(final Configuration configuration) {
		final DefaultTableModel tableModel = createEmptyPreferencesTableModel();

		final List<Preference> preferences = configuration.getPreferences();

		if (preferences != null) {
			for (final Preference preference : preferences) {
				tableModel.addRow(new Object[] { preference.getId(), preference.getValue() });
			}
		}

		getPreferencesTable().setModel(tableModel);
	}
}
