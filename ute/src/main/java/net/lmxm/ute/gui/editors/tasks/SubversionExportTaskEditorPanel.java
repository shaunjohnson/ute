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
package net.lmxm.ute.gui.editors.tasks;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;

/**
 * The Class SubversionExportTaskEditorPanel.
 */
public final class SubversionExportTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class SubversionExportTaskEditorToolBar.
	 */
	private static class SubversionExportTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -4774652510779094497L;

		/**
		 * Instantiates a new subversion export task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public SubversionExportTaskEditorToolBar(final ActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -254745593912919513L;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionExportTaskEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.SUBVERSION_EXPORT_TASK, new SubversionExportTaskEditorToolBar(actionListener),
				actionListener);

		final JPanel contentPanel = getContentPanel();

		addTaskCommonFields();

		addSeparator(contentPanel, GuiComponentLabel.SOURCE);
		addSubversionRepositorySourceFields();

		addSeparator(contentPanel, GuiComponentLabel.TARGET);
		addFileSystemTargetFields();
		addFilesFields();
	}
}
