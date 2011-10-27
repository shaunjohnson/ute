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

import static net.lmxm.ute.gui.ActionConstants.ABOUT;
import static net.lmxm.ute.gui.ActionConstants.ADD_FILE_SYSTEM_LOCATION;
import static net.lmxm.ute.gui.ActionConstants.ADD_HTTP_LOCATION;
import static net.lmxm.ute.gui.ActionConstants.ADD_JOB;
import static net.lmxm.ute.gui.ActionConstants.ADD_PREFERENCE;
import static net.lmxm.ute.gui.ActionConstants.ADD_PROPERTY;
import static net.lmxm.ute.gui.ActionConstants.ADD_SUBVERSION_REPOSITORY_LOCATION;
import static net.lmxm.ute.gui.ActionConstants.COLLAPSE;
import static net.lmxm.ute.gui.ActionConstants.DELETE_FILE_SYSTEM_LOCATION;
import static net.lmxm.ute.gui.ActionConstants.DELETE_HTTP_LOCATION;
import static net.lmxm.ute.gui.ActionConstants.DELETE_PREFERENCE;
import static net.lmxm.ute.gui.ActionConstants.DELETE_PROPERTY;
import static net.lmxm.ute.gui.ActionConstants.DELETE_SUBVERSION_REPOSITORY_LOCATION;
import static net.lmxm.ute.gui.ActionConstants.EDIT_PREFERENCES;
import static net.lmxm.ute.gui.ActionConstants.EXECUTE;
import static net.lmxm.ute.gui.ActionConstants.EXIT;
import static net.lmxm.ute.gui.ActionConstants.EXPAND;
import static net.lmxm.ute.gui.ActionConstants.NEW_FILE;
import static net.lmxm.ute.gui.ActionConstants.OPEN_FILE;
import static net.lmxm.ute.gui.ActionConstants.SAVE_FILE;
import static net.lmxm.ute.gui.ActionConstants.SAVE_FILE_AS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import net.lmxm.ute.ConfigurationHolder;
import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.jobs.SingleTaskJob;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.components.MainTree;
import net.lmxm.ute.gui.components.StatusOutputPanel;
import net.lmxm.ute.gui.components.StatusOutputTab;
import net.lmxm.ute.gui.dialogs.AboutDialog;
import net.lmxm.ute.gui.dialogs.EditPreferencesDialog;
import net.lmxm.ute.gui.editors.AbstractEditorPanel;
import net.lmxm.ute.gui.editors.JobsEditorPanel;
import net.lmxm.ute.gui.editors.PreferenceEditorPanel;
import net.lmxm.ute.gui.editors.PreferencesEditorPanel;
import net.lmxm.ute.gui.editors.PropertiesEditorPanel;
import net.lmxm.ute.gui.editors.PropertyEditorPanel;
import net.lmxm.ute.gui.editors.SequentialJobEditorPanel;
import net.lmxm.ute.gui.editors.locations.FileSystemLocationEditorPanel;
import net.lmxm.ute.gui.editors.locations.FileSystemLocationsEditorPanel;
import net.lmxm.ute.gui.editors.locations.HttpLocationEditorPanel;
import net.lmxm.ute.gui.editors.locations.HttpLocationsEditorPanel;
import net.lmxm.ute.gui.editors.locations.SubversionRepositoryLocationEditorPanel;
import net.lmxm.ute.gui.editors.locations.SubversionRepositoryLocationsEditorPanel;
import net.lmxm.ute.gui.editors.tasks.FileSystemDeleteTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.FindReplaceTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.GroovyTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.HttpDownloadTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.SubversionExportTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.SubversionUpdateTaskEditorPanel;
import net.lmxm.ute.gui.menus.MainMenuBar;
import net.lmxm.ute.gui.nodes.FileSystemLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.HttpLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.JobsRootTreeNode;
import net.lmxm.ute.gui.nodes.PreferencesRootTreeNode;
import net.lmxm.ute.gui.nodes.PropertiesRootTreeNode;
import net.lmxm.ute.gui.nodes.SubversionRepositoryLocationsRootTreeNode;
import net.lmxm.ute.gui.toolbars.FileToolBar;
import net.lmxm.ute.gui.toolbars.MainTreeToolBar;
import net.lmxm.ute.gui.utils.DialogUtil;
import net.lmxm.ute.gui.utils.ImageUtil;
import net.lmxm.ute.gui.utils.UserPreferences;
import net.lmxm.ute.gui.workers.ExecuteJobWorker;
import net.lmxm.ute.mapper.ConfigurationMapper;
import net.lmxm.ute.utils.ApplicationPreferences;
import net.lmxm.ute.utils.ConfigurationUtils;
import net.lmxm.ute.utils.FileSystemUtils;
import net.lmxm.ute.utils.ResourcesUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MainFrame.
 */
