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

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.SubversionExportTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SubversionExportTaskEditorPanel.
 */
public final class SubversionExportTaskEditorPanel extends AbstractTaskEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionExportTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -254745593912919513L;

	/**
	 * Instantiates a new job editor panel.
	 *
	 * @param configuration the configuration
	 */
	public SubversionExportTaskEditorPanel(final Configuration configuration) {
		super(configuration, "Subversion Export Task");

		addTaskCommonFields();
		addSubversionRepositorySourceFields();
		addFileSystemTargetFields();
		addFilesFields();
	}

	/**
	 * Load data.
	 *
	 * @param subversionExportTask the subversion export task
	 */
	public void loadData(final SubversionExportTask subversionExportTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, subversionExportTask={}", prefix, subversionExportTask);

		final SubversionRepositorySource source = subversionExportTask == null ? null : subversionExportTask
				.getSource();
		final FileSystemTarget target = subversionExportTask == null ? null : subversionExportTask.getTarget();

		loadTaskCommonFieldData(subversionExportTask);
		loadSubversionRepositorySourceFieldData(source);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(subversionExportTask);

		LOGGER.debug("{} leaving", prefix);
	}
}
