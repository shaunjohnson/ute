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
package net.lmxm.ute.gui.utils;

import net.lmxm.ute.gui.GuiContants;

import java.awt.Dimension;
import java.awt.Point;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Retrieves and persists user settings stored using java Preferences.
 */
public final class UserPreferences {

	/** The Constant JOBS_SPLIT_PANE_DIVIDER_LOCATION. */
	private static final String JOBS_SPLIT_PANE_DIVIDER_LOCATION = "jobsSplitPaneDividerLocation";

	/** The Constant LAST_FILE_EDITED_PATH. */
	private static final String LAST_FILE_EDITED_PATH = "lastFileEditedPath";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserPreferences.class);

	/** The Constant MAIN_SPLIT_PANE_DIVIDER_LOCATION. */
	private static final String MAIN_SPLIT_PANE_DIVIDER_LOCATION = "mainSplitPaneDividerLocation";

	/** Handle to the Preferences node for the application. */
	private static Preferences prefs = Preferences.userNodeForPackage(UserPreferences.class);

	/** The Constant WINDOW_LOCATION_X. */
	private static final String WINDOW_LOCATION_X = "windowLocationX";

	/** The Constant WINDOW_LOCATION_Y. */
	private static final String WINDOW_LOCATION_Y = "windowLocationY";

	/** The Constant WINDOW_SIZE_HEIGHT. */
	private static final String WINDOW_SIZE_HEIGHT = "windowSizeHeight";

	/** The Constant WINDOW_SIZE_WIDTH. */
	private static final String WINDOW_SIZE_WIDTH = "windowSizeWidth";

	/** The Constant WINDOW_STATE. */
	private static final String WINDOW_STATE = "windowState";

	/**
	 * Gets the integer preference.
	 *
	 * @param preferenceName the preference name
	 * @param defaultValue the default value
	 * @return the integer
	 */
	private static int getIntegerPreference(final String preferenceName, final int defaultValue) {
		final String prefix = "getIntegerPreference() :";

		LOGGER.debug("{} entered, preferenceName={}, defaultValue=" + defaultValue, prefix, preferenceName);

		int value = defaultValue;

		final String valueString = prefs.get(preferenceName, null);

		if (StringUtils.isBlank(valueString)) {
			LOGGER.debug("{} preference {} is blank, returning default value", prefix, preferenceName);

			value = defaultValue;
		}
		else {
			try {
				value = Integer.valueOf(valueString);

				LOGGER.debug("{} preference value is an int", prefix);
			}
			catch (final NumberFormatException e) {
				LOGGER.debug("{} preference value \"{}\" is not an int, returning default value", prefix, valueString);

				value = defaultValue;
			}
		}

		LOGGER.debug("{} returning {}", prefix, value);

		return value;
	}

	/**
	 * Gets the jobs split pane divider location.
	 *
	 * @return the jobs split pane divider location
	 */
	public static int getJobsSplitPaneDividerLocation() {
		final String prefix = "getJobsSplitPaneDividerLocation() :";

		LOGGER.debug("{} entered", prefix);

		final int value = getIntegerPreference(JOBS_SPLIT_PANE_DIVIDER_LOCATION,
				GuiContants.DEFAULT_JOBS_SPLIT_PANE_DIVIDER_LOCATION);

		LOGGER.debug("{} returning {}", prefix, value);

		return value;
	}

	/**
	 * Retrieves "last edited file path" setting from Preferences.
	 * 
	 * @return Last file edited path
	 */
	public static String getLastFileEditedPath() {
		final String prefix = "getLastFileEditedPath() :";

		LOGGER.debug("{} entered", prefix);

		final String value = prefs.get(LAST_FILE_EDITED_PATH, null);

		LOGGER.debug("{} returning {}", prefix, value);

		return value;
	}

	/**
	 * Gets the main split pane divider location.
	 *
	 * @return the main split pane divider location
	 */
	public static int getMainSplitPaneDividerLocation() {
		final String prefix = "getMainSplitPaneDividerLocation() :";

		LOGGER.debug("{} entered", prefix);

		final int value = getIntegerPreference(MAIN_SPLIT_PANE_DIVIDER_LOCATION,
				GuiContants.DEFAULT_MAIN_SPLIT_PANE_DIVIDER_LOCATION);

		LOGGER.debug("{} returning {}", prefix, value);

		return value;
	}

	/**
	 * Gets the window location.
	 *
	 * @return the window location
	 */
	public static Point getWindowLocation() {
		final String prefix = "getWindowLocation() :";

		LOGGER.debug("{} entered", prefix);

		final int x = getIntegerPreference(WINDOW_LOCATION_X, -1);
		final int y = getIntegerPreference(WINDOW_LOCATION_Y, -1);

		if (x < 0 || y < 0) {
			LOGGER.debug("{} x or y is invalid, returning null", prefix);

			return null;
		}

		final Point value = new Point(x, y);

		LOGGER.debug("{} returning {}", prefix, value);

		return value;
	}

	/**
	 * Gets the window size.
	 *
	 * @return the window size
	 */
	public static Dimension getWindowSize() {
		final String prefix = "getWindowSize() :";

		LOGGER.debug("{} entered", prefix);

		final int height = getIntegerPreference(WINDOW_SIZE_HEIGHT, GuiContants.DEFAULT_WINDOW_HEIGHT);
		final int width = getIntegerPreference(WINDOW_SIZE_WIDTH, GuiContants.DEFAULT_WINDOW_WIDTH);

		final Dimension value = new Dimension(width, height);

		LOGGER.debug("{} returning {}", prefix, value);

		return value;
	}

	/**
	 * Gets the window state.
	 *
	 * @return the window state
	 */
	public static Integer getWindowState() {
		return getIntegerPreference(WINDOW_STATE, JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Removes the last file edited path.
	 */
	public static void removeLastFileEditedPath() {
		final String prefix = "removeLastFileEditedPath() :";

		LOGGER.debug("{} entered", prefix);

		try {
			prefs.remove(LAST_FILE_EDITED_PATH);
		}
		catch (final IllegalStateException e) {
			LOGGER.warn("{} IllegalStateException caught removing prererence", prefix);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the integer preference.
	 *
	 * @param preferenceName the preference name
	 * @param value the value
	 */
	private static void setIntegerPreference(final String preferenceName, final int value) {
		final String prefix = "setIntegerPreference() :";

		LOGGER.debug("{} entered, preferenceName={}, value=" + value, prefix, preferenceName);

		prefs.put(preferenceName, Integer.toString(value));

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the jobs split pane divider location.
	 *
	 * @param dividerLocation the new jobs split pane divider location
	 */
	public static void setJobsSplitPaneDividerLocation(final int dividerLocation) {
		final String prefix = "setJobsSplitPaneDividerLocation() :";

		LOGGER.debug("{} entered, dividerLocation={}", prefix, dividerLocation);

		setIntegerPreference(JOBS_SPLIT_PANE_DIVIDER_LOCATION, dividerLocation);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Persists "open last edited file" setting to Preferences.
	 *
	 * @param path the new last file edited path
	 */
	public static void setLastFileEditedPath(final String path) {
		final String prefix = "setLastFileEditedPath() :";

		LOGGER.debug("{} entered, path={}", prefix, path);

		prefs.put(LAST_FILE_EDITED_PATH, path);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the main split pane divider location.
	 *
	 * @param dividerLocation the new main split pane divider location
	 */
	public static void setMainSplitPaneDividerLocation(final int dividerLocation) {
		final String prefix = "setMainSplitPaneDividerLocation() :";

		LOGGER.debug("{} entered, dividerLocation={}", prefix, dividerLocation);

		setIntegerPreference(MAIN_SPLIT_PANE_DIVIDER_LOCATION, dividerLocation);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the window location.
	 *
	 * @param location the new window location
	 */
	public static void setWindowLocation(final Point location) {
		final String prefix = "setWindowLocation() :";

		LOGGER.debug("{} entered, location={}", prefix, location);

		if (location == null) {
			LOGGER.debug("{} location is null, returning without setting", prefix);

			return;
		}

		setIntegerPreference(WINDOW_LOCATION_X, (int)location.getX());
		setIntegerPreference(WINDOW_LOCATION_Y, (int)location.getY());

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the window size.
	 *
	 * @param windowSize the new window size
	 */
	public static void setWindowSize(final Dimension windowSize) {
		final String prefix = "setWindowSize() :";

		LOGGER.debug("{} entered, windowSize={}", prefix, windowSize);

		if (windowSize == null) {
			LOGGER.debug("{} windowSize is null, returning without setting", prefix);

			return;
		}

		setIntegerPreference(WINDOW_SIZE_HEIGHT, (int)windowSize.getHeight());
		setIntegerPreference(WINDOW_SIZE_WIDTH, (int)windowSize.getWidth());

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the window state.
	 *
	 * @param windowState the new window state
	 */
	public static void setWindowState(final int windowState) {
		final String prefix = "setWindowState() :";

		LOGGER.debug("{} entered, windowState={}", prefix, windowState);

		setIntegerPreference(WINDOW_STATE, windowState);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Instantiates a new user preferences.
	 */
	private UserPreferences() {
		throw new AssertionError();
	}
}