@SuppressWarnings("serial")
public final class MainFrame extends JFrame implements ConfigurationHolder, ActionListener, TreeSelectionListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);

	/** The Constant PADDING_SIZE. */
	private static final int PADDING_SIZE = 7;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2194241637714084500L;

	/** The application preferences. */
	private ApplicationPreferences applicationPreferences = null;

	/** The bottom panel. */
	private JTabbedPane bottomPanel = null;

	/** The configuration. */
	private Configuration configuration;

	/** The file system delete task editor panel. */
	private FileSystemDeleteTaskEditorPanel fileSystemDeleteTaskEditorPanel = null;

	/** The file system location editor panel. */
	private FileSystemLocationEditorPanel fileSystemLocationEditorPanel = null;

	/** The file system locations editor panel. */
	private FileSystemLocationsEditorPanel fileSystemLocationsEditorPanel = null;

	/** The file tool bar. */
	private FileToolBar fileToolBar = null;

	/** The find replace task editor panel. */
	private FindReplaceTaskEditorPanel findReplaceTaskEditorPanel = null;

	/** The groovy task editor panel. */
	private GroovyTaskEditorPanel groovyTaskEditorPanel = null;

	/** The http download task editor panel. */
	private HttpDownloadTaskEditorPanel httpDownloadTaskEditorPanel = null;

	/** The http location editor panel. */
	private HttpLocationEditorPanel httpLocationEditorPanel = null;

	/** The http locations editor panel. */
	private HttpLocationsEditorPanel httpLocationsEditorPanel = null;

	/** The j content pane. */
	private JPanel jContentPane = null;

	/** The job details editor scroll pane. */
	private JScrollPane jobDetailsEditorScrollPane = null;

	/** The jobs editor panel. */
	private JobsEditorPanel jobsEditorPanel = null;

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

	/** The preference editor panel. */
	private PreferenceEditorPanel preferenceEditorPanel = null;

	/** The preferences editor panel. */
	private PreferencesEditorPanel preferencesEditorPanel;

	/** The properties editor panel. */
	private PropertiesEditorPanel propertiesEditorPanel = null;

	/** The property editor panel. */
	private PropertyEditorPanel propertyEditorPanel = null;

	/** The sequential job editor panel. */
	private SequentialJobEditorPanel sequentialJobEditorPanel = null;

	/** The subversion export task editor panel. */
	private SubversionExportTaskEditorPanel subversionExportTaskEditorPanel = null;

	/** The subversion repository location editor panel. */
	private SubversionRepositoryLocationEditorPanel subversionRepositoryLocationEditorPanel = null;

	/** The subversion repository locations editor panel. */
	private SubversionRepositoryLocationsEditorPanel subversionRepositoryLocationsEditorPanel = null;

	/** The subversion update task editor panel. */
	private SubversionUpdateTaskEditorPanel subversionUpdateTaskEditorPanel = null;

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
		DialogUtil.center(dialog);
		dialog.setVisible(true);
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
	 * Action add http location.
	 */
	private void actionAddHttpLocation() {
		final HttpLocation httpLocation = new HttpLocation();
		configuration.getHttpLocations().add(httpLocation);
		mainTree.addHttpLocation(httpLocation);
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
	 * Action add subversion repository location.
	 */
	private void actionAddSubversionRepositoryLocation() {
		final SubversionRepositoryLocation subversionRepositoryLocation = new SubversionRepositoryLocation();
		configuration.getSubversionRepositoryLocations().add(subversionRepositoryLocation);
		mainTree.addSubversionRepositoryLocation(subversionRepositoryLocation);
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
	 * Action edit preferences.
	 */
	private void actionEditPreferences() {
		final EditPreferencesDialog dialog = new EditPreferencesDialog(getConfiguration());
		DialogUtil.center(dialog);
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
			job = ConfigurationUtils.interpolateJobValues(job, configuration);

			final JTabbedPane tabbedPane = getBottomPanel();
			final StatusOutputPanel statusOutputPanel = new StatusOutputPanel(job);
			final StatusOutputTab statusOutputTab = new StatusOutputTab(tabbedPane, job.getId());

			final ExecuteJobWorker jobWorker = new ExecuteJobWorker(job, configuration);
			jobWorker.addJobStatusListener(statusOutputPanel);
			jobWorker.addJobStatusListener(statusOutputTab);
			jobWorker.addStatusChangeListener(statusOutputPanel);
			jobWorker.addStatusChangeListener(statusOutputTab);

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

		final int returnVal = fcOpen.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final File file = fcOpen.getSelectedFile();

			LOGGER.debug("{} opening file {}", prefix, file.getName());

			try {
				configuration = ConfigurationMapper.getInstance().parse(file);

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
		final String actionCommand = actionEvent.getActionCommand();

		if (actionCommand.equals(ABOUT)) {
			actionAbout();
		}
		else if (actionCommand.equals(ADD_FILE_SYSTEM_LOCATION)) {
			actionAddFileSystemLocation();
		}
		else if (actionCommand.equals(ADD_HTTP_LOCATION)) {
			actionAddHttpLocation();
		}
		else if (actionCommand.equals(ADD_JOB)) {
			// TODO
		}
		else if (actionCommand.equals(ADD_PREFERENCE)) {
			actionAddPreference();
		}
		else if (actionCommand.equals(ADD_PROPERTY)) {
			actionAddProperty();
		}
		else if (actionCommand.equals(ADD_SUBVERSION_REPOSITORY_LOCATION)) {
			actionAddSubversionRepositoryLocation();
		}
		else if (actionCommand.equals(COLLAPSE)) {
			actionCollapse();
		}
		else if (actionCommand.equals(DELETE_FILE_SYSTEM_LOCATION)) {
			actionDeleteFileSystemLocation();
		}
		else if (actionCommand.equals(DELETE_HTTP_LOCATION)) {
			actionDeleteHttpLocation();
		}
		else if (actionCommand.equals(DELETE_PREFERENCE)) {
			actionDeletePreference();
		}
		else if (actionCommand.equals(DELETE_PROPERTY)) {
			actionDeleteProperty();
		}
		else if (actionCommand.equals(DELETE_SUBVERSION_REPOSITORY_LOCATION)) {
			actionDeleteSubversionRepositoryLocation();
		}
		else if (actionCommand.equals(EDIT_PREFERENCES)) {
			actionEditPreferences();
		}
		else if (actionCommand.equals(EXIT)) {
			actionExit();
		}
		else if (actionCommand.equals(EXPAND)) {
			actionExpand();
		}
		else if (actionCommand.equals(EXECUTE)) {
			actionExecute();
		}
		else if (actionCommand.equals(NEW_FILE)) {
			actionNewFile();
		}
		else if (actionCommand.equals(OPEN_FILE)) {
			actionOpenFile();
		}
		else if (actionCommand.equals(SAVE_FILE)) {
			// TODO
		}
		else if (actionCommand.equals(SAVE_FILE_AS)) {
			// TODO
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
			final String configurationPath = configuration.getAbsolutePath();

			if (configurationPath == null) {
				return new File(".").getCanonicalPath();
			}
			else {
				return configurationPath;
			}
		}
		catch (final IOException e) {
			return null;
		}
	}

	/**
	 * Gets the file system delete task editor panel.
	 * 
	 * @param fileSystemDeleteTask the file system delete task
	 * @return the file system delete task editor panel
	 */
	private FileSystemDeleteTaskEditorPanel getFileSystemDeleteTaskEditorPanel(
			final FileSystemDeleteTask fileSystemDeleteTask) {
		if (fileSystemDeleteTaskEditorPanel == null) {
			fileSystemDeleteTaskEditorPanel = new FileSystemDeleteTaskEditorPanel(this);
		}

		fileSystemDeleteTaskEditorPanel.loadData(fileSystemDeleteTask);

		return fileSystemDeleteTaskEditorPanel;
	}

	/**
	 * Gets the file system location editor panel.
	 * 
	 * @param fileSystemLocation the file system location
	 * @return the file system location editor panel
	 */
	private FileSystemLocationEditorPanel getFileSystemLocationEditorPanel(final FileSystemLocation fileSystemLocation) {
		if (fileSystemLocationEditorPanel == null) {
			fileSystemLocationEditorPanel = new FileSystemLocationEditorPanel(this);
		}

		fileSystemLocationEditorPanel.loadData(fileSystemLocation);

		return fileSystemLocationEditorPanel;
	}

	/**
	 * Gets the file system locations editor panel.
	 * 
	 * @return the file system locations editor panel
	 */
	private FileSystemLocationsEditorPanel getFileSystemLocationsEditorPanel() {
		if (fileSystemLocationsEditorPanel == null) {
			fileSystemLocationsEditorPanel = new FileSystemLocationsEditorPanel(this);
		}

		return fileSystemLocationsEditorPanel;
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
	 * Gets the find replace editor panel.
	 * 
	 * @param findReplaceTask the find replace task
	 * @return the find replace editor panel
	 */
	private FindReplaceTaskEditorPanel getFindReplaceTaskEditorPanel(final FindReplaceTask findReplaceTask) {
		if (findReplaceTaskEditorPanel == null) {
			findReplaceTaskEditorPanel = new FindReplaceTaskEditorPanel(this);
		}

		findReplaceTaskEditorPanel.loadData(findReplaceTask);

		return findReplaceTaskEditorPanel;
	}

	/**
	 * Gets the groovy task editor panel.
	 * 
	 * @param groovyTask the groovy task
	 * @return the groovy task editor panel
	 */
	private GroovyTaskEditorPanel getGroovyTaskEditorPanel(final GroovyTask groovyTask) {
		if (groovyTaskEditorPanel == null) {
			groovyTaskEditorPanel = new GroovyTaskEditorPanel(this);
		}

		groovyTaskEditorPanel.loadData(groovyTask);

		return groovyTaskEditorPanel;
	}

	/**
	 * Gets the http download task editor panel.
	 * 
	 * @param httpDownloadTask the http download task
	 * @return the http download task editor panel
	 */
	private HttpDownloadTaskEditorPanel getHttpDownloadTaskEditorPanel(final HttpDownloadTask httpDownloadTask) {
		if (httpDownloadTaskEditorPanel == null) {
			httpDownloadTaskEditorPanel = new HttpDownloadTaskEditorPanel(this);
		}

		httpDownloadTaskEditorPanel.loadData(httpDownloadTask);

		return httpDownloadTaskEditorPanel;
	}

	/**
	 * Gets the http location editor panel.
	 * 
	 * @param httpLocation the http location
	 * @return the http location editor panel
	 */
	private HttpLocationEditorPanel getHttpLocationEditorPanel(final HttpLocation httpLocation) {
		if (httpLocationEditorPanel == null) {
			httpLocationEditorPanel = new HttpLocationEditorPanel(this);
		}

		httpLocationEditorPanel.loadData(httpLocation);

		return httpLocationEditorPanel;
	}

	/**
	 * Gets the http locations editor panel.
	 * 
	 * @return the http locations editor panel
	 */
	private HttpLocationsEditorPanel getHttpLocationsEditorPanel() {
		if (httpLocationsEditorPanel == null) {
			httpLocationsEditorPanel = new HttpLocationsEditorPanel(this);
		}

		return httpLocationsEditorPanel;
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
		}
		return jobDetailsEditorScrollPane;
	}

	/**
	 * Gets the jobs editor panel.
	 * 
	 * @return the jobs editor panel
	 */
	private JobsEditorPanel getJobsEditorPanel() {
		if (jobsEditorPanel == null) {
			jobsEditorPanel = new JobsEditorPanel(this);
		}

		return jobsEditorPanel;
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
	 * Gets the preference editor panel.
	 * 
	 * @param preference the preference
	 * @return the preference editor panel
	 */
	private PreferenceEditorPanel getPreferenceEditorPanel(final Preference preference) {
		if (preferenceEditorPanel == null) {
			preferenceEditorPanel = new PreferenceEditorPanel(this);
		}

		preferenceEditorPanel.loadData(preference);

		return preferenceEditorPanel;
	}

	/**
	 * Gets the preferences editor panel.
	 * 
	 * @return the preferences editor panel
	 */
	private PreferencesEditorPanel getPreferencesEditorPanel() {
		if (preferencesEditorPanel == null) {
			preferencesEditorPanel = new PreferencesEditorPanel(this);
		}

		return preferencesEditorPanel;
	}

	/**
	 * Gets the properties editor panel.
	 * 
	 * @return the properties editor panel
	 */
	private PropertiesEditorPanel getPropertiesEditorPanel() {
		if (propertiesEditorPanel == null) {
			propertiesEditorPanel = new PropertiesEditorPanel(this);
		}

		return propertiesEditorPanel;
	}

	/**
	 * Gets the property editor panel.
	 * 
	 * @param property the property
	 * @return the property editor panel
	 */
	private PropertyEditorPanel getPropertyEditorPanel(final Property property) {
		if (propertyEditorPanel == null) {
			propertyEditorPanel = new PropertyEditorPanel(this);
		}

		propertyEditorPanel.loadData(property);

		return propertyEditorPanel;
	}

	/**
	 * Gets the sequential job editor panel.
	 * 
	 * @param job the job
	 * @return the sequential job editor panel
	 */
	private SequentialJobEditorPanel getSequentialJobEditorPanel(final Job job) {
		if (sequentialJobEditorPanel == null) {
			sequentialJobEditorPanel = new SequentialJobEditorPanel(this);
		}

		sequentialJobEditorPanel.loadData(job);

		return sequentialJobEditorPanel;
	}

	/**
	 * Gets the subversion export task editor panel.
	 * 
	 * @param subversionExportTask the subversion export task
	 * @return the subversion export task editor panel
	 */
	private SubversionExportTaskEditorPanel getSubversionExportTaskEditorPanel(
			final SubversionExportTask subversionExportTask) {
		if (subversionExportTaskEditorPanel == null) {
			subversionExportTaskEditorPanel = new SubversionExportTaskEditorPanel(this);
		}

		subversionExportTaskEditorPanel.loadData(subversionExportTask);

		return subversionExportTaskEditorPanel;
	}

	/**
	 * Gets the subversion repository location editor panel.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 * @return the subversion repository location editor panel
	 */
	private SubversionRepositoryLocationEditorPanel getSubversionRepositoryLocationEditorPanel(
			final SubversionRepositoryLocation subversionRepositoryLocation) {
		if (subversionRepositoryLocationEditorPanel == null) {
			subversionRepositoryLocationEditorPanel = new SubversionRepositoryLocationEditorPanel(this);
		}

		subversionRepositoryLocationEditorPanel.loadData(subversionRepositoryLocation);

		return subversionRepositoryLocationEditorPanel;
	}

	/**
	 * Gets the subversion repository locations editor panel.
	 * 
	 * @return the subversion repository locations editor panel
	 */
	private SubversionRepositoryLocationsEditorPanel getSubversionRepositoryLocationsEditorPanel() {
		if (subversionRepositoryLocationsEditorPanel == null) {
			subversionRepositoryLocationsEditorPanel = new SubversionRepositoryLocationsEditorPanel(this);
		}

		return subversionRepositoryLocationsEditorPanel;
	}

	/**
	 * Gets the subversion update task editor panel.
	 * 
	 * @param subversionUpdateTask the subversion update task
	 * @return the subversion update task editor panel
	 */
	private SubversionUpdateTaskEditorPanel getSubversionUpdateTaskEditorPanel(
			final SubversionUpdateTask subversionUpdateTask) {
		if (subversionUpdateTaskEditorPanel == null) {
			subversionUpdateTaskEditorPanel = new SubversionUpdateTaskEditorPanel(this);
		}

		subversionUpdateTaskEditorPanel.loadData(subversionUpdateTask);

		return subversionUpdateTaskEditorPanel;
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
				configuration = ConfigurationMapper.getInstance().parse(configurationFile);

				loadAndValidatePreferences(configurationFile);
			}
			catch (final Exception e) {
				LOGGER.debug("initialize() : Error occurred loading configuration file", e);

				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				configuration = new Configuration();
			}
		}
		else {
			LOGGER.debug("{} starting with an empty configruation", prefix);

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

		// Preload editors
		getJobsEditorPanel();
		getSequentialJobEditorPanel(null);
		getFileSystemDeleteTaskEditorPanel(null);
		getHttpDownloadTaskEditorPanel(null);
		getHttpLocationEditorPanel(null);
		getHttpLocationsEditorPanel();
		getSubversionExportTaskEditorPanel(null);
		getSubversionUpdateTaskEditorPanel(null);
		getFileSystemLocationEditorPanel(null);
		getFileSystemLocationsEditorPanel();
		getSubversionRepositoryLocationEditorPanel(null);
		getSubversionRepositoryLocationsEditorPanel();
		getPreferenceEditorPanel(null);
		getPreferencesEditorPanel();
		getPropertyEditorPanel(null);
		getPropertiesEditorPanel();

		pack();

		// Load preferences, including window size, location and state
		loadUserPreferences();

		LOGGER.debug("{} leaving", prefix);
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
		// final TreeModel treeModel = GuiUtils.loadJobDetailsTreeModel(configuration);
		//
		// getMainTree().setModel(treeModel);
		// getMainTree().expandPath(mainTree.getNextMatch("Job", 0, Position.Bias.Forward));

		getMainTree().refresh();
	}

	/**
	 * Update title.
	 */
	private void updateTitle() {
		final StringBuilder builder = new StringBuilder();

		builder.append(ResourcesUtils.getApplicationName());
		builder.append(" ");
		builder.append(ResourcesUtils.getApplicationVersion());

		if (configuration != null) {
			builder.append(" - ");
			builder.append(configuration.getAbsolutePath());
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
	public void valueChanged(final TreeSelectionEvent e) {
		final Object userObject = getMainTree().getSelectedTreeObject();

		// Load appropriate editor
		AbstractEditorPanel editorPane = null;

		if (userObject instanceof SequentialJob) {
			editorPane = getSequentialJobEditorPanel((Job) userObject);
		}
		else if (userObject instanceof FileSystemDeleteTask) {
			editorPane = getFileSystemDeleteTaskEditorPanel((FileSystemDeleteTask) userObject);
		}
		else if (userObject instanceof FileSystemLocation) {
			editorPane = getFileSystemLocationEditorPanel((FileSystemLocation) userObject);
		}
		else if (userObject instanceof FileSystemLocationsRootTreeNode) {
			editorPane = getFileSystemLocationsEditorPanel();
		}
		else if (userObject instanceof FindReplaceTask) {
			editorPane = getFindReplaceTaskEditorPanel((FindReplaceTask) userObject);
		}
		else if (userObject instanceof GroovyTask) {
			editorPane = getGroovyTaskEditorPanel((GroovyTask) userObject);
		}
		else if (userObject instanceof HttpDownloadTask) {
			editorPane = getHttpDownloadTaskEditorPanel((HttpDownloadTask) userObject);
		}
		else if (userObject instanceof HttpLocation) {
			editorPane = getHttpLocationEditorPanel((HttpLocation) userObject);
		}
		else if (userObject instanceof HttpLocationsRootTreeNode) {
			editorPane = getHttpLocationsEditorPanel();
		}
		else if (userObject instanceof JobsRootTreeNode) {
			editorPane = getJobsEditorPanel();
		}
		else if (userObject instanceof Preference) {
			editorPane = getPreferenceEditorPanel((Preference) userObject);
		}
		else if (userObject instanceof PreferencesRootTreeNode) {
			editorPane = getPreferencesEditorPanel();
		}
		else if (userObject instanceof PropertiesRootTreeNode) {
			editorPane = getPropertiesEditorPanel();
		}
		else if (userObject instanceof Property) {
			editorPane = getPropertyEditorPanel((Property) userObject);
		}
		else if (userObject instanceof SubversionExportTask) {
			editorPane = getSubversionExportTaskEditorPanel((SubversionExportTask) userObject);
		}
		else if (userObject instanceof SubversionRepositoryLocation) {
			editorPane = getSubversionRepositoryLocationEditorPanel((SubversionRepositoryLocation) userObject);
		}
		else if (userObject instanceof SubversionRepositoryLocationsRootTreeNode) {
			editorPane = getSubversionRepositoryLocationsEditorPanel();
		}
		else if (userObject instanceof SubversionUpdateTask) {
			editorPane = getSubversionUpdateTaskEditorPanel((SubversionUpdateTask) userObject);
		}
		else {
			// TODO
		}

		if (editorPane != null) {
			editorPane.initialize(configuration);
		}

		getJobDetailsEditorScrollPane().setViewportView(editorPane);
	}
}
