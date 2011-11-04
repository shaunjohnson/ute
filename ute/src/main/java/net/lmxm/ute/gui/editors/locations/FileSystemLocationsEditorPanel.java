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

import net.lmxm.ute.gui.components.LabelResourceType;
import net.lmxm.ute.gui.components.ToolbarButtonResourceType;
import net.lmxm.ute.gui.editors.AbstractReadonlyEditorPanel;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;

/**
 * The Class HttpLocationsEditorPanel.
 */
public class FileSystemLocationsEditorPanel extends AbstractReadonlyEditorPanel {

	/**
	 * The Class FileSystemLocationsEditorToolBar.
	 */
	private static class FileSystemLocationsEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 8767030052836785508L;

		/**
		 * Instantiates a new file system locations editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public FileSystemLocationsEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.ADD_FILE_SYSTEM_LOCATION);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2293199305022188083L;

	/**
	 * Instantiates a new http locations editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public FileSystemLocationsEditorPanel(final ActionListener actionListener) {
		super(LabelResourceType.FILE_SYSTEM_LOCATIONS, new FileSystemLocationsEditorToolBar(actionListener),
				actionListener);

		addFields();
	}
}
