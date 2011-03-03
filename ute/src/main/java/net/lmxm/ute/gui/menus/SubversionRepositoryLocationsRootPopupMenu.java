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
package net.lmxm.ute.gui.menus;

import net.lmxm.ute.gui.ActionConstants;
import net.lmxm.ute.gui.MainFrame;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * The Class SubversionRepositoryLocationsRootPopupMenu.
 */
public final class SubversionRepositoryLocationsRootPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7912225582390290333L;

	/** The add subversion repository location menu item. */
	private JMenuItem addSubversionRepositoryLocationMenuItem = null;

	/**
	 * Instantiates a new subversion repository locations root popup menu.
	 *
	 * @param mainFrame the main frame
	 * @param actionListener the action listener
	 */
	public SubversionRepositoryLocationsRootPopupMenu(final MainFrame mainFrame, final ActionListener actionListener) {
		super(mainFrame, actionListener);

		add(getAddSubversionRepositoryLocationMenuItem());
	}

	/* (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems()
	 */
	@Override
	public void enableDisableMenuItems() {

	}

	/**
	 * Gets the adds the subversion repository location menu item.
	 *
	 * @return the adds the subversion repository location menu item
	 */
	private JMenuItem getAddSubversionRepositoryLocationMenuItem() {
		if (addSubversionRepositoryLocationMenuItem == null) {
			addSubversionRepositoryLocationMenuItem = new JMenuItem();
			addSubversionRepositoryLocationMenuItem.setText("Add Subversion Repository Location");
			addSubversionRepositoryLocationMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			addSubversionRepositoryLocationMenuItem.addActionListener(getActionListener());
			addSubversionRepositoryLocationMenuItem
					.setActionCommand(ActionConstants.ADD_SUBVERSION_REPOSITORY_LOCATION);
		}
		return addSubversionRepositoryLocationMenuItem;
	}
}
