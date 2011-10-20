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
 * The Class PropertiesRootPopupMenu.
 */
@SuppressWarnings("serial")
public final class PropertiesRootPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8494251261195748943L;

	/** The add property menu item. */
	private JMenuItem addPropertyMenuItem = null;

	/**
	 * Instantiates a new properties root popup menu.
	 * 
	 * @param actionListener the action listener
	 */
	public PropertiesRootPopupMenu(final ActionListener actionListener) {
		super(actionListener);

		add(getAddPropertyMenuItem());
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
					setToolTipText("Add new property");

					addActionListener(getActionListener());
					setActionCommand(ActionConstants.ADD_PROPERTY);
				}
			};
		}
		return addPropertyMenuItem;
	}
}
