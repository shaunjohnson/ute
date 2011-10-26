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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.AbstractFilesTask;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.gui.editors.AbstractIdEditorPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AbstractTaskEditorPanel.
 */
public abstract class AbstractTaskEditorPanel extends AbstractIdEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTaskEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1317100477637996007L;

	/** The enabled checkbox. */
	private JCheckBox enabledCheckbox = null;

	/** The files pane. */
	private JPanel filesPane = null;

	/** The files scroll pane. */
	private JScrollPane filesScrollPane = null;

	/** The files table. */
	private JTable filesTable = null;

	/** The monospace font. */
	private Font monospaceFont = null;

	/** The source relative path text field. */
	private JTextField sourceRelativePathTextField = null;

	/** The target relative path text field. */
	private JTextField targetRelativePathTextField = null;

	/**
	 * Instantiates a new abstract task editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param actionListener the action listener
	 */
	public AbstractTaskEditorPanel(final GuiComponentLabel guiComponentLabel, final ActionListener actionListener) {
		super(guiComponentLabel, actionListener);

		monospaceFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	}

	/**
	 * Adds the files fields.
	 */
	protected final void addFilesFields() {
		final JPanel contentPanel = getContentPanel();

		addLabel(contentPanel, GuiComponentLabel.FILES);
		contentPanel.add(getFilesPane());
	}

	/**
	 * Adds the file system target fields.
	 */
	protected final void addFileSystemTargetFields() {
		final JPanel contentPanel = getContentPanel();

		addLabel(contentPanel, GuiComponentLabel.LOCATION);
		contentPanel.add(getFileSystemLocationTargetComboBox());

		addLabel(contentPanel, GuiComponentLabel.PATH);
		contentPanel.add(getTargetRelativePathTextField());
	}

	/**
	 * Adds the http source fields.
	 */
	protected final void addHttpSourceFields() {
		final JPanel contentPanel = getContentPanel();

		addLabel(contentPanel, GuiComponentLabel.SERVER);
		contentPanel.add(getHttpLocationSourceComboBox());

		addLabel(contentPanel, GuiComponentLabel.PATH);
		contentPanel.add(getSourceRelativePathTextField());
	}

	/**
	 * Adds the subversion repository source fields.
	 */
	protected final void addSubversionRepositorySourceFields() {
		final JPanel contentPanel = getContentPanel();

		addLabel(contentPanel, GuiComponentLabel.SERVER);
		contentPanel.add(getSubversionRepositoryLocationSourceComboBox());

		addLabel(contentPanel, GuiComponentLabel.PATH);
		contentPanel.add(getSourceRelativePathTextField());
	}

	/**
	 * Adds the task common fields.
	 */
	protected final void addTaskCommonFields() {
		final JPanel contentPanel = getContentPanel();

		addIdCommonFields();

		addLabel(contentPanel, GuiComponentLabel.DESCRIPTION);
		contentPanel.add(getDescriptionPane());

		addCheckbox(contentPanel, getEnabledCheckbox(), GuiComponentLabel.ENABLED);
	}

	/**
	 * Creates the empty files table model.
	 * 
	 * @return the default table model
	 */
	protected DefaultTableModel createEmptyFilesTableModel() {
		final DefaultTableModel tableModel = new DefaultTableModel();

		tableModel.addColumn("File Name/Pattern");
		tableModel.addColumn("Target File Name");

		return tableModel;
	}

	/**
	 * Gets the enabled checkbox.
	 * 
	 * @return the enabled checkbox
	 */
	protected final JCheckBox getEnabledCheckbox() {
		if (enabledCheckbox == null) {
			enabledCheckbox = new JCheckBox();
		}

		return enabledCheckbox;
	}

	/**
	 * Gets the files pane.
	 * 
	 * @return the files pane
	 */
	protected final JPanel getFilesPane() {
		if (filesPane == null) {
			filesPane = new JPanel();
			filesPane.setLayout(new BorderLayout());
			filesPane.add(getFilesScrollPane(), BorderLayout.CENTER);
			filesPane.setMaximumSize(new Dimension(400, 100));
		}

		return filesPane;
	}

	/**
	 * Gets the files scroll pane.
	 * 
	 * @return the files scroll pane
	 */
	protected final JScrollPane getFilesScrollPane() {
		if (filesScrollPane == null) {
			filesScrollPane = new JScrollPane(getFilesTable());
			filesScrollPane.setMaximumSize(new Dimension(400, 100));
		}

		return filesScrollPane;
	}

	/**
	 * Gets the files table.
	 * 
	 * @return the files table
	 */
	protected final JTable getFilesTable() {
		if (filesTable == null) {
			filesTable = new JTable(createEmptyFilesTableModel());
			filesTable.setFillsViewportHeight(true);
		}

		return filesTable;
	}

	/**
	 * Gets the monospace font.
	 * 
	 * @return the monospace font
	 */
	protected final Font getMonospaceFont() {
		return monospaceFont;
	}

	/**
	 * Gets the source relative path text field.
	 * 
	 * @return the source relative path text field
	 */
	protected final JTextField getSourceRelativePathTextField() {
		if (sourceRelativePathTextField == null) {
			sourceRelativePathTextField = new JTextField();
			sourceRelativePathTextField.setFont(monospaceFont);
			sourceRelativePathTextField.setMinimumSize(new Dimension(400, (int) sourceRelativePathTextField.getSize()
					.getHeight()));
		}

		return sourceRelativePathTextField;
	}

	/**
	 * Gets the target relative path text field.
	 * 
	 * @return the target relative path text field
	 */
	protected final JTextField getTargetRelativePathTextField() {
		if (targetRelativePathTextField == null) {
			targetRelativePathTextField = new JTextField();
			targetRelativePathTextField.setFont(monospaceFont);
			targetRelativePathTextField.setMinimumSize(new Dimension(400, (int) targetRelativePathTextField.getSize()
					.getHeight()));
		}

		return targetRelativePathTextField;
	}

	/**
	 * Load files field data.
	 * 
	 * @param abstractFilesTask the abstract files task
	 */
	protected final void loadFilesFieldData(final AbstractFilesTask abstractFilesTask) {
		final DefaultTableModel tableModel = createEmptyFilesTableModel();

		if (abstractFilesTask != null) {
			for (final FileReference fileReference : abstractFilesTask.getFiles()) {
				tableModel.addRow(new Object[] { fileReference.getName(), fileReference.getTargetName() });
			}
		}

		getFilesTable().setModel(tableModel);
	}

	/**
	 * Load file system target field data.
	 * 
	 * @param fileSystemTarget the file system target
	 */
	protected final void loadFileSystemTargetFieldData(final FileSystemTarget fileSystemTarget) {
		final String prefix = "loadFileSystemTargetFieldData(): ";

		LOGGER.debug("{} entered, fileSystemTarget={}", prefix, fileSystemTarget);

		final FileSystemLocation targetLocation = fileSystemTarget == null ? null : fileSystemTarget.getLocation();
		LOGGER.debug("{} setting target location to {}", prefix, targetLocation);
		setSelectedIndex(getFileSystemLocationTargetComboBox(), targetLocation);

		final String targetRelativePath = fileSystemTarget == null ? null : fileSystemTarget.getRelativePath();
		LOGGER.debug("{} setting target relative path to {}", prefix, targetRelativePath);
		getTargetRelativePathTextField().setText(targetRelativePath);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load http source field data.
	 * 
	 * @param httpSource the http source
	 */
	protected final void loadHttpSourceFieldData(final HttpSource httpSource) {
		final String prefix = "loadHttpSourceFieldData(): ";

		LOGGER.debug("{} entered, httpSource={}", prefix, httpSource);

		final HttpLocation sourceLocation = httpSource == null ? null : httpSource.getLocation();
		LOGGER.debug("{} setting source location to {}", prefix, sourceLocation);
		setSelectedIndex(getHttpLocationSourceComboBox(), sourceLocation);

		final String sourceRelativePath = httpSource == null ? null : httpSource.getRelativePath();
		LOGGER.debug("{} setting source relative path to {}", prefix, sourceRelativePath);
		getSourceRelativePathTextField().setText(sourceRelativePath);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load subversion repository source field data.
	 * 
	 * @param subversionRepositorySource the subversion repository source
	 */
	protected final void loadSubversionRepositorySourceFieldData(
			final SubversionRepositorySource subversionRepositorySource) {
		final String prefix = "loadSubversionRepositorySourceFieldData(): ";

		LOGGER.debug("{} entered, subversionRepositorySource={}", prefix, subversionRepositorySource);

		final SubversionRepositoryLocation sourceLocation = subversionRepositorySource == null ? null
				: subversionRepositorySource.getLocation();
		LOGGER.debug("{} setting source location to {}", prefix, sourceLocation);
		setSelectedIndex(getSubversionRepositoryLocationSourceComboBox(), sourceLocation);

		final String sourceRelativePath = subversionRepositorySource == null ? null : subversionRepositorySource
				.getRelativePath();
		LOGGER.debug("{} setting source relative path to {}", prefix, sourceRelativePath);
		getSourceRelativePathTextField().setText(sourceRelativePath);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load task common field data.
	 * 
	 * @param task the task
	 */
	protected final void loadTaskCommonFieldData(final Task task) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, task={}", prefix, task);

		loadIdCommonFieldData(task);

		if (task == null) {
			getDescriptionTextArea().setText("");
			getEnabledCheckbox().setSelected(false);
		}
		else {
			getDescriptionTextArea().setText(task.getDescription());
			getEnabledCheckbox().setSelected(task.getEnabled());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
