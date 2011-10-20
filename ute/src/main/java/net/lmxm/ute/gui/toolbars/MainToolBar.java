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

import static net.lmxm.ute.gui.ActionConstants.ADD_JOB;
import static net.lmxm.ute.gui.ActionConstants.ADD_LOCATION;
import static net.lmxm.ute.gui.ActionConstants.ADD_PREFERENCE;
import static net.lmxm.ute.gui.ActionConstants.ADD_PROPERTY;
import static net.lmxm.ute.gui.ActionConstants.EXECUTE;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Class MainToolBar.
 */
@SuppressWarnings("serial")
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
			executeJobButton = new JButton() {{
				setText("Execute");
				setIcon(ImageUtil.EXECUTE_ICON);
				setToolTipText("Execute selected job");
				setEnabled(false);
	
				addActionListener(getActionListener());
				setActionCommand(EXECUTE);
			}};
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
			addJobButton = new JButton() {{
				setText("Add Job");
				setIcon(ImageUtil.ADD_JOB_ICON);
				setToolTipText("Add a new job");
				setEnabled(false); // TODO disabled since it is not implemented
	
				addActionListener(getActionListener());
				setActionCommand(ADD_JOB);
			}};
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
			addLocationButton = new JButton() {{
				setText("Add Location");
				setIcon(ImageUtil.ADD_LOCATION_ICON);
				setToolTipText("Add new location");
				setEnabled(false); // TODO disabled since it is not implemented
				
				addActionListener(getActionListener());
				setActionCommand(ADD_LOCATION);
			}};
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
			addPreferenceButton = new JButton() {{
				setIcon(ImageUtil.ADD_PREFERENCE_ICON);
				setToolTipText("Add new preference");
				setText("Add Preference");
				setEnabled(false); // TODO disabled since it is not implemented
				
				addActionListener(getActionListener());
				setActionCommand(ADD_PREFERENCE);
			}};
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
			addPropertyButton = new JButton() {{
				setIcon(ImageUtil.ADD_PROPERTY_ICON);
				setToolTipText("Add new property");
				setText("Add Property");
				
				addActionListener(getActionListener());
				setActionCommand(ADD_PROPERTY);
			}};
		}
		return addPropertyButton;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)treeSelectionEvent.getNewLeadSelectionPath().getLastPathComponent();
		
		getExecuteJobButton().setEnabled (treeNode.getUserObject() instanceof Job);
	}}
