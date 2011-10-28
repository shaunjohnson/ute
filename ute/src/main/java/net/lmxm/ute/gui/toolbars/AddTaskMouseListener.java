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
package net.lmxm.ute.gui.toolbars;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import net.lmxm.ute.gui.menus.AddTaskPopupMenu;

/**
 * The listener interface for receiving addTaskMouse events. The class that is interested in processing a addTaskMouse
 * event implements this interface, and the object created with that class is registered with a component using the
 * component's <code>addAddTaskMouseListener<code> method. When
 * the addTaskMouse event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see AddTaskMouseEvent
 */
public class AddTaskMouseListener extends MouseAdapter {

	/** The add task popup menu. */
	private final JPopupMenu addTaskPopupMenu;

	/**
	 * Instantiates a new adds the task mouse listener.
	 * 
	 * @param actionListener the action listener
	 */
	public AddTaskMouseListener(final ActionListener actionListener) {
		super();

		addTaskPopupMenu = new AddTaskPopupMenu(actionListener);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(final MouseEvent mouseEvent) {
		addTaskPopupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
	}
}
