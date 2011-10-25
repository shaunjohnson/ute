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

import net.lmxm.ute.gui.components.GuiComponentButton;
import net.lmxm.ute.gui.components.GuiComponentFactory;

/**
 * The Class PreferencesEditorToolBar.
 */
public class PreferencesEditorToolBar extends AbstractToolBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1596742516975543683L;

	/** The add preference button. */
	private JButton addPreferenceButton = null;

	/**
	 * Instantiates a new preferences editor tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public PreferencesEditorToolBar(final ActionListener actionListener) {
		super(actionListener);

		setBorder(EDITER_TOOLBAR_BORDER);

		add(getAddPreferenceButton());
	}

	/**
	 * Gets the adds the preference button.
	 * 
	 * @return the adds the preference button
	 */
	private JButton getAddPreferenceButton() {
		if (addPreferenceButton == null) {
			addPreferenceButton = GuiComponentFactory.createButton(GuiComponentButton.ADD_PREFERENCE,
					getActionListener());
		}
		return addPreferenceButton;
	}
}
