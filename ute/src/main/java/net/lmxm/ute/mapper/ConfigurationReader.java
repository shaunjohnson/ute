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
package net.lmxm.ute.mapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.AbstractFilesTask;
import net.lmxm.ute.beans.tasks.AbstractTask;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.exceptions.ConfigurationException;
import net.lmxm.ute.utils.ConfigurationUtils;
import noNamespace.FileSystemDeleteTaskType;
import noNamespace.FileSystemLocationType;
import noNamespace.FileSystemTargetType;
import noNamespace.FileType;
import noNamespace.FilesType;
import noNamespace.FindReplaceTaskType;
import noNamespace.GroovyTaskType;
import noNamespace.HttpDownloadTaskType;
import noNamespace.HttpLocationType;
import noNamespace.HttpSourceType;
import noNamespace.JobType;
import noNamespace.LocationsType;
import noNamespace.PatternType;
import noNamespace.PatternsType;
import noNamespace.PreferenceType;
import noNamespace.PropertyType;
import noNamespace.QueryParam;
import noNamespace.ScopeType;
import noNamespace.SubversionExportTaskType;
import noNamespace.SubversionRepositorySourceType;
import noNamespace.SubversionRespositoryLocationType;
import noNamespace.SubversionUpdateTaskType;
import noNamespace.TaskType;
import noNamespace.UteConfigurationDocument;
import noNamespace.UteConfigurationType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ConfigurationReader.
 */
