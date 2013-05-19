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
package net.lmxm.ute.gui.editors;

import net.lmxm.ute.beans.DescribableBean;
import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.event.IdChangeEvent;
import net.lmxm.ute.event.IdChangeEventBus;
import net.lmxm.ute.gui.validation.InputValidator;
import net.lmxm.ute.gui.validation.InputValidatorFactory;
import net.lmxm.ute.resources.types.LabelResourceType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class AbstractCommonEditorPanel.
 */
public abstract class AbstractCommonEditorPanel extends AbstractEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommonEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7144352139412573257L;

	/** The description pane. */
	private JScrollPane descriptionPane = null;

	/** The description text area. */
	private JTextArea descriptionTextArea = null;

	/** The id text field. */
	private JTextField idTextField = null;

	/**
	 * Instantiates a new abstract common editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param toolBar the tool bar
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public AbstractCommonEditorPanel(final LabelResourceType guiComponentLabel, final JToolBar toolBar,
			final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super(guiComponentLabel, toolBar, configurationHolder, actionListener);
	}

	/**
	 * Adds the common fields.
	 */
	@Override
	protected void addFields() {
		final JPanel contentPanel = getContentPanel();

		if (IdentifiableBean.class.isInstance(getEditedObjectClass())) {
			addRequiredLabel(LabelResourceType.ID);
			contentPanel.add(getIdTextField());
		}

		if (DescribableBean.class.isInstance(getEditedObjectClass())) {
			addLabel(LabelResourceType.DESCRIPTION);
			contentPanel.add(getDescriptionPane());
		}
	}

	/**
	 * Adds the input validators.
	 */
	private void addInputValidators() {
		if (getUserObject() instanceof IdentifiableBean) {
			removeInputValidator(getIdTextField());

			final InputValidator inputValidator = InputValidatorFactory.createIdValidator(getIdTextField(),
					getConfigurationHolder(), (IdentifiableBean) getUserObject());

			getIdTextField().setInputVerifier(inputValidator);
			addInputValidator(inputValidator);
		}
	}

	/**
	 * Gets the description pane.
	 * 
	 * @return the description pane
	 */
	private JScrollPane getDescriptionPane() {
		if (descriptionPane == null) {
			descriptionPane = new JScrollPane(getDescriptionTextArea());
		}

		return descriptionPane;
	}

	/**
	 * Gets the description text area.
	 * 
	 * @return the description text area
	 */
	private JTextArea getDescriptionTextArea() {
		if (descriptionTextArea == null) {
			descriptionTextArea = new JTextArea();
			descriptionTextArea.setColumns(40);
			descriptionTextArea.setRows(5);
			descriptionTextArea.setLineWrap(true);
			descriptionTextArea.setTabSize(4);
			descriptionTextArea.setDragEnabled(true);
			descriptionTextArea.getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof DescribableBean) {
						((DescribableBean) getUserObject()).setDescription(newValue);
					}
				}
			});
		}
		return descriptionTextArea;
	}

	/**
	 * Gets the id text field.
	 * 
	 * @return the id text field
	 */
	private JTextField getIdTextField() {
		if (idTextField == null) {
			idTextField = new JTextField();
			idTextField.setMinimumSize(new Dimension(400, (int) idTextField.getSize().getHeight()));
			idTextField.setDragEnabled(true);
			idTextField.getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				public void valueChanged(final String newValue) {
                    if (getUserObject() instanceof IdentifiableBean) {
						final IdentifiableBean identifiableBean = (IdentifiableBean) getUserObject();
						identifiableBean.setId(newValue);

                        IdChangeEventBus.post(new IdChangeEvent(identifiableBean));
					}
				}
			});
		}

		return idTextField;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#loadData()
	 */
	@Override
	public void loadData() {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered", prefix);

		if (getUserObject() instanceof IdentifiableBean) {
			final IdentifiableBean identifiableBean = (IdentifiableBean) getUserObject();

			getIdTextField().setText(identifiableBean.getId());
		}

		if (getUserObject() instanceof DescribableBean) {
			final DescribableBean describableBean = (DescribableBean) getUserObject();

			getDescriptionTextArea().setText(describableBean.getDescription());
		}

		addInputValidators();

		LOGGER.debug("{} leaving", prefix);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#setFocusToFirstInput()
	 */
	@Override
	public final void setFocusToFirstInput() {
		if (StringUtils.isBlank(getIdTextField().getText())) {
			getIdTextField().requestFocusInWindow();
		}
	}
}
