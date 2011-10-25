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

/**
 * The Enum GuiComponentLabel.
 */
public enum GuiComponentLabel implements GuiComponentType {

	/** The CURRENT_VALUE. */
	CURRENT_VALUE,

	/** The DESCRIPTION. */
	DESCRIPTION,

	/** The ENABLED. */
	ENABLED,

	/** The FILES. */
	FILES,

	/** The FIND_AND_REPLACE. */
	FIND_AND_REPLACE,

	/** The GROOVY_SCRIPT. */
	GROOVY_SCRIPT,

	/** The ID. */
	ID,

	/** The JOB. */
	JOB,

	/** The LOCATION. */
	LOCATION,

	/** The PASSWORD. */
	PASSWORD,

	/** The PATH. */
	PATH,

	/** The PATTERNS. */
	PATTERNS,

	/** The PREFERENCE. */
	PREFERENCE,

	/** The PROPERTY. */
	PROPERTY,

	/** The SCOPE. */
	SCOPE,

	/** The SCRIPT. */
	SCRIPT,

	/** The SERVER. */
	SERVER,

	/** The SOURCE. */
	SOURCE,

	/** The STOP_ON_ERROR. */
	STOP_ON_ERROR,

	/** The TARGET. */
	TARGET,

	/** The TASK. */
	TASK,

	/** The URL. */
	URL,

	/** The USERNAME. */
	USERNAME,

	/** The VALUE. */
	VALUE;

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getActionCommand()
	 */
	@Override
	public String getActionCommand() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getGuiComponentCategory()
	 */
	@Override
	public GuiComponentCategory getGuiComponentCategory() {
		return GuiComponentCategory.LABEL;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.components.GuiComponentType#getIcon()
	 */
	@Override
	public Icon getIcon() {
		return null;
	}
}