public final class ConfigurationReader {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationReader.class);

	/** The file. */
	private final File file;

	/**
	 * Instantiates a new configuration reader.
	 * 
	 */
	public ConfigurationReader(final File file) {
		super();

		this.file = file;
	}

	/**
	 * Convert scope type to scope.
	 * 
	 * @param scopeType the scope type
	 * @return the scope
	 */
	private Scope convertScopeTypeToScope(final ScopeType.Enum scopeType) {
		final Scope scope;

		if (scopeType == ScopeType.FILE) {
			scope = Scope.FILE;
		}
		else if (scopeType == ScopeType.LINE) {
			scope = Scope.LINE;
		}
		else {
			scope = Scope.LINE;
		}

		return scope;
	}

	/**
	 * Parses the file.
	 * 
	 * @param fileType the file type
	 * 
	 * @return the file
	 */
	private FileReference parseFile(final FileType fileType) {
		final String prefix = "parseFile() :";

		LOGGER.debug("{} entered", prefix);

		final FileReference file = new FileReference();

		file.setName(StringUtils.trim(fileType.getName()));
		file.setTargetName(StringUtils.trim(fileType.getTargetName()));

		LOGGER.debug("{} returning {}", prefix, file);

		return file;
	}

	/**
	 * Parses the files.
	 * 
	 * @param task the task
	 * @param filesType the files type
	 */
	private void parseFiles(final AbstractFilesTask task, final FilesType filesType) {
		final String prefix = "parseFiles() :";

		LOGGER.debug("{} entered", prefix);

		if (filesType == null) {
			LOGGER.debug("{} filesType is null, exiting", prefix);

			return;
		}

		final List<FileReference> files = task.getFiles();
		final FileType[] fileTypeArray = filesType.getFileArray();

		if (ArrayUtils.isEmpty(fileTypeArray)) {
			LOGGER.debug("{} fileArray is empty is null, exiting", prefix);

			return;
		}

		LOGGER.debug("{} parsing {} file types", prefix, fileTypeArray.length);

		for (final FileType fileType : fileTypeArray) {
			files.add(parseFile(fileType));
		}

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the file system delete task.
	 * 
	 * @param taskType the task type
	 * @param configuration the configuration
	 * 
	 * @return the task
	 */
	private AbstractTask parseFileSystemDeleteTask(final FileSystemDeleteTaskType taskType,
			final Configuration configuration) {
		final String prefix = "parseFileSystemDeleteTask() :";

		LOGGER.debug("{} entered", prefix);

		final FileSystemDeleteTask task = new FileSystemDeleteTask();

		task.setStopOnError(taskType.getStopOnError());
		task.setTarget(parseFileSystemTarget(taskType.getFileSystemTarget(), configuration));

		parseFiles(task, taskType.getFiles());

		LOGGER.debug("{} returning {}", prefix, task);

		return task;
	}

	/**
	 * Parses the file system location.
	 * 
	 * @param fileSystemLocationType the file system location type
	 * @param configuration the configuration
	 * 
	 * @return the file system location
	 */
	private FileSystemLocation parseFileSystemLocation(final FileSystemLocationType fileSystemLocationType,
			final Configuration configuration) {
		final String prefix = "parseFileSystemLocation() :";

		LOGGER.debug("{} entered", prefix);

		final FileSystemLocation fileSystemLocation = new FileSystemLocation();
		final String id = fileSystemLocationType.getId();
		final String path = fileSystemLocationType.getPath();

		fileSystemLocation.setId(id);
		fileSystemLocation.setPath(path);

		LOGGER.debug("{} returning {}", prefix, fileSystemLocation);

		return fileSystemLocation;
	}

	/**
	 * Parses the file system locations.
	 * 
	 * @param locationsType the locations type
	 * @param configuration the configuration
	 */
	private void parseFileSystemLocations(final LocationsType locationsType, final Configuration configuration) {
		final String prefix = "parseFileSystemLocations() :";

		LOGGER.debug("{} entered", prefix);

		final List<FileSystemLocation> fileSystemLocations = configuration.getFileSystemLocations();

		LOGGER.debug("{} parsing {} file system locations", prefix, fileSystemLocations.size());

		for (final FileSystemLocationType fileSystemLocationType : locationsType.getFileSystemLocationArray()) {
			fileSystemLocations.add(parseFileSystemLocation(fileSystemLocationType, configuration));
		}

		Collections.sort(fileSystemLocations);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the file system target.
	 * 
	 * @param targetType the target type
	 * @param configuration the configuration
	 * 
	 * @return the file system target
	 */
	private FileSystemTarget parseFileSystemTarget(final FileSystemTargetType targetType,
			final Configuration configuration) {
		final String prefix = "parseFileSystemTarget() :";

		LOGGER.debug("{} entered", prefix);

		final FileSystemTarget target = new FileSystemTarget();
		final FileSystemLocation targetLocation = ConfigurationUtils.getFileSystemLocation(configuration,
				targetType.getLocationId());

		target.setLocation(targetLocation);
		target.setRelativePath(targetType.getRelativePath());

		LOGGER.debug("{} returning {}", prefix, target);

		return target;
	}

	/**
	 * Parses the find replace task.
	 * 
	 * @param taskType the task type
	 * @param configuration the configuration
	 * @return the abstract task
	 */
	private AbstractTask parseFindReplaceTask(final FindReplaceTaskType taskType, final Configuration configuration) {
		final String prefix = "parseFindReplaceTask() :";

		LOGGER.debug("{} entered", prefix);

		final FindReplaceTask task = new FindReplaceTask();

		task.setScope(convertScopeTypeToScope(taskType.getScope()));
		task.setTarget(parseFileSystemTarget(taskType.getFileSystemTarget(), configuration));

		parseFiles(task, taskType.getFiles());
		parsePatterns(task, taskType.getPatterns());

		LOGGER.debug("{} returning {}", prefix, task);

		return task;
	}

	/**
	 * Parses the groovy task.
	 * 
	 * @param taskType the task type
	 * @param configuration the configuration
	 * @return the abstract task
	 */
	private AbstractTask parseGroovyTask(final GroovyTaskType taskType, final Configuration configuration) {
		final String prefix = "parseGroovyTask() :";

		LOGGER.debug("{} entered", prefix);

		final GroovyTask task = new GroovyTask();

		task.setScript(taskType.getScript());
		task.setTarget(parseFileSystemTarget(taskType.getFileSystemTarget(), configuration));

		parseFiles(task, taskType.getFiles());

		LOGGER.debug("{} returning {}", prefix, task);

		return task;
	}

	/**
	 * Parses the http download task.
	 * 
	 * @param taskType the task type
	 * @param configuration the configuration
	 * @return the abstract task
	 */
	private AbstractTask parseHttpDownloadTask(final HttpDownloadTaskType taskType, final Configuration configuration) {
		final String prefix = "parseHttpDownloadTask() :";

		LOGGER.debug("{} entered", prefix);

		final HttpDownloadTask task = new HttpDownloadTask();

		task.setSource(parseHttpSource(taskType.getHttpSource(), configuration));
		task.setTarget(parseFileSystemTarget(taskType.getFileSystemTarget(), configuration));

		parseFiles(task, taskType.getFiles());

		LOGGER.debug("{} returning {}", prefix, task);

		return task;
	}

	/**
	 * Parses the http location.
	 * 
	 * @param httpLocationType the http location type
	 * @param configuration the configuration
	 * @return the http location
	 */
	private HttpLocation parseHttpLocation(final HttpLocationType httpLocationType, final Configuration configuration) {
		final String prefix = "parseHttpLocation() :";

		LOGGER.debug("{} entered", prefix);

		final HttpLocation httpLocation = new HttpLocation();
		final String id = httpLocationType.getId();
		final String url = StringUtils.trim(httpLocationType.getUrl());

		httpLocation.setId(id);
		httpLocation.setUrl(url);

		LOGGER.debug("{} returning {}", prefix, httpLocation);

		return httpLocation;
	}

	/**
	 * Parses the http locations.
	 * 
	 * @param locationsType the locations type
	 * @param configuration the configuration
	 */
	private void parseHttpLocations(final LocationsType locationsType, final Configuration configuration) {
		final String prefix = "parseHttpLocations() :";

		LOGGER.debug("{} entered", prefix);

		final List<HttpLocation> httpLocations = configuration.getHttpLocations();

		final HttpLocationType[] locationTypeArray = locationsType.getHttpLocationArray();

		LOGGER.debug("{} parsing {} HTTP locations", prefix, locationTypeArray.length);

		for (final HttpLocationType httpLocationType : locationTypeArray) {
			httpLocations.add(parseHttpLocation(httpLocationType, configuration));
		}

		Collections.sort(httpLocations);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the http query params.
	 * 
	 * @param sourceType the source type
	 * @param source the source
	 */
	private void parseHttpQueryParams(final HttpSourceType sourceType, final HttpSource source) {
		final String prefix = "parseHttpQueryParams() :";

		LOGGER.debug("{} entered", prefix);

		final Map<String, String> queryParams = new HashMap<String, String>();

		if (sourceType.isSetQueryParams()) {
			for (final QueryParam queryParam : sourceType.getQueryParams().getQueryParamArray()) {
				queryParams.put(queryParam.getName(), queryParam.getValue());
			}
		}

		source.setQueryParams(queryParams);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Parses the http source.
	 * 
	 * @param sourceType the source type
	 * @param configuration the configuration
	 * @return the http source
	 */
	private HttpSource parseHttpSource(final HttpSourceType sourceType, final Configuration configuration) {
		final String prefix = "parseHttpSource() :";

		LOGGER.debug("{} entered", prefix);

		final HttpSource source = new HttpSource();
		final HttpLocation location = ConfigurationUtils.getHttpLocation(configuration, sourceType.getLocationId());

		source.setLocation(location);
		source.setRelativePath(sourceType.getRelativePath());

		parseHttpQueryParams(sourceType, source);

		LOGGER.debug("{} returning {}", prefix, source);

		return source;
	}

	/**
	 * Parses the job.
	 * 
	 * @param jobType the job type
	 * @param configuration the configuration
	 * 
	 * @return the job
	 */
	private SequentialJob parseJob(final JobType jobType, final Configuration configuration) {
		final String prefix = "parseJob() :";

		LOGGER.debug("{} entered", prefix);

		final SequentialJob job = new SequentialJob();

		job.setDescription(jobType.getDescription());
		job.setId(jobType.getId());

		final List<Task> tasks = job.getTasks();

		LOGGER.debug("{} parsing {} tasks", prefix, tasks.size());

		for (final TaskType taskType : jobType.getTasks().getTaskArray()) {
			tasks.add(parseTask(taskType, configuration));
		}

		LOGGER.debug("{} returning {}", prefix, job);

		return job;
	}

	/**
	 * Parses the jobs.
	 * 
	 * @param configurationType the configuration type
	 * @param configuration the configuration
	 */
	private void parseJobs(final UteConfigurationType configurationType, final Configuration configuration) {
		final String prefix = "parseJobs() :";

		LOGGER.debug("{} entered", prefix);

		final List<Job> jobs = configuration.getJobs();

		LOGGER.debug("{} parsing {} jobs", prefix, jobs.size());

		for (final JobType jobType : configurationType.getJobs().getJobArray()) {
			jobs.add(parseJob(jobType, configuration));
		}

		Collections.sort(jobs);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the locations.
	 * 
	 * @param configurationType the configuration type
	 * @param configuration the configuration
	 */
	private void parseLocations(final UteConfigurationType configurationType, final Configuration configuration) {
		final String prefix = "parseLocations() :";

		LOGGER.debug("{} entered", prefix);

		final LocationsType locationsType = configurationType.getLocations();

		parseFileSystemLocations(locationsType, configuration);
		parseHttpLocations(locationsType, configuration);
		parseSubversionRepositoryLocations(locationsType, configuration);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the pattern.
	 * 
	 * @param patternType the pattern type
	 * @return the find replace pattern
	 */
	private FindReplacePattern parsePattern(final PatternType patternType) {
		final String prefix = "parsePattern() :";

		LOGGER.debug("{} entered", prefix);

		final FindReplacePattern pattern = new FindReplacePattern();

		pattern.setFind(patternType.getFind());
		pattern.setReplace(patternType.getReplace());

		if (!pattern.isValid()) {
			throw new ConfigurationException("The find pattern \"" + patternType.getFind() + "\" is not valid");
		}

		LOGGER.debug("{} returning {}", prefix, pattern);

		return pattern;
	}

	/**
	 * Parses the patterns.
	 * 
	 * @param task the task
	 * @param patternsType the patterns type
	 */
	private void parsePatterns(final FindReplaceTask task, final PatternsType patternsType) {
		final String prefix = "parsePatterns() :";

		LOGGER.debug("{} entered", prefix);

		if (patternsType == null) {
			LOGGER.debug("{} patternsType is null, exiting", prefix);

			return;
		}

		final List<FindReplacePattern> patterns = task.getPatterns();
		final PatternType[] patternTypeArray = patternsType.getPatternArray();

		if (ArrayUtils.isEmpty(patternTypeArray)) {
			LOGGER.debug("{} fileArray is empty is null, exiting", prefix);

			return;
		}

		LOGGER.debug("{} parsing {} pattern types", prefix, patternTypeArray.length);

		for (final PatternType patternType : patternTypeArray) {
			patterns.add(parsePattern(patternType));
		}

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the preference.
	 * 
	 * @param preferenceType the preference type
	 * @return the preference
	 */
	private Preference parsePreference(final PreferenceType preferenceType) {
		final String prefix = "parsePreference() :";

		LOGGER.debug("{} entered", prefix);

		final Preference preference = new Preference();

		preference.setId(preferenceType.getId());

		LOGGER.debug("{} returning {}", prefix, preference);

		return preference;
	}

	/**
	 * Parses the preferences.
	 * 
	 * @param configurationType the configuration type
	 * @param configuration the configuration
	 */
	private void parsePreferences(final UteConfigurationType configurationType, final Configuration configuration) {
		final String prefix = "parsePreferences() :";

		LOGGER.debug("{} entered", prefix);

		final List<Preference> preferences = configuration.getPreferences();

		LOGGER.debug("{} parsing {} preferences", prefix, preferences.size());

		for (final PreferenceType preferenceType : configurationType.getPreferences().getPreferenceArray()) {
			preferences.add(parsePreference(preferenceType));
		}

		Collections.sort(preferences);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the properties.
	 * 
	 * @param configurationType the configuration type
	 * @param configuration the configuration
	 */
	private void parseProperties(final UteConfigurationType configurationType, final Configuration configuration) {
		final String prefix = "parseProperties() :";

		LOGGER.debug("{} entered", prefix);

		final List<Property> properties = configuration.getProperties();

		LOGGER.debug("{} parsing {} properties", prefix, properties.size());

		for (final PropertyType propertyType : configurationType.getProperties().getPropertyArray()) {
			properties.add(parseProperty(propertyType));
		}

		Collections.sort(properties);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the property.
	 * 
	 * @param propertyType the property type
	 * 
	 * @return the property
	 */
	private Property parseProperty(final PropertyType propertyType) {
		final String prefix = "parseProperty() :";

		LOGGER.debug("{} entered", prefix);

		final Property property = new Property();

		property.setId(propertyType.getId());
		property.setValue(propertyType.getValue());

		LOGGER.debug("{} returning {}", prefix, property);

		return property;
	}

	/**
	 * Parses the subversion export task.
	 * 
	 * @param taskType the task type
	 * @param configuration the configuration
	 * @return the task
	 */
	private AbstractTask parseSubversionExportTask(final SubversionExportTaskType taskType,
			final Configuration configuration) {
		final String prefix = "parseSubversionExportTask() :";

		LOGGER.debug("{} entered", prefix);

		final SubversionExportTask task = new SubversionExportTask();

		task.setSource(parseSubversionRepositorySource(taskType.getSubversionRepositorySource(), configuration));
		task.setTarget(parseFileSystemTarget(taskType.getFileSystemTarget(), configuration));

		parseFiles(task, taskType.getFiles());

		LOGGER.debug("{} returning {}", prefix, task);

		return task;
	}

	/**
	 * Parses the subversion repository location.
	 * 
	 * @param subversionRepositoryLocationType the subversion repository location type
	 * @param configuration the configuration
	 * 
	 * @return the subversion repository location
	 */
	private SubversionRepositoryLocation parseSubversionRepositoryLocation(
			final SubversionRespositoryLocationType subversionRepositoryLocationType, final Configuration configuration) {
		final String prefix = "parseSubversionRepositoryLocation() :";

		LOGGER.debug("{} entered", prefix);

		final SubversionRepositoryLocation subversionRepositoryLocation = new SubversionRepositoryLocation();
		final String id = subversionRepositoryLocationType.getId();
		final String url = subversionRepositoryLocationType.getUrl();
		final String username = subversionRepositoryLocationType.getUsername();
		final String password = subversionRepositoryLocationType.getPassword();

		subversionRepositoryLocation.setId(id);
		subversionRepositoryLocation.setUrl(url);
		subversionRepositoryLocation.setUsername(username);
		subversionRepositoryLocation.setPassword(password);

		LOGGER.debug("{} returning {}", prefix, subversionRepositoryLocation);

		return subversionRepositoryLocation;
	}

	/**
	 * Parses the subversion repository locations.
	 * 
	 * @param locationsType the locations type
	 * @param configuration the configuration
	 */
	private void parseSubversionRepositoryLocations(final LocationsType locationsType, final Configuration configuration) {
		final String prefix = "parseSubversionRepositoryLocations() :";

		LOGGER.debug("{} entered", prefix);

		final List<SubversionRepositoryLocation> subversionRepositoryLocations = configuration
				.getSubversionRepositoryLocations();

		final SubversionRespositoryLocationType[] locationTypeArray = locationsType
				.getSubversionRespositoryLocationArray();

		LOGGER.debug("{} parsing {} Subversion repository locations", prefix, locationTypeArray.length);

		for (final SubversionRespositoryLocationType subversionRepositoryLocationType : locationTypeArray) {
			subversionRepositoryLocations.add(parseSubversionRepositoryLocation(subversionRepositoryLocationType,
					configuration));
		}

		Collections.sort(subversionRepositoryLocations);

		LOGGER.debug("{} exiting", prefix);
	}

	/**
	 * Parses the subversion repository source.
	 * 
	 * @param sourceType the source type
	 * @param configuration the configuration
	 * 
	 * @return the subversion repository source
	 */
	private SubversionRepositorySource parseSubversionRepositorySource(final SubversionRepositorySourceType sourceType,
			final Configuration configuration) {
		final String prefix = "parseSubversionRepositorySource() :";

		LOGGER.debug("{} entered", prefix);

		final SubversionRepositorySource source = new SubversionRepositorySource();
		final SubversionRepositoryLocation location = ConfigurationUtils.getSubversionRepositoryLocation(configuration,
				sourceType.getLocationId());

		source.setLocation(location);
		source.setRelativePath(sourceType.getRelativePath());

		LOGGER.debug("{} returning {}", prefix, source);

		return source;
	}

	/**
	 * Parses the subversion export task.
	 * 
	 * @param taskType the task type
	 * @param configuration the configuration
	 * @return the task
	 */
	private AbstractTask parseSubversionUpdateTask(final SubversionUpdateTaskType taskType,
			final Configuration configuration) {
		final String prefix = "parseSubversionUpdateTask() :";

		LOGGER.debug("{} entered", prefix);

		final SubversionUpdateTask task = new SubversionUpdateTask();

		task.setTarget(parseFileSystemTarget(taskType.getFileSystemTarget(), configuration));

		parseFiles(task, taskType.getFiles());

		LOGGER.debug("{} returning {}", prefix, task);

		return task;
	}

	/**
	 * Parses the task.
	 * 
	 * @param taskType the task type
	 * @param configuration the configuration
	 * 
	 * @return the task
	 */
	private AbstractTask parseTask(final TaskType taskType, final Configuration configuration) {
		final String prefix = "parseTask() :";

		LOGGER.debug("{} entered", prefix);

		AbstractTask task = null;

		if (taskType.isSetFileSystemDeleteTask()) {
			task = parseFileSystemDeleteTask(taskType.getFileSystemDeleteTask(), configuration);
		}
		else if (taskType.isSetFindReplaceTask()) {
			task = parseFindReplaceTask(taskType.getFindReplaceTask(), configuration);
		}
		else if (taskType.isSetGroovyTask()) {
			task = parseGroovyTask(taskType.getGroovyTask(), configuration);
		}
		else if (taskType.isSetHttpDownloadTask()) {
			task = parseHttpDownloadTask(taskType.getHttpDownloadTask(), configuration);
		}
		else if (taskType.isSetSubversionExportTask()) {
			task = parseSubversionExportTask(taskType.getSubversionExportTask(), configuration);
		}
		else if (taskType.isSetSubversionUpdateTask()) {
			task = parseSubversionUpdateTask(taskType.getSubversionUpdateTask(), configuration);
		}
		else {
			throw new ConfigurationException("Unsupported task type");
		}

		task.setDescription(taskType.getDescription());
		task.setEnabled(taskType.getEnabled());
		task.setId(taskType.getId());

		LOGGER.debug("{} returning {}", prefix, task);

		return task;
	}

	/**
	 * Read.
	 * 
	 * @return the configuration
	 */
	public Configuration read() {
		final String prefix = "read() :";

		LOGGER.debug("{} entered, file={}", prefix, file);

		final Configuration configuration = new Configuration();

		configuration.setAbsolutePath(file.getAbsolutePath());

		try {
			final UteConfigurationDocument document = UteConfigurationDocument.Factory.parse(file);
			final UteConfigurationType configurationType = document.getUteConfiguration();

			parsePreferences(configurationType, configuration);
			parseProperties(configurationType, configuration);
			parseLocations(configurationType, configuration);
			parseJobs(configurationType, configuration);

			ConfigurationUtils.validateConfiguration(configuration);
		}
		catch (final XmlException e) {
			LOGGER.error("XmlException caught while parsing configuration file", e);

			throw new ConfigurationException("Error occurred loading configuration file", e);
		}
		catch (final IOException e) {
			LOGGER.error("IOException caught while parsing configuration file", e);

			throw new ConfigurationException("Error occurred loading configuration file", e);
		}

		LOGGER.debug("{} returning {}", prefix, configuration);

		return configuration;
	}
}
