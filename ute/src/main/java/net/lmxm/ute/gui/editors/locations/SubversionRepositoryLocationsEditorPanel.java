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
package net.lmxm.ute.gui.editors.locations;

import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.editors.AbstractEditorPanel;
import net.lmxm.ute.gui.toolbars.SubversionRepositoryLocationsEditorToolBar;

/**
 * The Class SubversionRepositoryLocationsEditorPanel.
 */
public class SubversionRepositoryLocationsEditorPanel extends AbstractEditorPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1279109535635849415L;

	/** The tool bar. */
	private JToolBar toolBar;

	/**
	 * Instantiates a new subversion repository locations editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionRepositoryLocationsEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.SUBVERSION_REPOSITORY_LOCATIONS, actionListener);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getToolBar()
	 */
	@Override
	protected JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new SubversionRepositoryLocationsEditorToolBar(getActionListener());
		}
		return toolBar;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#setFocusToFirstInput()
	 */
	@Override
	public void setFocusToFirstInput() {

	}
}
