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

import java.awt.GraphicsEnvironment;

import net.lmxm.ute.console.ConsoleApplication;
import net.lmxm.ute.gui.GuiApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application is the main entry-point into the UTE application. If the application is executed on a headless machine
 * or if arguments were provided the console application version of UTE is launched, otherwise the GUI application is
 * launched.
 */
public final class Application {

    /**
     * The LOGGER handle.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(final String[] args) throws InterruptedException {
        final String prefix = "main(String[]) :";

        LOGGER.info("{} Application started", prefix);

        if (GraphicsEnvironment.isHeadless() || args.length > 0) {
            new ConsoleApplication(args).execute();
        }
        else {
            new GuiApplication().execute();
        }

        LOGGER.info("{} Application ended", prefix);
    }

    /**
     * Prevent instantiation.
     */
    private Application() {
        throw new AssertionError("Cannot be instantiated");
    }
}
