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

import javax.swing.JPanel;
import javax.swing.JToolBar;

import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.toolbars.JobEditorToolBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SequentialJobEditorPanel.
 */
public final class SequentialJobEditorPanel extends AbstractIdEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SequentialJobEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -254745593912919513L;

	/** The tool bar. */
	private JToolBar toolBar;

	/**
	 * Instantiates a new sequential job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SequentialJobEditorPanel(final ActionListener actionListener) {
		super(GuiComponentLabel.SEQUENTIAL_JOB, actionListener);

		final JPanel contentPanel = getContentPanel();

		addIdCommonFields();

		addLabel(contentPanel, GuiComponentLabel.DESCRIPTION);
		contentPanel.add(getDescriptionPane());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getToolBar()
	 */
	@Override
	protected JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JobEditorToolBar(getActionListener());
		}
		return toolBar;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractIdEditorPanel#loadData()
	 */
	@Override
	public void loadData() {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered", prefix);

		super.loadData();

		if (getUserObject() instanceof SequentialJob) {
			final SequentialJob sequentialJob = (SequentialJob) getUserObject();

			getDescriptionTextArea().setText(sequentialJob.getDescription());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
