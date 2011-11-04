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

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.resources.MenuItemResourceType;

/**
 * The Class AbstractMenuBar.
 */
public abstract class AbstractMenuBar extends JMenuBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 345845895637052235L;

	/** The action listener. */
	private final ActionListener actionListener;

	/**
	 * Instantiates a new abstract menu bar.
	 * 
	 * @param actionListener the action listener
	 */
	public AbstractMenuBar(final ActionListener actionListener) {
		super();

		this.actionListener = actionListener;
	}

	/**
	 * Adds the menu item.
	 * 
	 * @param menu the menu
	 * @param guiComponentMenuItem the gui component menu item
	 */
	protected final void addMenuItem(final JMenu menu, final MenuItemResourceType guiComponentMenuItem) {
		menu.add(GuiComponentFactory.createMenuItem(guiComponentMenuItem, actionListener));
	}
}
