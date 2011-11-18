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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;

import org.apache.commons.lang.StringUtils;
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
	 * @param locationId the location id
	 * @return the file system location
	 */
	public static FileSystemLocation findFileSystemLocationById(final Configuration configuration,
			final String locationId) {
		final String prefix = "findFileSystemLocationById() :";

		LOGGER.debug("{} entered, locationId={}", prefix, locationId);

		FileSystemLocation foundLocation = null;

		for (final FileSystemLocation location : configuration.getFileSystemLocations()) {
			if (location.getId().equals(locationId)) {
				foundLocation = location;
				break;
			}
		}

		LOGGER.debug("{} returning {}", prefix, foundLocation);

		return foundLocation;
	}

	/**
	 * Find http location by id.
	 * 
	 * @param configuration the configuration
	 * @param locationId the location id
	 * @return the http location
	 */
	public static HttpLocation findHttpLocationById(final Configuration configuration, final String locationId) {
		final String prefix = "findHttpLocationById() :";

		LOGGER.debug("{} entered, locationId={}", prefix, locationId);

		HttpLocation foundLocation = null;

		for (final HttpLocation location : configuration.getHttpLocations()) {
			if (location.getId().equals(locationId)) {
				foundLocation = location;
				break;
			}
		}

		LOGGER.debug("{} returning {}", prefix, foundLocation);

		return foundLocation;
	}

	/**
	 * Find job by id.
	 * 
	 * @param configuration the configuration
	 * @param jobId the job id
	 * @return the job
	 */
	public static Job findJobById(final Configuration configuration, final String jobId) {
		final String prefix = "findJobById() :";

		LOGGER.debug("{} entered, jobId={}", prefix, jobId);

		Job foundJob = null;

		for (final Job job : configuration.getJobs()) {
			if (job.getId().equals(jobId)) {
				foundJob = job;
				break;
			}
		}

		LOGGER.debug("{} returning {}", prefix, foundJob);

		return foundJob;
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
		final String prefix = "findSubversionRepositoryLocationById() :";

		LOGGER.debug("{} entered, locationId={}", prefix, locationId);

		SubversionRepositoryLocation foundLocation = null;

		for (final SubversionRepositoryLocation location : configuration.getSubversionRepositoryLocations()) {
			if (location.getId().equals(locationId)) {
				foundLocation = location;
				break;
			}
		}

		LOGGER.debug("{} returning {}", prefix, foundLocation);

		return foundLocation;
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
	 * Validate does not contain properties.
	 * 
	 * @param string the string
	 */
	protected static void validateDoesNotContainProperties(final String string) {
		final String prefix = "validateDoesNotContainProperties() :";

		LOGGER.debug("{} entered, string={}", prefix, string);

		if (StringUtils.isBlank(string)) {
			LOGGER.debug("{} leaving, string is blank", prefix);

			return;
		}

		final Pattern pattern = Pattern.compile("^(.*)(\\$\\{)(.*)(\\})(.*)$");
		final Matcher matcher = pattern.matcher(string);

		if (matcher.find()) {
			LOGGER.error("{} undefined property with name \"{}\"", prefix, matcher.group(3));

			throw new RuntimeException("Undefined property with name \"" + matcher.group(3) + "\""); // TODO
		}
		else {
			LOGGER.debug("{} no property references found", prefix);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Instantiates a new configuration utils.
	 */
	private ConfigurationUtils() {
		throw new AssertionError();
	}
}
