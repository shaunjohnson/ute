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

import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.resources.types.MenuItemResourceType;
import net.lmxm.ute.resources.types.MenuResourceType;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The Class MainMenuBar.
 */
public final class MainMenuBar extends AbstractMenuBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1523852524013333901L;

	/** The edit menu. */
	private JMenu editMenu = null;

	/** The file menu. */
	private JMenu fileMenu = null;

	/** The help menu. */
	private JMenu helpMenu = null;

	/**
	 * Instantiates a new main menu bar.
	 * 
	 * @param actionListener the action listener
	 */
	public MainMenuBar(final UteActionListener actionListener) {
		super(actionListener);

		add(getFileMenu());
		add(getEditMenu());
		add(getHelpMenu());
	}

	/**
	 * Gets the edits the menu.
	 * 
	 * @return the edits the menu
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = GuiComponentFactory.createMenu(MenuResourceType.EDIT);

			addMenuItem(editMenu, MenuItemResourceType.EDIT_PREFERENCES);
		}
		return editMenu;
	}

	/**
	 * Gets the file menu.
	 * 
	 * @return the file menu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = GuiComponentFactory.createMenu(MenuResourceType.FILE);

			addMenuItem(fileMenu, MenuItemResourceType.NEW_FILE);
			addMenuItem(fileMenu, MenuItemResourceType.OPEN_FILE);
			addMenuItem(fileMenu, MenuItemResourceType.RELOAD_FILE);
			addMenuItem(fileMenu, MenuItemResourceType.SAVE_FILE);
			addMenuItem(fileMenu, MenuItemResourceType.SAVE_FILE_AS);
			fileMenu.add(new JSeparator());
			addMenuItem(fileMenu, MenuItemResourceType.EXIT);
		}
		return fileMenu;
	}

	/**
	 * Gets the help menu.
	 * 
	 * @return the help menu
	 */
	@Override
	public JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = GuiComponentFactory.createMenu(MenuResourceType.HELP);

			addMenuItem(helpMenu, MenuItemResourceType.ABOUT);
		}
		return helpMenu;
	}

}
