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
package net.lmxm.ute.console;

import com.google.common.eventbus.Subscribe;
import net.lmxm.ute.event.StatusChangeEvent;
import net.lmxm.ute.event.StatusChangeEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

/**
 * The listener interface for receiving consoleStatusChange events. The class that is interested in processing a
 * consoleStatusChange event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addConsoleStatusChangeListener<code> method. When
 * the consoleStatusChange event occurs, that object's appropriate
 * method is invoked.
 *
 * @see StatusChangeEvent
 */
public final class ConsoleStatusChangeListener {

    /**
     * Logger handle
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleStatusChangeListener.class);

    /**
     * Handle to standard error print stream.
     */
    private static final PrintStream STDERR = System.err;

    /**
     * Handle to standard output print stream.
     */
    private static final PrintStream STDOUT = System.out;

    /*
     * (non-Javadoc)
     * @see net.lmxm.ute.listeners.StatusChangeListener#statusChange(net.lmxm.ute.listeners.StatusChangeEvent)
     */
    @Subscribe
    public void handleStatusChange(final StatusChangeEvent changeEvent) {
        final String logPrefix = "statusChange() :";
        final StatusChangeEventType eventType = changeEvent.getEventType();

        if (eventType == StatusChangeEventType.ERROR) {
            STDERR.println(changeEvent.getMessage());
        }
        else if (eventType == StatusChangeEventType.FATAL) {
            STDERR.println(changeEvent.getMessage());
        }
        else if (eventType == StatusChangeEventType.HEADING) {
            STDOUT.println("== " + changeEvent.getMessage() + " ==");
        }
        else if (eventType == StatusChangeEventType.IMPORTANT) {
            STDOUT.println(">>>> " + changeEvent.getMessage() + " <<<<");
        }
        else if (eventType == StatusChangeEventType.INFO) {
            STDOUT.println(changeEvent.getMessage());
        }
        else {
            LOGGER.error("{} unsupported event type: {}", logPrefix, eventType);
        }
    }
}
