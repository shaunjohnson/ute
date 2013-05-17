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
package net.lmxm.ute.gui;

/**
 * GUI related constant values.
 */
public final class GuiContants {

    /**
     * Default font size.
     */
    public static int DEFAULT_FONT_SIZE = 12;

    /**
     * Default location for the jobs split pane divider.
     */
    public static final int DEFAULT_JOBS_SPLIT_PANE_DIVIDER_LOCATION = 350;

    /**
     * Default location for the main split pane divider.
     */
    public static final int DEFAULT_MAIN_SPLIT_PANE_DIVIDER_LOCATION = 500;

    /**
     * Default window height.
     */
    public static final int DEFAULT_WINDOW_HEIGHT = 800;

    /**
     * Default window width.
     */
    public static final int DEFAULT_WINDOW_WIDTH = 1000;

    /**
     * Prevent instantiation.
     */
    private GuiContants() {
        throw new AssertionError("Cannot be instantiated");
    }
}
