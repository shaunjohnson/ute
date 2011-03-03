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
package net.lmxm.ute.gui.editors;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.Property;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PropertyEditor.
 */
public final class PropertyEditorPanel extends AbstractIdEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2153662249557350246L;

	/** The property value text field. */
	private JTextField propertyValueTextField = null;

	/**
	 * Instantiates a new property editor.
	 *
	 * @param configuration the configuration
	 */
	public PropertyEditorPanel(final Configuration configuration) {
		super(configuration, "Property");

		addIdCommonFields();

		final JPanel contentPanel = getContentPanel();

		contentPanel.add(new JLabel("Value:"));
		contentPanel.add(getPropertyValueTextField());
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
					.setMinimumSize(new Dimension(400, (int)propertyValueTextField.getSize().getHeight()));
		}
		return propertyValueTextField;
	}

	/**
	 * Load data.
	 *
	 * @param property the property
	 */
	public void loadData(final Property property) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, property={}", prefix, property);

		loadIdCommonFieldData(property);

		if (property == null) {
			getPropertyValueTextField().setText("");
		}
		else {
			getPropertyValueTextField().setText(property.getValue());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
