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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.enums.Scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FindReplaceTaskEditorPanel.
 */
public final class FindReplaceTaskEditorPanel extends AbstractTaskEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FindReplaceTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5004821032399609379L;

	/** The file scope radio button. */
	private JRadioButton fileScopeRadioButton;

	/** The line scope radio button. */
	private JRadioButton lineScopeRadioButton;

	/** The patterns pane. */
	private JPanel patternsPane = null;

	/** The patterns scroll pane. */
	private JScrollPane patternsScrollPane = null;

	/** The patterns table. */
	private JTable patternsTable = null;

	/** The scope pane. */
	private JPanel scopePane = null;

	/**
	 * Instantiates a new find replace task editor panel.
	 * 
	 * @param configuration the configuration
	 */
	public FindReplaceTaskEditorPanel(final Configuration configuration) {
		super(configuration, "Find and Replace Task");

		addTaskCommonFields();
		addFileSystemTargetFields();
		addFilesFields();

		final JPanel contentPanel = getContentPanel();

		contentPanel.add(new JLabel("Scope:"));
		contentPanel.add(getScopePane());

		addPatternsFields();
	}

	/**
	 * Adds the patterns fields.
	 */
	protected final void addPatternsFields() {
		final JPanel contentPanel = getContentPanel();

		contentPanel.add(new JLabel("Patterns:"), "top");
		contentPanel.add(getPatternsPane());
	}

	/**
	 * Creates the empty patterns table model.
	 * 
	 * @return the default table model
	 */
	protected final DefaultTableModel createEmptyPatternsTableModel() {
		final DefaultTableModel tableModel = new DefaultTableModel();

		tableModel.addColumn("Pattern");
		tableModel.addColumn("Replacement");

		return tableModel;
	}

	/**
	 * Gets the file scope radio button.
	 * 
	 * @return the file scope radio button
	 */
	private JRadioButton getFileScopeRadioButton() {
		if (fileScopeRadioButton == null) {
			fileScopeRadioButton = new JRadioButton(Scope.FILE.toString());
		}

		return fileScopeRadioButton;
	}

	/**
	 * Gets the line scope radio button.
	 * 
	 * @return the line scope radio button
	 */
	private JRadioButton getLineScopeRadioButton() {
		if (lineScopeRadioButton == null) {
			lineScopeRadioButton = new JRadioButton(Scope.LINE.toString());
		}

		return lineScopeRadioButton;
	}

	/**
	 * Gets the patterns pane.
	 * 
	 * @return the patterns pane
	 */
	protected final JPanel getPatternsPane() {
		if (patternsPane == null) {
			patternsPane = new JPanel();
			patternsPane.setLayout(new BorderLayout());
			patternsPane.add(getPatternsScrollPane(), BorderLayout.CENTER);
			patternsPane.setMaximumSize(new Dimension(400, 100));
		}

		return patternsPane;
	}

	/**
	 * Gets the patterns scroll pane.
	 * 
	 * @return the patterns scroll pane
	 */
	protected final JScrollPane getPatternsScrollPane() {
		if (patternsScrollPane == null) {
			patternsScrollPane = new JScrollPane(getPatternsTable());
			patternsScrollPane.setMaximumSize(new Dimension(400, 100));
		}

		return patternsScrollPane;
	}

	/**
	 * Gets the patterns table.
	 * 
	 * @return the patterns table
	 */
	protected final JTable getPatternsTable() {
		if (patternsTable == null) {
			patternsTable = new JTable(createEmptyPatternsTableModel());
			patternsTable.setFillsViewportHeight(true);
		}

		return patternsTable;
	}

	/**
	 * Gets the scope pane.
	 * 
	 * @return the scope pane
	 */
	private final JPanel getScopePane() {
		if (scopePane == null) {
			scopePane = new JPanel();
			scopePane.setLayout(new FlowLayout(FlowLayout.LEFT));
			scopePane.add(getLineScopeRadioButton());
			scopePane.add(getFileScopeRadioButton());

			final ButtonGroup group = new ButtonGroup();
			group.add(getLineScopeRadioButton());
			group.add(getFileScopeRadioButton());
		}

		return scopePane;
	}

	/**
	 * Load data.
	 * 
	 * @param findReplaceTask the find replace task
	 */
	public void loadData(final FindReplaceTask findReplaceTask) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, findReplaceTask={}", prefix, findReplaceTask);

		final FileSystemTarget target = findReplaceTask == null ? null : findReplaceTask.getTarget();

		loadTaskCommonFieldData(findReplaceTask);
		loadFileSystemTargetFieldData(target);
		loadFilesFieldData(findReplaceTask);
		loadPatternsFieldData(findReplaceTask);

		if (findReplaceTask == null) {
			getLineScopeRadioButton().setSelected(true);
		}
		else {
			if (findReplaceTask.getScope() == Scope.LINE) {
				getLineScopeRadioButton().setSelected(true);
			}
			else {
				getFileScopeRadioButton().setSelected(true);
			}
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load patterns field data.
	 * 
	 * @param findReplaceTask the find replace task
	 */
	protected final void loadPatternsFieldData(final FindReplaceTask findReplaceTask) {
		final DefaultTableModel tableModel = createEmptyPatternsTableModel();

		if (findReplaceTask != null) {
			for (final FindReplacePattern findReplacePattern : findReplaceTask.getPatterns()) {
				tableModel.addRow(new Object[] { findReplacePattern.getFind(), findReplacePattern.getReplace() });
			}
		}

		getPatternsTable().setModel(tableModel);
	}
}
