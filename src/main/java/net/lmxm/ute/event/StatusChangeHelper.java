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
package net.lmxm.ute.event;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.StatusChangeMessageResourceType;

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
		checkNotNull(statusChangeListener, "Status change listener is null");

		statusChangeListeners.add(statusChangeListener);
	}

	/**
	 * Error.
	 * 
	 * @param source the source
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 */
	public final void error(final Object source, final StatusChangeMessageResourceType statusChangeMessage,
			final Object... arguments) {
		fireEvent(source, StatusChangeEventType.ERROR, statusChangeMessage, arguments);
	}

	/**
	 * Fatal.
	 * 
	 * @param source the source
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 */
	public final void fatal(final Object source, final StatusChangeMessageResourceType statusChangeMessage,
			final Object... arguments) {
		fireEvent(source, StatusChangeEventType.FATAL, statusChangeMessage, arguments);
	}

	/**
	 * Fire event.
	 * 
	 * @param source the source
	 * @param eventType the event type
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 */
	protected void fireEvent(final Object source, final StatusChangeEventType eventType,
			final StatusChangeMessageResourceType statusChangeMessage, final Object... arguments) {
		checkNotNull(source, "Source is null");
		checkNotNull(eventType, "Status change event type is null");
		checkNotNull(statusChangeMessage, "Status change message resource type is null");

		final String message = formatMessage(statusChangeMessage, arguments);

		for (final StatusChangeListener statusChangeListener : statusChangeListeners) {
			statusChangeListener.statusChange(new StatusChangeEvent(source, eventType, message));
		}
	}

	/**
	 * Format message.
	 * 
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 * @return the string
	 */
	private String formatMessage(final StatusChangeMessageResourceType statusChangeMessage, final Object[] arguments) {
		return String.format(ResourcesUtils.getResourceMessage(statusChangeMessage), arguments);
	}

	/**
	 * Heading.
	 * 
	 * @param source the source
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 */
	public final void heading(final Object source, final StatusChangeMessageResourceType statusChangeMessage,
			final Object... arguments) {
		fireEvent(source, StatusChangeEventType.HEADING, statusChangeMessage, arguments);
	}

	/**
	 * Important.
	 * 
	 * @param source the source
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 */
	public final void important(final Object source, final StatusChangeMessageResourceType statusChangeMessage,
			final Object... arguments) {
		fireEvent(source, StatusChangeEventType.IMPORTANT, statusChangeMessage, arguments);
	}

	/**
	 * Info.
	 * 
	 * @param source the source
	 * @param statusChangeMessage the status change message
	 * @param arguments the arguments
	 */
	public final void info(final Object source, final StatusChangeMessageResourceType statusChangeMessage,
			final Object... arguments) {
		fireEvent(source, StatusChangeEventType.INFO, statusChangeMessage, arguments);
	}
}
