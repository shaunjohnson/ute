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

import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.ActionConstants;
import net.lmxm.ute.gui.MainFrame;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Class TaskPopupMenu.
 */
public final class TaskPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4016776722491013039L;

	/** The add task after menu item. */
	private JMenuItem addTaskAfterMenuItem = null;

	/** The add task before menu item. */
	private JMenuItem addTaskBeforeMenuItem = null;

	/** The delete task menu item. */
	private JMenuItem deleteTaskMenuItem = null;

	/** The execute task menu item. */
	private JMenuItem executeTaskMenuItem = null;

	/**
	 * Instantiates a new task popup menu.
	 * 
	 * @param mainFrame the main frame
	 * @param actionListener the action listener
	 */
	public TaskPopupMenu(final MainFrame mainFrame, final ActionListener actionListener) {
		super(mainFrame, actionListener);

		add(getExecuteTaskMenuItem());
		add(getAddTaskBeforeMenuItem());
		add(getAddTaskAfterMenuItem());
		add(getDeleteTaskMenuItem());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems(java.lang.Object)
	 */
	@Override
	public void enableDisableMenuItems(final Object object) {
		if (object == null) {
			getExecuteTaskMenuItem().setEnabled(false);
		}
		else {
			final Task task = (Task) object;

			getExecuteTaskMenuItem().setEnabled(task.getEnabled());
		}
	}

	/**
	 * Gets the adds the task after menu item.
	 * 
	 * @return the adds the task after menu item
	 */
	private JMenuItem getAddTaskAfterMenuItem() {
		if (addTaskAfterMenuItem == null) {
			addTaskAfterMenuItem = new JMenuItem();
			addTaskAfterMenuItem.setText("Add Task After");
			addTaskAfterMenuItem.setEnabled(false); // TODO disabled since it is not implemented
		}
		return addTaskAfterMenuItem;
	}

	/**
	 * Gets the adds the task before menu item.
	 * 
	 * @return the adds the task before menu item
	 */
	private JMenuItem getAddTaskBeforeMenuItem() {
		if (addTaskBeforeMenuItem == null) {
			addTaskBeforeMenuItem = new JMenuItem();
			addTaskBeforeMenuItem.setText("Add Task Before");
			addTaskBeforeMenuItem.setEnabled(false); // TODO disabled since it is not implemented
		}
		return addTaskBeforeMenuItem;
	}

	/**
	 * Gets the delete task menu item.
	 * 
	 * @return the delete task menu item
	 */
	private JMenuItem getDeleteTaskMenuItem() {
		if (deleteTaskMenuItem == null) {
			deleteTaskMenuItem = new JMenuItem();
			deleteTaskMenuItem.setText("Delete Task");
			deleteTaskMenuItem.setEnabled(false); // TODO disabled since it is not implemented
		}
		return deleteTaskMenuItem;
	}

	/**
	 * This method initializes executeTaskMenuItem.
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getExecuteTaskMenuItem() {
		if (executeTaskMenuItem == null) {
			executeTaskMenuItem = new JMenuItem();
			executeTaskMenuItem.setText("Execute Task");
			executeTaskMenuItem.setIcon(ImageUtil.EXECUTE_ICON);
			executeTaskMenuItem.addActionListener(getActionListener());
			executeTaskMenuItem.setActionCommand(ActionConstants.EXECUTE);
		}
		return executeTaskMenuItem;
	}
}
