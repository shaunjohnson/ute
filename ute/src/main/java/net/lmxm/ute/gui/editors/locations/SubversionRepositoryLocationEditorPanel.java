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
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.components.GuiComponentToolbarButton;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.listeners.ChangeAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SubversionRepositoryLocationEditorPanel.
 */
public final class SubversionRepositoryLocationEditorPanel extends AbstractHttpLocationEditorPanel {

	/**
	 * The Class SubversionRepositoryLocationEditorToolBar.
	 */
	private static class SubversionRepositoryLocationEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -1616267977286142640L;

		/**
		 * Instantiates a new subversion repository location editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public SubversionRepositoryLocationEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(GuiComponentToolbarButton.DELETE_SUBVERSION_REPOSITORY_LOCATION);
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionRepositoryLocationEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8782148253716749536L;

	/** The password text field. */
	private JTextField passwordTextField = null;

	/** The username text field. */
	private JTextField usernameTextField = null;

	/**
	 * Instantiates a new subversion repository location editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionRepositoryLocationEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.SUBVERSION_REPOSITORY_LOCATION, actionListener);

		final JPanel contentPanel = getContentPanel();

		addHttpLocationCommonFields();

		addLabel(contentPanel, GuiComponentLabel.USERNAME);
		contentPanel.add(getUsernameTextField());

		addLabel(contentPanel, GuiComponentLabel.PASSWORD);
		contentPanel.add(getPasswordTextField());
	}

	/**
	 * Gets the password text field.
	 * 
	 * @return the password text field
	 */
	private JTextField getPasswordTextField() {
		if (passwordTextField == null) {
			passwordTextField = new JTextField();
			passwordTextField.setMinimumSize(new Dimension(400, (int) passwordTextField.getSize().getHeight()));
			passwordTextField.getDocument().addDocumentListener(new ChangeAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof SubversionRepositoryLocation) {
						((SubversionRepositoryLocation) getUserObject()).setPassword(newValue);
					}
				}
			});
		}
		return passwordTextField;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getToolBar()
	 */
	@Override
	protected JToolBar getToolBar() {
		return new SubversionRepositoryLocationEditorToolBar(getActionListener());
	}

	/**
	 * Gets the username text field.
	 * 
	 * @return the username text field
	 */
	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setMinimumSize(new Dimension(400, (int) usernameTextField.getSize().getHeight()));
			usernameTextField.getDocument().addDocumentListener(new ChangeAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof SubversionRepositoryLocation) {
						((SubversionRepositoryLocation) getUserObject()).setUsername(newValue);
					}
				}
			});
		}
		return usernameTextField;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.locations.AbstractHttpLocationEditorPanel#loadData()
	 */
	@Override
	public void loadData() {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered", prefix);

		super.loadData();

		if (getUserObject() instanceof SubversionRepositoryLocation) {
			final SubversionRepositoryLocation subversionRepositoryLocation = (SubversionRepositoryLocation) getUserObject();

			getUsernameTextField().setText(subversionRepositoryLocation.getUsername());
			getPasswordTextField().setText(subversionRepositoryLocation.getPassword());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
