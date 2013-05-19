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
package net.lmxm.ute.gui;

import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.configuration.ApplicationPreferences;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.jobs.SingleTaskJob;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.*;
import net.lmxm.ute.configuration.*;
import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.event.IdChangeEventBus;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.executers.jobs.JobStatusEventBus;
import net.lmxm.ute.gui.components.OptionPaneFactory;
import net.lmxm.ute.gui.statusoutput.StatusOutputPanel;
import net.lmxm.ute.gui.statusoutput.StatusOutputTab;
import net.lmxm.ute.gui.dialogs.AboutDialog;
import net.lmxm.ute.gui.dialogs.EditPreferencesDialog;
import net.lmxm.ute.gui.editors.*;
import net.lmxm.ute.gui.editors.locations.*;
import net.lmxm.ute.gui.editors.tasks.*;
import net.lmxm.ute.gui.frames.AbstractFrame;
import net.lmxm.ute.gui.maintree.MainTree;
import net.lmxm.ute.gui.maintree.nodes.*;
import net.lmxm.ute.gui.menus.MainMenuBar;
import net.lmxm.ute.gui.toolbars.FileToolBar;
import net.lmxm.ute.gui.toolbars.MainTreeToolBar;
import net.lmxm.ute.gui.utils.GuiUtils;
import net.lmxm.ute.gui.workers.ExecuteJobWorker;
import net.lmxm.ute.preferences.UserPreferences;
import net.lmxm.ute.resources.ImageUtil;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ApplicationResourceType;
import net.lmxm.ute.resources.types.ConfirmationResourceType;
import net.lmxm.ute.utils.FileSystemUtils;
import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.lmxm.ute.ApplicationConstants.FILE_EXTENSION;
import static net.lmxm.ute.enums.ActionCommand.*;

/**
 * The Class MainFrame.
 */
