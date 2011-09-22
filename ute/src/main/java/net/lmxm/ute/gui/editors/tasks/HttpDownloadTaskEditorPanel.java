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

import javax.swing.JPanel;

import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class HttpDownloadTaskEditorPanel.
 */
public final class HttpDownloadTaskEditorPanel extends AbstractTaskEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpDownloadTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7076073228977636114L;

	/**
	 * Instantiates a new http download task editor panel.
	 */
	public HttpDownloadTaskEditorPanel() {
		super("HTTP Download Task");

		final JPanel contentPanel = getContentPanel();

		addTaskCommonFields();

		addSeparator(contentPanel, "Source");
		addHttpSourceFields();

		addSeparator(contentPanel, "Target");
		addFileSystemTargetFields();
		addFilesFields();
	}

	/**
	 * Load data.
	 * 
	 * @param httpDownloadTask the http download task
	 */
	public void loadData(final HttpDownloadTask httpDownloadTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, httpDownloadTask={}", prefix, httpDownloadTask);

		final HttpSource source = httpDownloadTask == null ? null : httpDownloadTask.getSource();
		final FileSystemTarget target = httpDownloadTask == null ? null : httpDownloadTask.getTarget();

		loadTaskCommonFieldData(httpDownloadTask);
		loadHttpSourceFieldData(source);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(httpDownloadTask);

		LOGGER.debug("{} leaving", prefix);
	}
}
