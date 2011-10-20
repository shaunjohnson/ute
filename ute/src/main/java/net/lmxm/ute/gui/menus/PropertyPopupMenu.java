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
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Class PropertyPopupMenu.
 */
@SuppressWarnings("serial")
public final class PropertyPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3214707499737502970L;

	/** The add property menu item. */
	private JMenuItem addPropertyMenuItem = null;

	/** The delete property menu item. */
	private JMenuItem deletePropertyMenuItem = null;

	/**
	 * Instantiates a new property popup menu.
	 * 
	 * @param actionListener the action listener
	 */
	public PropertyPopupMenu(final ActionListener actionListener) {
		super(actionListener);

		add(getAddPropertyMenuItem());
		add(getDeletePropertyMenuItem());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems(java.lang.Object)
	 */
	@Override
	public void enableDisableMenuItems(final Object object) {

	}

	/**
	 * Gets the adds the property menu item.
	 * 
	 * @return the adds the property menu item
	 */
	private JMenuItem getAddPropertyMenuItem() {
		if (addPropertyMenuItem == null) {
			addPropertyMenuItem = new JMenuItem() {
				{
					setIcon(ImageUtil.ADD_PROPERTY_ICON);
					setText("Add Property");
					addActionListener(getActionListener());
					setActionCommand(ActionConstants.ADD_PROPERTY);
				}
			};
		}
		return addPropertyMenuItem;
	}

	/**
	 * Gets the delete property menu item.
	 * 
	 * @return the delete property menu item
	 */
	private JMenuItem getDeletePropertyMenuItem() {
		if (deletePropertyMenuItem == null) {
			deletePropertyMenuItem = new JMenuItem() {
				{
					setText("Delete Property");
					addActionListener(getActionListener());
					setActionCommand(ActionConstants.DELETE_PROPERTY);
				}
			};
		}
		return deletePropertyMenuItem;
	}
}