@SuppressWarnings("serial")
public final class MainFrame extends AbstractFrame implements ConfigurationHolder, UteActionListener,
		TreeSelectionListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);

	/** The Constant PADDING_SIZE. */
	private static final int PADDING_SIZE = 7;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2194241637714084500L;

    /**
     * Set of supported action commands.
     */
    private static final EnumSet<ActionCommand> SUPPORTED_ACTION_COMMANDS = EnumSet.of(
            ABOUT, ADD_FILE_SYSTEM_DELETE_TASK, ADD_FILE_SYSTEM_LOCATION, ADD_FIND_REPLACE_TASK,
            ADD_GROOVY_TASK, ADD_HTTP_DOWNLOAD_TASK, ADD_HTTP_LOCATION, ADD_MAVEN_REPOSITORY_DOWNLOAD_TASK,
            ADD_MAVEN_REPOSITORY_LOCATION, ADD_JOB, ADD_PREFERENCE, ADD_PROPERTY,
            ADD_SUBVERSION_EXPORT_TASK, ADD_SUBVERSION_REPOSITORY_LOCATION, ADD_SUBVERSION_UPDATE_TASK,
            ADD_TASK, CLONE_FILE_SYSTEM_LOCATION, CLONE_HTTP_LOCATION, CLONE_JOB,
            CLONE_MAVEN_REPOSITORY_LOCATION, CLONE_PREFERENCE, CLONE_PROPERTY,
            CLONE_SUBVERSION_REPOSITORY_LOCATION, CLONE_TASK, COLLAPSE, DELETE_FILE_SYSTEM_LOCATION,
            DELETE_HTTP_LOCATION, DELETE_JOB, DELETE_MAVEN_REPOSITORY_LOCATION, DELETE_PREFERENCE,
            DELETE_PROPERTY, DELETE_SUBVERSION_REPOSITORY_LOCATION, DELETE_TASK, EDIT_PREFERENCES,
            EXIT, EXPAND, EXECUTE, NEW_FILE, OPEN_FILE, RELOAD_FILE, SAVE_FILE, SAVE_FILE_AS);

	/** The application preferences. */
	private ApplicationPreferences applicationPreferences = null;

	/** The bottom panel. */
	private JTabbedPane bottomPanel = null;

	/** The configuration. */
	private Configuration configuration;

    private final Map<Class, AbstractEditorPanel> treeNodeEditorMap = new HashMap<Class, AbstractEditorPanel>();

	/** The file tool bar. */
	private FileToolBar fileToolBar = null;

	/** The j content pane. */
	private JPanel jContentPane = null;

	/** The job details editor scroll pane. */
	private JScrollPane jobDetailsEditorScrollPane = null;

	/** The jobs split pane. */
	private JSplitPane jobsSplitPane = null;

	/** The jobs tree pane. */
	private JPanel jobsTreePane = null;

	/** The jobs tree scroll pane. */
	private JScrollPane jobsTreeScrollPane = null;

	/** The main menu bar. */
	private MainMenuBar mainMenuBar = null;

    /** The main split pane. */
	private JSplitPane mainSplitPane = null;

	/** The main tree. */
	private MainTree mainTree = null;

	/** The main tree tool bar. */
	private MainTreeToolBar mainTreeToolBar = null;

	/** The user preferences. */
	private final UserPreferences userPreferences = new UserPreferences();

	/**
	 * Instantiates a new main frame.
	 */
	public MainFrame() {
		super();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		initialize();
	}

	/**
	 * Action about.
	 */
	private void actionAbout() {
		final JDialog dialog = new AboutDialog();
		GuiUtils.center(dialog);
		dialog.setVisible(true);
	}

	/**
	 * Action add file system delete task.
	 */
	private void actionAddFileSystemDeleteTask() {
		addNewTask(new FileSystemDeleteTask(getCurrentJob()));
	}

	/**
	 * Action add file system location.
	 */
	private void actionAddFileSystemLocation() {
		final FileSystemLocation fileSystemLocation = new FileSystemLocation();
		configuration.getFileSystemLocations().add(fileSystemLocation);
		mainTree.addFileSystemLocation(fileSystemLocation);
	}

	/**
	 * Action add find replace task.
	 */
	private void actionAddFindReplaceTask() {
		addNewTask(new FindReplaceTask(getCurrentJob()));
	}

	/**
	 * Action add groovy task.
	 */
	private void actionAddGroovyTask() {
		addNewTask(new GroovyTask(getCurrentJob()));
	}

	/**
	 * Action add http download task.
	 */
	private void actionAddHttpDownloadTask() {
		addNewTask(new HttpDownloadTask(getCurrentJob()));
	}

	/**
	 * Action add Maven repository download task.
	 */
	private void actionAddMavenRepositoryDownloadTask() {
		addNewTask(new MavenRepositoryDownloadTask(getCurrentJob()));
	}

	/**
	 * Action add http location.
	 */
	private void actionAddHttpLocation() {
		final HttpLocation httpLocation = new HttpLocation();
		configuration.getHttpLocations().add(httpLocation);
		mainTree.addHttpLocation(httpLocation);
	}

	/**
	 * Action add job.
	 */
	private void actionAddJob() {
		final Job job = new SequentialJob();
		configuration.getJobs().add(job);
		mainTree.addJob(job);
	}

    /**
     * Action add Maven repository location.
     */
    private void actionAddMavenRepositoryLocation() {
        final MavenRepositoryLocation mavenRepositoryLocation= new MavenRepositoryLocation();
        configuration.getMavenRepositoryLocations().add(mavenRepositoryLocation);
        mainTree.addMavenRepositoryLocation(mavenRepositoryLocation);
    }

	/**
	 * Action add preference.
	 */
	private void actionAddPreference() {
		final Preference preference = new Preference();
		configuration.getPreferences().add(preference);
		mainTree.addPreference(preference);
	}

	/**
	 * Action add property.
	 */
	private void actionAddProperty() {
		final Property property = new Property();
		configuration.getProperties().add(property);
		mainTree.addProperty(property);
	}

	/**
	 * Action add subversion export task.
	 */
	private void actionAddSubversionExportTask() {
		addNewTask(new SubversionExportTask(getCurrentJob()));
	}

	/**
	 * Action add subversion repository location.
	 */
	private void actionAddSubversionRepositoryLocation() {
		final SubversionRepositoryLocation subversionRepositoryLocation = new SubversionRepositoryLocation();
		configuration.getSubversionRepositoryLocations().add(subversionRepositoryLocation);
		mainTree.addSubversionRepositoryLocation(subversionRepositoryLocation);
	}

	/**
	 * Action add subversion update task.
	 */
	private void actionAddSubversionUpdateTask() {
		addNewTask(new SubversionUpdateTask(getCurrentJob()));
	}

    /**
     * Action add task.
     */
    private void actionAddTask() {
        // Do nothing for now
    }

	/**
	 * Action clone file system location.
	 */
	private void actionCloneFileSystemLocation() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof FileSystemLocation)) {
			return;
		}

		final FileSystemLocation fileSystemLocation = (FileSystemLocation) cloneIdentifiableBean((IdentifiableBean) userObject);
		configuration.getFileSystemLocations().add(fileSystemLocation);
		mainTree.addFileSystemLocation(fileSystemLocation);
	}

	/**
	 * Action clone http location.
	 */
	private void actionCloneHttpLocation() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof HttpLocation)) {
			return;
		}

		final HttpLocation httpLocation = (HttpLocation) cloneIdentifiableBean((IdentifiableBean) userObject);
		configuration.getHttpLocations().add(httpLocation);
		mainTree.addHttpLocation(httpLocation);
	}

	/**
	 * Action clone job.
	 */
	private void actionCloneJob() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Job)) {
			return;
		}

		final Job job = (Job) cloneIdentifiableBean((IdentifiableBean) userObject);
		configuration.getJobs().add(job);
		mainTree.addJob(job);
	}

    /**
     * Action clone Maven repository location.
     */
    private void actionCloneMavenRepositoryLocation() {
        final Object userObject = getMainTree().getSelectedTreeObject();
        if (!(userObject instanceof MavenRepositoryLocation)) {
            return;
        }

        final MavenRepositoryLocation mavenRepositoryLocation = (MavenRepositoryLocation) cloneIdentifiableBean((IdentifiableBean) userObject);
        configuration.getMavenRepositoryLocations().add(mavenRepositoryLocation);
        mainTree.addMavenRepositoryLocation(mavenRepositoryLocation);
    }

	/**
	 * Action clone preference.
	 */
	private void actionClonePreference() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Preference)) {
			return;
		}

		final Preference preference = (Preference) cloneIdentifiableBean((IdentifiableBean) userObject);
		configuration.getPreferences().add(preference);
		mainTree.addPreference(preference);
	}

	/**
	 * Action clone property.
	 */
	private void actionCloneProperty() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Property)) {
			return;
		}

		final Property property = (Property) cloneIdentifiableBean((IdentifiableBean) userObject);
		configuration.getProperties().add(property);
		mainTree.addProperty(property);
	}

	/**
	 * Action clone subversion repository location.
	 */
	private void actionCloneSubversionRepositoryLocation() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof SubversionRepositoryLocation)) {
			return;
		}

		final SubversionRepositoryLocation subversionRepositoryLocation = (SubversionRepositoryLocation) cloneIdentifiableBean((IdentifiableBean) userObject);
		configuration.getSubversionRepositoryLocations().add(subversionRepositoryLocation);
		mainTree.addSubversionRepositoryLocation(subversionRepositoryLocation);
	}

	/**
	 * Action clone task.
	 */
	private void actionCloneTask() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Task)) {
			return;
		}

		final Task originalTask = (Task) userObject;
		final Task cloneTask = (Task) cloneIdentifiableBean(originalTask);

		// Change clone task to point to original Job
		cloneTask.setJob(originalTask.getJob());

		addNewTask(cloneTask);
	}

	/**
	 * Action collapse.
	 */
	private void actionCollapse() {
		getMainTree().collapseAll();
	}

	/**
	 * Action delete file system location.
	 */
	private void actionDeleteFileSystemLocation() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof FileSystemLocation)) {
			return;
		}

		final FileSystemLocation fileSystemLocation = (FileSystemLocation) userObject;
		configuration.getFileSystemLocations().remove(fileSystemLocation);
		mainTree.deleteFileSystemLocation(fileSystemLocation);
	}

	/**
	 * Action delete http location.
	 */
	private void actionDeleteHttpLocation() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof HttpLocation)) {
			return;
		}

		final HttpLocation httpLocation = (HttpLocation) userObject;
		configuration.getHttpLocations().remove(httpLocation);
		mainTree.deleteHttpLocation(httpLocation);
	}

	/**
	 * Action delete job.
	 */
	private void actionDeleteJob() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Job)) {
			return;
		}

		final Job job = (Job) userObject;

		if (job.getTasks().size() > 0) {
			final int result = OptionPaneFactory.showConfirmation(this, ConfirmationResourceType.DELETE_JOB,
					job.getId(), job.getTasks().size());

			if (result == JOptionPane.NO_OPTION) {
				return;
			}
		}

		configuration.getJobs().remove(job);
		mainTree.deleteJob(job);
	}

    /**
     * Action delete Maven repository location.
     */
    private void actionDeleteMavenRepositoryLocation() {
        final Object userObject = getMainTree().getSelectedTreeObject();
        if (!(userObject instanceof MavenRepositoryLocation)) {
            return;
        }

        final MavenRepositoryLocation mavenRepositoryLocation = (MavenRepositoryLocation) userObject;
        configuration.getMavenRepositoryLocations().remove(mavenRepositoryLocation);
        mainTree.deleteMavenRepositoryLocation(mavenRepositoryLocation);
    }

	/**
	 * Action delete preference.
	 */
	private void actionDeletePreference() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Preference)) {
			return;
		}

		final Preference preference = (Preference) userObject;
		configuration.getPreferences().remove(preference);
		mainTree.deletePreference(preference);
	}

	/**
	 * Action delete property.
	 */
	private void actionDeleteProperty() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Property)) {
			return;
		}

		final Property property = (Property) userObject;
		configuration.getProperties().remove(property);
		mainTree.deleteProperty(property);
	}

	/**
	 * Action delete subversion repository location.
	 */
	private void actionDeleteSubversionRepositoryLocation() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof SubversionRepositoryLocation)) {
			return;
		}

		final SubversionRepositoryLocation subversionRepositoryLocation = (SubversionRepositoryLocation) userObject;
		configuration.getSubversionRepositoryLocations().remove(subversionRepositoryLocation);
		mainTree.deleteSubversionRepositoryLocation(subversionRepositoryLocation);
	}

	/**
	 * Action delete task.
	 */
	private void actionDeleteTask() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (!(userObject instanceof Task)) {
			return;
		}

		final Task task = (Task) userObject;
		final Job job = task.getJob();

		final List<Job> jobs = configuration.getJobs();
		jobs.get(jobs.indexOf(job));
		job.getTasks().remove(task);

		mainTree.deleteTask(task);
	}

	/**
	 * Action edit preferences.
	 */
	private void actionEditPreferences() {
		final EditPreferencesDialog dialog = new EditPreferencesDialog(getConfiguration());
		GuiUtils.center(dialog);
		dialog.setVisible(true);
	}

	/**
	 * Action execute.
	 */
	private void actionExecute() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (userObject == null) {
			return;
		}

		Job job = null;

		if (userObject instanceof Job) {
			job = (Job) userObject;
		}
		else if (userObject instanceof Task) {
			job = new SingleTaskJob((Task) userObject);
		}

		if (job != null) {
			job = ConfigurationInterpolator.interpolateJobValues(job, configuration);
			job.removeEmptyObjects();

			final JTabbedPane tabbedPane = getBottomPanel();
			final StatusOutputPanel statusOutputPanel = new StatusOutputPanel(job);
			final StatusOutputTab statusOutputTab = new StatusOutputTab(job, tabbedPane);

			final ExecuteJobWorker jobWorker = new ExecuteJobWorker(job, configuration);

            JobStatusEventBus.register(statusOutputPanel, statusOutputTab);
            StatusChangeEventBus.register(statusOutputPanel, statusOutputTab);

			statusOutputPanel.setJobWorker(jobWorker);

			tabbedPane.insertTab("", null, statusOutputPanel, null, 0);
			tabbedPane.setSelectedIndex(0);
			tabbedPane.setTabComponentAt(0, statusOutputTab);

			jobWorker.execute();
		}
	}

	/**
	 * Action exit.
	 */
	private void actionExit() {
		final WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	/**
	 * Action expand.
	 */
	private void actionExpand() {
		getMainTree().expandAll();
	}

	/**
	 * Action new file.
	 */
	private void actionNewFile() {
		configuration = new Configuration();
		updateTitle();
		getMainTree().refresh();
	}

	/**
	 * Action open file.
	 */
	private void actionOpenFile() {
		final String prefix = "actionOpenFile() :";

		final JFileChooser fcOpen = new JFileChooser(getCurrentDirectory());

		fcOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fcOpen.setFileFilter(getFileFilter());

		final int returnVal = fcOpen.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final File file = fcOpen.getSelectedFile();

			LOGGER.debug("{} opening file {}", prefix, file.getName());

			try {
				configuration = new ConfigurationReader(file).read();

				loadAndValidatePreferences(file);

				userPreferences.setLastFileEditedPath(file.getAbsolutePath());

				refreshJobsTree();
				updateTitle();
				getJobDetailsEditorScrollPane().setViewportView(null);
			}
			catch (final Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			LOGGER.debug("{} cancelled by user", prefix);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent actionEvent) {
		try {
			final ActionCommand actionCommand = ActionCommand.valueOf(actionEvent.getActionCommand());

			if (actionCommand.equals(ABOUT)) {
				actionAbout();
			}
			else if (actionCommand == ADD_FILE_SYSTEM_DELETE_TASK) {
				actionAddFileSystemDeleteTask();
			}
			else if (actionCommand == ADD_FILE_SYSTEM_LOCATION) {
				actionAddFileSystemLocation();
			}
			else if (actionCommand == ADD_FIND_REPLACE_TASK) {
				actionAddFindReplaceTask();
			}
			else if (actionCommand == ADD_GROOVY_TASK) {
				actionAddGroovyTask();
			}
			else if (actionCommand == ADD_HTTP_DOWNLOAD_TASK) {
				actionAddHttpDownloadTask();
			}
			else if (actionCommand == ADD_HTTP_LOCATION) {
				actionAddHttpLocation();
			}
			else if (actionCommand == ADD_MAVEN_REPOSITORY_DOWNLOAD_TASK) {
				actionAddMavenRepositoryDownloadTask();
			}
			else if (actionCommand == ADD_MAVEN_REPOSITORY_LOCATION) {
				actionAddMavenRepositoryLocation();
			}
			else if (actionCommand == ADD_JOB) {
				actionAddJob();
			}
			else if (actionCommand == ADD_PREFERENCE) {
				actionAddPreference();
			}
			else if (actionCommand == ADD_PROPERTY) {
				actionAddProperty();
			}
			else if (actionCommand == ADD_SUBVERSION_EXPORT_TASK) {
				actionAddSubversionExportTask();
			}
			else if (actionCommand == ADD_SUBVERSION_REPOSITORY_LOCATION) {
				actionAddSubversionRepositoryLocation();
			}
			else if (actionCommand == ADD_SUBVERSION_UPDATE_TASK) {
				actionAddSubversionUpdateTask();
			}
            else if (actionCommand == ADD_TASK) {
                actionAddTask();
            }
			else if (actionCommand == CLONE_FILE_SYSTEM_LOCATION) {
				actionCloneFileSystemLocation();
			}
			else if (actionCommand == CLONE_HTTP_LOCATION) {
				actionCloneHttpLocation();
			}
			else if (actionCommand == CLONE_JOB) {
				actionCloneJob();
			}
			else if (actionCommand == CLONE_MAVEN_REPOSITORY_LOCATION) {
				actionCloneMavenRepositoryLocation();
			}
			else if (actionCommand == CLONE_PREFERENCE) {
				actionClonePreference();
			}
			else if (actionCommand == CLONE_PROPERTY) {
				actionCloneProperty();
			}
			else if (actionCommand == CLONE_SUBVERSION_REPOSITORY_LOCATION) {
				actionCloneSubversionRepositoryLocation();
			}
			else if (actionCommand == CLONE_TASK) {
				actionCloneTask();
			}
			else if (actionCommand == COLLAPSE) {
				actionCollapse();
			}
			else if (actionCommand == DELETE_FILE_SYSTEM_LOCATION) {
				actionDeleteFileSystemLocation();
			}
			else if (actionCommand == DELETE_HTTP_LOCATION) {
				actionDeleteHttpLocation();
			}
			else if (actionCommand == DELETE_JOB) {
				actionDeleteJob();
			}
			else if (actionCommand == DELETE_MAVEN_REPOSITORY_LOCATION) {
				actionDeleteMavenRepositoryLocation();
			}
			else if (actionCommand == DELETE_PREFERENCE) {
				actionDeletePreference();
			}
			else if (actionCommand == DELETE_PROPERTY) {
				actionDeleteProperty();
			}
			else if (actionCommand == DELETE_SUBVERSION_REPOSITORY_LOCATION) {
				actionDeleteSubversionRepositoryLocation();
			}
			else if (actionCommand == DELETE_TASK) {
				actionDeleteTask();
			}
			else if (actionCommand == EDIT_PREFERENCES) {
				actionEditPreferences();
			}
			else if (actionCommand == EXIT) {
				actionExit();
			}
			else if (actionCommand == EXPAND) {
				actionExpand();
			}
			else if (actionCommand == EXECUTE) {
				actionExecute();
			}
			else if (actionCommand == NEW_FILE) {
				actionNewFile();
			}
			else if (actionCommand == OPEN_FILE) {
				actionOpenFile();
			}
			else if (actionCommand == RELOAD_FILE) {
				actionReloadFile();
			}
			else if (actionCommand == SAVE_FILE) {
				actionSaveFile();
			}
			else if (actionCommand == SAVE_FILE_AS) {
				actionSaveFileAs();
			}
		}
		catch (final Exception exception) {
			displayError(exception);
		}
	}

    @Override
         public boolean supports(ActionCommand actionCommand) {
        return SUPPORTED_ACTION_COMMANDS.contains(actionCommand);
    }

	/**
	 * Action reload file.
	 */
	private void actionReloadFile() {
		final File configurationFile = configuration.getConfigurationFile();

		if (configurationFile != null) {
			configuration = new ConfigurationReader(configurationFile).read();
			refreshJobsTree();
			updateTitle();
		}
	}

	/**
	 * Action save file.
	 */
	private void actionSaveFile() {
		if (configuration.getConfigurationFile() == null) {
			final JFileChooser fcSave = new JFileChooser(getCurrentDirectory());

			fcSave.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fcSave.setDialogType(JFileChooser.SAVE_DIALOG);
			fcSave.setFileFilter(getFileFilter());

			final int returnVal = fcSave.showSaveDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				final String path = fcSave.getSelectedFile().getAbsolutePath();

				configuration.setConfigurationFile(new File(ConfigurationUtils.appendFileExtension(path)));

				updateTitle();
				new ConfigurationWriter(configuration).write();
			}
		}
		else {
			new ConfigurationWriter(configuration).write();
		}
	}

	/**
	 * Action save file as.
	 */
	private void actionSaveFileAs() {
		final JFileChooser fcSaveAs = new JFileChooser(getCurrentDirectory());

		fcSaveAs.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fcSaveAs.setDialogType(JFileChooser.SAVE_DIALOG);
		fcSaveAs.setFileFilter(getFileFilter());
		fcSaveAs.setDialogTitle(ResourcesUtils.getResourceTitle(ApplicationResourceType.SAVE_FILE_AS));

		final int returnVal = fcSaveAs.showSaveDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final String path = fcSaveAs.getSelectedFile().getAbsolutePath();

			configuration.setConfigurationFile(new File(ConfigurationUtils.appendFileExtension(path)));

			updateTitle();
			new ConfigurationWriter(configuration).write();
		}
	}

	/**
	 * Adds the task to job.
	 *
	 * @param task the task
	 */
	private void addNewTask(final Task task) {
		final Task currentTask = getCurrentTask();
		final List<Task> tasks = task.getJob().getTasks();
		int index = 0;

		if (currentTask == null) {
			// Add to start of the list
			tasks.add(index, task);
		}
		else {
			// Add after the currently selected task
			index = tasks.indexOf(currentTask) + 1;
			tasks.add(index, task);
		}

		getMainTree().addTask(index, task);
	}

	/**
	 * Clear input validators.
	 */
	public void clearInputValidators() {
        for (AbstractEditorPanel editorPanel : treeNodeEditorMap.values()) {
            editorPanel.clearInputValidators();
        }
	}

	/**
	 * Clear input validators for current editor.
	 */
	private void clearInputValidatorsForCurrentEditor() {
		final JScrollPane scrollPane = getJobDetailsEditorScrollPane();
		if (scrollPane != null) {
			final JViewport viewport = scrollPane.getViewport();

			if (viewport != null) {
				final Component view = viewport.getView();

				if (view instanceof AbstractEditorPanel) {
					((AbstractEditorPanel) view).clearInputValidators();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone not supported");
	}

	/**
	 * Clone identifiable bean.
	 *
	 * @param bean the bean
	 * @return the identifiable bean
	 */
	private IdentifiableBean cloneIdentifiableBean(final IdentifiableBean bean) {
		final IdentifiableBean newBean = (IdentifiableBean) SerializationUtils.clone(bean);

		newBean.setId("Copy of " + newBean.getId());

		return newBean;
	}

	/**
	 * Gets the bottom panel.
	 *
	 * @return the bottom panel
	 */
	private JTabbedPane getBottomPanel() {
		if (bottomPanel == null) {
			bottomPanel = new JTabbedPane(JTabbedPane.TOP);
		}
		return bottomPanel;
	}

	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Gets the current directory.
	 *
	 * @return the current directory
	 */
	private String getCurrentDirectory() {
		try {
			final File configurationFile = configuration.getConfigurationFile();

			if (configurationFile == null) {
				return new File(".").getCanonicalPath();
			}
			else {
				return configurationFile.getAbsolutePath();
			}
		}
		catch (final IOException e) {
			return null;
		}
	}

	/**
	 * Gets the current job.
	 *
	 * @return the current job
	 */
	private Job getCurrentJob() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (userObject instanceof Job) {
			return (Job) userObject;
		}
		else if (userObject instanceof Task) {
			return ((Task) userObject).getJob();
		}
		else {
			return null;
		}
	}

	/**
	 * Gets the current task.
	 *
	 * @return the current task
	 */
	private Task getCurrentTask() {
		final Object userObject = getMainTree().getSelectedTreeObject();
		if (userObject instanceof Task) {
			return (Task) userObject;
		}
		else {
			return null;
		}
	}

	/**
	 * Gets the file filter.
	 *
	 * @return the file filter
	 */
	private FileFilter getFileFilter() {
		final String description = ResourcesUtils.getResourceText(ApplicationResourceType.FILE_DESCRIPTION);
		return new FileNameExtensionFilter(description, FILE_EXTENSION);
	}

	/**
	 * Gets the file tool bar.
	 *
	 * @return the file tool bar
	 */
	private JToolBar getFileToolBar() {
		if (fileToolBar == null) {
			fileToolBar = new FileToolBar(this);
		}
		return fileToolBar;
	}

	/**
	 * Gets the j content pane.
	 *
	 * @return the j content pane
	 */
	private JPanel getJContentPane() {
		final String prefix = "getJContentPane() :";

		LOGGER.debug("{} entered", prefix);

		if (jContentPane == null) {
			LOGGER.debug("{} Creating component", prefix);

			jContentPane = new JPanel(new BorderLayout()) {
				{
					add(getFileToolBar(), BorderLayout.NORTH);
					add(getMainSplitPane(), BorderLayout.CENTER);
				}
			};
		}

		LOGGER.debug("{} leaving", prefix);

		return jContentPane;
	}

	/**
	 * Gets the job details editor scroll pane.
	 *
	 * @return the job details editor scroll pane
	 */
	private JScrollPane getJobDetailsEditorScrollPane() {
		if (jobDetailsEditorScrollPane == null) {
			jobDetailsEditorScrollPane = new JScrollPane();
			jobDetailsEditorScrollPane.setViewport(null);
		}
		return jobDetailsEditorScrollPane;
	}

	/**
	 * Gets the jobs split pane.
	 *
	 * @return the jobs split pane
	 */
	protected JSplitPane getJobsSplitPane() {
		if (jobsSplitPane == null) {
			jobsSplitPane = new JSplitPane() {
				{
					setDividerLocation(GuiContants.DEFAULT_JOBS_SPLIT_PANE_DIVIDER_LOCATION);
					setOneTouchExpandable(true);
					setLeftComponent(getJobsTreePane());
					setRightComponent(getJobDetailsEditorScrollPane());
				}
			};
		}
		return jobsSplitPane;
	}

	/**
	 * Gets the jobs tree pane.
	 *
	 * @return the jobs tree pane
	 */
	private JPanel getJobsTreePane() {
		if (jobsTreePane == null) {
			jobsTreePane = new JPanel(new BorderLayout()) {
				{
					add(getMainTreeToolBar(), BorderLayout.NORTH);
					add(getJobsTreeScrollPane(), BorderLayout.CENTER);
				}
			};
		}
		return jobsTreePane;
	}

	/**
	 * Gets the jobs tree scroll pane.
	 *
	 * @return the jobs tree scroll pane
	 */
	private JScrollPane getJobsTreeScrollPane() {
		if (jobsTreeScrollPane == null) {
			jobsTreeScrollPane = new JScrollPane() {
				{
					setViewportView(getMainTree());
				}
			};
		}

		return jobsTreeScrollPane;
	}

	/**
	 * Gets the main menu bar.
	 *
	 * @return the main menu bar
	 */
	private MainMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new MainMenuBar(this);
        }
		return mainMenuBar;
	}

	/**
	 * Gets the main split pane.
	 *
	 * @return the main split pane
	 */
	protected JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane() {
				{
					setBorder(BorderFactory.createEmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));
					setOrientation(JSplitPane.VERTICAL_SPLIT);
					setDividerLocation(GuiContants.DEFAULT_MAIN_SPLIT_PANE_DIVIDER_LOCATION);
					setOneTouchExpandable(true);
					setBottomComponent(getBottomPanel());
					setTopComponent(getJobsSplitPane());
				}
			};
		}
		return mainSplitPane;
	}

	/**
	 * Gets the main tree.
	 *
	 * @return the main tree
	 */
	private MainTree getMainTree() {
		if (mainTree == null) {
			mainTree = new MainTree(this, this);
			mainTree.addTreeSelectionListener(this);

            IdChangeEventBus.register(mainTree);
		}

		return mainTree;
	}

	/**
	 * Gets the main tree tool bar.
	 *
	 * @return the main tree tool bar
	 */
	private JToolBar getMainTreeToolBar() {
		if (mainTreeToolBar == null) {
			mainTreeToolBar = new MainTreeToolBar(this);
		}
		return mainTreeToolBar;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		final String prefix = "initialize() :";

		LOGGER.debug("{} entered", prefix);

		final String filePath = userPreferences.getLastFileEditedPath();

		if (FileSystemUtils.getInstance().fileExists(filePath)) {
			LOGGER.debug("{} loading file {}", prefix, filePath);

			try {
				final File configurationFile = new File(filePath);
				configuration = new ConfigurationReader(configurationFile).read();

				loadAndValidatePreferences(configurationFile);
			}
			catch (final Exception e) {
				LOGGER.debug("initialize() : Error occurred loading configuration file", e);

				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				configuration = new Configuration();
			}
		}
		else {
			LOGGER.debug("{} starting with an empty configuration", prefix);

			configuration = new Configuration();

			// Clear out invalid entry in user preferences
			userPreferences.removeLastFileEditedPath();
		}

		addWindowListener(new MainFrameWindowListener(this));

		refreshJobsTree();

		updateTitle();
		setJMenuBar(getMainMenuBar());
		setIconImage(ImageUtil.APPLICATION_ICON_IMAGE);
		setMinimumSize(new Dimension(600, 500));
		setContentPane(getJContentPane());

		// Load editor mapping
        initializeTreeNodeEditorMap();

        pack();

		// Load preferences, including window size, location and state
		loadUserPreferences();

		LOGGER.debug("{} leaving", prefix);
	}

    /**
     * Initializes the tree node editor map.
     */
    private void initializeTreeNodeEditorMap() {
        final ConfigurationHolder configurationHolder = this;
        final UteActionListener actionListener = this;
        
        treeNodeEditorMap.put(FileSystemDeleteTask.class,
                new FileSystemDeleteTaskEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(FileSystemLocation.class,
                new FileSystemLocationEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(FileSystemLocationsRootTreeNode.class,
                new FileSystemLocationsEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(FindReplaceTask.class,
                new FindReplaceTaskEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(GroovyTask.class,
                new GroovyTaskEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(HttpDownloadTask.class,
                new HttpDownloadTaskEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(HttpLocation.class,
                new HttpLocationEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(HttpLocationsRootTreeNode.class,
                new HttpLocationsEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(JobsRootTreeNode.class,
                new JobsEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(MavenRepositoryDownloadTask.class,
                new MavenRepositoryDownloadTaskEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(MavenRepositoryLocation.class,
                new MavenRepositoryLocationEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(MavenRepositoryLocationsRootTreeNode.class,
                new MavenRepositoryLocationsEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(Preference.class,
                new PreferenceEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(PreferencesRootTreeNode.class,
                new PreferencesEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(PropertiesRootTreeNode.class,
                new PropertiesEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(Property.class,
                new PropertyEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(SequentialJob.class,
                new SequentialJobEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(SubversionExportTask.class,
                new SubversionExportTaskEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(SubversionRepositoryLocation.class,
                new SubversionRepositoryLocationEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(SubversionRepositoryLocationsRootTreeNode.class,
                new SubversionRepositoryLocationsEditorPanel(configurationHolder, actionListener));
        treeNodeEditorMap.put(SubversionUpdateTask.class,
                new SubversionUpdateTaskEditorPanel(configurationHolder, actionListener));
    }

    /**
	 * Load and validate preferences.
	 *
	 * @param configurationFile the configuration file
	 */
	private void loadAndValidatePreferences(final File configurationFile) {
		applicationPreferences = new ApplicationPreferences(configurationFile);
		validatePreferencesAreSet();
	}

	/**
	 * Load user preferences.
	 */
	private void loadUserPreferences() {
		final Dimension windowSize = userPreferences.getWindowSize();

		if (windowSize != null) {
			setSize(windowSize);
		}

		final Point location = userPreferences.getWindowLocation();

		if (location != null) {
			setLocation(location);
		}

		setExtendedState(userPreferences.getWindowState());

		getMainSplitPane().setDividerLocation(userPreferences.getMainSplitPaneDividerLocation());
		getJobsSplitPane().setDividerLocation(userPreferences.getJobsSplitPaneDividerLocation());
	}

	/**
	 * Refresh jobs tree.
	 */
	private void refreshJobsTree() {
		getMainTree().refresh();
	}

	/**
	 * Update title.
	 */
	private void updateTitle() {
		final StringBuilder builder = new StringBuilder();

		builder.append(ResourcesUtils.getResourceText(ApplicationResourceType.NAME));
		builder.append(" ");
		builder.append(ResourcesUtils.getResourceText(ApplicationResourceType.VERSION));

		if (configuration != null) {
			final File configurationFile = configuration.getConfigurationFile();
			final String newFile = ResourcesUtils.getResourceText(ApplicationResourceType.NEW_FILE);

			builder.append(" - ");
			builder.append(configurationFile == null ? newFile : configurationFile.getAbsolutePath());
		}

		setTitle(builder.toString());
	}

	/**
	 * Validate preferences are set.
	 */
	private void validatePreferencesAreSet() {
		final String prefix = "validatePreferencesAreSet() :";

		LOGGER.debug("{} entered", prefix);

		applicationPreferences.loadPreferenceValues(configuration.getPreferences());

		if (applicationPreferences.hasAllPreferences(configuration.getPreferences())) {
			LOGGER.debug("{} all preferences have values", prefix);
		}
		else {
			LOGGER.debug("{} at least one preference does not have a value", prefix);

			JOptionPane.showMessageDialog(this, "Preferences must be assigned values before continuing", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
        final String prefix = "valueChanged() :";

        try {
            final Object userObject = getMainTree().getSelectedTreeObject();

            // Load appropriate editor
            AbstractEditorPanel editorPane = null;

            if (userObject != null) {
                LOGGER.debug("{} Selected tree object is of type: {}", prefix, userObject.getClass().getName());

                editorPane = treeNodeEditorMap.get(userObject.getClass());

                if (editorPane == null) {
                    LOGGER.debug("{} Unsupported tree object type: {}", prefix, userObject.getClass().getName());
                }
            }
            else {
                LOGGER.debug("{} Selected tree object is null", prefix);
            }

            clearInputValidatorsForCurrentEditor();

            if (editorPane == null) {
                LOGGER.debug("{} No matching editor found", prefix);
                getJobDetailsEditorScrollPane().setViewportView(null);
            }
            else {
                LOGGER.debug("{} Matching editor is of type: {}", prefix, editorPane.getClass().getName());
                editorPane.initialize(configuration);
                editorPane.setUserObject(userObject);
                editorPane.loadData();
                getJobDetailsEditorScrollPane().setViewportView(editorPane);
                editorPane.setFocusToFirstInput();
            }
        }
        catch (Exception e) {
            LOGGER.error(prefix + " Error occurred handling value changed event", e);
            displayError(e);
        }
	}
}
