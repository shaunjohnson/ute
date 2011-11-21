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
package net.lmxm.ute.beans.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.DomainBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.PropertiesHolder;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.utils.DomainBeanUtils;

import org.apache.commons.collections.CollectionUtils;

/**
 * The Class Configuration.
 */
public final class Configuration implements DomainBean, PropertiesHolder {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 351774939048448102L;

	/** The configuration file. */
	private File configurationFile = null;

	/** The file system locations. */
	private List<FileSystemLocation> fileSystemLocations;

	/** The http locations. */
	private List<HttpLocation> httpLocations;

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
		httpLocations = new ArrayList<HttpLocation>();
		preferences = new ArrayList<Preference>();
		properties = new ArrayList<Property>();
		subversionRepositoryLocations = new ArrayList<SubversionRepositoryLocation>();
	}

	/**
	 * Gets the configuration file.
	 * 
	 * @return the configuration file
	 */
	public File getConfigurationFile() {
		return configurationFile;
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
		return httpLocations;
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

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return configurationFile == null && DomainBeanUtils.isEmpty(fileSystemLocations)
				&& DomainBeanUtils.isEmpty(httpLocations) && DomainBeanUtils.isEmpty(jobs)
				&& DomainBeanUtils.isEmpty(preferences) && DomainBeanUtils.isEmpty(properties)
				&& DomainBeanUtils.isEmpty(subversionRepositoryLocations);
	}

	/**
	 * Checks if is locations empty.
	 * 
	 * @return true, if is locations empty
	 */
	public boolean isLocationsEmpty() {
		return CollectionUtils.isEmpty(fileSystemLocations) && CollectionUtils.isEmpty(httpLocations)
				&& CollectionUtils.isEmpty(subversionRepositoryLocations);
	}

	/**
	 * Removes the empty objects.
	 */
	@Override
	public void removeEmptyObjects() {
		DomainBeanUtils.removeEmptyObjects(fileSystemLocations);
		DomainBeanUtils.removeEmptyObjects(httpLocations);
		DomainBeanUtils.removeEmptyObjects(jobs);
		DomainBeanUtils.removeEmptyObjects(preferences);
		DomainBeanUtils.removeEmptyObjects(properties);
		DomainBeanUtils.removeEmptyObjects(subversionRepositoryLocations);
	}

	/**
	 * Sets the configuration file.
	 * 
	 * @param configurationFile the new configuration file
	 */
	public void setConfigurationFile(final File configurationFile) {
		this.configurationFile = configurationFile;
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
		this.httpLocations = httpLocations;
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
