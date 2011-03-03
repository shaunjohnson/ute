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
import net.lmxm.ute.beans.jobs.Job;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class JobEditorPanel.
 */
public final class JobEditorPanel extends AbstractEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(JobEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -254745593912919513L;

	/**
	 * Instantiates a new job editor panel.
	 *
	 * @param configuration the configuration
	 */
	public JobEditorPanel(final Configuration configuration) {
		super(configuration, "Job");

		final JPanel contentPanel = getContentPanel();

		contentPanel.add(new JLabel("ID:"));
		contentPanel.add(getIdTextField());

		contentPanel.add(new JLabel("Description:"), "top");
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
