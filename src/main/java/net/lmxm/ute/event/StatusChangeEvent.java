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

import net.lmxm.ute.beans.jobs.Job;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Class StatusChangeEvent.
 */
public final class StatusChangeEvent {
    /**
     * The event type.
     */
    private final StatusChangeEventType eventType;

    /**
     * The associated job object.
     */
    private final Job job;

    /**
     * The message.
     */
    private final String message;

    /**
     * Instantiates a new status change event.
     *
     * @param eventType the event type
     * @param message   the message
     */
    protected StatusChangeEvent(final StatusChangeEventType eventType, final Job job, final String message) {
        this.eventType = checkNotNull(eventType, "Event type may not be null");
        this.job = checkNotNull(job, "Job may not be null");
        this.message = checkNotNull(message, "Message may not be null");

        checkArgument(StringUtils.isNotBlank(message), "Message may not be blank");
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
     * Gets the associated job.
     *
     * @return The associated job object
     */
    public Job getJob() {
        return job;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return eventType.toString() + " : " + job.getId() + " : " + message;
    }
}
