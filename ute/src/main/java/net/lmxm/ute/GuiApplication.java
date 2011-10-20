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
package net.lmxm.ute;

import javax.swing.JFrame;
import javax.swing.UIManager;

import net.lmxm.ute.gui.MainFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgoodies.looks.Options;

/**
 * The Class GuiApplication.
 */
public class GuiApplication {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GuiApplication.class);

	/**
	 * Execute.
	 */
	public void execute() {
		final String prefix = "executeGui() :";

		LOGGER.debug("{} entered", prefix);

		try {
			UIManager.setLookAndFeel(Options.getCrossPlatformLookAndFeelClassName());
		}
		catch (final Exception e) {
			LOGGER.error("Error setting native LAF", e);
		}

		final JFrame main = new MainFrame();

		main.setVisible(true);

		LOGGER.debug("{} exiting", prefix);
	}
}
