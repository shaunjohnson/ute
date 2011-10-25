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

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.lmxm.ute.beans.IdentifiableBean;

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

	/**
	 * Instantiates a new abstract id editor panel.
	 * 
	 * @param titleText the title text
	 */
	public AbstractIdEditorPanel(final String titleText, final ActionListener actionListener) {
		super(titleText, actionListener);
	}

	/**
	 * Adds the id common fields.
	 */
	protected final void addIdCommonFields() {
		final JPanel contentPanel = getContentPanel();

		addLabel(contentPanel, "ID");
		contentPanel.add(getIdTextField());
	}

	/**
	 * Load id common field data.
	 * 
	 * @param identifiableBean the identifiable bean
	 */
	protected final void loadIdCommonFieldData(final IdentifiableBean identifiableBean) {
		final String prefix = "loadData(): ";

		LOGGER.debug("{} entered, identifiableBean={}", prefix, identifiableBean);

		if (identifiableBean == null) {
			getIdTextField().setText("");
		}
		else {
			getIdTextField().setText(identifiableBean.getId());
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
