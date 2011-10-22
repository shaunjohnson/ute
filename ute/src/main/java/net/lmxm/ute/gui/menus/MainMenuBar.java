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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import net.lmxm.ute.ConfigurationHolder;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentMenu;
import net.lmxm.ute.gui.components.GuiComponentMenuItem;
import net.lmxm.ute.gui.dialogs.AboutDialog;
import net.lmxm.ute.gui.dialogs.EditPreferencesDialog;
import net.lmxm.ute.gui.utils.DialogUtil;

/**
 * The Class MainMenuBar.
 */
public class MainMenuBar extends JMenuBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1523852524013333901L;

	/** The about menu item. */
	private JMenuItem aboutMenuItem = null;

	/** The action listener. */
	private final ActionListener actionListener;

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/** The edit menu. */
	private JMenu editMenu = null;

	/** The edit preferences menu item. */
	private JMenuItem editPreferencesMenuItem = null;

	/** The exit menu item. */
	private JMenuItem exitMenuItem = null;

	/** The file menu. */
	private JMenu fileMenu = null;

	/** The help menu. */
	private JMenu helpMenu = null;

	/** The new file menu item. */
	private JMenuItem newFileMenuItem = null;

	/** The open file menu item. */
	private JMenuItem openFileMenuItem = null;

	/** The save as menu item. */
	private JMenuItem saveAsMenuItem = null;

	/** The save menu item. */
	private JMenuItem saveMenuItem = null;

	/**
	 * Instantiates a new main menu bar.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public MainMenuBar(final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super();

		this.configurationHolder = configurationHolder;
		this.actionListener = actionListener;

		add(getFileMenu());
		add(getEditMenu());
		add(getHelpMenu());
	}

	/**
	 * Gets the about menu item.
	 * 
	 * @return the about menu item
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.ABOUT, new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent actionEvent) {
					final JDialog dialog = new AboutDialog();
					DialogUtil.center(dialog);
					dialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * Gets the action listener.
	 * 
	 * @return the action listener
	 */
	private ActionListener getActionListener() {
		return actionListener;
	}

	/**
	 * Gets the edits the menu.
	 * 
	 * @return the edits the menu
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = GuiComponentFactory.createMenu(GuiComponentMenu.EDIT);
			editMenu.add(getEditPreferencesMenuItem());
		}
		return editMenu;
	}

	/**
	 * Gets the edits the preferences menu item.
	 * 
	 * @return the edits the preferences menu item
	 */
	private JMenuItem getEditPreferencesMenuItem() {
		if (editPreferencesMenuItem == null) {
			editPreferencesMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.EDIT_PREFERENCES,
					new ActionListener() {
						@Override
						public void actionPerformed(final ActionEvent actionEvent) {
							final EditPreferencesDialog dialog = new EditPreferencesDialog(configurationHolder
									.getConfiguration());
							DialogUtil.center(dialog);
							dialog.setVisible(true);
						}
					});
		}
		return editPreferencesMenuItem;
	}

	/**
	 * Gets the exit menu item.
	 * 
	 * @return the exit menu item
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.EXIT, getActionListener());
		}
		return exitMenuItem;
	}

	/**
	 * Gets the file menu.
	 * 
	 * @return the file menu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = GuiComponentFactory.createMenu(GuiComponentMenu.FILE);
			fileMenu.add(getNewFileMenuItem());
			fileMenu.add(getOpenFileMenuItem());
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getSaveAsMenuItem());
			fileMenu.add(new JSeparator());
			fileMenu.add(getExitMenuItem());
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
			helpMenu = GuiComponentFactory.createMenu(GuiComponentMenu.HELP);
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * Gets the new file menu item.
	 * 
	 * @return the new file menu item
	 */
	private JMenuItem getNewFileMenuItem() {
		if (newFileMenuItem == null) {
			newFileMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.NEW_FILE, getActionListener());
			newFileMenuItem.setEnabled(false);
		}
		return newFileMenuItem;
	}

	/**
	 * Gets the open file menu item.
	 * 
	 * @return the open file menu item
	 */
	private JMenuItem getOpenFileMenuItem() {
		if (openFileMenuItem == null) {
			openFileMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.OPEN_FILE, getActionListener());
		}
		return openFileMenuItem;
	}

	/**
	 * Gets the save as menu item.
	 * 
	 * @return the save as menu item
	 */
	private JMenuItem getSaveAsMenuItem() {
		if (saveAsMenuItem == null) {
			saveAsMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.SAVE_FILE_AS, getActionListener());
			saveAsMenuItem.setEnabled(false);
		}
		return saveAsMenuItem;
	}

	/**
	 * Gets the save menu item.
	 * 
	 * @return the save menu item
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = GuiComponentFactory.createMenuItem(GuiComponentMenuItem.SAVE_FILE, getActionListener());
			saveMenuItem.setEnabled(false);
		}
		return saveMenuItem;
	}
}
