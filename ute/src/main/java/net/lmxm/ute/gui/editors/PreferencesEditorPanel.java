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
package net.lmxm.ute.gui.editors;

import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.components.GuiComponentToolbarButton;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;

/**
 * The Class PreferencesEditorPanel.
 */
public class PreferencesEditorPanel extends AbstractReadonlyEditorPanel {

	/**
	 * The Class PreferencesEditorToolBar.
	 */
	private static class PreferencesEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1596742516975543683L;

		/**
		 * Instantiates a new preferences editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public PreferencesEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(GuiComponentToolbarButton.ADD_PREFERENCE);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3805356364223511110L;

	/**
	 * Instantiates a new properties editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public PreferencesEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.PREFERENCES, actionListener);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getToolBar()
	 */
	@Override
	protected JToolBar getToolBar() {
		return new PreferencesEditorToolBar(getActionListener());
	}
}
