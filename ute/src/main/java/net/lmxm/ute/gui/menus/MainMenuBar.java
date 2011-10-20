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

import static net.lmxm.ute.gui.ActionConstants.EXIT;
import static net.lmxm.ute.gui.ActionConstants.NEW_FILE;
import static net.lmxm.ute.gui.ActionConstants.OPEN_FILE;
import static net.lmxm.ute.gui.ActionConstants.SAVE_FILE;
import static net.lmxm.ute.gui.ActionConstants.SAVE_FILE_AS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import net.lmxm.ute.ConfigurationHolder;
import net.lmxm.ute.gui.dialogs.AboutDialog;
import net.lmxm.ute.gui.dialogs.EditPreferencesDialog;
import net.lmxm.ute.gui.utils.DialogUtil;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Class MainMenuBar.
 */
@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1523852524013333901L;

	/** The about menu item. */
	private JMenuItem aboutMenuItem = null;

	/** The action listener. */
	private final ActionListener actionListener;

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

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;
	
	/**
	 * Instantiates a new main menu bar.
	 * 
	 * @param actionListener the action listener
	 */
	public MainMenuBar(ConfigurationHolder configurationHolder, ActionListener actionListener) {
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
			aboutMenuItem = new JMenuItem() {{
				setText("About");
				setIcon(ImageUtil.ABOUT_ICON);
			
				addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent actionEvent) {
						final JDialog dialog = new AboutDialog();
						DialogUtil.center(dialog);
						dialog.setVisible(true);
					}
				});
			}};
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
			editMenu = new JMenu() {{
				setText("Edit");
				
				add(getEditPreferencesMenuItem());
			}};
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
			editPreferencesMenuItem = new JMenuItem() {{
				setText("Edit Preferences");
				setIcon(ImageUtil.EDIT_PREFERENCES_ICON);

				addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent actionEvent) {
						final EditPreferencesDialog dialog = new EditPreferencesDialog(configurationHolder.getConfiguration());
						DialogUtil.center(dialog);
						dialog.setVisible(true);
					}
				});
			}};
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
			exitMenuItem = new JMenuItem() {{
				setText("Exit");
				setIcon(ImageUtil.EXIT_ICON);

				addActionListener(getActionListener());
				setActionCommand(EXIT);
			}};
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
			fileMenu = new JMenu() {{
				setText("File");
				
				add(getNewFileMenuItem());
				add(getOpenFileMenuItem());
				add(getSaveMenuItem());
				add(getSaveAsMenuItem());
				add(new JSeparator());
				add(getExitMenuItem());
			}};
		}
		return fileMenu;
	}

	/**
	 * Gets the help menu.
	 * 
	 * @return the help menu
	 */
	public JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu() {{
				setText("Help");
				add(getAboutMenuItem());
			}};
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
			newFileMenuItem = new JMenuItem() {{
				setText("New");
				setIcon(ImageUtil.NEW_FILE_ICON);
				setEnabled(false); // TODO disabled since it is not implemented
				
				addActionListener(getActionListener());
				setActionCommand(NEW_FILE);
			}};
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
			openFileMenuItem = new JMenuItem() {{
				setText("Open...");
				setIcon(ImageUtil.OPEN_FILE_ICON);
				
				addActionListener(getActionListener());
				setActionCommand(OPEN_FILE);
			}};
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
			saveAsMenuItem = new JMenuItem() {{
				setText("Save as...");
				setIcon(ImageUtil.SAVE_FILE_AS_ICON);
				setEnabled(false); // TODO disabled since it is not implemented
				
				addActionListener(getActionListener());
				setActionCommand(SAVE_FILE_AS);
			}};
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
			saveMenuItem = new JMenuItem() {{
				setText("Save");
				setIcon(ImageUtil.SAVE_FILE_ICON);
				setEnabled(false); // TODO disabled since it is not implemented
				
				addActionListener(getActionListener());
				setActionCommand(SAVE_FILE);
			}};
		}
		return saveMenuItem;
	}
}
