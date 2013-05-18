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
package net.lmxm.ute.gui.editors.locations;

import net.lmxm.ute.beans.locations.AbstractHttpLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.gui.validation.InputValidator;
import net.lmxm.ute.gui.validation.InputValidatorFactory;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import net.lmxm.ute.resources.types.LabelResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Class AbstractHttpLocationEditorPanel.
 */
public abstract class AbstractHttpLocationEditorPanel extends AbstractLocationEditorPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6846533044393594400L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpLocationEditorPanel.class);

	/** The url text field. */
	private JTextField urlTextField = null;

	/**
	 * Instantiates a new abstract http location editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param toolBar the tool bar
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public AbstractHttpLocationEditorPanel(final LabelResourceType guiComponentLabel, final JToolBar toolBar,
			final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super(guiComponentLabel, toolBar, configurationHolder, actionListener);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractCommonEditorPanel#addFields()
	 */
	@Override
	protected void addFields() {
		super.addFields();

		final JPanel contentPanel = getContentPanel();

		addRequiredLabel(LabelResourceType.URL);
		contentPanel.add(getUrlTextField());
	}

	/**
	 * Adds the input validators.
	 */
	private void addInputValidators() {
        final String prefix = "addInputValidators() : ";

		if (getUserObject() instanceof AbstractHttpLocation) {
			final JTextComponent component = getUrlTextField();
			removeInputValidator(component);

			final InputValidator inputValidator;
			if (getUserObject() instanceof HttpLocation) {
				inputValidator = InputValidatorFactory.createHttpLocationUrlValidator(component);
			}
			else if (getUserObject() instanceof MavenRepositoryLocation) {
				inputValidator = InputValidatorFactory.createMavenRepositoryLocationUrlValidator(component);
			}
			else if (getUserObject() instanceof SubversionRepositoryLocation) {
				inputValidator = InputValidatorFactory.createSubversionRepositoryLocationUrlValidator(component);
			}
			else {
                LOGGER.error("{} Unsupported HTTP location type", prefix);
                throw new GuiException(ExceptionResourceType.UNSUPPORTED_HTTP_LOCATION_TYPE);
			}

			component.setInputVerifier(inputValidator);
			addInputValidator(inputValidator);
		}
	}

	/**
	 * Gets the url text field.
	 * 
	 * @return the url text field
	 */
	private JTextField getUrlTextField() {
		if (urlTextField == null) {
			urlTextField = new JTextField();
			urlTextField.setMinimumSize(new Dimension(400, (int) urlTextField.getSize().getHeight()));
			urlTextField.setDragEnabled(true);
			urlTextField.getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof AbstractHttpLocation) {
						((AbstractHttpLocation) getUserObject()).setUrl(newValue);
					}
				}
			});
		}
		return urlTextField;
	}

	/**
	 * Loat http common field data.
	 */
	@Override
	public void loadData() {
		super.loadData();

		if (getUserObject() instanceof AbstractHttpLocation) {
			final AbstractHttpLocation abstractHttpLocation = (AbstractHttpLocation) getUserObject();

			getUrlTextField().setText(abstractHttpLocation.getUrl());
		}

		addInputValidators();
	}
}
