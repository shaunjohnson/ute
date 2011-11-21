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

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import net.lmxm.ute.beans.Property;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.gui.toolbars.AbstractToolBar;
import net.lmxm.ute.gui.validation.InputValidatorFactory;
import net.lmxm.ute.resources.types.LabelResourceType;
import net.lmxm.ute.resources.types.ToolbarButtonResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PropertyEditor.
 */
public final class PropertyEditorPanel extends AbstractCommonEditorPanel {

	/**
	 * The Class PropertyEditorToolBar.
	 */
	private static class PropertyEditorToolBar extends AbstractToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -3489509073783668457L;

		/**
		 * Instantiates a new properties editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public PropertyEditorToolBar(final ActionListener actionListener) {
			super(actionListener);

			addToolbarButton(ToolbarButtonResourceType.DELETE_PROPERTY);
		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2153662249557350246L;

	/** The property value text field. */
	private JTextField propertyValueTextField = null;

	/**
	 * Instantiates a new property editor.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public PropertyEditorPanel(final ConfigurationHolder configurationHolder, final ActionListener actionListener) {
		super(LabelResourceType.PROPERTY, new PropertyEditorToolBar(actionListener), configurationHolder,
				actionListener);

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

		addRequiredLabel(LabelResourceType.VALUE);
		contentPanel.add(getPropertyValueTextField());
	}

	/**
	 * Adds the input validators.
	 */
	private void addInputValidators() {
		if (getUserObject() instanceof Property) {
			final JTextComponent component = getPropertyValueTextField();

			removeInputValidator(component);

			final InputVerifier inputVerifier = InputValidatorFactory.createPropertyValueValidator(component);
			component.setInputVerifier(inputVerifier);
			addInputValidator(inputVerifier);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new Property();
	}

	/**
	 * Gets the property value text field.
	 * 
	 * @return the property value text field
	 */
	private JTextField getPropertyValueTextField() {
		if (propertyValueTextField == null) {
			propertyValueTextField = new JTextField();
			propertyValueTextField
					.setMinimumSize(new Dimension(400, (int) propertyValueTextField.getSize().getHeight()));
			propertyValueTextField.setDragEnabled(true);
			propertyValueTextField.getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof Property) {
						((Property) getUserObject()).setValue(newValue);
					}
				}
			});
		}
		return propertyValueTextField;
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

		if (getUserObject() instanceof Property) {
			final Property property = (Property) getUserObject();

			getPropertyValueTextField().setText(property.getValue());
		}

		addInputValidators();

		LOGGER.debug("{} leaving", prefix);
	}
}
