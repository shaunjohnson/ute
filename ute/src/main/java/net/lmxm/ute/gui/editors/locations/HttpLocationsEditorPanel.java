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

import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.gui.editors.AbstractReadonlyEditorPanel;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.lmxm.ute.resources.types.ToolbarButtonResourceType;

/**
 * The Class HttpLocationsEditorPanel.
 */
public class HttpLocationsEditorPanel extends AbstractReadonlyEditorPanel {

	/**
	 * The Class HttpLocationsEditorToolBar.
	 */
	private static class HttpLocationsEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -690594193291992431L;

		/**
		 * Instantiates a new http locations editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public HttpLocationsEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.ADD_HTTP_LOCATION);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2293199305022188083L;

	/**
	 * Instantiates a new http locations editor panel.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public HttpLocationsEditorPanel(final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super(LabelResourceType.HTTP_LOCATIONS, new HttpLocationsEditorToolBar(actionListener), configurationHolder,
				actionListener);

		addFields();
	}
}
