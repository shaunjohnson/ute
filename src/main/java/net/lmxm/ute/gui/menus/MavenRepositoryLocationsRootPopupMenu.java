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
package net.lmxm.ute.gui.menus;

import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.resources.types.MenuItemResourceType;

import java.awt.event.ActionListener;

/**
 * The Class MavenRepositoryLocationsRootPopupMenu.
 */
public final class MavenRepositoryLocationsRootPopupMenu extends AbstractPopupMenu {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4380168113799561407L;

    /**
	 * Instantiates a new Maven repository locations root popup menu.
	 *
	 * @param actionListener the action listener
	 */
	public MavenRepositoryLocationsRootPopupMenu(final UteActionListener actionListener) {
		super(actionListener);

		addMenuItem(MenuItemResourceType.ADD_MAVEN_REPOSITORY_LOCATION);
	}
}
