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
package net.lmxm.ute.resources.types;

import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.resources.ResourceCategory;

import javax.swing.*;

import static net.lmxm.ute.resources.types.ResourceValueType.TEXT;
import static net.lmxm.ute.resources.types.ResourceValueType.TOOLTIP_TEXT;

/**
 * The Enum ButtonResourceType.
 */
public enum ButtonResourceType implements ResourceType {

    CLOSE_DIALOG(null, null),
    CLOSE_TAB(null, null),
    DIRECTORY_BROWSE(null, ActionCommand.DIRECTORY_BROWSE);

    private final ResourceValueType[] types = {TEXT, TOOLTIP_TEXT};

    private final ActionCommand actionCommand;

    private final Icon icon;

    /**
     * Instantiates a new gui component button.
     *
     * @param icon          the icon
     * @param actionCommand the action command
     */
    ButtonResourceType(final Icon icon, final ActionCommand actionCommand) {
        this.icon = icon;
        this.actionCommand = actionCommand;
    }

    public ActionCommand getActionCommand() {
        return actionCommand;
    }

    public Icon getIcon() {
        return icon;
    }

    public ResourceCategory getResourceCategory() {
        return ResourceCategory.BUTTON;
    }

    public ResourceValueType[] getResourceValueTypes() {
        return types;
    }
}
