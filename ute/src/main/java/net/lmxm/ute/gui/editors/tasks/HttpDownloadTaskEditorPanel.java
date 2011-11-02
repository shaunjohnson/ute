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
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;

/**
 * The Class HttpDownloadTaskEditorPanel.
 */
public final class HttpDownloadTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class HttpDownloadTaskEditorToolBar.
	 */
	private static class HttpDownloadTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 2430542852877132983L;

		/**
		 * Instantiates a new http download task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public HttpDownloadTaskEditorToolBar(final ActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7076073228977636114L;

	/**
	 * Instantiates a new http download task editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public HttpDownloadTaskEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.HTTP_DOWNLOAD_TASK, new HttpDownloadTaskEditorToolBar(actionListener), actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new HttpDownloadTask(new SequentialJob());
	}
}
