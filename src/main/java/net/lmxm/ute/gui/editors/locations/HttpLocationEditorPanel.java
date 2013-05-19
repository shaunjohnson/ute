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

import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.lmxm.ute.resources.types.ToolbarButtonResourceType;

import java.awt.event.ActionListener;

/**
 * The Class HttpLocationEditorPanel.
 */
public final class HttpLocationEditorPanel extends AbstractHttpLocationEditorPanel {

	/**
	 * The Class HttpLocationEditorToolBar.
	 */
	private static class HttpLocationEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 7975688924393008182L;

		/**
		 * Instantiates a new http location editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public HttpLocationEditorToolBar(final UteActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.DELETE_HTTP_LOCATION);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4152865192476292446L;

	/**
	 * Instantiates a new http location editor panel.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public HttpLocationEditorPanel(final ConfigurationHolder configurationHolder, final UteActionListener actionListener) {
		super(LabelResourceType.HTTP_LOCATION, new HttpLocationEditorToolBar(actionListener), configurationHolder,
				actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new HttpLocation();
	}
}
