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
package net.lmxm.ute.gui.components;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.lmxm.ute.ConfigurationHolder;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
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
import net.lmxm.ute.gui.nodes.FileSystemLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.HttpLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.JobsRootTreeNode;
import net.lmxm.ute.gui.nodes.PreferencesRootTreeNode;
import net.lmxm.ute.gui.nodes.PropertiesRootTreeNode;
import net.lmxm.ute.gui.nodes.SubversionRepositoryLocationsRootTreeNode;
import net.lmxm.ute.gui.renderers.JobDetailsTreeCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MainTree.
 */
public class MainTree extends JTree {
	/**
	 * The listener interface for receiving mainTreeMouse events. The class that is interested in processing a
	 * mainTreeMouse event implements this interface, and the object created with that class is registered with a
	 * component using the component's <code>addMainTreeMouseListener<code> method. When
	 * the mainTreeMouse event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see MainTreeMouseEvent
	 */
	private class MainTreeMouseListener extends MouseAdapter {

		/**
		 * Handle popup trigger.
		 * 
		 * @param mouseEvent the mouse event
		 */
		public void handlePopupTrigger(final MouseEvent mouseEvent) {
			final String prefix = "mouseClicked() :";

			LOGGER.debug("{} entered", prefix);

			if (mouseEvent.isPopupTrigger()) {
				final int x = mouseEvent.getX();
				final int y = mouseEvent.getY();

				selectTreeObjectAtLocation(x, y);

				final Object object = getSelectedTreeObject();

				if (object != null) {
					JPopupMenu popupMenu = null;

					// Find popup menu appropriate to the item selected
					if (object instanceof JobsRootTreeNode) {
						popupMenu = getJobsRootPopupMenu();
					}
					else if (object instanceof Job) {
						popupMenu = getJobPopupMenu();
						((JobPopupMenu) popupMenu).enableDisableMenuItems(object);
					}
					else if (object instanceof Task) {
						popupMenu = getTaskPopupMenu();
						((TaskPopupMenu) popupMenu).enableDisableMenuItems(object);
					}
					else if (object instanceof FileSystemLocationsRootTreeNode) {
						popupMenu = getFileSystemLocationsRootPopupMenu();
					}
					else if (object instanceof FileSystemLocation) {
						popupMenu = getFileSystemLocationPopupMenu();
					}
					else if (object instanceof HttpLocationsRootTreeNode) {
						popupMenu = getHttpLocationsRootPopupMenu();
					}
					else if (object instanceof HttpLocation) {
						popupMenu = getHttpLocationPopupMenu();
					}
					else if (object instanceof SubversionRepositoryLocationsRootTreeNode) {
						popupMenu = getSubversionRepositoryLocationsRootPopupMenu();
					}
					else if (object instanceof SubversionRepositoryLocation) {
						popupMenu = getSubversionRepositoryLocationPopupMenu();
					}
					else if (object instanceof PreferencesRootTreeNode) {
						popupMenu = getPreferencesRootPopupMenu();
					}
					else if (object instanceof Preference) {
						popupMenu = getPreferencePopupMenu();
					}
					else if (object instanceof PropertiesRootTreeNode) {
						popupMenu = getPropertiesRootPopupMenu();
					}
					else if (object instanceof Property) {
						popupMenu = getPropertyPopupMenu();
					}
					else {
						LOGGER.debug("{} unsupported object; no popup will be displayed", prefix);
					}

					// Display the popup menu if a match was found
					if (popupMenu != null) {
						popupMenu.show(mouseEvent.getComponent(), x, y);
					}
					else {
						LOGGER.debug("{} no matching popup found", prefix);
					}
				}
				else {
					LOGGER.debug("{} no object selected", prefix);
				}
			}
			else {
				LOGGER.debug("{} not a popup trigger", prefix);
			}

			LOGGER.debug("{} leaving", prefix);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(final MouseEvent mouseEvent) {
			handlePopupTrigger(mouseEvent);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(final MouseEvent mouseEvent) {
			handlePopupTrigger(mouseEvent);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(final MouseEvent mouseEvent) {
			handlePopupTrigger(mouseEvent);
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainTree.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8466615023209238787L;

	/** The action listener. */
	private final ActionListener actionListener;

	/** The file system location popup menu. */
	private FileSystemLocationPopupMenu fileSystemLocationPopupMenu = null;

	/** The file system locations root popup menu. */
	private FileSystemLocationsRootPopupMenu fileSystemLocationsRootPopupMenu = null;

	/** The http location popup menu. */
	private HttpLocationPopupMenu httpLocationPopupMenu = null;

	/** The http locations root popup menu. */
	private HttpLocationsRootPopupMenu httpLocationsRootPopupMenu = null;

	/** The job popup menu. */
	private JobPopupMenu jobPopupMenu = null;

	/** The jobs root popup menu. */
	private JobsRootPopupMenu jobsRootPopupMenu = null;

	/** The main tree model. */
	private final MainTreeModel mainTreeModel;

	/** The preference popup menu. */
	private PreferencePopupMenu preferencePopupMenu = null;

	/** The preferences root popup menu. */
	private PreferencesRootPopupMenu preferencesRootPopupMenu = null;

	/** The properties root popup menu. */
	private PropertiesRootPopupMenu propertiesRootPopupMenu = null;

	/** The property popup menu. */
	private PropertyPopupMenu propertyPopupMenu = null;

	/** The subversion repository location popup menu. */
	private SubversionRepositoryLocationPopupMenu subversionRepositoryLocationPopupMenu = null;

	/** The subversion repository locations root popup menu. */
	private SubversionRepositoryLocationsRootPopupMenu subversionRepositoryLocationsRootPopupMenu = null;

	/** The task popup menu. */
	private TaskPopupMenu taskPopupMenu = null;

	/**
	 * Instantiates a new main tree.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public MainTree(final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super();

		this.actionListener = actionListener;

		mainTreeModel = new MainTreeModel(configurationHolder);
		setModel(mainTreeModel);

		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setCellRenderer(new JobDetailsTreeCellRenderer());
		setExpandsSelectedPaths(true);
		setRootVisible(false);
		setAutoscrolls(true);
		setShowsRootHandles(true);

		addMouseListener(new MainTreeMouseListener());
	}

	/**
	 * Adds the file system location.
	 * 
	 * @param fileSystemLocation the file system location
	 */
	public void addFileSystemLocation(final FileSystemLocation fileSystemLocation) {
		showSelectedPath(mainTreeModel.addFileSystemLocation(fileSystemLocation));
	}

	/**
	 * Adds the http location.
	 * 
	 * @param httpLocation the http location
	 */
	public void addHttpLocation(final HttpLocation httpLocation) {
		showSelectedPath(mainTreeModel.addHttpLocation(httpLocation));
	}

	/**
	 * Adds the preference.
	 * 
	 * @param preference the preference
	 */
	public void addPreference(final Preference preference) {
		showSelectedPath(mainTreeModel.addPreference(preference));
	}

	/**
	 * Adds the property.
	 * 
	 * @param property the property
	 */
	public void addProperty(final Property property) {
		showSelectedPath(mainTreeModel.addProperty(property));
	}

	/**
	 * Adds the subversion repository location.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 */
	public void addSubversionRepositoryLocation(final SubversionRepositoryLocation subversionRepositoryLocation) {
		showSelectedPath(mainTreeModel.addSubversionRepositoryLocation(subversionRepositoryLocation));
	}

	/**
	 * Delete file system location.
	 * 
	 * @param fileSystemLocation the file system location
	 */
	public void deleteFileSystemLocation(final FileSystemLocation fileSystemLocation) {
		mainTreeModel.deleteFileSystemLocation(fileSystemLocation);
	}

	/**
	 * Delete http location.
	 * 
	 * @param httpLocation the http location
	 */
	public void deleteHttpLocation(final HttpLocation httpLocation) {
		mainTreeModel.deleteHttpLocation(httpLocation);
	}

	/**
	 * Delete preference.
	 * 
	 * @param preference the preference
	 */
	public void deletePreference(final Preference preference) {
		mainTreeModel.deletePreference(preference);
	}

	/**
	 * Delete property.
	 * 
	 * @param property the property
	 */
	public void deleteProperty(final Property property) {
		mainTreeModel.deleteProperty(property);
	}

	/**
	 * Delete subversion repository location.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 */
	public void deleteSubversionRepositoryLocation(final SubversionRepositoryLocation subversionRepositoryLocation) {
		mainTreeModel.deleteSubversionRepositoryLocation(subversionRepositoryLocation);
	}

	/**
	 * Gets the action listener.
	 * 
	 * @return the action listener
	 */
	private ActionListener getActionListener() {
		return actionListener;
	}

	/**
	 * Gets the file system location popup menu.
	 * 
	 * @return the file system location popup menu
	 */
	protected FileSystemLocationPopupMenu getFileSystemLocationPopupMenu() {
		if (fileSystemLocationPopupMenu == null) {
			fileSystemLocationPopupMenu = new FileSystemLocationPopupMenu(getActionListener());
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
			fileSystemLocationsRootPopupMenu = new FileSystemLocationsRootPopupMenu(getActionListener());
		}

		return fileSystemLocationsRootPopupMenu;
	}

	/**
	 * Gets the http location popup menu.
	 * 
	 * @return the http location popup menu
	 */
	protected HttpLocationPopupMenu getHttpLocationPopupMenu() {
		if (httpLocationPopupMenu == null) {
			httpLocationPopupMenu = new HttpLocationPopupMenu(getActionListener());
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
			httpLocationsRootPopupMenu = new HttpLocationsRootPopupMenu(getActionListener());
		}

		return httpLocationsRootPopupMenu;
	}

	/**
	 * Gets the job popup menu.
	 * 
	 * @return the job popup menu
	 */
	private JobPopupMenu getJobPopupMenu() {
		if (jobPopupMenu == null) {
			jobPopupMenu = new JobPopupMenu(getActionListener());
		}

		return jobPopupMenu;
	}

	/**
	 * Gets the jobs root popup menu.
	 * 
	 * @return the jobs root popup menu
	 */
	private JobsRootPopupMenu getJobsRootPopupMenu() {
		if (jobsRootPopupMenu == null) {
			jobsRootPopupMenu = new JobsRootPopupMenu(getActionListener());
		}

		return jobsRootPopupMenu;
	}

	/**
	 * Gets the preference popup menu.
	 * 
	 * @return the preference popup menu
	 */
	protected PreferencePopupMenu getPreferencePopupMenu() {
		if (preferencePopupMenu == null) {
			preferencePopupMenu = new PreferencePopupMenu(getActionListener());
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
			preferencesRootPopupMenu = new PreferencesRootPopupMenu(getActionListener());
		}

		return preferencesRootPopupMenu;
	}

	/**
	 * Gets the properties root popup menu.
	 * 
	 * @return the properties root popup menu
	 */
	protected PropertiesRootPopupMenu getPropertiesRootPopupMenu() {
		if (propertiesRootPopupMenu == null) {
			propertiesRootPopupMenu = new PropertiesRootPopupMenu(getActionListener());
		}

		return propertiesRootPopupMenu;
	}

	/**
	 * Gets the property popup menu.
	 * 
	 * @return the property popup menu
	 */
	protected PropertyPopupMenu getPropertyPopupMenu() {
		if (propertyPopupMenu == null) {
			propertyPopupMenu = new PropertyPopupMenu(getActionListener());
		}

		return propertyPopupMenu;
	}

	/**
	 * Gets the selected tree object.
	 * 
	 * @return the selected tree object
	 */
	public Object getSelectedTreeObject() {
		final TreePath treePath = getSelectionPath();

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
	 * Gets the subversion repository location popup menu.
	 * 
	 * @return the subversion repository location popup menu
	 */
	protected SubversionRepositoryLocationPopupMenu getSubversionRepositoryLocationPopupMenu() {
		if (subversionRepositoryLocationPopupMenu == null) {
			subversionRepositoryLocationPopupMenu = new SubversionRepositoryLocationPopupMenu(getActionListener());
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
			subversionRepositoryLocationsRootPopupMenu = new SubversionRepositoryLocationsRootPopupMenu(
					getActionListener());
		}

		return subversionRepositoryLocationsRootPopupMenu;
	}

	/**
	 * Gets the task popup menu.
	 * 
	 * @return the task popup menu
	 */
	protected TaskPopupMenu getTaskPopupMenu() {
		if (taskPopupMenu == null) {
			taskPopupMenu = new TaskPopupMenu(getActionListener());
		}

		return taskPopupMenu;
	}

	/**
	 * Refresh.
	 * 
	 * @return the main tree
	 */
	public MainTree refresh() {
		mainTreeModel.refresh();
		expandPath(mainTreeModel.getPathToJobs());

		return this;
	}

	/**
	 * Select tree object at location.
	 * 
	 * @param x the x
	 * @param y the y
	 */
	protected void selectTreeObjectAtLocation(final int x, final int y) {
		final TreePath newTreePath = getPathForLocation(x, y);

		if (newTreePath != null) {
			setSelectionPath(newTreePath);
		}
	}

	/**
	 * Show selected path.
	 * 
	 * @param treePath the tree path
	 */
	private void showSelectedPath(final TreePath treePath) {
		setSelectionPath(treePath);
		scrollPathToVisible(treePath);
	}
}
