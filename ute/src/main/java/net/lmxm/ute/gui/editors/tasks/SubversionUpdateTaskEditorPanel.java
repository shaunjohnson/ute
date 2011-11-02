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

import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;

/**
 * The Class SubversionUpdateTaskEditorPanel.
 */
public final class SubversionUpdateTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class SubversionUpdateTaskEditorToolBar.
	 */
	private static class SubversionUpdateTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -7138989729064070675L;

		/**
		 * Instantiates a new subversion update task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public SubversionUpdateTaskEditorToolBar(final ActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 12035329665657526L;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionUpdateTaskEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.SUBVERSION_UPDATE_TASK, new SubversionUpdateTaskEditorToolBar(actionListener),
				actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractCommonEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		addSeparator(GuiComponentLabel.SOURCE);
		addSubversionRepositorySourceFields();

		addSeparator(GuiComponentLabel.TARGET);
		addFileSystemTargetFields();
		addFilesFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new SubversionUpdateTask(new SequentialJob());
	}
}
