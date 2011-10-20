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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

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
import net.lmxm.ute.gui.components.StatusOutputPanel;
import net.lmxm.ute.gui.components.StatusOutputTab;
import net.lmxm.ute.gui.dialogs.AboutDialog;
import net.lmxm.ute.gui.dialogs.EditPreferencesDialog;
import net.lmxm.ute.gui.editors.AbstractEditorPanel;
import net.lmxm.ute.gui.editors.PreferenceEditorPanel;
import net.lmxm.ute.gui.editors.PropertiesEditorPanel;
import net.lmxm.ute.gui.editors.PropertyEditorPanel;
import net.lmxm.ute.gui.editors.SequentialJobEditorPanel;
import net.lmxm.ute.gui.editors.locations.FileSystemLocationEditorPanel;
import net.lmxm.ute.gui.editors.locations.HttpLocationEditorPanel;
import net.lmxm.ute.gui.editors.locations.SubversionRepositoryLocationEditorPanel;
import net.lmxm.ute.gui.editors.tasks.FileSystemDeleteTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.FindReplaceTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.GroovyTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.HttpDownloadTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.SubversionExportTaskEditorPanel;
import net.lmxm.ute.gui.editors.tasks.SubversionUpdateTaskEditorPanel;
import net.lmxm.ute.gui.menus.FileSystemLocationPopupMenu;
import net.lmxm.ute.gui.menus.FileSystemLocationsRootPopupMenu;
import net.lmxm.ute.gui.menus.HttpLocationPopupMenu;
import net.lmxm.ute.gui.menus.HttpLocationsRootPopupMenu;
import net.lmxm.ute.gui.menus.JobPopupMenu;
import net.lmxm.ute.gui.menus.JobsRootPopupMenu;
import net.lmxm.ute.gui.menus.PreferencePopupMenu;
import net.lmxm.ute.gui.menus.PreferencesRootPopupMenu;
import net.lmxm.ute.gui.menus.PropertiesRootPopupMenu;
import net.lmxm.ute.gui.menus.PropertyPopupMenu;
import net.lmxm.ute.gui.menus.SubversionRepositoryLocationPopupMenu;
import net.lmxm.ute.gui.menus.SubversionRepositoryLocationsRootPopupMenu;
import net.lmxm.ute.gui.menus.TaskPopupMenu;
import net.lmxm.ute.gui.nodes.PropertiesRootTreeNode;
import net.lmxm.ute.gui.renderers.JobDetailsTreeCellRenderer;
import net.lmxm.ute.gui.toolbars.FileToolBar;
import net.lmxm.ute.gui.toolbars.MainToolBar;
import net.lmxm.ute.gui.utils.DialogUtil;
import net.lmxm.ute.gui.utils.GuiUtils;
import net.lmxm.ute.gui.utils.ImageUtil;
import net.lmxm.ute.gui.utils.UserPreferences;
import net.lmxm.ute.gui.workers.ExecuteJobWorker;
import net.lmxm.ute.mapper.ConfigurationMapper;
import net.lmxm.ute.utils.ApplicationPreferences;
import net.lmxm.ute.utils.ConfigurationUtils;
import net.lmxm.ute.utils.FileSystemUtils;
import net.lmxm.ute.utils.ResourcesUtils;
import static net.lmxm.ute.gui.ActionConstants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MainFrame.
 */
