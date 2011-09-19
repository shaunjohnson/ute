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
package net.lmxm.ute.subversion.utils;

import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeEventType;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNCancelException;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.ISVNEventHandler;
import org.tmatesoft.svn.core.wc.SVNEvent;
import org.tmatesoft.svn.core.wc.SVNEventAction;
import org.tmatesoft.svn.core.wc.SVNStatusType;

/**
 * The Class EventHandler.
 */
public final class EventHandler implements ISVNEventHandler {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EventHandler.class);

	/** The listener. */
	private final StatusChangeListener listener;

	/** The source. */
	private final Object source;

	/**
	 * Instantiates a new event handler.
	 * 
	 * @param source the source
	 * @param listener the listener
	 */
	public EventHandler(final Object source, final StatusChangeListener listener) {
		super();

		this.source = source;
		this.listener = listener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tmatesoft.svn.core.ISVNCanceller#checkCancelled()
	 */
	@Override
	public void checkCancelled() throws SVNCancelException {

	}

	/**
	 * Fire event.
	 *
	 * @param eventType the event type
	 * @param message the message
	 */
	private void fireEvent(final StatusChangeEventType eventType, final String message) {
		listener.statusChange(new StatusChangeEvent(source, eventType, message));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tmatesoft.svn.core.wc.ISVNEventHandler#handleEvent(org.tmatesoft.svn.core.wc.SVNEvent, double)
	 */
	@Override
	public void handleEvent(final SVNEvent event, final double progress) throws SVNException {
		final String prefix = "handleEvent() :";

		LOGGER.debug("{} entered, event={}", prefix, event);

		final SVNEventAction action = event.getAction();

		LOGGER.debug("{} action={}", prefix, action);

		if (action == SVNEventAction.UPDATE_ADD) {
			handlePathChange(event, "A");
		}
		else if (action == SVNEventAction.UPDATE_DELETE) {
			handlePathChange(event, "D");
		}
		else if (action == SVNEventAction.UPDATE_NONE) {
			fireEvent(StatusChangeEventType.INFO, "-     " + event.getFile());
		}
		else if (action == SVNEventAction.UPDATE_UPDATE) {
			handleUpdateEvent(event);
		}
		else if (action == SVNEventAction.UPDATE_EXTERNAL) {
			fireEvent(StatusChangeEventType.INFO, "Fetching external item into '" + event.getFile().getAbsolutePath()
					+ "'");
			fireEvent(StatusChangeEventType.INFO, "External at revision " + event.getRevision());
		}
		else if (action == SVNEventAction.UPDATE_COMPLETED) {
			fireEvent(StatusChangeEventType.INFO, "At revision " + event.getRevision());
		}
		else if (action == SVNEventAction.ADD) {
			fireEvent(StatusChangeEventType.INFO, "A     " + event.getFile());
		}
		else if (action == SVNEventAction.DELETE) {
			fireEvent(StatusChangeEventType.INFO, "D     " + event.getFile());
		}
		else if (action == SVNEventAction.LOCKED) {
			fireEvent(StatusChangeEventType.INFO, "L     " + event.getFile());
		}
		else if (action == SVNEventAction.LOCK_FAILED) {
			fireEvent(StatusChangeEventType.ERROR, "failed to lock    " + event.getFile());
		}
		else if (action == SVNEventAction.FAILED_EXTERNAL) {
			fireEvent(StatusChangeEventType.ERROR, "failed to get external    " + event.getFile());
		}
		else {
			LOGGER.error("{} unsupported action {}", prefix, action);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Handle path change.
	 *
	 * @param event the event
	 * @param pathChangeType the path change type
	 */
	private void handlePathChange(final SVNEvent event, final String pathChangeType) {
		final String prefix = "handlePathChange() :";

		LOGGER.debug("{} entered, event={}, pathChangeType=" + pathChangeType, prefix, event);

		final SVNStatusType propertiesStatus = event.getPropertiesStatus();

		String propertiesChangeType = " ";

		if (propertiesStatus == SVNStatusType.CHANGED) {
			propertiesChangeType = "U";
		}
		else if (propertiesStatus == SVNStatusType.CONFLICTED) {
			propertiesChangeType = "C";
		}
		else if (propertiesStatus == SVNStatusType.MERGED) {
			propertiesChangeType = "G";
		}

		// Get item's lock status
		String lockLabel = " ";
		final SVNStatusType lockType = event.getLockStatus();

		if (lockType == SVNStatusType.LOCK_UNLOCKED) {
			LOGGER.debug("{} item is locked", prefix);

			lockLabel = "B";
		}

		fireEvent(StatusChangeEventType.INFO,
				pathChangeType + propertiesChangeType + lockLabel + "       " + event.getFile());

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Handle update event.
	 *
	 * @param event the event
	 */
	private void handleUpdateEvent(final SVNEvent event) {
		final String prefix = "handleUpdateEvent() :";

		LOGGER.debug("{} entered, event={}", prefix, event);

		final SVNStatusType contentsStatus = event.getContentsStatus();

		LOGGER.debug("{} contentsStatus={}", prefix, contentsStatus);

		if (contentsStatus == SVNStatusType.CHANGED) {
			handlePathChange(event, "U");
		}
		else if (contentsStatus == SVNStatusType.CONFLICTED) {
			handlePathChange(event, "C");
		}
		else if (contentsStatus == SVNStatusType.MERGED) {
			handlePathChange(event, "G");
		}
		else {
			LOGGER.error("{} unsupported contentsStatus {}", prefix, contentsStatus);
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
