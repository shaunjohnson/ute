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

import javax.swing.JPanel;

import net.lmxm.ute.gui.editors.AbstractIdEditorPanel;

/**
 * The Class AbstractLocationEditorPanel.
 */
public abstract class AbstractLocationEditorPanel extends AbstractIdEditorPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7277397468352612606L;

	/**
	 * Instantiates a new abstract location editor panel.
	 * 
	 * @param titleText the title text
	 */
	public AbstractLocationEditorPanel(final String titleText) {
		super(titleText);
	}

	/**
	 * Adds the location common fields.
	 */
	protected final void addLocationCommonFields() {
		final JPanel contentPanel = getContentPanel();

		addSeparator(contentPanel, "Location");
		addIdCommonFields();
	}
}