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
     * Generic error message dialog.
     *
     * @param throwable the throwable/exception that occurred
     */
    protected void displayError(final Throwable throwable) {
        LOGGER.error("Error occurred.", throwable);

        // TODO handle AbstractRuntimeException and other errors differently?
        final String message = throwable.getMessage();
        final String title = ResourcesUtils.getResourceTitle(ApplicationResourceType.ERROR_OCCURRED);

        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
