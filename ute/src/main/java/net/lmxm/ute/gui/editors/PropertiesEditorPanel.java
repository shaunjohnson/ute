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
import net.lmxm.ute.gui.toolbars.PropertiesEditorToolBar;

/**
 * The Class PropertiesEditorPanel.
 */
public class PropertiesEditorPanel extends AbstractEditorPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2351152862222662562L;

	/** The tool bar. */
	private JToolBar toolBar;

	/**
	 * Instantiates a new properties editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public PropertiesEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.PROPERTIES, actionListener);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getToolBar()
	 */
	@Override
	protected JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new PropertiesEditorToolBar(getActionListener());
		}
		return toolBar;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#loadData()
	 */
	@Override
	protected void loadData() {

	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#setFocusToFirstInput()
	 */
	@Override
	public void setFocusToFirstInput() {

	}
}
