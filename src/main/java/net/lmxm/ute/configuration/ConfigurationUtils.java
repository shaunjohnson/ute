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
package net.lmxm.ute.configuration;

import static net.lmxm.ute.ApplicationConstants.FILE_EXTENSION;

import java.util.List;

import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;

import org.codehaus.plexus.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ConfigurationUtils.
 */
public final class ConfigurationUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationUtils.class);

	/**
	 * Append file extension.
	 * 
	 * @param path the path
	 * @return the string
	 */
	public static String appendFileExtension(final String path) {
		if (path == null) {
			return null;
		}
		if (FileUtils.extension(path).equals(FILE_EXTENSION)) {
			return path;
		}
		else {
			return path + "." + FILE_EXTENSION;
		}
	}

	/**
	 * Find file system location by id.
	 * 
	 * @param configuration the configuration
	 * @param id the id
	 * @return the file system location
	 */
	public static FileSystemLocation findFileSystemLocationById(final Configuration configuration, final String id) {
		return (FileSystemLocation) findIdentifiableBeanById(configuration.getFileSystemLocations(), id);
	}

	/**
	 * Find http location by id.
	 * 
	 * @param configuration the configuration
	 * @param id the id
	 * @return the http location
	 */
	public static HttpLocation findHttpLocationById(final Configuration configuration, final String id) {
		return (HttpLocation) findIdentifiableBeanById(configuration.getHttpLocations(), id);
	}

	/**
	 * Find identifiable bean by id.
	 * 
	 * @param identifiableBeans the identifiable beans
	 * @param id the id
	 * @return the identifiable bean
	 */
	protected static IdentifiableBean findIdentifiableBeanById(
			final List<? extends IdentifiableBean> identifiableBeans, final String id) {
		final String prefix = "findIdentifiableBeanById() :";

		LOGGER.debug("{} entered, id={}", prefix, id);

		IdentifiableBean foundIdentifiableBean = null;

		for (final IdentifiableBean identifiableBean : identifiableBeans) {
			if (identifiableBean.getId().equals(id)) {
				foundIdentifiableBean = identifiableBean;
				break;
			}
		}

		LOGGER.debug("{} returning {}", prefix, foundIdentifiableBean);

		return foundIdentifiableBean;
	}

	/**
	 * Find job by id.
	 * 
	 * @param configuration the configuration
	 * @param id the id
	 * @return the job
	 */
	public static Job findJobById(final Configuration configuration, final String id) {
		return (Job) findIdentifiableBeanById(configuration.getJobs(), id);
	}

    /**
     * Find Maven repository location by id.
     *
     * @param configuration the configuration
     * @param locationId the location id
     * @return the Maven repository location
     */
    public static MavenRepositoryLocation findMavenRepositoryLocationById(final Configuration configuration,
                                                                                    final String locationId) {
        return (MavenRepositoryLocation) findIdentifiableBeanById(
                configuration.getMavenRepositoryLocations(), locationId);
    }

	/**
	 * Find preference by id.
	 * 
	 * @param configuration the configuration
	 * @param id the id
	 * @return the preference
	 */
	public static Preference findPreferenceById(final Configuration configuration, final String id) {
		return (Preference) findIdentifiableBeanById(configuration.getPreferences(), id);
	}

	/**
	 * Find property by id.
	 * 
	 * @param configuration the configuration
	 * @param id the id
	 * @return the property
	 */
	public static Property findPropertyById(final Configuration configuration, final String id) {
		return (Property) findIdentifiableBeanById(configuration.getProperties(), id);
	}

	/**
	 * Find subversion repository location by id.
	 * 
	 * @param configuration the configuration
	 * @param locationId the location id
	 * @return the subversion repository location
	 */
	public static SubversionRepositoryLocation findSubversionRepositoryLocationById(final Configuration configuration,
			final String locationId) {
		return (SubversionRepositoryLocation) findIdentifiableBeanById(
				configuration.getSubversionRepositoryLocations(), locationId);
	}

	/**
	 * Find task by id.
	 * 
	 * @param configuration the configuration
	 * @param id the id
	 * @return the task
	 */
	public static Task findTaskById(final Configuration configuration, final String id) {
		Task task = null;

		for (final Job job : configuration.getJobs()) {
			task = (Task) findIdentifiableBeanById(job.getTasks(), id);

			if (task != null) {
				break;
			}
		}

		return task;
	}

	/**
	 * Validate configuration.
	 * 
	 * @param configuration the configuration
	 */
	public static void validateConfiguration(final Configuration configuration) {
		for (final Job job : configuration.getJobs()) {
			ConfigurationInterpolator.interpolateJobValues(job, configuration);
		}
	}

	/**
	 * Instantiates a new configuration utils.
	 */
	private ConfigurationUtils() {
		throw new AssertionError();
	}
}
