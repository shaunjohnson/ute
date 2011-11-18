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
package net.lmxm.ute.preferences;

import java.awt.Dimension;
import java.awt.Point;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AbstractPreferences.
 */
public abstract class AbstractPreferences {

	/** The Constant HEIGHT_SUFFIX. */
	private static final String HEIGHT_SUFFIX = "_HEIGHT";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPreferences.class);

	/** The Constant WIDTH_SUFFIX. */
	private static final String WIDTH_SUFFIX = "_WIDTH";

	/** The Constant X_SUFFIX. */
	private static final String X_SUFFIX = "_X";

	/** The Constant Y_SUFFIX. */
	private static final String Y_SUFFIX = "_Y";

	/** The preferences. */
	private final Preferences preferences;

	/**
	 * Instantiates a new abstract preferences.
	 * 
	 * @param userNode the user node
	 */
	public AbstractPreferences(final Class<?> userNode) {
		preferences = Preferences.userNodeForPackage(userNode);
	}

	/**
	 * Do all keys exist.
	 * 
	 * @param keys the keys
	 * @return true, if successful
	 */
	public final boolean doAllKeysExist(final String... keys) {
		boolean allKeysExist = true;

		for (final String key : keys) {
			if (preferences.get(key, null) == null) {
				allKeysExist = false;
				break;
			}
		}

		return allKeysExist;
	}

	/**
	 * Gets the all keys.
	 * 
	 * @return the all keys
	 */
	protected final String[] getAllKeys() {
		final String prefix = "getDimension() :";

		LOGGER.debug("{} entered", prefix);

		String[] keys;

		try {
			keys = preferences.keys();
		}
		catch (final BackingStoreException e) {
			LOGGER.warn("{} IllegalStateException caught loading preference keys", prefix);

			keys = new String[0];
		}

		return keys;
	}

	/**
	 * Gets the dimension.
	 * 
	 * @param key the key
	 * @return the dimension
	 */
	protected final Dimension getDimension(final String key) {
		final String prefix = "getDimension() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		if (!doAllKeysExist(key + HEIGHT_SUFFIX, key + WIDTH_SUFFIX)) {
			LOGGER.debug("{} height or width is missing, returning null", prefix);

			return null;
		}

		final int height = getInt(key + HEIGHT_SUFFIX, 0);
		final int width = getInt(key + WIDTH_SUFFIX, 0);

		final Dimension dimension = new Dimension(width, height);

		LOGGER.debug("{} returning {}", prefix, dimension);

		return dimension;
	}

	/**
	 * Gets the int.
	 * 
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the int
	 */
	protected final int getInt(final String key, final int defaultValue) {
		final String prefix = "getInt() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		final int intValue = preferences.getInt(key, defaultValue);

		LOGGER.debug("{} returning {}", prefix, intValue);

		return intValue;
	}

	/**
	 * Gets the point.
	 * 
	 * @param key the key
	 * @return the point
	 */
	protected final Point getPoint(final String key) {
		final String prefix = "getPoint() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		if (!doAllKeysExist(key + X_SUFFIX, key + Y_SUFFIX)) {
			LOGGER.debug("{} X or Y is missing, returning null", prefix);

			return null;
		}

		final int x = getInt(key + X_SUFFIX, 0);
		final int y = getInt(key + Y_SUFFIX, 0);

		if (x < 0 || y < 0) {
			LOGGER.debug("{} x or y is invalid, returning null", prefix);

			return null;
		}

		final Point point = new Point(x, y);

		LOGGER.debug("{} returning {}", prefix, point);

		return point;
	}

	/**
	 * Gets the string.
	 * 
	 * @param key the key
	 * @return the string
	 */
	protected final String getString(final String key) {
		final String prefix = "getString() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		final String value = preferences.get(key, null);

		LOGGER.debug("{} returning {}", prefix, value);

		return value;
	}

	/**
	 * Checks for preference.
	 * 
	 * @param key the key
	 * @return true, if successful
	 */
	protected final boolean hasPreference(final String key) {
		final String prefix = "hasPreference() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		final boolean hasPreference = StringUtils.isNotBlank(getString(key));

		LOGGER.debug("{} returning {}", prefix, hasPreference);

		return hasPreference;
	}

	/**
	 * Removes the all preferences.
	 */
	public final void removeAllPreferences() {
		for (final String key : getAllKeys()) {
			removePreference(key);
		}
	}

	/**
	 * Removes the preference.
	 * 
	 * @param key the key
	 */
	protected final void removePreference(final String key) {
		final String prefix = "removePreference() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		try {
			preferences.remove(key);
		}
		catch (final IllegalStateException e) {
			LOGGER.warn("{} IllegalStateException caught removing preference", prefix);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the dimension.
	 * 
	 * @param key the key
	 * @param dimension the dimension
	 */
	protected final void setDimension(final String key, final Dimension dimension) {
		final String prefix = "setDimension() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		if (dimension == null) {
			LOGGER.debug("{} dimension is null, returning without setting", prefix);

			return;
		}

		setInt(key + HEIGHT_SUFFIX, (int) dimension.getHeight());
		setInt(key + WIDTH_SUFFIX, (int) dimension.getWidth());

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the int.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	protected final void setInt(final String key, final int value) {
		final String prefix = "setInt() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		preferences.putInt(key, value);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the point.
	 * 
	 * @param key the key
	 * @param point the point
	 */
	protected final void setPoint(final String key, final Point point) {
		final String prefix = "setPoint() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		if (point == null) {
			LOGGER.debug("{} point is null, returning without setting", prefix);

			return;
		}

		setInt(key + X_SUFFIX, (int) point.getX());
		setInt(key + Y_SUFFIX, (int) point.getY());

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the string.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	protected final void setString(final String key, final String value) {
		final String prefix = "setString() :";

		LOGGER.debug("{} entered, key={}", prefix, key);

		preferences.put(key, value);

		LOGGER.debug("{} leaving", prefix);
	}
}
