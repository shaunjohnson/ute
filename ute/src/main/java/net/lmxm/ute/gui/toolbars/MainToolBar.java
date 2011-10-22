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
package net.lmxm.ute.gui.toolbars;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentType;

/**
 * The Class MainToolBar.
 */
public class MainToolBar extends AbstractToolBar implements TreeSelectionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -354062493293031844L;

	/** The add job button. */
	private JButton addJobButton = null;

	/** The add location button. */
	private JButton addLocationButton = null;

	/** The add preference button. */
	private JButton addPreferenceButton = null;

	/** The add property button. */
	private JButton addPropertyButton = null;

	/**
	 * Instantiates a new main tool bar.
	 * 
	 * @param actionListener the action listener
	 */
	public MainToolBar(ActionListener actionListener) {
		super(actionListener);

		setBorder(TOOLBAR_BORDER);

		add(getExecuteJobButton());
		add(getAddJobButton());
		add(getAddLocationButton());
		add(getAddPropertyButton());
		add(getAddPreferenceButton());
	}

	/** The execute job button. */
	private JButton executeJobButton = null;

	/**
	 * Gets the execute job button.
	 * 
	 * @return the execute job button
	 */
	private JButton getExecuteJobButton() {
		if (executeJobButton == null) {
			executeJobButton = GuiComponentFactory.createButton(GuiComponentType.EXECUTE_JOB_BUTTON, getActionListener());
			executeJobButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return executeJobButton;
	}

	/**
	 * Gets the adds the job button.
	 * 
	 * @return the adds the job button
	 */
	private JButton getAddJobButton() {
		if (addJobButton == null) {
			addJobButton = GuiComponentFactory.createButton(GuiComponentType.ADD_JOB_BUTTON, getActionListener());
			addJobButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return addJobButton;
	}

	/**
	 * Gets the adds the location button.
	 * 
	 * @return the adds the location button
	 */
	private JButton getAddLocationButton() {
		if (addLocationButton == null) {
			addLocationButton = GuiComponentFactory.createButton(GuiComponentType.ADD_LOCATION_BUTTON, getActionListener());
			addLocationButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return addLocationButton;
	}

	/**
	 * Gets the adds the preference button.
	 * 
	 * @return the adds the preference button
	 */
	private JButton getAddPreferenceButton() {
		if (addPreferenceButton == null) {
			addPreferenceButton = GuiComponentFactory.createButton(GuiComponentType.ADD_PREFERENCE_BUTTON, getActionListener());
			addPreferenceButton.setEnabled(false); // TODO disabled since it is not implemented
		}
		return addPreferenceButton;
	}

	/**
	 * Gets the adds the property button.
	 * 
	 * @return the adds the property button
	 */
	private JButton getAddPropertyButton() {
		if (addPropertyButton == null) {
			addPropertyButton = GuiComponentFactory.createButton(GuiComponentType.ADD_PROPERTY_BUTTON, getActionListener());
		}
		return addPropertyButton;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treeSelectionEvent.getNewLeadSelectionPath()
				.getLastPathComponent();

		getExecuteJobButton().setEnabled(treeNode.getUserObject() instanceof Job);
	}
}
