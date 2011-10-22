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

import javax.swing.JButton;

import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentType;

/**
 * The Class FileToolBar.
 */
public class FileToolBar extends AbstractToolBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1759208575265786781L;

	/** The exit button. */
	private JButton exitButton = null;

	/** The new file button. */
	private JButton newFileButton = null;

	/** The open file button. */
	private JButton openFileButton = null;

	/** The save as button. */
	private JButton saveAsButton = null;

	/** The save button. */
	private JButton saveButton = null;

	/**
	 * Instantiates a new file tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public FileToolBar(final ActionListener actionListener) {
		super(actionListener);

		setBorder(TOOLBAR_BORDER);

		add(getNewFileButton());
		add(getOpenFileButton());
		add(getSaveButton());
		add(getSaveAsButton());
		add(getExitButton());
	}

	/**
	 * Gets the exit button.
	 * 
	 * @return the exit button
	 */
	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = GuiComponentFactory.createButton(GuiComponentType.EXIT_BUTTON, getActionListener());
		}
		return exitButton;
	}

	/**
	 * Gets the new file button.
	 * 
	 * @return the new file button
	 */
	private JButton getNewFileButton() {
		if (newFileButton == null) {
			newFileButton = GuiComponentFactory.createButton(GuiComponentType.NEW_FILE_BUTTON, getActionListener());
			newFileButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return newFileButton;
	}

	/**
	 * Gets the open file button.
	 * 
	 * @return the open file button
	 */
	private JButton getOpenFileButton() {
		if (openFileButton == null) {
			openFileButton = GuiComponentFactory.createButton(GuiComponentType.OPEN_FILE_BUTTON, getActionListener());
		}
		return openFileButton;
	}

	/**
	 * Gets the save as button.
	 * 
	 * @return the save as button
	 */
	private JButton getSaveAsButton() {
		if (saveAsButton == null) {
			saveAsButton = GuiComponentFactory.createButton(GuiComponentType.SAVE_FILE_AS_BUTTON, getActionListener());
			saveAsButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return saveAsButton;
	}

	/**
	 * Gets the save button.
	 * 
	 * @return the save button
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = GuiComponentFactory.createButton(GuiComponentType.SAVE_FILE_BUTTON, getActionListener());
			saveButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return saveButton;
	}
}
