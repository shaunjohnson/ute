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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.FileSystemTargetTask;
import net.lmxm.ute.beans.tasks.FilesTask;
import net.lmxm.ute.beans.tasks.HttpSourceTask;
import net.lmxm.ute.beans.tasks.SubversionRepositorySourceTask;
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

	/** The file system location target combo box. */
	private JComboBox fileSystemLocationTargetComboBox = null;

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
	 * Gets the file system location target combo box.
	 * 
	 * @return the file system location target combo box
	 */
	protected final JComboBox getFileSystemLocationTargetComboBox() {
		if (fileSystemLocationTargetComboBox == null) {
			fileSystemLocationTargetComboBox = new JComboBox();
			fileSystemLocationTargetComboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent actionEvent) {
					if (getUserObject() instanceof FileSystemTargetTask) {
						final FileSystemTarget target = ((FileSystemTargetTask) getUserObject()).getTarget();

						if (target == null) {
							LOGGER.error("File system target is null");
							throw new IllegalStateException("File system target is null"); // TODO
						}

						if (fileSystemLocationTargetComboBox.getSelectedIndex() == -1) {
							target.setLocation(null);
						}
						else {
							final FileSystemLocation location = (FileSystemLocation) fileSystemLocationTargetComboBox
									.getSelectedItem();
							target.setLocation(location);
						}
					}
				}
			});
		}

		return fileSystemLocationTargetComboBox;
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

	@Override
	public final void initialize(final Configuration configuration) {
		super.initialize(configuration);

		getFileSystemLocationTargetComboBox().setModel(
				createDefaultComboBoxModel(configuration.getFileSystemLocations()));
	}

	@Override
	public void loadData() {
		// Load Common Fields
		super.loadData();
		loadTaskCommonFieldData();

		// Load Source Fields
		loadHttpSourceFieldData();
		loadSubversionRepositorySourceFieldData();

		// Load Target Fields
		loadFileSystemTargetFieldData();

		// Load Other Fields
		loadFilesFieldData();
	}

	private final void loadFilesFieldData() {
		final DefaultTableModel tableModel = createEmptyFilesTableModel();
		getFilesTable().setModel(tableModel);

		if (getUserObject() instanceof FilesTask) {
			final FilesTask filesTask = (FilesTask) getUserObject();

			for (final FileReference fileReference : filesTask.getFiles()) {
				tableModel.addRow(new Object[] { fileReference.getName(), fileReference.getTargetName() });
			}
		}
	}

	/**
	 * Load file system target field data.
	 * 
	 * @param fileSystemTarget the file system target
	 */
	private final void loadFileSystemTargetFieldData() {
		final String prefix = "loadFileSystemTargetFieldData(): ";

		LOGGER.debug("{} entered");

		if (getUserObject() instanceof FileSystemTargetTask) {
			final FileSystemTarget fileSystemTarget = ((FileSystemTargetTask) getUserObject()).getTarget();

			getFileSystemLocationTargetComboBox().setSelectedItem(fileSystemTarget.getLocation());
			getTargetRelativePathTextField().setText(fileSystemTarget.getRelativePath());
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load http source field data.
	 * 
	 * @param httpSource the http source
	 */
	private final void loadHttpSourceFieldData() {
		final String prefix = "loadHttpSourceFieldData(): ";

		LOGGER.debug("{} entered");

		if (getUserObject() instanceof HttpSourceTask) {
			final HttpSource httpSource = ((HttpSourceTask) getUserObject()).getSource();

			getHttpLocationSourceComboBox().setSelectedItem(httpSource.getLocation());
			getSourceRelativePathTextField().setText(httpSource.getRelativePath());
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load subversion repository source field data.
	 */
	private final void loadSubversionRepositorySourceFieldData() {
		final String prefix = "loadSubversionRepositorySourceFieldData(): ";

		LOGGER.debug("{} entered");

		if (getUserObject() instanceof SubversionRepositorySourceTask) {
			final SubversionRepositorySource subversionRepositorySource = ((SubversionRepositorySourceTask) getUserObject())
					.getSource();

			getSubversionRepositoryLocationSourceComboBox().setSelectedItem(subversionRepositorySource.getLocation());
			getSourceRelativePathTextField().setText(subversionRepositorySource.getRelativePath());
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Load task common field data.
	 */
	private final void loadTaskCommonFieldData() {
		final String prefix = "loadTaskCommonFieldData(): ";

		LOGGER.debug("{} entered", prefix);

		if (getUserObject() instanceof Task) {
			final Task task = (Task) getUserObject();

			getDescriptionTextArea().setText(task.getDescription());
			getEnabledCheckbox().setSelected(task.getEnabled());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
