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

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

/**
 * The Class ResourcesUtils.
 */
public final class ResourcesUtils {

	/** The Constant applicationAttributions. */
	private static final String applicationAttributions;

	/** The Constant applicationName. */
	private static final String applicationName;

	/** The Constant applicationVersion. */
	private static final String applicationVersion;

	/** The Constant bundle. */
	private static final ResourceBundle bundle;

	/**
	 * Instantiates a new resources utils.
	 */
	static {
		bundle = ResourceBundle.getBundle("resources");

		applicationName = bundle.getString("application.name");
		applicationVersion = bundle.getString("application.version");
		applicationAttributions = bundle.getString("application.attributions");
	}

	/**
	 * Gets the application attributions.
	 * 
	 * @return the application attributions
	 */
	public static String getApplicationAttributions() {
		return applicationAttributions;
	}

	/**
	 * Gets the application name.
	 * 
	 * @return the application name
	 */
	public static String getApplicationName() {
		return applicationName;
	}

	/**
	 * Gets the application version.
	 * 
	 * @return the application version
	 */
	public static String getApplicationVersion() {
		return applicationVersion;
	}

	/**
	 * Gets the character.
	 * 
	 * @param key the key
	 * @return the character
	 */
	public static Character getCharacter(final String key) {
		final String string = getString(key);

		return string == null ? null : string.charAt(0);
	}

	/**
	 * Gets the string.
	 * 
	 * @param key the key
	 * @return the string
	 */
	public static String getString(final String key) {
		return StringUtils.trimToNull(bundle.getString(key));
	}

	/**
	 * Instantiates a new resources utils.
	 */
	private ResourcesUtils() {
		throw new AssertionError();
	}
}
