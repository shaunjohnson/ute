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
import java.awt.event.MouseListener;

import javax.swing.JToolBar;

import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.resources.types.ToolbarButtonResourceType;

/**
 * The Class AbstractToolBar.
 */
public abstract class AbstractToolBar extends JToolBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8062663522559613599L;

	/** The action listener. */
	private final ActionListener actionListener;

	/**
	 * Instantiates a new abstract tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public AbstractToolBar(final ActionListener actionListener) {
		super();

		this.actionListener = actionListener;

		setFloatable(false);
	}

	/**
	 * Adds the toolbar button.
	 * 
	 * @param guiComponentToolbarButton the gui component toolbar button
	 */
	protected final void addToolbarButton(final ToolbarButtonResourceType guiComponentToolbarButton) {
		add(GuiComponentFactory.createToolbarButton(guiComponentToolbarButton, actionListener));
	}

	/**
	 * Adds the toolbar button.
	 * 
	 * @param guiComponentToolbarButton the gui component toolbar button
	 * @param actionListener the action listener
	 */
	protected final void addToolbarButton(final ToolbarButtonResourceType guiComponentToolbarButton,
			final ActionListener actionListener) {
		add(GuiComponentFactory.createToolbarButton(guiComponentToolbarButton, actionListener));
	}

	/**
	 * Adds the toolbar button.
	 * 
	 * @param guiComponentToolbarButton the gui component toolbar button
	 * @param mouseListener the mouse listener
	 */
	protected final void addToolbarButton(final ToolbarButtonResourceType guiComponentToolbarButton,
			final MouseListener mouseListener) {
		add(GuiComponentFactory.createToolbarButton(guiComponentToolbarButton, actionListener, mouseListener));
	}

	/**
	 * Adds the toolbar button no text.
	 * 
	 * @param guiComponentToolbarButton the gui component toolbar button
	 */
	protected final void addToolbarButtonNoText(final ToolbarButtonResourceType guiComponentToolbarButton) {
		add(GuiComponentFactory.createToolbarButtonNoText(guiComponentToolbarButton, actionListener));
	}
}
