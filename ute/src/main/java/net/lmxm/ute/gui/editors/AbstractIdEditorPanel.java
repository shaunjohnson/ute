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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.listeners.IdChangeEvent;
import net.lmxm.ute.listeners.IdChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AbstractIdEditorPanel.
 */
public abstract class AbstractIdEditorPanel extends AbstractEditorPanel {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractIdEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7144352139412573257L;

	/** The id change listeners. */
	private final List<IdChangeListener> idChangeListeners = new ArrayList<IdChangeListener>();

	/** The identifiable bean. */
	private IdentifiableBean identifiableBean = null;

	/** The id text field. */
	private JTextField idTextField = null;

	/**
	 * Instantiates a new abstract id editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param actionListener the action listener
	 */
	public AbstractIdEditorPanel(final GuiComponentLabel guiComponentLabel, final ActionListener actionListener) {
		super(guiComponentLabel, actionListener);
	}

	/**
	 * Adds the id change listener.
	 * 
	 * @param idChangeListener the id change listener
	 */
	public final void addIdChangeListener(final IdChangeListener idChangeListener) {
		idChangeListeners.add(idChangeListener);
	}

	/**
	 * Adds the id common fields.
	 */
	protected final void addIdCommonFields() {
		final JPanel contentPanel = getContentPanel();

		addLabel(contentPanel, GuiComponentLabel.ID);
		contentPanel.add(getIdTextField());
	}

	/**
	 * Fire id changed event.
	 * 
	 * @param identifiableBean the identifiable bean
	 */
	private void fireIdChangedEvent(final IdentifiableBean identifiableBean) {
		final IdChangeEvent idChangeEvent = new IdChangeEvent(this, identifiableBean);

		for (final IdChangeListener idChangeListener : idChangeListeners) {
			idChangeListener.idChanged(idChangeEvent);
		}
	}

	/**
	 * Gets the id text field.
	 * 
	 * @return the id text field
	 */
	protected final JTextField getIdTextField() {
		if (idTextField == null) {
			idTextField = new JTextField();
			idTextField.setMinimumSize(new Dimension(400, (int) idTextField.getSize().getHeight()));

			idTextField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(final DocumentEvent documentEvent) {
					idChanged(documentEvent);
				}

				private void idChanged(final DocumentEvent documentEvent) {
					try {
						final Document document = documentEvent.getDocument();
						final String newId = document.getText(0, document.getLength());
						identifiableBean.setId(newId);

						fireIdChangedEvent(identifiableBean);
					}
					catch (final BadLocationException e) {
						e.printStackTrace(); // TODO Throw appropriate exception
					}
				}

				@Override
				public void insertUpdate(final DocumentEvent documentEvent) {
					idChanged(documentEvent);
				}

				@Override
				public void removeUpdate(final DocumentEvent documentEvent) {
					idChanged(documentEvent);
				}
			});
		}

		return idTextField;
	}

	/**
	 * Load id common field data.
	 * 
	 * @param identifiableBean the identifiable bean
	 */
	protected final void loadIdCommonFieldData(final IdentifiableBean identifiableBean) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, identifiableBean={}", prefix, identifiableBean);

		this.identifiableBean = identifiableBean;

		if (identifiableBean == null) {
			getIdTextField().setText("");
		}
		else {
			getIdTextField().setText(identifiableBean.getId());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
