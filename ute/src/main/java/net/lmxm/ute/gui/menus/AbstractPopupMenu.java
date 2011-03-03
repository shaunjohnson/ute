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

import net.lmxm.ute.gui.MainFrame;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractPopupMenu.
 */
public abstract class AbstractPopupMenu extends JPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7518698215552137785L;

	/** The action listener. */
	private final ActionListener actionListener;

	/** The main frame. */
	private final MainFrame mainFrame;

	/**
	 * Instantiates a new abstract popup menu.
	 *
	 * @param mainFrame the main frame
	 * @param actionListener the action listener
	 */
	public AbstractPopupMenu(final MainFrame mainFrame, final ActionListener actionListener) {
		super();

		Preconditions.checkNotNull(mainFrame, "Main frame may not be null");
		Preconditions.checkNotNull(actionListener, "Action listener may not be null");

		this.actionListener = actionListener;
		this.mainFrame = mainFrame;
	}

	/**
	 * Enable disable menu items.
	 */
	public abstract void enableDisableMenuItems();

	/**
	 * Gets the action listener.
	 *
	 * @return the action listener
	 */
	protected final ActionListener getActionListener() {
		return actionListener;
	}

	/**
	 * Gets the main frame.
	 *
	 * @return the main frame
	 */
	protected final MainFrame getMainFrame() {
		return mainFrame;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JPopupMenu#show(java.awt.Component, int, int)
	 */
	@Override
	public final void show(final Component invoker, final int x, final int y) {
		enableDisableMenuItems();

		super.show(invoker, x, y);
	}
}
