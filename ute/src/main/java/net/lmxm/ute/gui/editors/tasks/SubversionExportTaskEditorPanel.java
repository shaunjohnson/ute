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
import javax.swing.JToolBar;

import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.toolbars.SubversionExportTaskEditorToolBar;

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

	/** The tool bar. */
	private JToolBar toolBar;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionExportTaskEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.SUBVERSION_EXPORT_TASK, actionListener);

		final JPanel contentPanel = getContentPanel();

		addTaskCommonFields();

		addSeparator(contentPanel, GuiComponentLabel.SOURCE);
		addSubversionRepositorySourceFields();

		addSeparator(contentPanel, GuiComponentLabel.TARGET);
		addFileSystemTargetFields();
		addFilesFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getToolBar()
	 */
	@Override
	protected JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new SubversionExportTaskEditorToolBar(getActionListener());
		}
		return toolBar;
	}

	/**
	 * Load data.
	 * 
	 * @param subversionExportTask the subversion export task
	 */
	public void loadData(final SubversionExportTask subversionExportTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, subversionExportTask={}", prefix, subversionExportTask);

		setUserObject(subversionExportTask);

		final SubversionRepositorySource source = subversionExportTask == null ? null : subversionExportTask
				.getSource();
		final FileSystemTarget target = subversionExportTask == null ? null : subversionExportTask.getTarget();

		loadTaskCommonFieldData();
		loadSubversionRepositorySourceFieldData(source);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(subversionExportTask);

		LOGGER.debug("{} leaving", prefix);
	}
}
