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

import net.lmxm.ute.resources.types.ToolbarButtonResourceType;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Class FileToolBar.
 */
public class FileToolBar extends AbstractToolBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1759208575265786781L;

	/**
	 * Instantiates a new file tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public FileToolBar(final ActionListener actionListener) {
		super(actionListener);

		addToolbarButton(ToolbarButtonResourceType.NEW_FILE);
		addToolbarButton(ToolbarButtonResourceType.OPEN_FILE);
		addToolbarButton(ToolbarButtonResourceType.RELOAD_FILE);
		addToolbarButton(ToolbarButtonResourceType.SAVE_FILE);
		addToolbarButton(ToolbarButtonResourceType.SAVE_FILE_AS);
		addSeparator(new Dimension(20, 20));
		addToolbarButton(ToolbarButtonResourceType.EXIT);
	}
}
