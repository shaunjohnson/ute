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
package net.lmxm.ute.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.menus.JobPopupMenu;
import net.lmxm.ute.gui.menus.TaskPopupMenu;
import net.lmxm.ute.gui.nodes.FileSystemLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.HttpLocationsRootTreeNode;
import net.lmxm.ute.gui.nodes.JobsRootTreeNode;
import net.lmxm.ute.gui.nodes.PreferencesRootTreeNode;
import net.lmxm.ute.gui.nodes.PropertiesRootTreeNode;
import net.lmxm.ute.gui.nodes.SubversionRepositoryLocationsRootTreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The listener interface for receiving mainTreeMouse events. The class that is interested in processing a mainTreeMouse
 * event implements this interface, and the object created with that class is registered with a component using the
 * component's <code>addMainTreeMouseListener<code> method. When
 * the mainTreeMouse event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see MainTreeMouseEvent
 */
public final class MainTreeMouseListener extends MouseAdapter {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MainTreeMouseListener.class);

	/** The main frame. */
	private final MainFrame mainFrame;

	/**
	 * Instantiates a new main tree mouse listener.
	 * 
	 * @param mainFrame the main frame
	 */
	public MainTreeMouseListener(final MainFrame mainFrame) {
		super();

		this.mainFrame = mainFrame;
	}

	/**
	 * Handle popup trigger.
	 * 
	 * @param mouseEvent the mouse event
	 */
	public void handlePopupTrigger(final MouseEvent mouseEvent) {
		final String prefix = "mouseClicked() :";

		LOGGER.debug("{} entered", prefix);

		if (mouseEvent.isPopupTrigger()) {
			final int x = mouseEvent.getX();
			final int y = mouseEvent.getY();

			mainFrame.selectTreeObjectAtLocation(x, y);

			final Object object = mainFrame.getSelectedTreeObject();

			if (object != null) {
				JPopupMenu popupMenu = null;

				// Find popup menu appropriate to the item selected
				if (object instanceof JobsRootTreeNode) {
					popupMenu = mainFrame.getJobsRootPopupMenu();
				}
				else if (object instanceof Job) {
					popupMenu = mainFrame.getJobPopupMenu();
					((JobPopupMenu) popupMenu).enableDisableMenuItems((Job) object);
				}
				else if (object instanceof Task) {
					popupMenu = mainFrame.getTaskPopupMenu();
					((TaskPopupMenu) popupMenu).enableDisableMenuItems(object);
				}
				else if (object instanceof FileSystemLocationsRootTreeNode) {
					popupMenu = mainFrame.getFileSystemLocationsRootPopupMenu();
				}
				else if (object instanceof FileSystemLocation) {
					popupMenu = mainFrame.getFileSystemLocationPopupMenu();
				}
				else if (object instanceof HttpLocationsRootTreeNode) {
					popupMenu = mainFrame.getHttpLocationsRootPopupMenu();
				}
				else if (object instanceof HttpLocation) {
					popupMenu = mainFrame.getHttpLocationPopupMenu();
				}
				else if (object instanceof SubversionRepositoryLocationsRootTreeNode) {
					popupMenu = mainFrame.getSubversionRepositoryLocationsRootPopupMenu();
				}
				else if (object instanceof SubversionRepositoryLocation) {
					popupMenu = mainFrame.getSubversionRepositoryLocationPopupMenu();
				}
				else if (object instanceof PreferencesRootTreeNode) {
					popupMenu = mainFrame.getPreferencesRootPopupMenu();
				}
				else if (object instanceof Preference) {
					popupMenu = mainFrame.getPreferencePopupMenu();
				}
				else if (object instanceof PropertiesRootTreeNode) {
					popupMenu = mainFrame.getPropertiesRootPopupMenu();
				}
				else if (object instanceof Property) {
					popupMenu = mainFrame.getPropertyPopupMenu();
				}
				else {
					LOGGER.debug("{} unsupported object; no popup will be displayed", prefix);
				}

				// Display the popup menu if a match was found
				if (popupMenu != null) {
					popupMenu.show(mouseEvent.getComponent(), x, y);
				}
				else {
					LOGGER.debug("{} no matching popup found", prefix);
				}
			}
			else {
				LOGGER.debug("{} no object selected", prefix);
			}
		}
		else {
			LOGGER.debug("{} not a popup trigger", prefix);
		}

		LOGGER.debug("{} leaving", prefix);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(final MouseEvent mouseEvent) {
		handlePopupTrigger(mouseEvent);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(final MouseEvent mouseEvent) {
		handlePopupTrigger(mouseEvent);
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(final MouseEvent mouseEvent) {
		handlePopupTrigger(mouseEvent);
	}
}
