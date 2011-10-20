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
package net.lmxm.ute.gui.menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import net.lmxm.ute.gui.ActionConstants;

/**
 * The Class PreferencePopupMenu.
 */
public final class PreferencePopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9191881768346122431L;

	/** The add preference menu item. */
	private JMenuItem addPreferenceMenuItem = null;

	/** The delete preference menu item. */
	private JMenuItem deletePreferenceMenuItem = null;

	/**
	 * Instantiates a new preference popup menu.
	 * 
	 * @param actionListener the action listener
	 */
	public PreferencePopupMenu(final ActionListener actionListener) {
		super(actionListener);

		add(getAddPreferenceMenuItem());
		add(getDeletePreferenceMenuItem());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems(java.lang.Object)
	 */
	@Override
	public void enableDisableMenuItems(final Object object) {

	}

	/**
	 * Gets the adds the preference menu item.
	 * 
	 * @return the adds the preference menu item
	 */
	private JMenuItem getAddPreferenceMenuItem() {
		if (addPreferenceMenuItem == null) {
			addPreferenceMenuItem = new JMenuItem();
			addPreferenceMenuItem.setText("Add Preference");
			addPreferenceMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			addPreferenceMenuItem.addActionListener(getActionListener());
			addPreferenceMenuItem.setActionCommand(ActionConstants.ADD_PREFERENCE);
		}
		return addPreferenceMenuItem;
	}

	/**
	 * Gets the delete preference menu item.
	 * 
	 * @return the delete preference menu item
	 */
	private JMenuItem getDeletePreferenceMenuItem() {
		if (deletePreferenceMenuItem == null) {
			deletePreferenceMenuItem = new JMenuItem();
			deletePreferenceMenuItem.setText("Delete Preference");
			deletePreferenceMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			deletePreferenceMenuItem.addActionListener(getActionListener());
			deletePreferenceMenuItem.setActionCommand(ActionConstants.DELETE_PREFERENCE);
		}
		return deletePreferenceMenuItem;
	}
}
