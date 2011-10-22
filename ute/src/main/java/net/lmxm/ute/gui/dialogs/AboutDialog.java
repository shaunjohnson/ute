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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentType;
import net.lmxm.ute.gui.utils.ImageUtil;
import net.lmxm.ute.utils.ResourcesUtils;

/**
 * The Class AboutDialog.
 */
public class AboutDialog extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3786034493887802467L;

	/** The attributions text pane. */
	private JTextPane attributionsTextPane = null;

	/** The button panel. */
	private JPanel buttonPanel = null;

	/** The close button. */
	private JButton closeButton = null;

	/** The main layout panel. */
	private JPanel mainLayoutPanel = null;

	/** The title label. */
	private JLabel titleLabel = null;

	/** The title panel. */
	private JPanel titlePanel = null;

	/** The version label. */
	private JLabel versionLabel = null;

	/**
	 * Instantiates a new about dialog.
	 */
	public AboutDialog() {
		initialize();
	}

	/**
	 * Gets the attributions text pane.
	 * 
	 * @return the attributions text pane
	 */
	private JTextPane getAttributionsTextPane() {
		if (attributionsTextPane == null) {
			attributionsTextPane = new JTextPane();
			attributionsTextPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			attributionsTextPane.setText(ResourcesUtils.getApplicationAttributions());
			attributionsTextPane.setOpaque(false);
		}
		return attributionsTextPane;
	}

	/**
	 * Gets the button panel.
	 * 
	 * @return the button panel
	 */
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getCloseButton());
		}
		return buttonPanel;
	}

	/**
	 * Gets the close button.
	 * 
	 * @return the close button
	 */
	private JButton getCloseButton() {
		if (closeButton == null) {
			closeButton = GuiComponentFactory.createButton(GuiComponentType.CLOSE_DIALOG_BUTTON, new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					dispose();
				}
			});
		}
		return closeButton;
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
			mainLayoutPanel.add(getAttributionsTextPane(), BorderLayout.CENTER);
			mainLayoutPanel.add(getButtonPanel(), BorderLayout.SOUTH);
		}
		return mainLayoutPanel;
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
			titleLabel.setText(ResourcesUtils.getApplicationName());
			titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

			versionLabel = new JLabel();
			versionLabel.setText(ResourcesUtils.getApplicationVersion());
			versionLabel.setHorizontalAlignment(SwingConstants.CENTER);

			titlePanel = new JPanel();
			titlePanel.setLayout(gridLayout);
			titlePanel.add(titleLabel, null);
			titlePanel.add(versionLabel, null);
		}
		return titlePanel;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		setIconImage(ImageUtil.APPLICATION_ICON_IMAGE);
		setMinimumSize(new Dimension(400, 200));
		setPreferredSize(new Dimension(400, 200));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setContentPane(getMainLayoutPanel());
		setModal(true);
		setTitle("About " + ResourcesUtils.getApplicationName());

		pack();

		getCloseButton().requestFocus();
	}
}
