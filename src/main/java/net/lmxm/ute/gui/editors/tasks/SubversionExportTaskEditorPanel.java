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

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.enums.SubversionDepth;
import net.lmxm.ute.enums.SubversionRevision;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.exceptions.TaskExecuterException;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.StringUtils;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.SortedSet;

/**
 * The Class SubversionExportTaskEditorPanel.
 */
public final class SubversionExportTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class SubversionExportTaskEditorToolBar.
	 */
	private static class SubversionExportTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -4774652510779094497L;

		/**
		 * Instantiates a new subversion export task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public SubversionExportTaskEditorToolBar(final UteActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionExportTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -254745593912919513L;

	/** The date revision radio button. */
	private JRadioButton dateRevisionRadioButton = null;

	/** The head revision radio button. */
	private JRadioButton headRevisionRadioButton = null;

	/** The numbered revision radio button. */
	private JRadioButton numberedRevisionRadioButton = null;

	/** The revision action listener. */
	private ActionListener revisionActionListener = null;

	/** The revision date text field. */
	private JXDatePicker revisionDateTextField = null;

	/** The revision number text field. */
	private JTextField revisionNumberTextField = null;

	/** The revision pane. */
	private JPanel revisionPane = null;

	private JComboBox subversionDepthComboBox = null;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public SubversionExportTaskEditorPanel(final ConfigurationHolder configurationHolder,
			final UteActionListener actionListener) {
		super(LabelResourceType.SUBVERSION_EXPORT_TASK, new SubversionExportTaskEditorToolBar(actionListener),
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

		addSeparator(LabelResourceType.OPTIONS);

		addRequiredLabel(LabelResourceType.DEPTH);
		getContentPanel().add(getSubversionDepthComboBox());

		addRequiredLabel(LabelResourceType.REVISION);
		getContentPanel().add(getRevisionPane());
	}

	/**
	 * Gets the date revision radio button.
	 * 
	 * @return the date revision radio button
	 */
	private JRadioButton getDateRevisionRadioButton() {
		if (dateRevisionRadioButton == null) {
			dateRevisionRadioButton = new JRadioButton(SubversionRevision.DATE.toString());
			dateRevisionRadioButton.addActionListener(getRevisionActionListener());
		}

		return dateRevisionRadioButton;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new SubversionExportTask(new SequentialJob());
	}

	/**
	 * Gets the head revision radio button.
	 * 
	 * @return the head revision radio button
	 */
	private JRadioButton getHeadRevisionRadioButton() {
		if (headRevisionRadioButton == null) {
			headRevisionRadioButton = new JRadioButton(SubversionRevision.HEAD.toString());
			headRevisionRadioButton.addActionListener(getRevisionActionListener());
		}

		return headRevisionRadioButton;
	}

	/**
	 * Gets the numbered revision radio button.
	 * 
	 * @return the numbered revision radio button
	 */
	private JRadioButton getNumberedRevisionRadioButton() {
		if (numberedRevisionRadioButton == null) {
			numberedRevisionRadioButton = new JRadioButton(SubversionRevision.NUMBERED.toString());
			numberedRevisionRadioButton.addActionListener(getRevisionActionListener());
		}

		return numberedRevisionRadioButton;
	}

	/**
	 * Gets the revision action listener.
	 * 
	 * @return the revision action listener
	 */
	private ActionListener getRevisionActionListener() {
        final String prefix = "getRevisionActionListener() : ";

		if (revisionActionListener == null) {
			revisionActionListener = new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent actionEvent) {
					if (getUserObject() instanceof SubversionExportTask) {
						final SubversionExportTask subversionExportTask = (SubversionExportTask) getUserObject();

						final Object source = actionEvent.getSource();
						if (source.equals(getHeadRevisionRadioButton())) {
							subversionExportTask.setRevision(SubversionRevision.HEAD);
						}
						else if (source.equals(getDateRevisionRadioButton())) {
							subversionExportTask.setRevision(SubversionRevision.DATE);
						}
						else if (source.equals(getNumberedRevisionRadioButton())) {
							subversionExportTask.setRevision(SubversionRevision.NUMBERED);
						}
						else {
							LOGGER.error("{} Unsupported revision", prefix);
                            throw new GuiException(ExceptionResourceType.INVALID_SUBVERSION_REVISION_VALUE);
						}

						updateRevisionFields(subversionExportTask.getRevision());
					}
				}
			};
		}

		return revisionActionListener;
	}

	/**
	 * Gets the revision date text field.
	 * 
	 * @return the revision date text field
	 */
	private JXDatePicker getRevisionDateTextField() {
		if (revisionDateTextField == null) {
			revisionDateTextField = new JXDatePicker(new Date());
			revisionDateTextField.setFormats("yyyy-MM-dd");

			final JXMonthView monthView = revisionDateTextField.getMonthView();

			monthView.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent actionEvent) {
					if (getUserObject() instanceof SubversionExportTask) {
						final SubversionExportTask subversionExportTask = (SubversionExportTask) getUserObject();

						final SortedSet<Date> selection = monthView.getSelection();
						if (selection.size() == 0) {
							subversionExportTask.setRevisionDate(null);
						}
						else {
							subversionExportTask.setRevisionDate(selection.first());
						}
					}
				}
			});
		}

		return revisionDateTextField;

	}

	/**
	 * Gets the revision number text field.
	 * 
	 * @return the revision number text field
	 */
	private JTextField getRevisionNumberTextField() {
		if (revisionNumberTextField == null) {
			revisionNumberTextField = new JTextField();
			revisionNumberTextField.setMinimumSize(new Dimension(75, (int) revisionNumberTextField.getSize()
					.getHeight()));
			revisionNumberTextField.setDragEnabled(true);
			revisionNumberTextField.setEnabled(false);
			revisionNumberTextField.getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof SubversionExportTask) {
						final Long revisionNumber = StringUtils.isBlank(newValue) ? null : Long.valueOf(newValue);
						((SubversionExportTask) getUserObject()).setRevisionNumber(revisionNumber);
					}
				}
			});
		}
		return revisionNumberTextField;
	}

	/**
	 * Gets the revision pane.
	 * 
	 * @return the revision pane
	 */
	private JPanel getRevisionPane() {
		if (revisionPane == null) {
			revisionPane = new JPanel(new MigLayout("gapy 0"));
			revisionPane.add(getHeadRevisionRadioButton(), "wrap");

			revisionPane.add(getNumberedRevisionRadioButton());
			revisionPane.add(getRevisionNumberTextField(), "wrap");

			revisionPane.add(getDateRevisionRadioButton());
			revisionPane.add(getRevisionDateTextField(), "wrap");

			final ButtonGroup group = new ButtonGroup();
			group.add(getHeadRevisionRadioButton());
			group.add(getNumberedRevisionRadioButton());
			group.add(getDateRevisionRadioButton());
		}

		return revisionPane;
	}

	private JComboBox getSubversionDepthComboBox() {
		if (subversionDepthComboBox == null) {
			subversionDepthComboBox = new JComboBox();
			subversionDepthComboBox.addActionListener(new ActionListener() {

				/*
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				@Override
				public void actionPerformed(final ActionEvent actionEvent) {
					if (getUserObject() instanceof SubversionExportTask) {
						final SubversionExportTask task = (SubversionExportTask) getUserObject();

						if (subversionDepthComboBox.getSelectedIndex() == -1) {
							task.setDepth(null);
						}
						else {
							final SubversionDepth depth = (SubversionDepth) subversionDepthComboBox.getSelectedItem();
							task.setDepth(depth);
						}
					}
				}
			});
		}

		return subversionDepthComboBox;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#initialize(net.lmxm.ute.beans.Configuration)
	 */
	@Override
	public void initialize(final Configuration configuration) {
		super.initialize(configuration);

		getSubversionDepthComboBox().setModel(new DefaultComboBoxModel(SubversionDepth.values()));
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

		if (getUserObject() instanceof SubversionExportTask) {
			final SubversionExportTask subversionExportTask = (SubversionExportTask) getUserObject();

			getSubversionDepthComboBox().setSelectedItem(subversionExportTask.getDepth());

			final SubversionRevision revision = subversionExportTask.getRevision();

			if (revision == SubversionRevision.HEAD) {
				getHeadRevisionRadioButton().setSelected(true);
			}
			else if (revision == SubversionRevision.DATE) {
				getDateRevisionRadioButton().setSelected(true);
			}
			else if (revision == SubversionRevision.NUMBERED) {
				getNumberedRevisionRadioButton().setSelected(true);
			}
			else {
                LOGGER.error("{} Unsupported revision", prefix);
                throw new GuiException(ExceptionResourceType.INVALID_SUBVERSION_REVISION_VALUE, revision);
			}

			final Date revisionDate = subversionExportTask.getRevisionDate();
			getRevisionDateTextField().setDate(revisionDate == null ? null : revisionDate);

			final Long revisionNumber = subversionExportTask.getRevisionNumber();
			getRevisionNumberTextField().setText(revisionNumber == null ? "" : revisionNumber.toString());

			updateRevisionFields(revision);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Update revision fields.
	 * 
	 * @param revision the revision
	 */
	private void updateRevisionFields(final SubversionRevision revision) {
        final String prefix = "updateRevisionFields(): ";

		if (revision == SubversionRevision.HEAD) {
			getRevisionDateTextField().setEnabled(false);
			getRevisionNumberTextField().setEnabled(false);
		}
		else if (revision == SubversionRevision.DATE) {
			getRevisionDateTextField().setEnabled(true);
			getRevisionNumberTextField().setEnabled(false);
		}
		else if (revision == SubversionRevision.NUMBERED) {
			getRevisionDateTextField().setEnabled(false);
			getRevisionNumberTextField().setEnabled(true);
		}
		else {
            LOGGER.error("{} Unsupported revision", prefix);
            throw new GuiException(ExceptionResourceType.INVALID_SUBVERSION_REVISION_VALUE, revision);
		}
	}
}
