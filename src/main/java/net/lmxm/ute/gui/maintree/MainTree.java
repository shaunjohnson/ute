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
package net.lmxm.ute.gui.maintree;

import com.google.common.eventbus.Subscribe;
import net.lmxm.ute.beans.EnabledStateBean;
import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.EnabledStateChangeEvent;
import net.lmxm.ute.event.EnabledStateChangeListener;
import net.lmxm.ute.event.IdChangeEvent;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.menus.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.util.Enumeration;

/**
 * The Class MainTree.
 */
public class MainTree extends JTree implements EnabledStateChangeListener {

    /** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainTree.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8466615023209238787L;

	/** The action listener. */
	private final UteActionListener actionListener;

	/** The main tree model. */
	private final MainTreeModel mainTreeModel;

	/**
	 * Instantiates a new main tree.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public MainTree(final ConfigurationHolder configurationHolder, final UteActionListener actionListener) {
		super();

		this.actionListener = actionListener;

		mainTreeModel = new MainTreeModel(configurationHolder);
		setModel(mainTreeModel);

		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setCellRenderer(new MainTreeCellRenderer());
		setExpandsSelectedPaths(true);
		setRootVisible(false);
		setAutoscrolls(true);
		setShowsRootHandles(true);

		addKeyListener(new MainTreeKeyListener(this, actionListener));
		addMouseListener(new MainTreeMouseListener(this, actionListener));

		setDragEnabled(true);
		setDropMode(DropMode.INSERT);
		setTransferHandler(new MainTreeTransferHandler());
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
	 * Adds the job.
	 * 
	 * @param job the job
	 */
	public void addJob(final Job job) {
		showSelectedPath(mainTreeModel.addJob(job));
	}

