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

import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.gui.components.FindReplacePatternsTableModel;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Class FindReplaceTaskEditorPanel.
 */
public final class FindReplaceTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class FindReplaceTaskEditorToolBar.
	 */
	private static class FindReplaceTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -4652716534538899767L;

		/**
		 * Instantiates a new find replace task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public FindReplaceTaskEditorToolBar(final ActionListener actionListener) {
			super(actionListener);
		}
	}

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

	/** The scope action listener. */
	private ActionListener scopeActionListener = null;

	/** The scope pane. */
	private JPanel scopePane = null;

	/**
	 * Instantiates a new find replace task editor panel.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public FindReplaceTaskEditorPanel(final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super(LabelResourceType.FIND_AND_REPLACE_TASK, new FindReplaceTaskEditorToolBar(actionListener),
				configurationHolder, actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.tasks.AbstractTaskEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		addSeparator(LabelResourceType.FIND_AND_REPLACE);
		addRequiredLabel(LabelResourceType.SCOPE);

		getContentPanel().add(getScopePane());

		addRequiredLabel(LabelResourceType.PATTERNS);
		getContentPanel().add(getPatternsPane());
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new FindReplaceTask(new SequentialJob());
	}

	/**
	 * Gets the file scope radio button.
	 * 
	 * @return the file scope radio button
	 */
	private JRadioButton getFileScopeRadioButton() {
		if (fileScopeRadioButton == null) {
			fileScopeRadioButton = new JRadioButton(Scope.FILE.toString());
			fileScopeRadioButton.addActionListener(getScopeActionListener());
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
			lineScopeRadioButton.addActionListener(getScopeActionListener());
		}

		return lineScopeRadioButton;
	}

	/**
	 * Gets the patterns pane.
	 * 
	 * @return the patterns pane
	 */
	private JPanel getPatternsPane() {
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
	private JScrollPane getPatternsScrollPane() {
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
	private JTable getPatternsTable() {
		if (patternsTable == null) {
			patternsTable = new JTable();
			patternsTable.setFillsViewportHeight(true);
		}

		return patternsTable;
	}

	/**
	 * Gets the scope action listener.
	 * 
	 * @return the scope action listener
	 */
	private ActionListener getScopeActionListener() {
		if (scopeActionListener == null) {
			scopeActionListener = new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent actionEvent) {
					if (getUserObject() instanceof FindReplaceTask) {
						final FindReplaceTask findReplaceTask = (FindReplaceTask) getUserObject();

						final Object source = actionEvent.getSource();
						if (source.equals(getFileScopeRadioButton())) {
							findReplaceTask.setScope(Scope.FILE);
						}
						else if (source.equals(getLineScopeRadioButton())) {
							findReplaceTask.setScope(Scope.LINE);
						}
						else {
							throw new RuntimeException("Unsupported scope"); // TODO
						}
					}
				}
			};
		}

		return scopeActionListener;
	}

	/**
	 * Gets the scope pane.
	 * 
	 * @return the scope pane
	 */
	private JPanel getScopePane() {
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

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.tasks.AbstractTaskEditorPanel#loadData()
	 */
	@Override
	public void loadData() {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered", prefix);

		super.loadData();

		if (getUserObject() instanceof FindReplaceTask) {
			final FindReplaceTask findReplaceTask = (FindReplaceTask) getUserObject();

			getPatternsTable().setModel(new FindReplacePatternsTableModel(findReplaceTask));

			if (findReplaceTask.getScope() == Scope.FILE) {
				getFileScopeRadioButton().setSelected(true);
			}
			else if (findReplaceTask.getScope() == Scope.LINE) {
				getFileScopeRadioButton().setSelected(true);
			}
			else {
				throw new RuntimeException("Unsupported scope"); // TODO
			}
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
