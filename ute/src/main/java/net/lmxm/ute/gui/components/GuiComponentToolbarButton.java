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
package net.lmxm.ute.gui.components;

import javax.swing.Icon;

import net.lmxm.ute.gui.ActionConstants;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Enum GuiComponentButton.
 */
public enum GuiComponentToolbarButton implements GuiComponentType {

	/** The ADD_JOB. */
	ADD_JOB(ImageUtil.ADD_JOB_ICON, ActionConstants.ADD_JOB),

	/** The ADD_LOCATION. */
	ADD_LOCATION(ImageUtil.ADD_LOCATION_ICON, ActionConstants.ADD_LOCATION),

	/** The ADD_PREFERENCE. */
	ADD_PREFERENCE(ImageUtil.ADD_PREFERENCE_ICON, ActionConstants.ADD_PREFERENCE),

	/** The ADD_PROPERTY. */
	ADD_PROPERTY(ImageUtil.ADD_PROPERTY_ICON, ActionConstants.ADD_PROPERTY),

	/** The EXIT. */
	EXIT(ImageUtil.EXIT_ICON, ActionConstants.EXIT),

	/** The NEW_FILE. */
	NEW_FILE(ImageUtil.NEW_FILE_ICON, ActionConstants.NEW_FILE),

	/** The OPEN_FILE. */
	OPEN_FILE(ImageUtil.OPEN_FILE_ICON, ActionConstants.OPEN_FILE),

	/** The SAVE_FILE. */
	SAVE_FILE(ImageUtil.SAVE_FILE_ICON, ActionConstants.SAVE_FILE),

	/** The SAVE_FILE_AS. */
	SAVE_FILE_AS(ImageUtil.SAVE_FILE_AS_ICON, ActionConstants.SAVE_FILE_AS);

	/** The action command. */
	private final String actionCommand;

	/** The icon. */
	private final Icon icon;

	/**
	 * Instantiates a new gui component button.
	 * 
	 * @param icon the icon
	 * @param actionCommand the action command
	 */
	GuiComponentToolbarButton(final Icon icon, final String actionCommand) {
		this.icon = icon;
		this.actionCommand = actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getActionCommand()
	 */
	@Override
	public String getActionCommand() {
		return actionCommand;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getGuiComponentCategory()
	 */
	@Override
	public GuiComponentCategory getGuiComponentCategory() {
		return GuiComponentCategory.TOOLBAR_BUTTON;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponent#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return icon;
	}
}
