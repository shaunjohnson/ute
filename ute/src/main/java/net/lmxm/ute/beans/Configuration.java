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
package net.lmxm.ute.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.FilesTask;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.beans.tasks.Task;

/**
 * The Class Configuration.
 */
public final class Configuration implements DomainBean, PropertiesHolder {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 351774939048448102L;

	/** The absolute path. */
	private String absolutePath;

	/** The file system locations. */
	private List<FileSystemLocation> fileSystemLocations;

	/** The Http locations. */
	private List<HttpLocation> HttpLocations;

	/** The jobs. */
	private List<Job> jobs;

	/** The preferences. */
	private List<Preference> preferences;

	/** The properties. */
	private List<Property> properties;

	/** The subversion repository locations. */
	private List<SubversionRepositoryLocation> subversionRepositoryLocations;

	/**
	 * Instantiates a new configuration.
	 */
	public Configuration() {
		super();

		jobs = new ArrayList<Job>();
		fileSystemLocations = new ArrayList<FileSystemLocation>();
		HttpLocations = new ArrayList<HttpLocation>();
		preferences = new ArrayList<Preference>();
		properties = new ArrayList<Property>();
		subversionRepositoryLocations = new ArrayList<SubversionRepositoryLocation>();
	}

	/**
	 * Gets the absolute path.
	 * 
	 * @return the absolute path
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}

	/**
	 * Gets the file system locations.
	 * 
	 * @return the file system locations
	 */
	public List<FileSystemLocation> getFileSystemLocations() {
		return fileSystemLocations;
	}

	/**
	 * Gets the http locations.
	 * 
	 * @return the http locations
	 */
	public List<HttpLocation> getHttpLocations() {
		return HttpLocations;
	}

	/**
	 * Gets the jobs.
	 * 
	 * @return the jobs
	 */
	public List<Job> getJobs() {
		return jobs;
	}

	/**
	 * Gets the preferences.
	 * 
	 * @return the preferences
	 */
	@Override
	public List<Preference> getPreferences() {
		return preferences;
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	@Override
	public List<Property> getProperties() {
		return properties;
	}

	/**
	 * Gets the subversion repository locations.
	 * 
	 * @return the subversion repository locations
	 */
	public List<SubversionRepositoryLocation> getSubversionRepositoryLocations() {
		return subversionRepositoryLocations;
	}

	/**
	 * Removes the empty objects.
	 */
	public void removeEmptyObjects() {
		for (final Job job : jobs) {
			for (final Task task : job.getTasks()) {
				if (task instanceof FilesTask) {
					final Iterator<FileReference> iterator = ((FilesTask) task).getFiles().iterator();

					while (iterator.hasNext()) {
						if (iterator.next().isEmpty()) {
							iterator.remove();
						}
					}
				}

				if (task instanceof FindReplaceTask) {
					final Iterator<FindReplacePattern> iterator = ((FindReplaceTask) task).getPatterns().iterator();

					while (iterator.hasNext()) {
						if (iterator.next().isEmpty()) {
							iterator.remove();
						}
					}
				}
			}
		}
	}

	/**
	 * Sets the absolute path.
	 * 
	 * @param absolutePath the new absolute path
	 */
	public void setAbsolutePath(final String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * Sets the file system locations.
	 * 
	 * @param fileSystemLocations the new file system locations
	 */
	public void setFileSystemLocations(final List<FileSystemLocation> fileSystemLocations) {
		this.fileSystemLocations = fileSystemLocations;
	}

	/**
	 * Sets the http locations.
	 * 
	 * @param httpLocations the new http locations
	 */
	public void setHttpLocations(final List<HttpLocation> httpLocations) {
		HttpLocations = httpLocations;
	}

	/**
	 * Sets the jobs.
	 * 
	 * @param jobs the new jobs
	 */
	public void setJobs(final List<Job> jobs) {
		this.jobs = jobs;
	}

	/**
	 * Sets the preferences.
	 * 
	 * @param preferences the new preferences
	 */
	public void setPreferences(final List<Preference> preferences) {
		this.preferences = preferences;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties the new properties
	 */
	public void setProperties(final List<Property> properties) {
		this.properties = properties;
	}

	/**
	 * Sets the subversion repository locations.
	 * 
	 * @param subversionRepositoryLocations the new subversion repository locations
	 */
	public void setSubversionRepositoryLocations(final List<SubversionRepositoryLocation> subversionRepositoryLocations) {
		this.subversionRepositoryLocations = subversionRepositoryLocations;
	}
}
