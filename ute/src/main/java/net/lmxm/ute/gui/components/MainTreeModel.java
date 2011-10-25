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

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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

	/** The http locations node. */
	private final DefaultMutableTreeNode httpLocationsNode;

	/** The jobs node. */
	private final DefaultMutableTreeNode jobsNode;

	/** The preferences node. */
	private final DefaultMutableTreeNode preferencesNode;

	/** The properties node. */
	private final DefaultMutableTreeNode propertiesNode;

	/** The root node. */
	private final DefaultMutableTreeNode rootNode;

	/** The subversion locations node. */
	private final DefaultMutableTreeNode subversionLocationsNode;

	/**
	 * Instantiates a new main tree model.
	 * 
	 * @param configurationHolder the configuration holder
	 */
	public MainTreeModel(final ConfigurationHolder configurationHolder) {
		super(new DefaultMutableTreeNode("Root"));

		this.configurationHolder = configurationHolder;

		rootNode = (DefaultMutableTreeNode) getRoot();

		jobsNode = new DefaultMutableTreeNode(new JobsRootTreeNode(configurationHolder));
		rootNode.add(jobsNode);

		fileSystemLocationsNode = new DefaultMutableTreeNode(new FileSystemLocationsRootTreeNode(configurationHolder));
		rootNode.add(fileSystemLocationsNode);

		httpLocationsNode = new DefaultMutableTreeNode(new HttpLocationsRootTreeNode(configurationHolder));
		rootNode.add(httpLocationsNode);

		subversionLocationsNode = new DefaultMutableTreeNode(new SubversionRepositoryLocationsRootTreeNode(
				configurationHolder));
		rootNode.add(subversionLocationsNode);

		propertiesNode = new DefaultMutableTreeNode(new PropertiesRootTreeNode(configurationHolder));
		rootNode.add(propertiesNode);

		preferencesNode = new DefaultMutableTreeNode(new PreferencesRootTreeNode(configurationHolder));
		rootNode.add(preferencesNode);
	}

	/**
	 * Adds the property.
	 * 
	 * @param property the property
	 * @return the tree path
	 */
	public TreePath addProperty(final Property property) {
		final DefaultMutableTreeNode propertyNode = new DefaultMutableTreeNode(property);

		insertNodeInto(propertyNode, propertiesNode, 0);

		return getPathToProperties().pathByAddingChild(propertyNode);
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
	 * Gets the path to jobs.
	 * 
	 * @return the path to jobs
	 */
	public TreePath getPathToJobs() {
		return new TreePath(rootNode).pathByAddingChild(jobsNode);
	}

	/**
	 * Gets the path to properties.
	 * 
	 * @return the path to properties
	 */
	public TreePath getPathToProperties() {
		return new TreePath(rootNode).pathByAddingChild(propertiesNode);
	}

	/**
	 * Load file system locations.
	 */
	private void loadFileSystemLocations() {
		final String prefix = "loadFileSystemLocations() :";

		final List<FileSystemLocation> fileSystemLocations = getConfiguration().getFileSystemLocations();

		LOGGER.debug("{} entered, loading {} locations", prefix, fileSystemLocations.size());

		for (final FileSystemLocation fileSystemLocation : fileSystemLocations) {
			fileSystemLocationsNode.add(new DefaultMutableTreeNode(fileSystemLocation));
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
			httpLocationsNode.add(new DefaultMutableTreeNode(httpLocation));
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
			final DefaultMutableTreeNode jobNode = new DefaultMutableTreeNode(job);
			final List<Task> tasks = job.getTasks();

			LOGGER.debug("{} loading {} tasks", prefix, tasks.size());

			for (final Task task : tasks) {
				final DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode(task);

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
			preferencesNode.add(new DefaultMutableTreeNode(preference));
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
			propertiesNode.add(new DefaultMutableTreeNode(property));
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
			subversionLocationsNode.add(new DefaultMutableTreeNode(subversionLocation));
		}

		LOGGER.debug("{} leaving");
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
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
	 * Removes the all children.
	 */
	private void removeAllChildren() {
		jobsNode.removeAllChildren();
		fileSystemLocationsNode.removeAllChildren();
		httpLocationsNode.removeAllChildren();
		subversionLocationsNode.removeAllChildren();
		propertiesNode.removeAllChildren();
		preferencesNode.removeAllChildren();
	}
}