@SuppressWarnings("serial")
public final class MainFrame extends JFrame implements ActionListener, KeyListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainFrame.class);

	/** The Constant PADDING_SIZE. */
	private static final int PADDING_SIZE = 7;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2194241637714084500L;

	/** The about menu item. */
	private JMenuItem aboutMenuItem = null;

	/** The application preferences. */
	private ApplicationPreferences applicationPreferences = null;

	/** The bottom panel. */
	private JTabbedPane bottomPanel = null;

	/** The configuration. */
	private Configuration configuration; // @jve:decl-index=0:

	/** The edit menu. */
	private JMenu editMenu = null;

	/** The edit preferences menu item. */
	private JMenuItem editPreferencesMenuItem = null;

	/** The exit menu item. */
	private JMenuItem exitMenuItem = null;

	/** The file menu. */
	private JMenu fileMenu = null;

	/** The file system delete task editor panel. */
	private FileSystemDeleteTaskEditorPanel fileSystemDeleteTaskEditorPanel = null;

	/** The file system location editor panel. */
	private FileSystemLocationEditorPanel fileSystemLocationEditorPanel = null;

	/** The file system location popup menu. */
	private FileSystemLocationPopupMenu fileSystemLocationPopupMenu = null;

	/** The file system locations root popup menu. */
	private FileSystemLocationsRootPopupMenu fileSystemLocationsRootPopupMenu = null;

	/** The file tool bar. */
	private FileToolBar fileToolBar = null;

	/** The find replace task editor panel. */
	private FindReplaceTaskEditorPanel findReplaceTaskEditorPanel = null;

	/** The groovy task editor panel. */
	private GroovyTaskEditorPanel groovyTaskEditorPanel = null;

	/** The help menu. */
	private JMenu helpMenu = null;

	/** The http download task editor panel. */
	private HttpDownloadTaskEditorPanel httpDownloadTaskEditorPanel = null;

	/** The http location editor panel. */
	private HttpLocationEditorPanel httpLocationEditorPanel = null;

	/** The http location popup menu. */
	private HttpLocationPopupMenu httpLocationPopupMenu = null;

	/** The http locations root popup menu. */
	private HttpLocationsRootPopupMenu httpLocationsRootPopupMenu = null;

	/** The j content pane. */
	private JPanel jContentPane = null;

	/** The job details editor scroll pane. */
	private JScrollPane jobDetailsEditorScrollPane = null;

	/** The job popup menu. */
	private JobPopupMenu jobPopupMenu = null;

	/** The jobs root popup menu. */
	private JobsRootPopupMenu jobsRootPopupMenu = null;

	/** The jobs split pane. */
	private JSplitPane jobsSplitPane = null;

	/** The jobs tree scroll pane. */
	private JScrollPane jobsTreeScrollPane = null;

	/** The main menu bar. */
	private JMenuBar mainMenuBar = null;

	/** The main split pane. */
	private JSplitPane mainSplitPane = null;

	/** The main tool bar. */
	private MainToolBar mainToolBar = null;

	/** The main tree. */
	private JTree mainTree = null;

	/** The new file menu item. */
	private JMenuItem newFileMenuItem = null;

	/** The open file menu item. */
	private JMenuItem openFileMenuItem = null;

	/** The preference editor panel. */
	private PreferenceEditorPanel preferenceEditorPanel = null;

	/** The preference popup menu. */
	private PreferencePopupMenu preferencePopupMenu = null;

	/** The preferences root popup menu. */
	private PreferencesRootPopupMenu preferencesRootPopupMenu = null;

	/** The properties editor panel. */
	private PropertiesEditorPanel propertiesEditorPanel = null;

	/** The properties root popup menu. */
	private PropertiesRootPopupMenu propertiesRootPopupMenu = null;

	/** The property editor panel. */
	private PropertyEditorPanel propertyEditorPanel = null;

	/** The property popup menu. */
	private PropertyPopupMenu propertyPopupMenu = null;

	/** The save as menu item. */
	private JMenuItem saveAsMenuItem = null;

	/** The save menu item. */
	private JMenuItem saveMenuItem = null;

	/** The sequential job editor panel. */
	private SequentialJobEditorPanel sequentialJobEditorPanel = null;

	/** The subversion export task editor panel. */
	private SubversionExportTaskEditorPanel subversionExportTaskEditorPanel = null;

	/** The subversion repository location editor panel. */
	private SubversionRepositoryLocationEditorPanel subversionRepositoryLocationEditorPanel = null;

	/** The subversion repository location popup menu. */
	private SubversionRepositoryLocationPopupMenu subversionRepositoryLocationPopupMenu = null;

	/** The subversion repository locations root popup menu. */
	private SubversionRepositoryLocationsRootPopupMenu subversionRepositoryLocationsRootPopupMenu = null;

	/** The subversion update task editor panel. */
	private SubversionUpdateTaskEditorPanel subversionUpdateTaskEditorPanel = null;

	/** The task popup menu. */
	private TaskPopupMenu taskPopupMenu = null;

	/** The toolbar panel. */
	private JPanel toolbarPanel = null;

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
	 * Action add property.
	 */
	private void actionAddProperty() {
		final Property property = new Property();
		configuration.getProperties().add(property);

		final TreePath treePath = GuiUtils.addPropertyToTreeModel(mainTree, property);
		if (treePath != null) {
			mainTree.setSelectionPath(treePath);
			mainTree.scrollPathToVisible(treePath);
		}
	}

	/**
	 * Action execute.
	 */
	private void actionExecute() {
		final Object userObject = getSelectedTreeObject();
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

			tabbedPane.insertTab(job.getId(), null, statusOutputPanel, null, 0);
			tabbedPane.setSelectedIndex(0);
			tabbedPane.setTabComponentAt(0, statusOutputTab);

			jobWorker.execute();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(final ActionEvent actionEvent) {
		final String actionCommand = actionEvent.getActionCommand();

		if (actionCommand.equals(ADD_JOB)) {
			// TODO Implement add job action
		}
		else if (actionCommand.equals(ADD_PROPERTY)) {
			actionAddProperty();
		}
		else if (actionCommand.equals(DELETE_PROPERTY)) {
			actionDeleteProperty();
		}
		else if (actionCommand.equals(EXIT)) {
			pullThePlug();
		}
		else if (actionCommand.equals(EXECUTE)) {
			actionExecute();
		}
		else if (actionCommand.equals(NEW_FILE)) {
			// TODO
		}
		else if (actionCommand.equals(OPEN_FILE)) {
			openFile();
		}
		else if (actionCommand.equals(SAVE_FILE)) {
			// TODO
		}
		else if (actionCommand.equals(SAVE_FILE_AS)) {
			// TODO
		}		
	}
	
	/**
	 * Action delete property.
	 */
	private void actionDeleteProperty() {
		// TODO
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
	 * Gets the about menu item.
	 * 
	 * @return the about menu item
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.setIcon(ImageUtil.ABOUT_ICON);

			final Component parent = this;
			aboutMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					final JDialog dialog = new AboutDialog();
					DialogUtil.center(parent, dialog);
					dialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
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
	 * Gets the edits the menu.
	 * 
	 * @return the edits the menu
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText("Edit");
			editMenu.add(getEditPreferencesMenuItem());
		}
		return editMenu;
	}

	/**
	 * Gets the edits the preferences menu item.
	 * 
	 * @return the edits the preferences menu item
	 */
	private JMenuItem getEditPreferencesMenuItem() {
		if (editPreferencesMenuItem == null) {
			editPreferencesMenuItem = new JMenuItem();
			editPreferencesMenuItem.setText("Edit Preferences");
			editPreferencesMenuItem.setIcon(ImageUtil.EDIT_PREFERENCES_ICON);

			final Component parent = this;
			editPreferencesMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					final EditPreferencesDialog dialog = new EditPreferencesDialog();

					dialog.loadPreferencesData(configuration);

					DialogUtil.center(parent, dialog);
					dialog.setVisible(true);
				}
			});
		}
		return editPreferencesMenuItem;
	}

	/**
	 * Gets the exit menu item.
	 * 
	 * @return the exit menu item
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.setIcon(ImageUtil.EXIT_ICON);

			exitMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					pullThePlug();
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * Gets the file menu.
	 * 
	 * @return the file menu
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getNewFileMenuItem());
			fileMenu.add(getOpenFileMenuItem());
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getSaveAsMenuItem());
			fileMenu.add(new JSeparator());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
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
			fileSystemDeleteTaskEditorPanel = new FileSystemDeleteTaskEditorPanel();
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
			fileSystemLocationEditorPanel = new FileSystemLocationEditorPanel();
		}

		fileSystemLocationEditorPanel.loadData(fileSystemLocation);

		return fileSystemLocationEditorPanel;
	}

	/**
	 * Gets the file system location popup menu.
	 * 
	 * @return the file system location popup menu
	 */
	protected FileSystemLocationPopupMenu getFileSystemLocationPopupMenu() {
		if (fileSystemLocationPopupMenu == null) {
			fileSystemLocationPopupMenu = new FileSystemLocationPopupMenu(this, this);
		}

		return fileSystemLocationPopupMenu;
	}

	/**
	 * Gets the file system locations root popup menu.
	 * 
	 * @return the file system locations root popup menu
	 */
	protected FileSystemLocationsRootPopupMenu getFileSystemLocationsRootPopupMenu() {
		if (fileSystemLocationsRootPopupMenu == null) {
			fileSystemLocationsRootPopupMenu = new FileSystemLocationsRootPopupMenu(this, this);
		}

		return fileSystemLocationsRootPopupMenu;
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
			findReplaceTaskEditorPanel = new FindReplaceTaskEditorPanel();
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
			groovyTaskEditorPanel = new GroovyTaskEditorPanel();
		}

		groovyTaskEditorPanel.loadData(groovyTask);

		return groovyTaskEditorPanel;
	}

	/**
	 * Gets the help menu.
	 * 
	 * @return the help menu
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * Gets the http download task editor panel.
	 * 
	 * @param httpDownloadTask the http download task
	 * @return the http download task editor panel
	 */
	private HttpDownloadTaskEditorPanel getHttpDownloadTaskEditorPanel(final HttpDownloadTask httpDownloadTask) {
		if (httpDownloadTaskEditorPanel == null) {
			httpDownloadTaskEditorPanel = new HttpDownloadTaskEditorPanel();
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
			httpLocationEditorPanel = new HttpLocationEditorPanel();
		}

		httpLocationEditorPanel.loadData(httpLocation);

		return httpLocationEditorPanel;
	}

	/**
	 * Gets the http location popup menu.
	 * 
	 * @return the http location popup menu
	 */
	protected HttpLocationPopupMenu getHttpLocationPopupMenu() {
		if (httpLocationPopupMenu == null) {
			httpLocationPopupMenu = new HttpLocationPopupMenu(this, this);
		}

		return httpLocationPopupMenu;
	}

	/**
	 * Gets the http locations root popup menu.
	 * 
	 * @return the http locations root popup menu
	 */
	protected HttpLocationsRootPopupMenu getHttpLocationsRootPopupMenu() {
		if (httpLocationsRootPopupMenu == null) {
			httpLocationsRootPopupMenu = new HttpLocationsRootPopupMenu(this, this);
		}

		return httpLocationsRootPopupMenu;
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

			jContentPane = new JPanel(new BorderLayout());
			jContentPane.setBorder(BorderFactory.createEmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE,
					PADDING_SIZE));
			jContentPane.add(getToolbarPanel(), BorderLayout.NORTH);
			jContentPane.add(getMainSplitPane(), BorderLayout.CENTER);
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
	 * Gets the job popup menu.
	 * 
	 * @return the job popup menu
	 */
	protected JobPopupMenu getJobPopupMenu() {
		if (jobPopupMenu == null) {
			jobPopupMenu = new JobPopupMenu(this, this);
		}

		return jobPopupMenu;
	}

	/**
	 * Gets the jobs root popup menu.
	 * 
	 * @return the jobs root popup menu
	 */
	protected JobsRootPopupMenu getJobsRootPopupMenu() {
		if (jobsRootPopupMenu == null) {
			jobsRootPopupMenu = new JobsRootPopupMenu(this, this);
		}

		return jobsRootPopupMenu;
	}

	/**
	 * Gets the jobs split pane.
	 * 
	 * @return the jobs split pane
	 */
	protected JSplitPane getJobsSplitPane() {
		if (jobsSplitPane == null) {
			jobsSplitPane = new JSplitPane();
			jobsSplitPane.setDividerLocation(GuiContants.DEFAULT_JOBS_SPLIT_PANE_DIVIDER_LOCATION);
			jobsSplitPane.setOneTouchExpandable(true);
			jobsSplitPane.setLeftComponent(getJobsTreeScrollPane());
			jobsSplitPane.setRightComponent(getJobDetailsEditorScrollPane());
		}
		return jobsSplitPane;
	}

	/**
	 * Gets the jobs tree scroll pane.
	 * 
	 * @return the jobs tree scroll pane
	 */
	private JScrollPane getJobsTreeScrollPane() {
		if (jobsTreeScrollPane == null) {
			jobsTreeScrollPane = new JScrollPane();
			jobsTreeScrollPane.setViewportView(getMainTree());
		}

		return jobsTreeScrollPane;
	}

	/**
	 * Gets the main menu bar.
	 * 
	 * @return the main menu bar
	 */
	private JMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getFileMenu());
			mainMenuBar.add(getEditMenu());
			mainMenuBar.add(getHelpMenu());
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
			mainSplitPane = new JSplitPane();
			mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			mainSplitPane.setDividerLocation(GuiContants.DEFAULT_MAIN_SPLIT_PANE_DIVIDER_LOCATION);
			mainSplitPane.setOneTouchExpandable(true);
			mainSplitPane.setBottomComponent(getBottomPanel());
			mainSplitPane.setTopComponent(getJobsSplitPane());
		}
		return mainSplitPane;
	}

	/**
	 * Gets the main tool bar.
	 * 
	 * @return the main tool bar
	 */
	private JToolBar getMainToolBar() {
		if (mainToolBar == null) {
			mainToolBar = new MainToolBar(this);
			
			getMainTree().addTreeSelectionListener(mainToolBar);
		}
		return mainToolBar;
	}

	/**
	 * Gets the main tree.
	 * 
	 * @return the main tree
	 */
	private JTree getMainTree() {
		if (mainTree == null) {
			mainTree = new JTree();
			mainTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			mainTree.setCellRenderer(new JobDetailsTreeCellRenderer());
			mainTree.setExpandsSelectedPaths(true);
			mainTree.setRootVisible(false);
			mainTree.setAutoscrolls(true);
			mainTree.setShowsRootHandles(true);

			mainTree.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(final KeyEvent e) {
					treeSelectionChanged();
				}
			});

			mainTree.addMouseListener(new MainTreeMouseListener(this));

			mainTree.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(final MouseEvent mouseEvent) {
					treeSelectionChanged();
				}
			});
		}

		return mainTree;
	}

	/**
	 * Gets the new file menu item.
	 * 
	 * @return the new file menu item
	 */
	private JMenuItem getNewFileMenuItem() {
		if (newFileMenuItem == null) {
			newFileMenuItem = new JMenuItem();
			newFileMenuItem.setText("New");
			newFileMenuItem.setIcon(ImageUtil.NEW_FILE_ICON);
			newFileMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			newFileMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					// TODO Implement new file menu item action
				}
			});
		}
		return newFileMenuItem;
	}


	/**
	 * Gets the open file menu item.
	 * 
	 * @return the open file menu item
	 */
	private JMenuItem getOpenFileMenuItem() {
		if (openFileMenuItem == null) {
			openFileMenuItem = new JMenuItem();
			openFileMenuItem.setText("Open...");
			openFileMenuItem.setIcon(ImageUtil.OPEN_FILE_ICON);
			openFileMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					openFile();
				}
			});
		}
		return openFileMenuItem;
	}

	/**
	 * Gets the preference editor panel.
	 * 
	 * @param preference the preference
	 * @return the preference editor panel
	 */
	private PreferenceEditorPanel getPreferenceEditorPanel(final Preference preference) {
		if (preferenceEditorPanel == null) {
			preferenceEditorPanel = new PreferenceEditorPanel();
		}

		preferenceEditorPanel.loadData(preference);

		return preferenceEditorPanel;
	}

	/**
	 * Gets the preference popup menu.
	 * 
	 * @return the preference popup menu
	 */
	protected PreferencePopupMenu getPreferencePopupMenu() {
		if (preferencePopupMenu == null) {
			preferencePopupMenu = new PreferencePopupMenu(this, this);
		}

		return preferencePopupMenu;
	}

	/**
	 * Gets the preferences root popup menu.
	 * 
	 * @return the preferences root popup menu
	 */
	protected PreferencesRootPopupMenu getPreferencesRootPopupMenu() {
		if (preferencesRootPopupMenu == null) {
			preferencesRootPopupMenu = new PreferencesRootPopupMenu(this, this);
		}

		return preferencesRootPopupMenu;
	}

	/**
	 * Gets the properties editor panel.
	 * 
	 * @return the properties editor panel
	 */
	private PropertiesEditorPanel getPropertiesEditorPanel() {
		if (propertiesEditorPanel == null) {
			propertiesEditorPanel = new PropertiesEditorPanel();
		}

		return propertiesEditorPanel;
	}

	/**
	 * Gets the properties root popup menu.
	 * 
	 * @return the properties root popup menu
	 */
	protected PropertiesRootPopupMenu getPropertiesRootPopupMenu() {
		if (propertiesRootPopupMenu == null) {
			propertiesRootPopupMenu = new PropertiesRootPopupMenu(this, this);
		}

		return propertiesRootPopupMenu;
	}

	/**
	 * Gets the property editor panel.
	 * 
	 * @param property the property
	 * @return the property editor panel
	 */
	private PropertyEditorPanel getPropertyEditorPanel(final Property property) {
		if (propertyEditorPanel == null) {
			propertyEditorPanel = new PropertyEditorPanel();
		}

		propertyEditorPanel.loadData(property);

		return propertyEditorPanel;
	}

	/**
	 * Gets the property popup menu.
	 * 
	 * @return the property popup menu
	 */
	protected PropertyPopupMenu getPropertyPopupMenu() {
		if (propertyPopupMenu == null) {
			propertyPopupMenu = new PropertyPopupMenu(this, this);
		}

		return propertyPopupMenu;
	}

	/**
	 * Gets the save as menu item.
	 * 
	 * @return the save as menu item
	 */
	private JMenuItem getSaveAsMenuItem() {
		if (saveAsMenuItem == null) {
			saveAsMenuItem = new JMenuItem();
			saveAsMenuItem.setIcon(ImageUtil.SAVE_FILE_AS_ICON);
			saveAsMenuItem.setActionCommand("SAVE_FILE_AS");
			saveAsMenuItem.setText("Save as...");
			saveAsMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			saveAsMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					// TODO Implement save as menu item action
				}
			});
		}
		return saveAsMenuItem;
	}

	/**
	 * Gets the save menu item.
	 * 
	 * @return the save menu item
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setIcon(ImageUtil.SAVE_FILE_ICON);
			saveMenuItem.setEnabled(false); // TODO disabled since it is not implemented
			saveMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					// TODO Implement save menu item action
				}
			});
		}
		return saveMenuItem;
	}

	/**
	 * Gets the selected tree object.
	 * 
	 * @return the selected tree object
	 */
	protected Object getSelectedTreeObject() {
		final TreePath treePath = mainTree.getSelectionPath();

		if (treePath == null) {
			return null;
		}

		final DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();

		if (node == null) {
			return null;
		}

		return node.getUserObject();
	}

	/**
	 * Gets the sequential job editor panel.
	 * 
	 * @param job the job
	 * @return the sequential job editor panel
	 */
	private SequentialJobEditorPanel getSequentialJobEditorPanel(final Job job) {
		if (sequentialJobEditorPanel == null) {
			sequentialJobEditorPanel = new SequentialJobEditorPanel();
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
			subversionExportTaskEditorPanel = new SubversionExportTaskEditorPanel();
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
			subversionRepositoryLocationEditorPanel = new SubversionRepositoryLocationEditorPanel();
		}

		subversionRepositoryLocationEditorPanel.loadData(subversionRepositoryLocation);

		return subversionRepositoryLocationEditorPanel;
	}

	/**
	 * Gets the subversion repository location popup menu.
	 * 
	 * @return the subversion repository location popup menu
	 */
	protected SubversionRepositoryLocationPopupMenu getSubversionRepositoryLocationPopupMenu() {
		if (subversionRepositoryLocationPopupMenu == null) {
			subversionRepositoryLocationPopupMenu = new SubversionRepositoryLocationPopupMenu(this, this);
		}

		return subversionRepositoryLocationPopupMenu;
	}

	/**
	 * Gets the subversion repository locations root popup menu.
	 * 
	 * @return the subversion repository locations root popup menu
	 */
	protected SubversionRepositoryLocationsRootPopupMenu getSubversionRepositoryLocationsRootPopupMenu() {
		if (subversionRepositoryLocationsRootPopupMenu == null) {
			subversionRepositoryLocationsRootPopupMenu = new SubversionRepositoryLocationsRootPopupMenu(this, this);
		}

		return subversionRepositoryLocationsRootPopupMenu;
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
			subversionUpdateTaskEditorPanel = new SubversionUpdateTaskEditorPanel();
		}

		subversionUpdateTaskEditorPanel.loadData(subversionUpdateTask);

		return subversionUpdateTaskEditorPanel;
	}

	/**
	 * Gets the task popup menu.
	 * 
	 * @return the task popup menu
	 */
	protected TaskPopupMenu getTaskPopupMenu() {
		if (taskPopupMenu == null) {
			taskPopupMenu = new TaskPopupMenu(this, this);
		}

		return taskPopupMenu;
	}

	/**
	 * Gets the toolbar panel.
	 * 
	 * @return the toolbar panel
	 */
	private JPanel getToolbarPanel() {
		if (toolbarPanel == null) {
			toolbarPanel = new JPanel();
			toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			toolbarPanel.add(getFileToolBar(), null);
			toolbarPanel.add(getMainToolBar(), null);
			toolbarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		}
		return toolbarPanel;
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
		addKeyListener(this);

		// Preload editors
		getSequentialJobEditorPanel(null);
		getFileSystemDeleteTaskEditorPanel(null);
		getHttpDownloadTaskEditorPanel(null);
		getHttpLocationEditorPanel(null);
		getSubversionExportTaskEditorPanel(null);
		getSubversionUpdateTaskEditorPanel(null);
		getFileSystemLocationEditorPanel(null);
		getSubversionRepositoryLocationEditorPanel(null);
		getPreferenceEditorPanel(null);
		getPropertyEditorPanel(null);
		getPropertiesEditorPanel();

		pack();

		// Load preferences, including window size, location and state
		loadUserPreferences();

		LOGGER.debug("{} leaving", prefix);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			pullThePlug();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(final KeyEvent e) {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(final KeyEvent e) {
		// Do nothing
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
	 * Open file.
	 */
	private void openFile() {
		final String prefix = "openFile() :";

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

	/**
	 * Pull the plug.
	 */
	private void pullThePlug() {
		final WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	/**
	 * Refresh jobs tree.
	 */
	private void refreshJobsTree() {
		final TreeModel treeModel = GuiUtils.loadJobDetailsTreeModel(configuration);

		getMainTree().setModel(treeModel);
		getMainTree().expandPath(mainTree.getNextMatch("Job", 0, Position.Bias.Forward));
	}

	/**
	 * Select tree object at location.
	 * 
	 * @param x the x
	 * @param y the y
	 */
	protected void selectTreeObjectAtLocation(final int x, final int y) {
		final TreePath newTreePath = mainTree.getPathForLocation(x, y);

		if (newTreePath != null) {
			mainTree.setSelectionPath(newTreePath);
		}
	}

	/**
	 * Tree selection changed.
	 */
	private void treeSelectionChanged() {
		final Object userObject = getSelectedTreeObject();

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
		else if (userObject instanceof Preference) {
			editorPane = getPreferenceEditorPanel((Preference) userObject);
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
}
