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
package net.lmxm.ute.executers.tasks.subversion;

import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.SubversionEventResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNCancelException;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.ISVNEventHandler;
import org.tmatesoft.svn.core.wc.SVNEvent;
import org.tmatesoft.svn.core.wc.SVNEventAction;
import org.tmatesoft.svn.core.wc.SVNStatusType;

import static net.lmxm.ute.resources.types.StatusChangeMessageResourceType.*;

/**
 * The Class EventHandler.
 */
public final class EventHandler implements ISVNEventHandler {

	/**
	 * The Enum UpdateChangeType.
	 */
	private enum UpdateChangeType {

		/** The ADDED. */
		ADDED(ResourcesUtils.getResourceText(SubversionEventResourceType.STATUS_ADDED)),

		/** The CONFLICTED. */
		CONFLICTED(ResourcesUtils.getResourceText(SubversionEventResourceType.STATUS_CONFLICTED)),

		/** The DELETED. */
		DELETED(ResourcesUtils.getResourceText(SubversionEventResourceType.STATUS_DELETED)),

		/** The MERGED. */
		MERGED(ResourcesUtils.getResourceText(SubversionEventResourceType.STATUS_MERGED)),

		/** The UPDATED. */
		UPDATED(ResourcesUtils.getResourceText(SubversionEventResourceType.STATUS_UPDATED));

		/** The value. */
		private final String value;

		/**
		 * Instantiates a new update change type.
		 * 
		 * @param value the value
		 */
		UpdateChangeType(final String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return value;
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EventHandler.class);

	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.ISVNCanceller#checkCancelled()
	 */
	@Override
	public void checkCancelled() throws SVNCancelException {

	}

	/*
	 * (non-Javadoc)
	 * @see org.tmatesoft.svn.core.wc.ISVNEventHandler#handleEvent(org.tmatesoft.svn.core.wc.SVNEvent, double)
	 */
	@Override
	public void handleEvent(final SVNEvent event, final double progress) throws SVNException {
		final String prefix = "handleEvent() :";

		LOGGER.debug("{} entered, event={}", prefix, event);

		final SVNEventAction action = event.getAction();

		LOGGER.debug("{} action={}", prefix, action);

		if (action == SVNEventAction.UPDATE_ADD) {
			handlePathChange(event, UpdateChangeType.ADDED);
		}
		else if (action == SVNEventAction.UPDATE_DELETE) {
			handlePathChange(event, UpdateChangeType.DELETED);
		}
		else if (action == SVNEventAction.UPDATE_NONE) {
			StatusChangeEventBus.info(SUBVERSION_UPDATE_EVENT_UPDATE_NONE, event.getFile()
					.getAbsolutePath());
		}
		else if (action == SVNEventAction.UPDATE_UPDATE) {
			handleUpdateEvent(event);
		}
		else if (action == SVNEventAction.UPDATE_EXTERNAL) {
			StatusChangeEventBus.info(SUBVERSION_UPDATE_EVENT_UPDATE_EXTERNAL, event.getRevision(), event.getFile().getAbsolutePath());
		}
		else if (action == SVNEventAction.UPDATE_COMPLETED) {
			StatusChangeEventBus.info(SUBVERSION_UPDATE_EVENT_UPDATE_COMPLETED, event.getRevision());
		}
		else if (action == SVNEventAction.ADD) {
			StatusChangeEventBus.info(SUBVERSION_UPDATE_EVENT_ADD, event.getFile().getAbsolutePath());
		}
		else if (action == SVNEventAction.DELETE) {
			StatusChangeEventBus.info(SUBVERSION_UPDATE_EVENT_DELETE, event.getFile().getAbsolutePath());
		}
		else if (action == SVNEventAction.LOCKED) {
			StatusChangeEventBus.info(SUBVERSION_UPDATE_EVENT_LOCKED, event.getFile().getAbsolutePath());
		}
		else if (action == SVNEventAction.LOCK_FAILED) {
			StatusChangeEventBus.error(SUBVERSION_UPDATE_EVENT_LOCK_FAILED, event.getFile().getAbsolutePath());
		}
		else if (action == SVNEventAction.FAILED_EXTERNAL) {
			StatusChangeEventBus.error(SUBVERSION_UPDATE_EVENT_FAILED_EXTERNAL, event.getFile().getAbsolutePath());
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
	 * @param updateChangeType the update change type
	 */
	private void handlePathChange(final SVNEvent event, final UpdateChangeType updateChangeType) {
		final String prefix = "handlePathChange() :";

		LOGGER.debug("{} entered, event={}, updateEvent=" + updateChangeType, prefix, event);

		final SVNStatusType propertiesStatus = event.getPropertiesStatus();

		UpdateChangeType propertiesChangeType = null;

		if (propertiesStatus == SVNStatusType.CHANGED) {
			propertiesChangeType = UpdateChangeType.UPDATED;
		}
		else if (propertiesStatus == SVNStatusType.CONFLICTED) {
			propertiesChangeType = UpdateChangeType.CONFLICTED;
		}
		else if (propertiesStatus == SVNStatusType.MERGED) {
			propertiesChangeType = UpdateChangeType.MERGED;
		}

		final String updateChangeString = updateChangeType.toString();
		final String propertyChangeString = propertiesChangeType == null ? "" : propertiesChangeType.toString();
		final String lockLabel = isUnlocked(event) ? ResourcesUtils
				.getResourceText(SubversionEventResourceType.LOCK_STATUS_UNLOCKED) : "";

		StatusChangeEventBus.info(SUBVERSION_UPDATE_EVENT_UPDATE, updateChangeString,
				propertyChangeString, lockLabel, event.getFile().getAbsolutePath());

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
			handlePathChange(event, UpdateChangeType.UPDATED);
		}
		else if (contentsStatus == SVNStatusType.CONFLICTED) {
			handlePathChange(event, UpdateChangeType.CONFLICTED);
		}
		else if (contentsStatus == SVNStatusType.MERGED) {
			handlePathChange(event, UpdateChangeType.MERGED);
		}
		else {
			LOGGER.error("{} unsupported contentsStatus {}", prefix, contentsStatus);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Checks if is unlocked.
	 * 
	 * @param event the event
	 * @return true, if is unlocked
	 */
	private boolean isUnlocked(final SVNEvent event) {
		return event.getLockStatus() == SVNStatusType.LOCK_UNLOCKED;
	}
}
