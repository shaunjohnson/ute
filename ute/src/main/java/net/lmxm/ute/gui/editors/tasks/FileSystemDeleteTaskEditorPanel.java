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
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.gui.components.LabelResourceType;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;

/**
 * The Class FileSystemDeleteTaskEditorPanel.
 */
public final class FileSystemDeleteTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class FileSystemDeleteTaskEditorToolBar.
	 */
	private static class FileSystemDeleteTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 3117780598467179092L;

		/**
		 * Instantiates a new file system delete task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public FileSystemDeleteTaskEditorToolBar(final ActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2318061314941784888L;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public FileSystemDeleteTaskEditorPanel(final ActionListener actionListener) {
		super(LabelResourceType.FILE_SYSTEM_DELETE_TASK, new FileSystemDeleteTaskEditorToolBar(actionListener),
				actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new FileSystemDeleteTask(new SequentialJob());
	}
}
