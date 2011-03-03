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
 * The Class FileSystemLocationsRootPopupMenu.
 */
public final class FileSystemLocationsRootPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2973143132421185637L;

	/** The add file system location menu item. */
	private JMenuItem addFileSystemLocationMenuItem = null;

	/**
	 * Instantiates a new file system locations root popup menu.
	 *
	 * @param mainFrame the main frame
	 * @param actionListener the action listener
	 */
	public FileSystemLocationsRootPopupMenu(final MainFrame mainFrame, final ActionListener actionListener) {
		super(mainFrame, actionListener);

		add(getAddFileSystemLocationMenuItem());
	}

	/* (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems()
	 */
	@Override
	public void enableDisableMenuItems() {

	}

	/**
	 * Gets the adds the file system location menu item.
	 *
	 * @return the adds the file system location menu item
	 */
	private JMenuItem getAddFileSystemLocationMenuItem() {
		if (addFileSystemLocationMenuItem == null) {
			addFileSystemLocationMenuItem = new JMenuItem();
			addFileSystemLocationMenuItem.setText("Add File System Location");
			addFileSystemLocationMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			addFileSystemLocationMenuItem.addActionListener(getActionListener());
			addFileSystemLocationMenuItem.setActionCommand(ActionConstants.ADD_FILE_SYSTEM_LOCATION);
		}
		return addFileSystemLocationMenuItem;
	}
}
