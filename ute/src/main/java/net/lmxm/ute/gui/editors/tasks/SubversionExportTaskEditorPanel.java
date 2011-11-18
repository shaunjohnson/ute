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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.enums.SubversionRevision;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;
import net.lmxm.ute.listeners.ChangeAdapter;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.lmxm.ute.subversion.utils.SubversionUtils;
import net.miginfocom.swing.MigLayout;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		public SubversionExportTaskEditorToolBar(final ActionListener actionListener) {
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
	private JTextField revisionDateTextField = null;

	/** The revision number text field. */
	private JTextField revisionNumberTextField = null;

	/** The revision pane. */
	private JPanel revisionPane = null;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SubversionExportTaskEditorPanel(final ActionListener actionListener) {
		super(LabelResourceType.SUBVERSION_EXPORT_TASK, new SubversionExportTaskEditorToolBar(actionListener),
				actionListener);

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
							throw new RuntimeException("Unsupported revision"); // TODO
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
	private JTextField getRevisionDateTextField() {
		if (revisionDateTextField == null) {
			revisionDateTextField = new JTextField();
			revisionDateTextField.setMinimumSize(new Dimension(75, (int) revisionDateTextField.getSize().getHeight()));
			revisionDateTextField.setDragEnabled(true);
			revisionDateTextField.setEnabled(false);
			revisionDateTextField.getDocument().addDocumentListener(new ChangeAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof SubversionExportTask) {
						final Date revisionDate = StringUtils.isBlank(newValue) ? null : SubversionUtils
								.parseRevisionDate(newValue);
						((SubversionExportTask) getUserObject()).setRevisionDate(revisionDate);
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
			revisionNumberTextField.getDocument().addDocumentListener(new ChangeAdapter() {
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
				throw new RuntimeException("Unsupported revision"); // TODO
			}

			final Date revisionDate = subversionExportTask.getRevisionDate();
			getRevisionDateTextField().setText(revisionDate == null ? "" : revisionDate.toString());

			final Long revisionNumber = subversionExportTask.getRevisionNumber();
			getRevisionNumberTextField().setText(revisionNumber == null ? "" : revisionNumber.toString());

			updateRevisionFields(revision);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	private void updateRevisionFields(final SubversionRevision revision) {
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
			throw new RuntimeException("Unsupported revision"); // TODO
		}
	}
}
