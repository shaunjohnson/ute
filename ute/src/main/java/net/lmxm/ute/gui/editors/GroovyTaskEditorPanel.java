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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.GroovyTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GroovyTaskEditorPanel.
 */
public final class GroovyTaskEditorPanel extends AbstractTaskEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GroovyTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5799320021366647084L;

	/** The script pane. */
	private JScrollPane scriptPane = null;

	/** The script text area. */
	private JTextArea scriptTextArea = null;

	/**
	 * Instantiates a new groovy task editor panel.
	 * 
	 * @param configuration the configuration
	 */
	public GroovyTaskEditorPanel(final Configuration configuration) {
		super(configuration, "Groovy Task");

		addTaskCommonFields();
		addFileSystemTargetFields();
		addFilesFields();

		final JPanel contentPanel = getContentPanel();

		contentPanel.add(new JLabel("Script:"));
		contentPanel.add(getScriptPane());
	}

	/**
	 * Gets the script pane.
	 * 
	 * @return the script pane
	 */
	protected final JScrollPane getScriptPane() {
		if (scriptPane == null) {
			scriptPane = new JScrollPane(getScriptTextArea());
		}

		return scriptPane;
	}

	/**
	 * Gets the script text area.
	 * 
	 * @return the script text area
	 */
	protected final JTextArea getScriptTextArea() {
		if (scriptTextArea == null) {
			scriptTextArea = new JTextArea();
			scriptTextArea.setColumns(80);
			scriptTextArea.setFont(getMonospaceFont());
			scriptTextArea.setRows(30);
			scriptTextArea.setLineWrap(false);
			scriptTextArea.setTabSize(4);
		}
		return scriptTextArea;
	}

	/**
	 * Load data.
	 * 
	 * @param groovyTask the groovy task
	 */
	public void loadData(final GroovyTask groovyTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, groovyTask={}", prefix, groovyTask);

		final FileSystemTarget target = groovyTask == null ? null : groovyTask.getTarget();

		loadTaskCommonFieldData(groovyTask);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(groovyTask);

		if (groovyTask == null) {
			getScriptTextArea().setText("");
		}
		else {
			getScriptTextArea().setText(groovyTask.getScript());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
