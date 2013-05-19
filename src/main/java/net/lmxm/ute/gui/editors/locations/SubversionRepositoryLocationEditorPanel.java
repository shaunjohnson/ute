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

import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.lmxm.ute.resources.types.ToolbarButtonResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
		public SubversionRepositoryLocationEditorToolBar(final UteActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.DELETE_SUBVERSION_REPOSITORY_LOCATION);
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
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public SubversionRepositoryLocationEditorPanel(final ConfigurationHolder configurationHolder,
			final UteActionListener actionListener) {
		super(LabelResourceType.SUBVERSION_REPOSITORY_LOCATION, new SubversionRepositoryLocationEditorToolBar(
				actionListener), configurationHolder, actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.locations.AbstractHttpLocationEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		final JPanel contentPanel = getContentPanel();

		addLabel(LabelResourceType.USERNAME);
		contentPanel.add(getUsernameTextField());

		addLabel(LabelResourceType.PASSWORD);
		contentPanel.add(getPasswordTextField());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new SubversionRepositoryLocation();
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
			passwordTextField.setDragEnabled(true);
			passwordTextField.getDocument().addDocumentListener(new DocumentAdapter() {
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

	/**
	 * Gets the username text field.
	 * 
	 * @return the username text field
	 */
	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setMinimumSize(new Dimension(400, (int) usernameTextField.getSize().getHeight()));
			usernameTextField.setDragEnabled(true);
			usernameTextField.getDocument().addDocumentListener(new DocumentAdapter() {
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
