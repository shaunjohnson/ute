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

import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.gui.toolbars.AbstractJobEditorToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SequentialJobEditorPanel.
 */
public final class SequentialJobEditorPanel extends AbstractCommonEditorPanel {

	/**
	 * The Class SequentialJobEditorToolbar.
	 */
	private static class SequentialJobEditorToolbar extends AbstractJobEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -416992524098561294L;

		/**
		 * Instantiates a new sequential job editor toolbar.
		 * 
		 * @param actionListener the action listener
		 */
		public SequentialJobEditorToolbar(final ActionListener actionListener) {
			super(actionListener);

		}
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SequentialJobEditorPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -254745593912919513L;

	/**
	 * Instantiates a new sequential job editor panel.
	 * 
	 * @param actionListener the action listener
	 */
	public SequentialJobEditorPanel(final ActionListener actionListener) {
		super(LabelResourceType.SEQUENTIAL_JOB, new SequentialJobEditorToolbar(actionListener), actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new SequentialJob();
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

		if (getUserObject() instanceof SequentialJob) {
			// final SequentialJob sequentialJob = (SequentialJob) getUserObject();
			// TODO
		}

		LOGGER.debug("{} leaving", prefix);
	}
}
