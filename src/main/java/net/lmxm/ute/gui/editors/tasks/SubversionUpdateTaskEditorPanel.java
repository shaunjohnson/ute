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
package net.lmxm.ute.gui.editors.tasks;

import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;

import java.awt.event.ActionListener;

/**
 * The Class SubversionUpdateTaskEditorPanel.
 */
public final class SubversionUpdateTaskEditorPanel extends AbstractTaskEditorPanel {

	/**
	 * The Class SubversionUpdateTaskEditorToolBar.
	 */
	private static class SubversionUpdateTaskEditorToolBar extends AbstractTaskEditorToolBar {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -7138989729064070675L;

		/**
		 * Instantiates a new subversion update task editor tool bar.
		 * 
		 * @param actionListener the action listener
		 */
		public SubversionUpdateTaskEditorToolBar(final UteActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 12035329665657526L;

	/**
	 * Instantiates a new job editor panel.
	 * 
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public SubversionUpdateTaskEditorPanel(final ConfigurationHolder configurationHolder,
			final UteActionListener actionListener) {
		super(LabelResourceType.SUBVERSION_UPDATE_TASK, new SubversionUpdateTaskEditorToolBar(actionListener),
				configurationHolder, actionListener);

		addFields();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new SubversionUpdateTask(new SequentialJob());
	}
}
