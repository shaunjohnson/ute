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

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import net.lmxm.ute.beans.locations.AbstractHttpLocation;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.listeners.ChangeAdapter;

/**
 * The Class AbstractHttpLocationEditorPanel.
 */
public abstract class AbstractHttpLocationEditorPanel extends AbstractLocationEditorPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6846533044393594400L;

	/** The url text field. */
	private JTextField urlTextField = null;

	/**
	 * Instantiates a new abstract http location editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param actionListener the action listener
	 */
	public AbstractHttpLocationEditorPanel(final GuiComponentLabel guiComponentLabel,
			final ActionListener actionListener) {
		super(guiComponentLabel, actionListener);
	}

	/**
	 * Adds the http location common fields.
	 */
	protected final void addHttpLocationCommonFields() {
		final JPanel contentPanel = getContentPanel();

		addLocationCommonFields();

		addLabel(contentPanel, GuiComponentLabel.URL);
		contentPanel.add(getUrlTextField());
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
			urlTextField.getDocument().addDocumentListener(new ChangeAdapter() {
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
	protected final void loatHttpLocationCommonFieldData() {
		loadIdCommonFieldData();

		if (getUserObject() instanceof AbstractHttpLocation) {
			final AbstractHttpLocation abstractHttpLocation = (AbstractHttpLocation) getUserObject();

			getUrlTextField().setText(abstractHttpLocation.getUrl());
		}
		else {
			getUrlTextField().setText("");
		}
	}
}
