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

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import net.lmxm.ute.gui.ActionConstants;
import net.lmxm.ute.gui.MainFrame;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Class JobsRootPopupMenu.
 */
public final class JobsRootPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6615140435658896019L;

	/** The add job menu item. */
	private JMenuItem addJobMenuItem = null;

	/**
	 * Instantiates a new job popup menu.
	 * 
	 * @param mainFrame the main frame
	 * @param actionListener the action listener
	 */
	public JobsRootPopupMenu(final MainFrame mainFrame, final ActionListener actionListener) {
		super(mainFrame, actionListener);

		add(getAddJobMenuItem());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems(java.lang.Object)
	 */
	@Override
	public void enableDisableMenuItems(final Object object) {

	}

	/**
	 * Gets the adds the job menu item.
	 * 
	 * @return the adds the job menu item
	 */
	private JMenuItem getAddJobMenuItem() {
		if (addJobMenuItem == null) {
			addJobMenuItem = new JMenuItem();
			addJobMenuItem.setText("Add Job");
			addJobMenuItem.setIcon(ImageUtil.ADD_JOB_ICON);
			addJobMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			addJobMenuItem.addActionListener(getActionListener());
			addJobMenuItem.setActionCommand(ActionConstants.ADD_JOB);
		}
		return addJobMenuItem;
	}
}
