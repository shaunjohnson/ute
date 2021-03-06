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

import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

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
	public static JButton createButton(final ButtonResourceType guiComponentButton, final UteActionListener actionListener) {
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
	public static JLabel createLabel(final LabelResourceType guiComponentLabel, final int alignment) {
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
	public static JMenu createMenu(final MenuResourceType guiComponentMenu) {
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
	public static JMenuItem createMenuItem(final MenuItemResourceType guiComponentMenuItem,
			final UteActionListener actionListener) {
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
	public static JLabel createPanelHeaderLabel(final LabelResourceType guiComponentLabel) {
		final JLabel label = new JLabel();

		setIcon(label, guiComponentLabel);
		setText(label, guiComponentLabel);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

		return label;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param alignment the alignment
	 * @return the j label
	 */
	public static JLabel createRequiredLabel(final LabelResourceType guiComponentLabel, final int alignment) {
		final JLabel label = new JLabel();

		setIcon(label, guiComponentLabel);
		setRequiredText(label, guiComponentLabel);
		label.setHorizontalAlignment(alignment);

		return label;
	}

	/**
	 * Creates a new GuiComponent object.
	 * 
	 * @param guiComponentButton the gui component button
	 * @param actionListener the action listener
	 * @return the j button
	 */
	public static JButton createToolbarButton(final ToolbarButtonResourceType guiComponentButton,
			final UteActionListener actionListener) {
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
	public static JButton createToolbarButton(final ToolbarButtonResourceType guiComponentButton,
			final UteActionListener actionListener, final MouseListener mouseListener) {
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
	public static JButton createToolbarButtonNoText(final ToolbarButtonResourceType guiComponentButton,
			final UteActionListener actionListener) {
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
	private static void setAccelerator(final JMenuItem menuItem, final ResourceType guiComponentType) {
		final Character accelerator = ResourcesUtils.getResourceAccelerator(guiComponentType);
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
	private static void setActionListener(final AbstractButton abstractButton, final ResourceType guiComponentType,
			final UteActionListener actionListener) {
        final ActionCommand actionCommand = guiComponentType.getActionCommand();

        if (actionListener != null && actionCommand != null) {
            if (!actionListener.supports(actionCommand)) {
                throw new GuiException(ExceptionResourceType.UNEXPECTED_ERROR);
            }

			abstractButton.addActionListener(actionListener);
            abstractButton.setActionCommand(guiComponentType.getActionCommand().name());
		}
        else if (!(actionListener == null && actionCommand == null)) {
            throw new GuiException(ExceptionResourceType.UNEXPECTED_ERROR);
        }
	}

	/**
	 * Sets the icon.
	 * 
	 * @param abstractButton the abstract button
	 * @param guiComponentType the gui component type
	 */
	private static void setIcon(final AbstractButton abstractButton, final ResourceType guiComponentType) {
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
	private static void setIcon(final JLabel label, final ResourceType guiComponentType) {
		if (guiComponentType.getIcon() != null) {
			label.setIcon(guiComponentType.getIcon());
		}
	}

	/**
	 * Sets the required text.
	 * 
	 * @param label the label
	 * @param guiComponentType the gui component type
	 */
	private static void setRequiredText(final JLabel label, final ResourceType guiComponentType) {
		final String text = ResourcesUtils.getResourceText(guiComponentType);
		if (text != null) {
			label.setText("<html><font color=red size=4><b>*</b></font> " + text + "</html>");
		}

		final String toolTipText = ResourcesUtils.getResourceToolTipText(guiComponentType);
		if (toolTipText != null) {
			label.setToolTipText(toolTipText);
		}
	}

	/**
	 * Sets the text.
	 * 
	 * @param abstractButton the abstract button
	 * @param guiComponentType the gui component type
	 */
	private static void setText(final AbstractButton abstractButton, final ResourceType guiComponentType) {
		final String text = ResourcesUtils.getResourceText(guiComponentType);
		if (text != null) {
			abstractButton.setText(text);
		}

		final String toolTipText = ResourcesUtils.getResourceToolTipText(guiComponentType);
		if (toolTipText != null) {
			abstractButton.setToolTipText(toolTipText);
		}
	}

	/**
	 * Sets the text.
	 * 
	 * @param label the label
	 * @param guiComponentType the gui component type
	 */
	private static void setText(final JLabel label, final ResourceType guiComponentType) {
		final String text = ResourcesUtils.getResourceText(guiComponentType);
		if (text != null) {
			label.setText(text);
		}

		final String toolTipText = ResourcesUtils.getResourceToolTipText(guiComponentType);
		if (toolTipText != null) {
			label.setToolTipText(toolTipText);
		}
	}

	/**
	 * Sets the tool tip text.
	 * 
	 * @param abstractButton the abstract button
	 * @param guiComponentType the gui component type
	 */
	private static void setToolTipText(final AbstractButton abstractButton, final ResourceType guiComponentType) {
		final String toolTipText = ResourcesUtils.getResourceToolTipText(guiComponentType);
		if (toolTipText != null) {
			abstractButton.setToolTipText(toolTipText);
		}
	}
}
