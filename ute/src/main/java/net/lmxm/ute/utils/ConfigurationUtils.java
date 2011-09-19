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
package net.lmxm.ute.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.beans.tasks.Task;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class ConfigurationUtils.
 */
public final class ConfigurationUtils {

	/**
	 * The Enum PropertyType.
	 */
	private enum PropertyType {

		/** The NAME. */
		NAME,
		/** The VALUE. */
		VALUE
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationUtils.class);

	/**
	 * Generate property maps.
	 * 
	 * @param configuration the configuration
	 * @return the map
	 */
	private static Map<PropertyType, String[]> generatePropertyMaps(final Configuration configuration) {
		final String prefix = "generatePropertyMaps() :";

		LOGGER.debug("{} entered, configuration={}", prefix, configuration);

		final List<String> propertyNames = new ArrayList<String>();
		final List<String> propertyValues = new ArrayList<String>();

		for (final Preference preference : configuration.getPreferences()) {
			propertyNames.add("${pref." + preference.getId() + "}");
			propertyValues.add(StringUtils.trimToEmpty(preference.getValue()));
		}

		for (final Property property : configuration.getProperties()) {
			propertyNames.add("${" + property.getId() + "}");
			propertyValues.add(StringUtils.trimToEmpty(property.getValue()));
		}

		for (final Entry<String, String> entry : System.getenv().entrySet()) {
			propertyNames.add("${env." + entry.getKey() + "}");
			propertyValues.add(StringUtils.trimToEmpty(entry.getValue()));
		}

		final Map<PropertyType, String[]> propertyMaps = new HashMap<PropertyType, String[]>();

		propertyMaps.put(PropertyType.NAME, propertyNames.toArray(new String[0]));
		propertyMaps.put(PropertyType.VALUE, propertyValues.toArray(new String[0]));

		LOGGER.debug("{} returning {}", prefix, propertyMaps);

		return propertyMaps;
	}

	/**
	 * Gets the file names.
	 * 
	 * @param files the files
	 * 
	 * @return the file names
	 */
	public static String[] getFileNames(final List<FileReference> files) {
		final String prefix = "getFileNames() :";

		LOGGER.debug("{} entered, files={}", prefix, files);

		final String[] fileNames = new String[files.size()];
		int i = 0;

		for (final FileReference file : files) {
			fileNames[i++] = file.getName();
		}

		LOGGER.debug("{} returning {}", prefix, fileNames);

		return fileNames;
	}

	/**
	 * Gets the file system location.
	 * 
	 * @param configuration the configuration
	 * @param locationId the location id
	 * 
	 * @return the file system location
	 */
	public static FileSystemLocation getFileSystemLocation(final Configuration configuration, final String locationId) {
		final String prefix = "getFileSystemLocation() :";

		LOGGER.debug("{} entered, locationId={}", prefix, locationId);

		for (final FileSystemLocation location : configuration.getFileSystemLocations()) {
			if (location.getId().equals(locationId)) {
				LOGGER.debug("{} returning {}", prefix, location);

				return location;
			}
		}

		LOGGER.debug("{} returning null", prefix);

		return null;
	}

	/**
	 * Gets the http location.
	 * 
	 * @param configuration the configuration
	 * @param locationId the location id
	 * @return the http location
	 */
	public static HttpLocation getHttpLocation(final Configuration configuration, final String locationId) {
		final String prefix = "getHttpLocation() :";

		LOGGER.debug("{} entered, locationId={}", prefix, locationId);

		for (final HttpLocation location : configuration.getHttpLocations()) {
			if (location.getId().equals(locationId)) {
				LOGGER.debug("{} returning {}", prefix, location);

				return location;
			}
		}

		LOGGER.debug("{} returning null", prefix);

		return null;
	}

	/**
	 * Gets the job.
	 * 
	 * @param configuration the configuration
	 * @param jobId the job id
	 * @return the job
	 */
	public static Job getJob(final Configuration configuration, final String jobId) {
		final String prefix = "getJob() :";

		LOGGER.debug("{} entered, jobId={}", prefix, jobId);

		for (final Job job : configuration.getJobs()) {
			if (job.getId().equals(jobId)) {
				LOGGER.debug("{} returning {}", prefix, job);

				return job;
			}
		}

		LOGGER.debug("{} returning null", prefix);

		return null;
	}

	/**
	 * Gets the subversion repository location.
	 * 
	 * @param configuration the configuration
	 * @param locationId the location id
	 * 
	 * @return the subversion repository location
	 */
	public static SubversionRepositoryLocation getSubversionRepositoryLocation(final Configuration configuration,
			final String locationId) {
		final String prefix = "getSubversionRepositoryLocation() :";

		LOGGER.debug("{} entered, locationId={}", prefix, locationId);

		for (final SubversionRepositoryLocation location : configuration.getSubversionRepositoryLocations()) {
			if (location.getId().equals(locationId)) {
				LOGGER.debug("{} returning {}", prefix, location);

				return location;
			}
		}

		LOGGER.debug("{} returning null", prefix);

		return null;
	}

