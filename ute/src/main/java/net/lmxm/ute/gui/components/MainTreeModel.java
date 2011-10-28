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

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import net.lmxm.ute.ConfigurationHolder;
import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.nodes.FileSystemLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.HttpLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.IdentifiableBeanTreeNode;
import net.lmxm.ute.gui.nodes.JobsRootTreeNode;
import net.lmxm.ute.gui.nodes.PreferencesRootTreeNode;
import net.lmxm.ute.gui.nodes.PropertiesRootTreeNode;
import net.lmxm.ute.gui.nodes.SubversionRepositoryLocationsRootTreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MainTreeModel.
 */
public class MainTreeModel extends DefaultTreeModel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainTreeModel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7645225463874454066L;

	/** The configuration holder. */
	private final ConfigurationHolder configurationHolder;

	/** The file system locations node. */
	private final DefaultMutableTreeNode fileSystemLocationsNode;

	private final TreePath fileSystemLocationsNodePath;

	/** The http locations node. */
	private final DefaultMutableTreeNode httpLocationsNode;

	private final TreePath httpLocationsNodePath;

	/** The jobs node. */
	private final DefaultMutableTreeNode jobsNode;

	private final TreePath jobsNodePath;

	/** The preferences node. */
	private final DefaultMutableTreeNode preferencesNode;

	private final TreePath preferencesNodePath;

	/** The properties node. */
	private final DefaultMutableTreeNode propertiesNode;
	private final TreePath propertiesNodePath;
	/** The root node. */
	private final DefaultMutableTreeNode rootNode;
	/** The subversion locations node. */
	private final DefaultMutableTreeNode subversionRepositoryLocationsNode;
	private final TreePath subversionRepositoryLocationsNodePath;

	/**
	 * Instantiates a new main tree model.
	 * 
	 * @param configurationHolder the configuration holder
	 */
	protected MainTreeModel(final ConfigurationHolder configurationHolder) {
		super(new DefaultMutableTreeNode("Root"));

		this.configurationHolder = configurationHolder;

		//
		// The order of the nodes must be maintained.
		//
		rootNode = (DefaultMutableTreeNode) getRoot();

		jobsNode = new DefaultMutableTreeNode(new JobsRootTreeNode(configurationHolder));
		rootNode.add(jobsNode);
		jobsNodePath = new TreePath(rootNode).pathByAddingChild(jobsNode);

		fileSystemLocationsNode = new DefaultMutableTreeNode(new FileSystemLocationsRootTreeNode(configurationHolder));
		rootNode.add(fileSystemLocationsNode);
		fileSystemLocationsNodePath = new TreePath(rootNode).pathByAddingChild(fileSystemLocationsNode);

		httpLocationsNode = new DefaultMutableTreeNode(new HttpLocationsRootTreeNode(configurationHolder));
		rootNode.add(httpLocationsNode);
		httpLocationsNodePath = new TreePath(rootNode).pathByAddingChild(httpLocationsNode);

		subversionRepositoryLocationsNode = new DefaultMutableTreeNode(new SubversionRepositoryLocationsRootTreeNode(
				configurationHolder));
		rootNode.add(subversionRepositoryLocationsNode);
		subversionRepositoryLocationsNodePath = new TreePath(rootNode)
				.pathByAddingChild(subversionRepositoryLocationsNode);

		propertiesNode = new DefaultMutableTreeNode(new PropertiesRootTreeNode(configurationHolder));
		rootNode.add(propertiesNode);
		propertiesNodePath = new TreePath(rootNode).pathByAddingChild(propertiesNode);

		preferencesNode = new DefaultMutableTreeNode(new PreferencesRootTreeNode(configurationHolder));
		rootNode.add(preferencesNode);
		preferencesNodePath = new TreePath(rootNode).pathByAddingChild(preferencesNode);
	}

	/**
	 * Adds the file system location.
	 * 
	 * @param fileSystemLocation the file system location
	 * @return the tree path
	 */
	protected TreePath addFileSystemLocation(final FileSystemLocation fileSystemLocation) {
		final DefaultMutableTreeNode fileSystemLocationNode = new IdentifiableBeanTreeNode(fileSystemLocation);

		insertNodeInto(fileSystemLocationNode, fileSystemLocationsNode, 0);

		return fileSystemLocationsNodePath.pathByAddingChild(fileSystemLocationNode);
	}

	/**
	 * Adds the http location.
	 * 
	 * @param httpLocation the http location
	 * @return the tree path
	 */
	protected TreePath addHttpLocation(final HttpLocation httpLocation) {
		final DefaultMutableTreeNode httpLocationNode = new IdentifiableBeanTreeNode(httpLocation);

		insertNodeInto(httpLocationNode, httpLocationsNode, 0);

		return httpLocationsNodePath.pathByAddingChild(httpLocationNode);
	}

	/**
	 * Adds the job.
	 * 
	 * @param job the job
	 * @return the tree path
	 */
	protected TreePath addJob(final Job job) {
		final DefaultMutableTreeNode jobNode = new IdentifiableBeanTreeNode(job);

		insertNodeInto(jobNode, jobsNode, 0);

		return jobsNodePath.pathByAddingChild(jobNode);
	}

	/**
	 * Adds the preference.
	 * 
	 * @param preference the preference
	 * @return the tree path
	 */
	protected TreePath addPreference(final Preference preference) {
		final DefaultMutableTreeNode preferenceNode = new IdentifiableBeanTreeNode(preference);

		insertNodeInto(preferenceNode, preferencesNode, 0);

		return preferencesNodePath.pathByAddingChild(preferenceNode);
	}

	/**
	 * Adds the property.
	 * 
	 * @param property the property
	 * @return the tree path
	 */
	protected TreePath addProperty(final Property property) {
		final DefaultMutableTreeNode propertyNode = new IdentifiableBeanTreeNode(property);

		insertNodeInto(propertyNode, propertiesNode, 0);

		return propertiesNodePath.pathByAddingChild(propertyNode);
	}

	/**
	 * Adds the subversion repository location.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 * @return the tree path
	 */
	protected TreePath addSubversionRepositoryLocation(final SubversionRepositoryLocation subversionRepositoryLocation) {
		final DefaultMutableTreeNode subversionRepositoryLocationNode = new IdentifiableBeanTreeNode(
				subversionRepositoryLocation);

		insertNodeInto(subversionRepositoryLocationNode, subversionRepositoryLocationsNode, 0);

		return subversionRepositoryLocationsNodePath.pathByAddingChild(subversionRepositoryLocationNode);
	}

	/**
	 * Adds the task.
	 * 
	 * @param task the task
	 * @return the tree path
	 */
	protected TreePath addTask(final int index, final Task task) {
		final DefaultMutableTreeNode taskNode = new IdentifiableBeanTreeNode(task);
		final DefaultMutableTreeNode jobNode = findJobNode(task.getJob());

		insertNodeInto(taskNode, jobNode, index);

		return jobsNodePath.pathByAddingChild(jobNode).pathByAddingChild(taskNode);
	}

	/**
	 * Delete file system location.
	 * 
	 * @param fileSystemLocation the file system location
	 * @return the tree path
	 */
	protected TreePath deleteFileSystemLocation(final FileSystemLocation fileSystemLocation) {
		final DefaultMutableTreeNode fileSystemLocationNode = findFileSystemLocationNode(fileSystemLocation);
		if (fileSystemLocationNode == null) {
			throw new RuntimeException("FileSystemLocation node does not exist"); // TODO Use proper exception
		}

		final TreePath selectPath = getSiblingOrParentPath(fileSystemLocationNode, fileSystemLocationsNodePath);

		removeNodeFromParent(fileSystemLocationNode);

		return selectPath;
	}

	/**
	 * Delete http location.
	 * 
	 * @param httpLocation the http location
	 * @return the tree path
	 */
	protected TreePath deleteHttpLocation(final HttpLocation httpLocation) {
		final DefaultMutableTreeNode httpLocationNode = findHttpLocationNode(httpLocation);
		if (httpLocationNode == null) {
			throw new RuntimeException("HttpLocation node does not exist"); // TODO Use proper exception
		}

		final TreePath selectPath = getSiblingOrParentPath(httpLocationNode, httpLocationsNodePath);

		removeNodeFromParent(httpLocationNode);

		return selectPath;
	}

	/**
	 * Delete job.
	 * 
	 * @param job the job
	 * @return the tree path
	 */
	protected TreePath deleteJob(final Job job) {
		final DefaultMutableTreeNode jobNode = findJobNode(job);
		if (jobNode == null) {
			throw new RuntimeException("Job node does not exist"); // TODO Use proper exception
		}

		final TreePath selectPath = getSiblingOrParentPath(jobNode, jobsNodePath);

		removeNodeFromParent(jobNode);

		return selectPath;
	}

	/**
	 * Delete preference.
	 * 
	 * @param preference the preference
	 * @return the tree path
	 */
	protected TreePath deletePreference(final Preference preference) {
		final DefaultMutableTreeNode preferenceNode = findPreferenceNode(preference);
		if (preferenceNode == null) {
			throw new RuntimeException("Preference node does not exist"); // TODO Use proper exception
		}

		final TreePath selectPath = getSiblingOrParentPath(preferenceNode, preferencesNodePath);

		removeNodeFromParent(preferenceNode);

		return selectPath;
	}

	/**
	 * Delete property.
	 * 
	 * @param property the property
	 * @return the tree path
	 */
	protected TreePath deleteProperty(final Property property) {
		final DefaultMutableTreeNode propertyNode = findPropertyNode(property);
		if (propertyNode == null) {
			throw new RuntimeException("Property node does not exist"); // TODO Use proper exception
		}

		final TreePath selectPath = getSiblingOrParentPath(propertyNode, propertiesNodePath);

		removeNodeFromParent(propertyNode);

		return selectPath;
	}

	/**
	 * Delete subversion repository location.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 * @return the tree path
	 */
	protected TreePath deleteSubversionRepositoryLocation(
			final SubversionRepositoryLocation subversionRepositoryLocation) {
		final DefaultMutableTreeNode subversionRepositoryLocationNode = findSubversionRepositoryLocationNode(subversionRepositoryLocation);
		if (subversionRepositoryLocationNode == null) {
			throw new RuntimeException("SubversionRepositoryLocation node does not exist"); // TODO Use proper exception
		}

		final TreePath selectPath = getSiblingOrParentPath(subversionRepositoryLocationNode,
				subversionRepositoryLocationsNodePath);

		removeNodeFromParent(subversionRepositoryLocationNode);

		return selectPath;
	}

	/**
	 * Delete task.
	 * 
	 * @param task the task
	 * @return the tree path
	 */
	protected TreePath deleteTask(final Task task) {
		final DefaultMutableTreeNode taskNode = findTaskNode(task);
		if (taskNode == null) {
			throw new RuntimeException("Task node does not exist"); // TODO Use proper exception
		}

		final TreePath selectPath = getSiblingOrParentPath(taskNode,
				jobsNodePath.pathByAddingChild(taskNode.getParent()));

		removeNodeFromParent(taskNode);

		return selectPath;
	}

	/**
	 * Find child node by user object.
	 * 
	 * @param treeNode the tree node
	 * @param userObject the user object
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findChildNodeByUserObject(final DefaultMutableTreeNode treeNode,
			final Object userObject) {
		@SuppressWarnings("rawtypes")
		final Enumeration enumeration = treeNode.children();

		while (enumeration.hasMoreElements()) {
			final DefaultMutableTreeNode child = (DefaultMutableTreeNode) enumeration.nextElement();

			if (child.getUserObject().equals(userObject)) {
				return child;
			}
		}

		return null;
	}

	/**
	 * Find file system location node.
	 * 
	 * @param fileSystemLocation the file system location
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findFileSystemLocationNode(final FileSystemLocation fileSystemLocation) {
		return findChildNodeByUserObject(fileSystemLocationsNode, fileSystemLocation);
	}

	/**
	 * Find http location node.
	 * 
	 * @param httpLocation the http location
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findHttpLocationNode(final HttpLocation httpLocation) {
		return findChildNodeByUserObject(httpLocationsNode, httpLocation);
	}

	/**
	 * Find job node.
	 * 
	 * @param job the job
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findJobNode(final Job job) {
		return findChildNodeByUserObject(jobsNode, job);
	}

	/**
	 * Find preference node.
	 * 
	 * @param preference the preference
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findPreferenceNode(final Preference preference) {
		return findChildNodeByUserObject(preferencesNode, preference);
	}

	/**
	 * Find property node.
	 * 
	 * @param property the property
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findPropertyNode(final Property property) {
		return findChildNodeByUserObject(propertiesNode, property);
	}

	/**
	 * Find subversion repository location node.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findSubversionRepositoryLocationNode(
			final SubversionRepositoryLocation subversionRepositoryLocation) {
		return findChildNodeByUserObject(subversionRepositoryLocationsNode, subversionRepositoryLocation);
	}

	/**
	 * Find task node.
	 * 
	 * @param task the task
	 * @return the default mutable tree node
	 */
	private DefaultMutableTreeNode findTaskNode(final Task task) {
		return findChildNodeByUserObject(findJobNode(task.getJob()), task);
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	private Configuration getConfiguration() {
		return configurationHolder.getConfiguration();
	}

	/**
	 * Gets the default path.
	 * 
	 * @return the default path
	 */
	protected TreePath getDefaultPath() {
		return jobsNodePath;
	}

	/**
	 * Gets the sibling or parent path.
	 * 
	 * @param defaultMutableTreeNode the default mutable tree node
	 * @param parentNodePath the parent node path
	 * @return the sibling or parent path
	 */
	private TreePath getSiblingOrParentPath(final DefaultMutableTreeNode defaultMutableTreeNode,
			final TreePath parentNodePath) {
		final TreeNode previousSibling = defaultMutableTreeNode.getPreviousSibling();
		if (previousSibling != null) {
			return parentNodePath.pathByAddingChild(previousSibling);
		}

		final TreeNode nextSibling = defaultMutableTreeNode.getNextSibling();
		if (nextSibling != null) {
			return parentNodePath.pathByAddingChild(nextSibling);
		}

		return parentNodePath;
	}

	/**
	 * Load file system locations.
	 */
	private void loadFileSystemLocations() {
		final String prefix = "loadFileSystemLocations() :";

		final List<FileSystemLocation> fileSystemLocations = getConfiguration().getFileSystemLocations();

		LOGGER.debug("{} entered, loading {} locations", prefix, fileSystemLocations.size());

		for (final FileSystemLocation fileSystemLocation : fileSystemLocations) {
			fileSystemLocationsNode.add(new IdentifiableBeanTreeNode(fileSystemLocation));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Load http locations.
	 */
	private void loadHttpLocations() {
		final String prefix = "loadHttpLocations() :";

		final List<HttpLocation> httpLocations = getConfiguration().getHttpLocations();

		LOGGER.debug("{} entered, loading {} locations", prefix, httpLocations.size());

		for (final HttpLocation httpLocation : httpLocations) {
			httpLocationsNode.add(new IdentifiableBeanTreeNode(httpLocation));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Load jobs.
	 */
	private void loadJobs() {
		final String prefix = "loadJobs() :";

		final List<Job> jobs = getConfiguration().getJobs();

		LOGGER.debug("{} entered, loading {} jobs", prefix, jobs.size());

		for (final Job job : jobs) {
			final DefaultMutableTreeNode jobNode = new IdentifiableBeanTreeNode(job);
			final List<Task> tasks = job.getTasks();

			LOGGER.debug("{} loading {} tasks", prefix, tasks.size());

			for (final Task task : tasks) {
				final DefaultMutableTreeNode taskNode = new IdentifiableBeanTreeNode(task);

				jobNode.add(taskNode);
			}

			jobsNode.add(jobNode);
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Load preferences.
	 */
	private void loadPreferences() {
		final String prefix = "loadPreferences() :";

		final List<Preference> preferences = getConfiguration().getPreferences();

		LOGGER.debug("{} entered, loading {} preferences", prefix, preferences.size());

		for (final Preference preference : preferences) {
			preferencesNode.add(new IdentifiableBeanTreeNode(preference));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Load properties.
	 */
	private void loadProperties() {
		final String prefix = "loadProperties() :";

		final List<Property> properties = getConfiguration().getProperties();

		LOGGER.debug("{} entered, loading {} properties", prefix, properties.size());

		for (final Property property : properties) {
			propertiesNode.add(new IdentifiableBeanTreeNode(property));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Load subversion locations.
	 */
	private void loadSubversionLocations() {
		final String prefix = "loadSubversionLocations() :";

		final List<SubversionRepositoryLocation> subversionRepositoryLocations = getConfiguration()
				.getSubversionRepositoryLocations();

		LOGGER.debug("{} entered, loading {} locations", prefix, subversionRepositoryLocations.size());

		for (final SubversionRepositoryLocation subversionLocation : subversionRepositoryLocations) {
			subversionRepositoryLocationsNode.add(new IdentifiableBeanTreeNode(subversionLocation));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Refresh.
	 */
	protected void refresh() {
		removeAllChildren();

		loadJobs();
		loadFileSystemLocations();
		loadHttpLocations();
		loadSubversionLocations();
		loadProperties();
		loadPreferences();

		reload();
	}

	/**
	 * Refresh file system location.
	 * 
	 * @param fileSystemLocation the file system location
	 */
	protected void refreshFileSystemLocation(final FileSystemLocation fileSystemLocation) {
		nodeChanged(findFileSystemLocationNode(fileSystemLocation));
	}

	/**
	 * Refresh http location.
	 * 
	 * @param httpLocation the http location
	 */
	protected void refreshHttpLocation(final HttpLocation httpLocation) {
		nodeChanged(findHttpLocationNode(httpLocation));
	}

	/**
	 * Refresh job.
	 * 
	 * @param job the job
	 */
	protected void refreshJob(final Job job) {
		nodeChanged(findJobNode(job));
	}

	/**
	 * Refresh preference.
	 * 
	 * @param preference the preference
	 */
	protected void refreshPreference(final Preference preference) {
		nodeChanged(findPreferenceNode(preference));
	}

	/**
	 * Refresh property.
	 * 
	 * @param property the property
	 */
	protected void refreshProperty(final Property property) {
		nodeChanged(findPropertyNode(property));
	}

	/**
	 * Refresh subversion repository location.
	 * 
	 * @param subversionRepositoryLocation the subversion repository location
	 */
	protected void refreshSubversionRepositoryLocation(final SubversionRepositoryLocation subversionRepositoryLocation) {
		nodeChanged(findSubversionRepositoryLocationNode(subversionRepositoryLocation));
	}

	/**
	 * Refresh task.
	 * 
	 * @param task the task
	 */
	protected void refreshTask(final Task task) {
		nodeChanged(findTaskNode(task));
	}

	/**
	 * Removes the all children.
	 */
	private void removeAllChildren() {
		jobsNode.removeAllChildren();
		fileSystemLocationsNode.removeAllChildren();
		httpLocationsNode.removeAllChildren();
		subversionRepositoryLocationsNode.removeAllChildren();
		propertiesNode.removeAllChildren();
		preferencesNode.removeAllChildren();
	}
}
