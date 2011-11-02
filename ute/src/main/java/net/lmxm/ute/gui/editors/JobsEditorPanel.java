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

import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.components.GuiComponentToolbarButton;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;

/**
 * The Class JobsEditorPanel.
 */
public class JobsEditorPanel extends AbstractReadonlyEditorPanel {

	/**
	 * The Class JobsEditorToolBar.
	 */
	private static class JobsEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -2652922370956062365L;

		/**
		 * Instantiates a new jobs editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public JobsEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(GuiComponentToolbarButton.ADD_JOB);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1456434546501638076L;

	/**
	 * Instantiates a new jobs editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public JobsEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.JOBS, new JobsEditorToolBar(actionListener), actionListener);
	}
}