    /**
     * Adds the Maven repository location.
     *
     * @param mavenRepositoryLocation the Maven repository location
     */
    public void addMavenRepositoryLocation(final MavenRepositoryLocation mavenRepositoryLocation) {
        showSelectedPath(mainTreeModel.addMavenRepositoryLocationLocation(mavenRepositoryLocation));
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
	 * Adds the task.
	 * 
	 * @param index the index
	 * @param task the task
	 */
	public void addTask(final int index, final Task task) {
		showSelectedPath(mainTreeModel.addTask(index, task));
	}

	/**
	 * Collapse all.
	 */
	public void collapseAll() {
		final TreePath root = new TreePath(mainTreeModel.getRoot());

		collapseAll(root);
		expandPath(root);
	}

	/**
	 * Collapse all.
	 * 
	 * @param parentTreePath the parent tree path
	 */
	@SuppressWarnings("rawtypes")
	private void collapseAll(final TreePath parentTreePath) {
		final TreeNode parentTreeNode = (TreeNode) parentTreePath.getLastPathComponent();

		if (parentTreeNode.getChildCount() >= 0) {
			for (final Enumeration enumeration = parentTreeNode.children(); enumeration.hasMoreElements();) {
				final TreeNode treeNode = (TreeNode) enumeration.nextElement();
				final TreePath path = parentTreePath.pathByAddingChild(treeNode);

				collapseAll(path);
			}
		}

		// Collapse from bottom up
		collapsePath(parentTreePath);
	}

	/**
	 * Delete file system location.
	 * 
	 * @param fileSystemLocation the file system location
	 */
	public void deleteFileSystemLocation(final FileSystemLocation fileSystemLocation) {
		showSelectedPath(mainTreeModel.deleteFileSystemLocation(fileSystemLocation));
	}

	/**
	 * Delete http location.
	 * 
	 * @param httpLocation the http location
	 */
	public void deleteHttpLocation(final HttpLocation httpLocation) {
		showSelectedPath(mainTreeModel.deleteHttpLocation(httpLocation));
	}

	/**
	 * Delete job.
	 * 
	 * @param job the job
	 */
	public void deleteJob(final Job job) {
		showSelectedPath(mainTreeModel.deleteJob(job));
	}

    /**
     * Delete http location.
     *
     * @param mavenRepositoryLocation the Maven repository location
     */
    public void deleteMavenRepositoryLocation(final MavenRepositoryLocation mavenRepositoryLocation) {
        showSelectedPath(mainTreeModel.deleteMavenRepositoryLocation(mavenRepositoryLocation));
    }

	/**
	 * Delete preference.
	 * 
	 * @param preference the preference
	 */
	public void deletePreference(final Preference preference) {
		showSelectedPath(mainTreeModel.deletePreference(preference));
	}

	/**
	 * Delete property.
	 * 
	 * @param property the property
	 */
	public void deleteProperty(final Property property) {
		showSelectedPath(mainTreeModel.deleteProperty(property));
	}

	/**
	 * Delete subversion repository location.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 */
	public void deleteSubversionRepositoryLocation(final SubversionRepositoryLocation subversionRepositoryLocation) {
		showSelectedPath(mainTreeModel.deleteSubversionRepositoryLocation(subversionRepositoryLocation));
	}

	/**
	 * Delete task.
	 * 
	 * @param task the task
	 */
	public void deleteTask(final Task task) {
		showSelectedPath(mainTreeModel.deleteTask(task));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * net.lmxm.ute.listeners.EnabledStateChangeListener#enabledStateChanged(net.lmxm.ute.listeners.EnabledStateChangeEvent
	 * )
	 */
	@Override
	public void enabledStateChanged(final EnabledStateChangeEvent enabledStateChangeEvent) {
		final EnabledStateBean enabledStateBean = enabledStateChangeEvent.getEnabledStateBean();

		if (enabledStateBean instanceof Task) {
			mainTreeModel.refreshTask((Task) enabledStateBean);
		}
		else {
			throw new IllegalStateException("Unsupported bean type"); // TODO
		}
	}

	/**
	 * Expand all.
	 */
	public void expandAll() {
		final TreePath root = new TreePath(mainTreeModel.getRoot());

		expandAll(root);
	}

	/**
	 * Expand all.
	 * 
	 * @param parentTreePath the parent tree path
	 */
	@SuppressWarnings("rawtypes")
	private void expandAll(final TreePath parentTreePath) {
		final TreeNode parentTreeNode = (TreeNode) parentTreePath.getLastPathComponent();

		if (parentTreeNode.getChildCount() >= 0) {
			for (final Enumeration enumeration = parentTreeNode.children(); enumeration.hasMoreElements();) {
				final TreeNode treeNode = (TreeNode) enumeration.nextElement();
				final TreePath path = parentTreePath.pathByAddingChild(treeNode);

				expandAll(path);
			}
		}

		// Expand from bottom up
		expandPath(parentTreePath);
	}

	/**
	 * Gets the action listener.
	 * 
	 * @return the action listener
	 */
	private UteActionListener getActionListener() {
		return actionListener;
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

	@Subscribe
	public void handleIdChanged(final IdChangeEvent idChangeEvent) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final IdentifiableBean identifiableBean = idChangeEvent.getIdentifiableBean();

                if (identifiableBean instanceof FileSystemLocation) {
                    mainTreeModel.refreshFileSystemLocation((FileSystemLocation) identifiableBean);
                }
                else if (identifiableBean instanceof Job) {
                    mainTreeModel.refreshJob((Job) identifiableBean);
                }
                else if (identifiableBean instanceof HttpLocation) {
                    mainTreeModel.refreshHttpLocation((HttpLocation) identifiableBean);
                }
                else if (identifiableBean instanceof MavenRepositoryLocation) {
                    mainTreeModel.refreshMavenRepositoryLocation((MavenRepositoryLocation) identifiableBean);
                }
                else if (identifiableBean instanceof Preference) {
                    mainTreeModel.refreshPreference((Preference) identifiableBean);
                }
                else if (identifiableBean instanceof Property) {
                    mainTreeModel.refreshProperty((Property) identifiableBean);
                }
                else if (identifiableBean instanceof SubversionRepositoryLocation) {
                    mainTreeModel.refreshSubversionRepositoryLocation((SubversionRepositoryLocation) identifiableBean);
                }
                else if (identifiableBean instanceof Task) {
                    mainTreeModel.refreshTask((Task) identifiableBean);
                }
                else {
                    throw new IllegalStateException("Unsupported bean type"); // TODO
                }
            }
        });
	}

	/**
	 * Refresh.
	 * 
	 * @return the main tree
	 */
	public MainTree refresh() {
		mainTreeModel.refresh();
		expandPath(mainTreeModel.getDefaultPath());

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
