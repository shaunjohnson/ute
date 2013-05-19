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
package net.lmxm.ute.gui.frames;

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ApplicationResourceType;
import net.lmxm.ute.resources.types.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * The Class AbstractFrame.
 */
public class AbstractFrame extends JFrame {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 2742976937617347846L;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFrame.class);

    /**
     * Display an error message to the user, for the provided exception.
     *
     * @param throwable the throwable/exception that occurred
     */
    protected final void displayError(final Throwable throwable) {
        LOGGER.error("Error occurred.", throwable);

        displayError(throwable.getMessage());
    }

    /**
     * Display an error message to the user.
     *
     * @param resourceType Resource type used to load error message text
     */
    protected final void displayError(final ResourceType resourceType) {
        LOGGER.error("Error occurred: {}", resourceType);

        displayError(ResourcesUtils.getResourceText(resourceType));
    }

    /**
     * Display error message implementation.
     *
     * @param message Localized message to display to the user
     */
    private void displayError(final String message) {
        final String title = ResourcesUtils.getResourceTitle(ApplicationResourceType.ERROR_OCCURRED);

        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
