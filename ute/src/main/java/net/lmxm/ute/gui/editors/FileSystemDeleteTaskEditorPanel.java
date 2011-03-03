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
package net.lmxm.ute.gui.editors;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FileSystemDeleteTaskEditorPanel.
 */
public final class FileSystemDeleteTaskEditorPanel extends AbstractTaskEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemDeleteTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2318061314941784888L;

	/**
	 * Instantiates a new job editor panel.
	 *
	 * @param configuration the configuration
	 */
	public FileSystemDeleteTaskEditorPanel(final Configuration configuration) {
		super(configuration, "File System Delete Task");

		addTaskCommonFields();
		addFileSystemTargetFields();
		addFilesFields();
	}

	/**
	 * Load data.
	 *
	 * @param fileSystemDeleteTask the file system delete task
	 */
	public void loadData(final FileSystemDeleteTask fileSystemDeleteTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, fileSystemDeleteTask={}", prefix, fileSystemDeleteTask);

		final FileSystemTarget target = fileSystemDeleteTask == null ? null : fileSystemDeleteTask.getTarget();

		loadTaskCommonFieldData(fileSystemDeleteTask);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(fileSystemDeleteTask);

		LOGGER.debug("{} leaving", prefix);
	}
}
