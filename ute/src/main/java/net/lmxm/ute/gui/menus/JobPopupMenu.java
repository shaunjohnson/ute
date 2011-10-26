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

import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentMenuItem;

/**
 * The Class JobPopupMenu.
 */
public final class JobPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5949848515922880028L;

	/** The add job menu item. */
	private JMenuItem addJobMenuItem = null;

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
	public JobPopupMenu(final ActionListener actionListener) {
		super(actionListener);

		add(getExecuteJobMenuItem());
		add(getAddJobMenuItem());
		add(getDeleteJobMenuItem());
		add(getAddTaskMenuItem());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems(java. lang.Object)
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
			addJobMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.ADD_JOB, getActionListener());
		}
		return addJobMenuItem;
	}

	/**
	 * This method initializes addTaskMenuItem.
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getAddTaskMenuItem() {
		if (addTaskMenuItem == null) {
			addTaskMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.ADD_TASK, getActionListener());
			addTaskMenuItem.setEnabled(false); // TODO not implemented
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
			deleteJobMenuItem = GuiComponentFactory
					.createMenuItem(GuiComponentMenuItem.DELETE_JOB, getActionListener());
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
			executeJobMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.EXECUTE_JOB,
					getActionListener());
		}
		return executeJobMenuItem;
	}
}
