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
import net.lmxm.ute.gui.utils.ImageUtil;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * The Class JobPopupMenu.
 */
public final class JobPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5949848515922880028L;

	/** The add task menu item. */
	private JMenuItem addTaskMenuItem = null;

	/** The delete job menu item. */
	private JMenuItem deleteJobMenuItem = null;

	/** The execute job menu item. */
	private JMenuItem executeJobMenuItem = null;

	/**
	 * Instantiates a new job popup menu.
	 *
	 * @param actionListener the action listener
	 */
	public JobPopupMenu(final MainFrame mainFrame, final ActionListener actionListener) {
		super(mainFrame, actionListener);

		add(getExecuteJobMenuItem());
		add(getAddTaskMenuItem());
		add(getDeleteJobMenuItem());
	}

	/* (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems()
	 */
	@Override
	public void enableDisableMenuItems() {
		getExecuteJobMenuItem().setEnabled(getMainFrame().isExecuteJobEnabled());
	}

	/**
	 * This method initializes addTaskMenuItem.
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getAddTaskMenuItem() {
		if (addTaskMenuItem == null) {
			addTaskMenuItem = new JMenuItem();
			addTaskMenuItem.setText("Add Task to Job");
			addTaskMenuItem.setEnabled(false); // TODO disabled since it is not implemented
		}
		return addTaskMenuItem;
	}

	/**
	 * This method initializes deleteJobMenuItem.
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getDeleteJobMenuItem() {
		if (deleteJobMenuItem == null) {
			deleteJobMenuItem = new JMenuItem();
			deleteJobMenuItem.setText("Delete Job");
			deleteJobMenuItem.setEnabled(false); // TODO disabled since it is not implemented
		}
		return deleteJobMenuItem;
	}

	/**
	 * This method initializes executeJobMenuItem.
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExecuteJobMenuItem() {
		if (executeJobMenuItem == null) {
			executeJobMenuItem = new JMenuItem();
			executeJobMenuItem.setText("Execute Job");
			executeJobMenuItem.setIcon(ImageUtil.EXECUTE_JOB_ICON);
			executeJobMenuItem.addActionListener(getActionListener());
			executeJobMenuItem.setActionCommand(ActionConstants.EXECUTE_JOB);
		}
		return executeJobMenuItem;
	}
}
