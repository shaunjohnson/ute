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

import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentMenuItem;

/**
 * The Class TaskPopupMenu.
 */
public final class TaskPopupMenu extends AbstractPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4016776722491013039L;

	/** The add task menu item. */
	private JMenuItem addTaskMenuItem = null;

	/** The delete task menu item. */
	private JMenuItem deleteTaskMenuItem = null;

	/** The execute task menu item. */
	private JMenuItem executeTaskMenuItem = null;

	/**
	 * Instantiates a new task popup menu.
	 * 
	 * @param actionListener the action listener
	 */
	public TaskPopupMenu(final ActionListener actionListener) {
		super(actionListener);

		add(getExecuteTaskMenuItem());
		add(getAddTaskMenuItem());
		add(getDeleteTaskMenuItem());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.lmxm.ute.gui.menus.AbstractPopupMenu#enableDisableMenuItems(java.
	 * lang.Object)
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
	 * Gets the adds the task menu item.
	 * 
	 * @return the adds the task menu item
	 */
	private JMenuItem getAddTaskMenuItem() {
		if (addTaskMenuItem == null) {
			addTaskMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.ADD_TASK, getActionListener());
			addTaskMenuItem.setEnabled(false); // TODO not implemented
		}
		return addTaskMenuItem;
	}

	/**
	 * Gets the delete task menu item.
	 * 
	 * @return the delete task menu item
	 */
	private JMenuItem getDeleteTaskMenuItem() {
		if (deleteTaskMenuItem == null) {
			deleteTaskMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.DELETE_TASK,
					getActionListener());
			deleteTaskMenuItem.setEnabled(false); // TODO not implemented
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
			executeTaskMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.EXECUTE_TASK,
					getActionListener());
		}
		return executeTaskMenuItem;
	}
}
