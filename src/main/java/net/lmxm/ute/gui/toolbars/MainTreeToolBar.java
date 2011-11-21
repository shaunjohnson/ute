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

import java.awt.Dimension;
import java.awt.event.ActionListener;

import net.lmxm.ute.resources.types.ToolbarButtonResourceType;

/**
 * The Class MainTreeToolBar.
 */
public class MainTreeToolBar extends AbstractToolBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -354062493293031844L;

	/**
	 * Instantiates a new main tree tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public MainTreeToolBar(final ActionListener actionListener) {
		super(actionListener);

		addToolbarButtonNoText(ToolbarButtonResourceType.ADD_JOB);
		addToolbarButtonNoText(ToolbarButtonResourceType.ADD_FILE_SYSTEM_LOCATION);
		addToolbarButtonNoText(ToolbarButtonResourceType.ADD_HTTP_LOCATION);
		addToolbarButtonNoText(ToolbarButtonResourceType.ADD_SUBVERSION_REPOSITORY_LOCATION);
		addToolbarButtonNoText(ToolbarButtonResourceType.ADD_PROPERTY);
		addToolbarButtonNoText(ToolbarButtonResourceType.ADD_PREFERENCE);
		addSeparator(new Dimension(20, 20));
		addToolbarButtonNoText(ToolbarButtonResourceType.COLLAPSE);
		addToolbarButtonNoText(ToolbarButtonResourceType.EXPAND);
	}
}
