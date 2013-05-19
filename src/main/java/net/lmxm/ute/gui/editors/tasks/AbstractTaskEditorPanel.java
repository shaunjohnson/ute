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

import net.lmxm.ute.beans.EnabledStateBean;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.sources.MavenRepositorySource;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.*;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.event.EnabledStateChangeEvent;
import net.lmxm.ute.event.EnabledStateChangeListener;
import net.lmxm.ute.gui.GuiContants;
import net.lmxm.ute.gui.components.FilesTableModel;
import net.lmxm.ute.gui.components.RenameFilesTableModel;
import net.lmxm.ute.gui.editors.AbstractCommonEditorPanel;
import net.lmxm.ute.resources.types.LabelResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class AbstractTaskEditorPanel.
 */
public abstract class AbstractTaskEditorPanel extends AbstractCommonEditorPanel {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTaskEditorPanel.class);

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1317100477637996007L;

    /**
     * The enabled checkbox.
     */
    private JCheckBox enabledCheckbox = null;

    /**
     * The enabled state change listeners.
     */
    private final List<EnabledStateChangeListener> enabledStateChangeListeners = new ArrayList<EnabledStateChangeListener>();

    /**
     * The files pane.
     */
    private JPanel filesPane = null;

    /**
     * The files scroll pane.
     */
    private JScrollPane filesScrollPane = null;

    /**
     * The files table.
     */
    private JTable filesTable = null;

    /**
     * The file system location target combo box.
     */
    private JComboBox fileSystemLocationTargetComboBox = null;

    /**
     * The http location target combo box.
     */
    private JComboBox httpLocationTargetComboBox = null;

    /**
     * The Maven repository location target combo box.
     */
    private JComboBox mavenRepositoryLocationTargetComboBox = null;

    /**
     * The monospace font.
     */
    private Font monospaceFont = null;

    /**
     * The source relative path text field.
     */
    private JTextField sourceRelativePathTextField = null;

    /**
     * The stop on error checkbox.
     */
    private JCheckBox stopOnErrorCheckbox = null;

    /**
     * The subversion repository location target combo box.
     */
    private JComboBox subversionRepositoryLocationTargetComboBox = null;

    /**
     * The target relative path text field.
     */
    private JTextField targetRelativePathTextField = null;

    /**
     * Instantiates a new abstract task editor panel.
     *
     * @param guiComponentLabel   the gui component label
     * @param toolBar             the tool bar
     * @param configurationHolder the configuration holder
     * @param actionListener      the action listener
     */
    public AbstractTaskEditorPanel(final LabelResourceType guiComponentLabel, final JToolBar toolBar,
                                   final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
        super(guiComponentLabel, toolBar, configurationHolder, actionListener);

        monospaceFont = new Font(Font.MONOSPACED, Font.PLAIN, GuiContants.DEFAULT_FONT_SIZE);
    }

    /**
     * Adds the enabled state change listener.
     *
     * @param enabledStateChangeListener the enabled state change listener
     */
    public final void addEnabledStateChangeListener(final EnabledStateChangeListener enabledStateChangeListener) {
        enabledStateChangeListeners.add(enabledStateChangeListener);
    }

    /*
     * (non-Javadoc)
     * @see net.lmxm.ute.gui.editors.AbstractCommonEditorPanel#addFields()
     */
    @Override
    protected void addFields() {
        super.addFields();

        addCheckbox(getEnabledCheckbox(), LabelResourceType.ENABLED);

        if (StopOnErrorTask.class.isInstance(getEditedObjectClass())) {
            addCheckbox(getStopOnErrorCheckbox(), LabelResourceType.STOP_ON_ERROR);
        }

        addSourceFields();
        addTargetFields();
    }

    /**
     * Adds the files fields.
     */
    protected final void addFilesFields() {
        if (FilesTask.class.isInstance(getEditedObjectClass())) {
            final JPanel contentPanel = getContentPanel();

            addLabel(LabelResourceType.FILES);
            contentPanel.add(getFilesPane(), "height max(200)");
        }
    }

    /**
     * Adds the file system target fields.
     */
    protected final void addFileSystemTargetFields() {
        if (FileSystemTargetTask.class.isInstance(getEditedObjectClass())) {
            final JPanel contentPanel = getContentPanel();

            addRequiredLabel(LabelResourceType.LOCATION);
            contentPanel.add(getFileSystemLocationTargetComboBox());

            addLabel(LabelResourceType.PATH);
            contentPanel.add(getTargetRelativePathTextField());
        }
    }

    /**
     * Adds the http source fields.
     */
    protected final void addHttpSourceFields() {
        if (HttpSourceTask.class.isInstance(getEditedObjectClass())) {
            final JPanel contentPanel = getContentPanel();

            addRequiredLabel(LabelResourceType.SERVER);
            contentPanel.add(getHttpLocationSourceComboBox());

            addLabel(LabelResourceType.PATH);
            contentPanel.add(getSourceRelativePathTextField());
        }
    }

    /**
     * Adds the Maven repository source fields.
     */
    protected final void addMavenRepositorySourceFields() {
        if (MavenRepositorySourceTask.class.isInstance(getEditedObjectClass())) {
            final JPanel contentPanel = getContentPanel();

            addRequiredLabel(LabelResourceType.SERVER);
            contentPanel.add(getMavenRepositoryLocationSourceComboBox());

            addLabel(LabelResourceType.PATH);
            contentPanel.add(getSourceRelativePathTextField());
        }
    }

    /**
     * Adds the source fields.
     */
    protected final void addSourceFields() {
        if (hasSourceFields()) {
            addSeparator(LabelResourceType.SOURCE);

            addHttpSourceFields();
            addMavenRepositorySourceFields();
            addSubversionRepositorySourceFields();
        }
    }

    /**
     * Adds the subversion repository source fields.
     */
    protected final void addSubversionRepositorySourceFields() {
        if (SubversionRepositorySourceTask.class.isInstance(getEditedObjectClass())) {
            final JPanel contentPanel = getContentPanel();

            addRequiredLabel(LabelResourceType.SERVER);
            contentPanel.add(getSubversionRepositoryLocationSourceComboBox());

            addLabel(LabelResourceType.PATH);
            contentPanel.add(getSourceRelativePathTextField());
        }
    }

    /**
     * Adds the target fields.
     */
    protected final void addTargetFields() {
        if (hasTargetFields()) {
            addSeparator(LabelResourceType.TARGET);

            addFileSystemTargetFields();
            addFilesFields();
        }
    }

    /**
     * Fire enabled state changed event.
     *
     * @param enabledStateBean the enabled state bean
     */
    private void fireEnabledStateChangedEvent(final EnabledStateBean enabledStateBean) {
        final EnabledStateChangeEvent enabledStateChangeEvent = new EnabledStateChangeEvent(this, enabledStateBean);

        for (final EnabledStateChangeListener enabledStateChangeListener : enabledStateChangeListeners) {
            enabledStateChangeListener.enabledStateChanged(enabledStateChangeEvent);
        }
    }

    /**
     * Gets the enabled checkbox.
     *
     * @return the enabled checkbox
     */
    private JCheckBox getEnabledCheckbox() {
        if (enabledCheckbox == null) {
            enabledCheckbox = new JCheckBox();
            enabledCheckbox.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(final ChangeEvent changeEvent) {
                    if (getUserObject() instanceof Task) {
                        final Task task = (Task) getUserObject();
                        final boolean isSelected = ((AbstractButton) changeEvent.getSource()).isSelected();

                        task.setEnabled(isSelected);
                        fireEnabledStateChangedEvent(task);
                    }
                }
            });
        }

        return enabledCheckbox;
    }

    /**
     * Gets the files pane.
     *
     * @return the files pane
     */
    private JPanel getFilesPane() {
        if (filesPane == null) {
            filesPane = new JPanel();
            filesPane.setLayout(new BorderLayout());
            filesPane.add(getFilesScrollPane(), BorderLayout.CENTER);
        }

        return filesPane;
    }

    /**
     * Gets the files scroll pane.
     *
     * @return the files scroll pane
     */
    private JScrollPane getFilesScrollPane() {
        if (filesScrollPane == null) {
            filesScrollPane = new JScrollPane(getFilesTable());
        }

        return filesScrollPane;
    }

    /**
     * Gets the files table.
     *
     * @return the files table
     */
    private JTable getFilesTable() {
        if (filesTable == null) {
            filesTable = new JTable();
            filesTable.setFillsViewportHeight(true);
        }

        return filesTable;
    }

    /**
     * Gets the file system location target combo box.
     *
     * @return the file system location target combo box
     */
    private JComboBox getFileSystemLocationTargetComboBox() {
        if (fileSystemLocationTargetComboBox == null) {
            fileSystemLocationTargetComboBox = new JComboBox();
            fileSystemLocationTargetComboBox.addActionListener(
                    new FileSystemLocationComboBoxActionListener(this, fileSystemLocationTargetComboBox));
        }

        return fileSystemLocationTargetComboBox;
    }

    /**
     * Gets the http location source combo box.
     *
     * @return the http location source combo box
     */
    private JComboBox getHttpLocationSourceComboBox() {
        if (httpLocationTargetComboBox == null) {
            httpLocationTargetComboBox = new JComboBox();
            httpLocationTargetComboBox.addActionListener(
                    new HttpLocationComboBoxActionListener(this, httpLocationTargetComboBox));
        }

        return httpLocationTargetComboBox;
    }

    /**
     * Gets the Maven repository location source combo box.
     *
     * @return the Maven repository location source combo box
     */
    private JComboBox getMavenRepositoryLocationSourceComboBox() {
        if (mavenRepositoryLocationTargetComboBox == null) {
            mavenRepositoryLocationTargetComboBox = new JComboBox();
            mavenRepositoryLocationTargetComboBox.addActionListener(
                    new MavenRepositoryLocationComboBoxActionListener(this, mavenRepositoryLocationTargetComboBox));
        }

        return mavenRepositoryLocationTargetComboBox;
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
    private JTextField getSourceRelativePathTextField() {
        if (sourceRelativePathTextField == null) {
            sourceRelativePathTextField = new JTextField();
            sourceRelativePathTextField.setFont(getMonospaceFont());
            sourceRelativePathTextField.setDragEnabled(true);
            sourceRelativePathTextField.getDocument().addDocumentListener(new DocumentAdapter() {
                @Override
                public void valueChanged(final String newValue) {
                    if (getUserObject() instanceof HttpSourceTask) {
                        final HttpSourceTask httpSourceTask = (HttpSourceTask) getUserObject();
                        httpSourceTask.getSource().setRelativePath(newValue);
                    }
                    else if (getUserObject() instanceof SubversionRepositorySourceTask) {
                        final SubversionRepositorySourceTask subversionRepositorySourceTask =
                                (SubversionRepositorySourceTask) getUserObject();
                        subversionRepositorySourceTask.getSource().setRelativePath(newValue);
                    }
                }
            });
        }

        return sourceRelativePathTextField;
    }

    /**
     * Gets the stop on error checkbox.
     *
     * @return the stop on error checkbox
     */
    private JCheckBox getStopOnErrorCheckbox() {
        if (stopOnErrorCheckbox == null) {
            stopOnErrorCheckbox = new JCheckBox();
            stopOnErrorCheckbox.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(final ChangeEvent changeEvent) {
                    if (getUserObject() instanceof StopOnErrorTask) {
                        final StopOnErrorTask stopOnErrorTask = (StopOnErrorTask) getUserObject();
                        final boolean isSelected = ((AbstractButton) changeEvent.getSource()).isSelected();

                        stopOnErrorTask.setStopOnError(isSelected);
                    }
                }
            });
        }

        return stopOnErrorCheckbox;
    }

    /**
     * Gets the subversion repository location source combo box.
     *
     * @return the subversion repository location source combo box
     */
    private JComboBox getSubversionRepositoryLocationSourceComboBox() {
        if (subversionRepositoryLocationTargetComboBox == null) {
            subversionRepositoryLocationTargetComboBox = new JComboBox();
            subversionRepositoryLocationTargetComboBox.addActionListener(
                    new SubversionRepositoryLocationComboBoxActionListener(this, subversionRepositoryLocationTargetComboBox));
        }

        return subversionRepositoryLocationTargetComboBox;
    }

    /**
     * Gets the target relative path text field.
     *
     * @return the target relative path text field
     */
    private JTextField getTargetRelativePathTextField() {
        if (targetRelativePathTextField == null) {
            targetRelativePathTextField = new JTextField();
            targetRelativePathTextField.setFont(getMonospaceFont());
            targetRelativePathTextField.setDragEnabled(true);
            targetRelativePathTextField.getDocument().addDocumentListener(new DocumentAdapter() {
                @Override
                public void valueChanged(final String newValue) {
                    if (getUserObject() instanceof FileSystemTargetTask) {
                        final FileSystemTargetTask fileSystemTargetTask = (FileSystemTargetTask) getUserObject();
                        fileSystemTargetTask.getTarget().setRelativePath(newValue);
                    }
                }
            });
        }

        return targetRelativePathTextField;
    }

    /**
     * Checks for source fields.
     *
     * @return true, if successful
     */
    private boolean hasSourceFields() {
        final Object editedObject = getEditedObjectClass();

        return HttpSourceTask.class.isInstance(editedObject)
                || MavenRepositoryDownloadTask.class.isInstance(editedObject)
                || SubversionRepositorySourceTask.class.isInstance(editedObject);
    }

    /**
     * Checks for target fields.
     *
     * @return true, if successful
     */
    private boolean hasTargetFields() {
        final Object editedObject = getEditedObjectClass();

        return FileSystemTargetTask.class.isInstance(editedObject) || FilesTask.class.isInstance(editedObject);
    }

    /*
     * (non-Javadoc)
     * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#initialize(net.lmxm.ute.beans.Configuration)
     */
    @Override
    public void initialize(final Configuration configuration) {
        super.initialize(configuration);

        getFileSystemLocationTargetComboBox().setModel(
                createDefaultComboBoxModel(configuration.getFileSystemLocations()));
        getHttpLocationSourceComboBox().setModel(createDefaultComboBoxModel(configuration.getHttpLocations()));
        getMavenRepositoryLocationSourceComboBox().setModel(createDefaultComboBoxModel(configuration.getMavenRepositoryLocations()));
        getSubversionRepositoryLocationSourceComboBox().setModel(
                createDefaultComboBoxModel(configuration.getSubversionRepositoryLocations()));
    }

    /**
     * Load common data.
     */
    private void loadCommonData() {
        final String prefix = "loadTaskCommonFieldData(): ";

        LOGGER.debug("{} entered", prefix);

        if (getUserObject() instanceof Task) {
            final Task task = (Task) getUserObject();

            getEnabledCheckbox().setSelected(task.getEnabled());
        }

        if (getUserObject() instanceof StopOnErrorTask) {
            final StopOnErrorTask stopOnErrorTask = (StopOnErrorTask) getUserObject();

            getStopOnErrorCheckbox().setSelected(stopOnErrorTask.getStopOnError());
        }

        LOGGER.debug("{} leaving", prefix);
    }

    /*
     * (non-Javadoc)
     * @see net.lmxm.ute.gui.editors.AbstractCommonEditorPanel#loadData()
     */
    @Override
    public void loadData() {
        // Load Common Fields
        super.loadData();
        loadCommonData();

        // Load Source Fields
        loadHttpSourceFieldData();
        loadMavenRepositorySourceFieldData();
        loadSubversionRepositorySourceFieldData();

        // Load Target Fields
        loadFileSystemTargetFieldData();

        // Load Other Fields
        loadFilesFieldData();
    }

    /**
     * Load files field data.
     */
    private void loadFilesFieldData() {
        if (getUserObject() instanceof RenameFilesTask) {
            final RenameFilesTask renameFilesTask = (RenameFilesTask) getUserObject();

            getFilesTable().setModel(new RenameFilesTableModel(renameFilesTask));
        }
        else if (getUserObject() instanceof FilesTask) {
            final FilesTask filesTask = (FilesTask) getUserObject();

            getFilesTable().setModel(new FilesTableModel(filesTask));
        }
    }

    /**
     * Load file system target field data.
     */
    private void loadFileSystemTargetFieldData() {
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
     */
    private void loadHttpSourceFieldData() {
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
     * Load Maven repository source field data.
     */
    private void loadMavenRepositorySourceFieldData() {
        final String prefix = "loadMavenRepositorySourceFieldData(): ";

        LOGGER.debug("{} entered");

        if (getUserObject() instanceof MavenRepositorySourceTask) {
            final MavenRepositorySource mavenRepositorySource = ((MavenRepositorySourceTask) getUserObject())
                    .getSource();

            getMavenRepositoryLocationSourceComboBox().setSelectedItem(mavenRepositorySource.getLocation());
            getSourceRelativePathTextField().setText(mavenRepositorySource.getRelativePath());
        }

        LOGGER.debug("{} leaving", prefix);
    }

    /**
     * Load subversion repository source field data.
     */
    private void loadSubversionRepositorySourceFieldData() {
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
}
