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

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * A factory for creating OptionPane objects.
 */
public class OptionPaneFactory extends AbstractGuiFactory {

	/**
	 * Show confirmation.
	 * 
	 * @param parentComponent the parent component
	 * @param guiComponentConfirmation the gui component confirmation
	 * @param args the args
	 * @return the int
	 */
	public static int showConfirmation(final Component parentComponent,
			final GuiComponentConfirmation guiComponentConfirmation, final Object... args) {
		final String message = String.format(getMessage(guiComponentConfirmation), args);
		final String title = String.format(getTitle(guiComponentConfirmation), args);

		return JOptionPane.showConfirmDialog(parentComponent, message, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
	}
}
