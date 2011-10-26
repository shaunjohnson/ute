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

import javax.swing.JButton;

import net.lmxm.ute.gui.components.GuiComponentButton;
import net.lmxm.ute.gui.components.GuiComponentFactory;

/**
 * The Class SubversionRepositoryLocationsEditorToolBar.
 */
public class SubversionRepositoryLocationsEditorToolBar extends AbstractToolBar {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2727238704897494027L;

	/** The add subversion repository location button. */
	private JButton addSubversionRepositoryLocationButton = null;

	/**
	 * Instantiates a new subversion repository locations editor tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionRepositoryLocationsEditorToolBar(final ActionListener actionListener) {
		super(actionListener);

		add(getAddSubversionRepositoryLocationButton());
	}

	/**
	 * Gets the adds the subversion repository location button.
	 * 
	 * @return the adds the subversion repository location button
	 */
	private JButton getAddSubversionRepositoryLocationButton() {
		if (addSubversionRepositoryLocationButton == null) {
			addSubversionRepositoryLocationButton = GuiComponentFactory.createButton(
					GuiComponentButton.ADD_SUBVERSION_REPOSITORY_LOCATION, getActionListener());
		}
		return addSubversionRepositoryLocationButton;
	}
}
