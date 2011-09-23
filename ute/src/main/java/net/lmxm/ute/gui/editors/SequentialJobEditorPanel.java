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

import javax.swing.JPanel;

import net.lmxm.ute.beans.jobs.Job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SequentialJobEditorPanel.
 */
public final class SequentialJobEditorPanel extends AbstractEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SequentialJobEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -254745593912919513L;

	/**
	 * Instantiates a new sequential job editor panel.
	 */
	public SequentialJobEditorPanel() {
		super("Sequential Job");

		final JPanel contentPanel = getContentPanel();

		addSeparator(contentPanel, "Job");

		addLabel(contentPanel, "ID");
		contentPanel.add(getIdTextField());

		addLabel(contentPanel, "Description");
		contentPanel.add(getDescriptionPane());
	}

	/**
	 * Load data.
	 * 
	 * @param job the job
	 */
	public void loadData(final Job job) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, job={}", prefix, job);

		if (job == null) {
			getIdTextField().setText("");
			getDescriptionTextArea().setText("");
		}
		else {
			getIdTextField().setText(job.getId());
			getDescriptionTextArea().setText(job.getDescription());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
