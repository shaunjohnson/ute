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

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;

import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentMenuItem;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractPopupMenu.
 */
public abstract class AbstractPopupMenu extends JPopupMenu {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7518698215552137785L;

	/** The action listener. */
	private final ActionListener actionListener;

	/**
	 * Instantiates a new abstract popup menu.
	 * 
	 * @param actionListener the action listener
	 */
	public AbstractPopupMenu(final ActionListener actionListener) {
		super();

		Preconditions.checkNotNull(actionListener, "Action listener may not be null");

		this.actionListener = actionListener;
	}

	/**
	 * Adds the menu item.
	 * 
	 * @param guiComponentMenuItem the gui component menu item
	 */
	protected final void addMenuItem(final GuiComponentMenuItem guiComponentMenuItem) {
		add(GuiComponentFactory.createMenuItem(guiComponentMenuItem, actionListener));
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JPopupMenu#show(java.awt.Component, int, int)
	 */
	@Override
	public final void show(final Component invoker, final int x, final int y) {
		super.show(invoker, x, y);
	}
}
