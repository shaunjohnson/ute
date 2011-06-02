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
package net.lmxm.ute;

import javax.swing.JFrame;
import javax.swing.UIManager;

import net.lmxm.ute.console.ConsoleApplication;
import net.lmxm.ute.gui.MainFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgoodies.looks.Options;

/**
 * The Class Application.
 */
public final class Application {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	/**
	 * Execute command line.
	 * 
	 * @param args the args
	 */
	private static void executeCommandLine(final String[] args) {
		final String prefix = "executeCommandLine() :";

		LOGGER.debug("{} entered", prefix);

		final ConsoleApplication consoleApplication = new ConsoleApplication(args);
		consoleApplication.execute();

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Execute gui.
	 */
	private static void executeGui() {
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

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		final String prefix = "main(String[]) :";

		LOGGER.info("{} Application started", prefix);

		if (args.length == 0) {
			executeGui();
		}
		else {
			executeCommandLine(args);
		}

		LOGGER.info("{} Application ended", prefix);
	}
}
