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

import net.lmxm.ute.gui.editors.AbstractReadonlyEditorPanel;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.lmxm.ute.resources.types.ToolbarButtonResourceType;

/**
 * The Class SubversionRepositoryLocationsEditorPanel.
 */
public class SubversionRepositoryLocationsEditorPanel extends AbstractReadonlyEditorPanel {

	/**
	 * The Class SubversionRepositoryLocationsEditorToolBar.
	 */
	private static class SubversionRepositoryLocationsEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 2727238704897494027L;

		/**
		 * Instantiates a new subversion repository locations editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public SubversionRepositoryLocationsEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.ADD_SUBVERSION_REPOSITORY_LOCATION);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1279109535635849415L;

	/**
	 * Instantiates a new subversion repository locations editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionRepositoryLocationsEditorPanel(final ActionListener actionListener) {
		super(LabelResourceType.SUBVERSION_REPOSITORY_LOCATIONS, new SubversionRepositoryLocationsEditorToolBar(
				actionListener), actionListener);

		addFields();
	}
}
