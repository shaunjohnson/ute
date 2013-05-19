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
package net.lmxm.ute.gui.editors.locations;

import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.gui.validation.InputValidator;
import net.lmxm.ute.gui.validation.InputValidatorFactory;
import net.lmxm.ute.resources.types.ButtonResourceType;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.lmxm.ute.resources.types.ToolbarButtonResourceType;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The Class FileSystemLocationEditorPanel.
 */
public final class FileSystemLocationEditorPanel extends AbstractLocationEditorPanel {

	/**
	 * The Class FileSystemLocationEditorToolBar.
	 */
	private static class FileSystemLocationEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 5647133443099985373L;

		/**
		 * Instantiates a new file system location editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public FileSystemLocationEditorToolBar(final UteActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.DELETE_FILE_SYSTEM_LOCATION);
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemLocationEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7086761608623968446L;

	/** The browse path button. */
	private JButton browsePathButton = null;

	/** The path panel. */
	private JPanel pathPanel = null;

	/** The path text field. */
	private JTextField pathTextField = null;

	/**
	 * Instantiates a new file system location editor panel.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public FileSystemLocationEditorPanel(final ConfigurationHolder configurationHolder,
			final UteActionListener actionListener) {
		super(LabelResourceType.FILE_SYSTEM_LOCATION, new FileSystemLocationEditorToolBar(actionListener),
				configurationHolder, actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractCommonEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		final JPanel contentPanel = getContentPanel();

		addRequiredLabel(LabelResourceType.PATH);
		contentPanel.add(getPathPanel());
	}

	/**
	 * Adds the input validators.
	 */
	private void addInputValidators() {
		if (getUserObject() instanceof FileSystemLocation) {
			final JTextComponent component = getPathTextField();
			removeInputValidator(component);

			final InputValidator inputValidator = InputValidatorFactory
					.createFileSystemLocationPathValidator(component);

			component.setInputVerifier(inputValidator);
			addInputValidator(inputValidator);
		}
	}

	/**
	 * Gets the browse path button.
	 * 
	 * @return the browse path button
	 */
	private JButton getBrowsePathButton() {
		if (browsePathButton == null) {
			final Component parent = this;

			browsePathButton = GuiComponentFactory.createButton(ButtonResourceType.DIRECTORY_BROWSE,
					new UteActionListener() {
						@Override
						public void actionPerformed(final ActionEvent actionEvent) {
							final JFileChooser fcOpen = new JFileChooser();

							fcOpen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

							final int returnVal = fcOpen.showOpenDialog(parent);

							if (returnVal == JFileChooser.APPROVE_OPTION) {
								final File file = fcOpen.getSelectedFile();

								getPathTextField().setText(file.getAbsolutePath());
							}
						}

                        @Override
                        public boolean supports(ActionCommand actionCommand) {
                            return ActionCommand.DIRECTORY_BROWSE.equals(actionCommand);
                        }
                    });
		}

		return browsePathButton;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new FileSystemLocation();
	}

	/**
	 * Gets the path panel.
	 * 
	 * @return the path panel
	 */
	private JPanel getPathPanel() {
		if (pathPanel == null) {
			pathPanel = new JPanel(new MigLayout("ins 0", "[left]"));

			pathPanel.add(getPathTextField());
			pathPanel.add(getBrowsePathButton());
		}
		return pathPanel;
	}

	/**
	 * Gets the path text field.
	 * 
	 * @return the path text field
	 */
	private JTextField getPathTextField() {
		if (pathTextField == null) {
			pathTextField = new JTextField();
			pathTextField.setMinimumSize(new Dimension(400, (int) pathTextField.getSize().getHeight()));
			pathTextField.setDragEnabled(true);
			pathTextField.getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof FileSystemLocation) {
						((FileSystemLocation) getUserObject()).setPath(newValue);
					}
				}
			});
		}
		return pathTextField;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractIdEditorPanel#loadData()
	 */
	@Override
	public void loadData() {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered", prefix);

		super.loadData();

		if (getUserObject() instanceof FileSystemLocation) {
			final FileSystemLocation fileSystemLocation = (FileSystemLocation) getUserObject();

			getPathTextField().setText(fileSystemLocation.getPath());
		}

		addInputValidators();

		LOGGER.debug("{} leaving", prefix);
	}
}
