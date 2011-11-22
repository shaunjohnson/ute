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
package net.lmxm.ute.event;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.EventObject;

import org.apache.commons.lang.StringUtils;

/**
 * The Class StatusChangeEvent.
 */
public final class StatusChangeEvent extends EventObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 911482339505974104L;

	/** The event type. */
	private final StatusChangeEventType eventType;

	/** The message. */
	private final String message;

	/**
	 * Instantiates a new status change event.
	 * 
	 * @param source the source
	 * @param eventType the event type
	 * @param message the message
	 */
	public StatusChangeEvent(final Object source, final StatusChangeEventType eventType, final String message) {
		super(source);

		checkNotNull(eventType, "Event type may not be null");
		checkNotNull(message, "Message may not be null");
		checkArgument(StringUtils.isNotBlank(message), "Message may not be blank");

		this.eventType = eventType;
		this.message = message;
	}

	/**
	 * Gets the event type.
	 * 
	 * @return the event type
	 */
	public StatusChangeEventType getEventType() {
		return eventType;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	@Override
	public String toString() {
		return eventType.toString() + " : " + message;
	}
}