	/**
	 * Interpolate files.
	 * 
	 * @param files the files
	 * @param propertyNames the property names
	 * @param propertyValues the property values
	 */
	private static void interpolateFiles(final List<FileReference> files, final String[] propertyNames,
			final String[] propertyValues) {
		final String prefix = "interpolateFiles() :";

		LOGGER.debug("{} entered, files={}", prefix, files);

		Preconditions.checkNotNull(files, "Files may not be null");

		for (final FileReference fileReference : files) {
			final String name = fileReference.getName();
			final String targetName = fileReference.getTargetName();

			fileReference.setName(interpolateProperties(name, propertyNames, propertyValues));
			fileReference.setTargetName(interpolateProperties(targetName, propertyNames, propertyValues));
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Interpolate file system location.
	 * 
	 * @param target the target
	 * @param propertyNames the property names
	 * @param propertyValues the property values
	 */
	private static void interpolateFileSystemTarget(final FileSystemTarget target, final String[] propertyNames,
			final String[] propertyValues) {
		final String prefix = "interpolateFileSystemTarget() :";

		LOGGER.debug("{} entered, target={}", prefix, target);

		if (target == null) {
			LOGGER.debug("{} skipping, target is null", prefix);
		}
		else {
			final FileSystemLocation location = target.getLocation();

			if (location == null) {
				LOGGER.debug("{} skipping, target.location is null", prefix);
			}
			else {
				final String path = location.getPath();

				location.setPath(interpolateProperties(path, propertyNames, propertyValues));
			}

			target.setRelativePath(interpolateProperties(target.getRelativePath(), propertyNames, propertyValues));
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Interpolate http query params.
	 * 
	 * @param source the source
	 * @param propertyNames the property names
	 * @param propertyValues the property values
	 */
	private static void interpolateHttpQueryParams(final HttpSource source, final String[] propertyNames,
			final String[] propertyValues) {
		final String prefix = "interpolateHttpQueryParams() :";

		LOGGER.debug("{} entered, source={}", prefix, source);

		if (source == null) {
			LOGGER.debug("{} skipping, source is null", prefix);
		}
		else {
			final Map<String, String> queryParams = new HashMap<String, String>();

			for (final Entry<String, String> queryParam : source.getQueryParams().entrySet()) {
				final String name = interpolateProperties(queryParam.getKey(), propertyNames, propertyValues);
				final String value = interpolateProperties(queryParam.getValue(), propertyNames, propertyValues);

				queryParams.put(name, value);
			}

			source.setQueryParams(queryParams);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Interpolate http source.
	 * 
	 * @param source the source
	 * @param propertyNames the property names
	 * @param propertyValues the property values
	 */
	private static void interpolateHttpSource(final HttpSource source, final String[] propertyNames,
			final String[] propertyValues) {
		final String prefix = "interpolateHttpSource() :";

		LOGGER.debug("{} entered, source={}", prefix, source);

		if (source == null) {
			LOGGER.debug("{} skipping, source is null", prefix);
		}
		else {
			final HttpLocation location = source.getLocation();

			if (location == null) {
				LOGGER.debug("{} skipping, source is null", prefix);
			}
			else {
				location.setUrl(interpolateProperties(location.getUrl(), propertyNames, propertyValues));
			}

			source.setRelativePath(interpolateProperties(source.getRelativePath(), propertyNames, propertyValues));

			interpolateHttpQueryParams(source, propertyNames, propertyValues);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Interpolate job values.
	 * 
	 * @param originalJob the original job
	 * @param configuration the configuration
	 * @return the job
	 */
	public static Job interpolateJobValues(final Job originalJob, final Configuration configuration) {
		final String prefix = "interpolateJobValues() :";

		LOGGER.debug("{} entered, originalJob={}", prefix, originalJob);

		final Job job = (Job) SerializationUtils.clone(originalJob);

		LOGGER.debug("{} cloned job={}", prefix, job);

		final Map<PropertyType, String[]> propertyMaps = generatePropertyMaps(configuration);
		final String[] propertyNames = propertyMaps.get(PropertyType.NAME);
		final String[] propertyValues = propertyMaps.get(PropertyType.VALUE);

		for (final Task task : job.getTasks()) {
			if (task instanceof FileSystemDeleteTask) {
				final FileSystemDeleteTask fileSystemDeleteTask = (FileSystemDeleteTask) task;

				interpolateFileSystemTarget(fileSystemDeleteTask.getTarget(), propertyNames, propertyValues);
			}
			else if (task instanceof FindReplaceTask) {
				final FindReplaceTask findReplaceTask = (FindReplaceTask) task;

				interpolateFileSystemTarget(findReplaceTask.getTarget(), propertyNames, propertyValues);
				interpolatePatterns(findReplaceTask.getPatterns(), propertyNames, propertyValues);
			}
			else if (task instanceof GroovyTask) {
				final GroovyTask groovyTask = (GroovyTask) task;

				interpolateFileSystemTarget(groovyTask.getTarget(), propertyNames, propertyValues);
			}
			else if (task instanceof HttpDownloadTask) {
				final HttpDownloadTask httpDownloadTask = (HttpDownloadTask) task;

				interpolateHttpSource(httpDownloadTask.getSource(), propertyNames, propertyValues);
				interpolateFileSystemTarget(httpDownloadTask.getTarget(), propertyNames, propertyValues);
				interpolateFiles(httpDownloadTask.getFiles(), propertyNames, propertyValues);
			}
			else if (task instanceof SubversionExportTask) {
				final SubversionExportTask subversionExportTask = (SubversionExportTask) task;

				interpolateSubversionRepositorySource(subversionExportTask.getSource(), propertyNames, propertyValues);
				interpolateFileSystemTarget(subversionExportTask.getTarget(), propertyNames, propertyValues);
				interpolateFiles(subversionExportTask.getFiles(), propertyNames, propertyValues);
			}
			else if (task instanceof SubversionUpdateTask) {
				final SubversionUpdateTask subversionUpdateTask = (SubversionUpdateTask) task;

				interpolateSubversionRepositorySource(subversionUpdateTask.getSource(), propertyNames, propertyValues);
				interpolateFileSystemTarget(subversionUpdateTask.getTarget(), propertyNames, propertyValues);
				interpolateFiles(subversionUpdateTask.getFiles(), propertyNames, propertyValues);
			}
			else {
				throw new RuntimeException("Unsupported task type"); // TODO Use appropriate exception
			}
		}

		LOGGER.debug("{} returning {}", prefix, job);

		return job;
	}

	/**
	 * Interpolate patterns.
	 * 
	 * @param patterns the patterns
	 * @param propertyNames the property names
	 * @param propertyValues the property values
	 */
	private static void interpolatePatterns(final List<FindReplacePattern> patterns, final String[] propertyNames,
			final String[] propertyValues) {
		final String prefix = "interpolatePatterns() :";

		LOGGER.debug("{} entered, patterns={}", prefix, patterns);

		Preconditions.checkNotNull(patterns, "Patterns may not be null");

		for (final FindReplacePattern pattern : patterns) {
			final String find = pattern.getFind();
			final String replace = pattern.getReplace();

			pattern.setFind(interpolateProperties(find, propertyNames, propertyValues));
			pattern.setReplace(interpolateProperties(replace, propertyNames, propertyValues));
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Interpolate properties.
	 * 
	 * @param string the string
	 * @param propertyNames the property names
	 * @param propertyValues the property values
	 * @return the string
	 */
	private static String interpolateProperties(final String string, final String[] propertyNames,
			final String[] propertyValues) {
		final String newString = StringUtils.replaceEachRepeatedly(string, propertyNames, propertyValues);

		validateDoesNotContainProperties(newString);

		return newString;
	}

	/**
	 * Interpolate subversion repository source.
	 * 
	 * @param source the source
	 * @param propertyNames the property names
	 * @param propertyValues the property values
	 */
	private static void interpolateSubversionRepositorySource(final SubversionRepositorySource source,
			final String[] propertyNames, final String[] propertyValues) {
		final String prefix = "interpolateSubversionRepositorySource() :";

		LOGGER.debug("{} entered, source={}", prefix, source);

		if (source == null) {
			LOGGER.debug("{} skipping, source is null", prefix);
		}
		else {
			final SubversionRepositoryLocation location = source.getLocation();

			if (location == null) {
				LOGGER.debug("{} skipping, source is null", prefix);
			}
			else {
				location.setUrl(interpolateProperties(location.getUrl(), propertyNames, propertyValues));
			}

			source.setRelativePath(interpolateProperties(source.getRelativePath(), propertyNames, propertyValues));
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Validate configuration.
	 * 
	 * @param configuration the configuration
	 */
	public static void validateConfiguration(final Configuration configuration) {
		for (final Job job : configuration.getJobs()) {
			interpolateJobValues(job, configuration);
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

			throw new RuntimeException("Undefined property with name \"" + matcher.group(3) + "\"");
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
