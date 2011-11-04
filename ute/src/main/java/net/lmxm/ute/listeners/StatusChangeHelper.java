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
package net.lmxm.ute.listeners;

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.resources.ResourcesUtils;

/**
 * The Class StatusChangeHelper.
 */
public class StatusChangeHelper {
	/** The status change listener. */
	private final List<StatusChangeListener> statusChangeListeners = new ArrayList<StatusChangeListener>();

	/**
	 * Adds the status change listener.
	 * 
	 * @param statusChangeListener the status change listener
	 */
	public final void addStatusChangeListener(final StatusChangeListener statusChangeListener) {
		statusChangeListeners.add(statusChangeListener);
	}

	/**
	 * Error.
	 * 
	 * @param source the source
	 * @param message the message
	 */
	public final void error(final Object source, final StatusChangeMessage statusChangeMessage,
			final Object... arguments) {
		final String message = formatMessage(statusChangeMessage, arguments);

		for (final StatusChangeListener statusChangeListener : statusChangeListeners) {
			statusChangeListener.statusChange(new StatusChangeEvent(source, StatusChangeEventType.ERROR, message));
		}
	}

	/**
	 * Fatal.
	 * 
	 * @param source the source
	 * @param message the message
	 */
	public final void fatal(final Object source, final StatusChangeMessage statusChangeMessage,
			final Object... arguments) {
		final String message = formatMessage(statusChangeMessage, arguments);

		for (final StatusChangeListener statusChangeListener : statusChangeListeners) {
			statusChangeListener.statusChange(new StatusChangeEvent(source, StatusChangeEventType.FATAL, message));
		}
	}

	/**
	 * Format message.
	 * 
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 * @return the string
	 */
	private String formatMessage(final StatusChangeMessage statusChangeMessage, final Object[] arguments) {
		final String format = ResourcesUtils.getString("STATUS_CHANGE_MESSAGE." + statusChangeMessage.name());

		return String.format(format, arguments);
	}

	/**
	 * Heading.
	 * 
	 * @param source the source
	 * @param message the message
	 */
	public final void heading(final Object source, final StatusChangeMessage statusChangeMessage,
			final Object... arguments) {
		final String message = formatMessage(statusChangeMessage, arguments);

		for (final StatusChangeListener statusChangeListener : statusChangeListeners) {
			statusChangeListener.statusChange(new StatusChangeEvent(source, StatusChangeEventType.HEADING, message));
		}
	}

	/**
	 * Important.
	 * 
	 * @param source the source
	 * @param message the message
	 */
	public final void important(final Object source, final StatusChangeMessage statusChangeMessage,
			final Object... arguments) {
		final String message = formatMessage(statusChangeMessage, arguments);

		for (final StatusChangeListener statusChangeListener : statusChangeListeners) {
			statusChangeListener.statusChange(new StatusChangeEvent(source, StatusChangeEventType.IMPORTANT, message));
		}
	}

	/**
	 * Info.
	 * 
	 * @param source the source
	 * @param message the message
	 */
	public final void info(final Object source, final StatusChangeMessage statusChangeMessage,
			final Object... arguments) {
		final String message = formatMessage(statusChangeMessage, arguments);

		for (final StatusChangeListener statusChangeListener : statusChangeListeners) {
			statusChangeListener.statusChange(new StatusChangeEvent(source, StatusChangeEventType.INFO, message));
		}
	}
}
