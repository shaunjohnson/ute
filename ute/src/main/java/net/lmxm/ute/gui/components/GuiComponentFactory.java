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

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.apache.commons.lang.StringUtils;

/**
 * A factory for creating GuiComponent objects.
 */
public class GuiComponentFactory extends AbstractGuiFactory {

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentButton the gui component button
	 * @param actionListener the action listener
	 * @return the j button
	 */
	public static JButton createButton(final GuiComponentButton guiComponentButton, final ActionListener actionListener) {
		final JButton button = new JButton();

		setIcon(button, guiComponentButton);
		setText(button, guiComponentButton);
		setActionListener(button, guiComponentButton, actionListener);

		return button;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param alignment the alignment
	 * @return the j label
	 */
	public static JLabel createLabel(final GuiComponentLabel guiComponentLabel, final int alignment) {
		final JLabel label = new JLabel();

		setIcon(label, guiComponentLabel);
		setText(label, guiComponentLabel);
		label.setHorizontalAlignment(alignment);

		return label;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentMenu the gui component menu
	 * @return the j menu
	 */
	public static JMenu createMenu(final GuiComponentMenu guiComponentMenu) {
		final JMenu menu = new JMenu();

		setIcon(menu, guiComponentMenu);
		setText(menu, guiComponentMenu);

		return menu;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentMenuItem the gui component menu item
	 * @param actionListener the action listener
	 * @return the j menu item
	 */
	public static JMenuItem createMenuItem(final GuiComponentMenuItem guiComponentMenuItem,
			final ActionListener actionListener) {
		final JMenuItem menuItem = new JMenuItem();

		setIcon(menuItem, guiComponentMenuItem);
		setText(menuItem, guiComponentMenuItem);
		setActionListener(menuItem, guiComponentMenuItem, actionListener);
		setAccelerator(menuItem, guiComponentMenuItem);

		return menuItem;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @return the j label
	 */
	public static JLabel createPanelHeaderLabel(final GuiComponentLabel guiComponentLabel) {
		final JLabel label = new JLabel();

		setIcon(label, guiComponentLabel);
		setText(label, guiComponentLabel);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

		return label;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentButton the gui component button
	 * @param actionListener the action listener
	 * @return the j button
	 */
	public static JButton createToolbarButton(final GuiComponentToolbarButton guiComponentButton,
			final ActionListener actionListener) {
		final JButton button = new JButton();

		setIcon(button, guiComponentButton);
		setText(button, guiComponentButton);
		setActionListener(button, guiComponentButton, actionListener);

		return button;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentButton the gui component button
	 * @param actionListener the action listener
	 * @param mouseListener the mouse listener
	 * @return the j button
	 */
	public static JButton createToolbarButton(final GuiComponentToolbarButton guiComponentButton,
			final ActionListener actionListener, final MouseListener mouseListener) {
		final JButton button = createToolbarButton(guiComponentButton, actionListener);

		if (mouseListener != null) {
			button.addMouseListener(mouseListener);
		}

		return button;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentButton the gui component button
	 * @param actionListener the action listener
	 * @return the j button
	 */
	public static JButton createToolbarButtonNoText(final GuiComponentToolbarButton guiComponentButton,
			final ActionListener actionListener) {
		final JButton button = new JButton();

		setIcon(button, guiComponentButton);
		setToolTipText(button, guiComponentButton);
		setActionListener(button, guiComponentButton, actionListener);

		return button;
	}

	/**
	 * Sets the accelerator.
	 * 
	 * @param menuItem the menu item
	 * @param guiComponentType the gui component type
	 */
	private static void setAccelerator(final JMenuItem menuItem, final GuiComponentType guiComponentType) {
		final Character accelerator = getAccelerator(guiComponentType);
		if (accelerator != null) {
			menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator, Toolkit.getDefaultToolkit()
					.getMenuShortcutKeyMask(), false));
		}
	}

	/**
	 * Sets the action listener.
	 * 
	 * @param abstractButton the abstract button
	 * @param guiComponentType the gui component type
	 * @param actionListener the action listener
	 */
	private static void setActionListener(final AbstractButton abstractButton, final GuiComponentType guiComponentType,
			final ActionListener actionListener) {
		if (actionListener != null) {
			abstractButton.addActionListener(actionListener);
		}

		if (guiComponentType.getActionCommand() != null) {
			abstractButton.setActionCommand(guiComponentType.getActionCommand());
		}
	}

	/**
	 * Sets the icon.
	 * 
	 * @param abstractButton the abstract button
	 * @param guiComponentType the gui component type
	 */
	private static void setIcon(final AbstractButton abstractButton, final GuiComponentType guiComponentType) {
		if (guiComponentType.getIcon() != null) {
			abstractButton.setIcon(guiComponentType.getIcon());
		}
	}

	/**
	 * Sets the icon.
	 * 
	 * @param label the label
	 * @param guiComponentType the gui component type
	 */
	private static void setIcon(final JLabel label, final GuiComponentType guiComponentType) {
		if (guiComponentType.getIcon() != null) {
			label.setIcon(guiComponentType.getIcon());
		}
	}

	/**
	 * Sets the text.
	 * 
	 * @param abstractButton the abstract button
	 * @param guiComponentType the gui component type
	 */
	private static void setText(final AbstractButton abstractButton, final GuiComponentType guiComponentType) {
		final String text = getText(guiComponentType);
		if (StringUtils.isNotBlank(text)) {
			abstractButton.setText(text);
		}

		final String toolTipText = getToolTipText(guiComponentType);
		if (StringUtils.isNotBlank(toolTipText)) {
			abstractButton.setToolTipText(toolTipText);
		}
	}

	/**
	 * Sets the text.
	 * 
	 * @param label the label
	 * @param guiComponentType the gui component type
	 */
	private static void setText(final JLabel label, final GuiComponentType guiComponentType) {
		final String text = getText(guiComponentType);
		if (StringUtils.isNotBlank(text)) {
			label.setText(text);
		}

		final String toolTipText = getToolTipText(guiComponentType);
		if (StringUtils.isNotBlank(toolTipText)) {
			label.setToolTipText(toolTipText);
		}
	}

	/**
	 * Sets the tool tip text.
	 * 
	 * @param abstractButton the abstract button
	 * @param guiComponentType the gui component type
	 */
	private static void setToolTipText(final AbstractButton abstractButton, final GuiComponentType guiComponentType) {
		final String toolTipText = getToolTipText(guiComponentType);
		if (StringUtils.isNotBlank(toolTipText)) {
			abstractButton.setToolTipText(toolTipText);
		}
	}
}
