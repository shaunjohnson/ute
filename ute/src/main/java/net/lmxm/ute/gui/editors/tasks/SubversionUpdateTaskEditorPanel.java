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

import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.gui.components.GuiComponentLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SubversionUpdateTaskEditorPanel.
 */
public final class SubversionUpdateTaskEditorPanel extends AbstractTaskEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionUpdateTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 12035329665657526L;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionUpdateTaskEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.SUBVERSION_UPDATE_TASK, actionListener);

		final JPanel contentPanel = getContentPanel();

		addTaskCommonFields();

		addSeparator(contentPanel, GuiComponentLabel.SOURCE);
		addSubversionRepositorySourceFields();

		addSeparator(contentPanel, GuiComponentLabel.TARGET);
		addFileSystemTargetFields();
		addFilesFields();
	}

	/**
	 * Load data.
	 * 
	 * @param subversionUpdateTask the subversion update task
	 */
	public void loadData(final SubversionUpdateTask subversionUpdateTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, subversionUpdateTask={}", prefix, subversionUpdateTask);

		final SubversionRepositorySource source = subversionUpdateTask == null ? null : subversionUpdateTask
				.getSource();
		final FileSystemTarget target = subversionUpdateTask == null ? null : subversionUpdateTask.getTarget();

		loadTaskCommonFieldData(subversionUpdateTask);
		loadSubversionRepositorySourceFieldData(source);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(subversionUpdateTask);

		LOGGER.debug("{} leaving", prefix);
	}
}
