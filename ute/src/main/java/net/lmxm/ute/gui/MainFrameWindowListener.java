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

import net.lmxm.ute.gui.utils.UserPreferences;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * The listener interface for receiving mainFrameWindow events.
 * The class that is interested in processing a mainFrameWindow
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addMainFrameWindowListener<code> method. When
 * the mainFrameWindow event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MainFrameWindowEvent
 */
public final class MainFrameWindowListener extends WindowAdapter {

	/** The main frame. */
	private final MainFrame mainFrame;

	/**
	 * Instantiates a new main frame window listener.
	 *
	 * @param mainFrame the main frame
	 */
	public MainFrameWindowListener(final MainFrame mainFrame) {
		super();

		this.mainFrame = mainFrame;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(final WindowEvent e) {
		UserPreferences.setWindowState(mainFrame.getExtendedState());

		mainFrame.setExtendedState(JFrame.NORMAL);
		UserPreferences.setWindowSize(mainFrame.getSize());

		UserPreferences.setWindowLocation(mainFrame.getLocation());
		UserPreferences.setMainSplitPaneDividerLocation(mainFrame.getMainSplitPane().getDividerLocation());
		UserPreferences.setJobsSplitPaneDividerLocation(mainFrame.getJobsSplitPane().getDividerLocation());
	}
}
