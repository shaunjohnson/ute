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
import net.lmxm.ute.beans.tasks.MavenRepositoryDownloadTask;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.components.MavenArtifactsTableModel;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;
import net.lmxm.ute.resources.types.LabelResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Class MavenRepositoryDownloadTaskEditorPanel.
 */
public final class MavenRepositoryDownloadTaskEditorPanel extends AbstractTaskEditorPanel {

    /**
	 * The Class MavenRepositoryDownloadTaskEditorToolBar.
	 */
	private static class MavenRepositoryDownloadTaskEditorToolBar extends AbstractTaskEditorToolBar {

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = -7740716789050607034L;

		/**
		 * Instantiates a new Maven repository download task editor tool bar.
		 *
		 * @param actionListener the action listener
		 */
		public MavenRepositoryDownloadTaskEditorToolBar(final UteActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4516471145057934333L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenRepositoryDownloadTaskEditorPanel.class);

    private JPanel artifactsPane = null;

    private JScrollPane artifactsScrollPane = null;

    private JTable artifactsTable = null;

	/**
	 * Instantiates a new Maven repository download task editor panel.
	 *
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public MavenRepositoryDownloadTaskEditorPanel(final ConfigurationHolder configurationHolder,
                                                  final UteActionListener actionListener) {
		super(LabelResourceType.MAVEN_REPOSITORY_DOWNLOAD_TASK, new MavenRepositoryDownloadTaskEditorToolBar(actionListener),
				configurationHolder, actionListener);

		addFields();
	}

    /**
     * Gets the artifacts pane.
     *
     * @return the artifacts pane
     */
    private JPanel getArtifactsPane() {
        if (artifactsPane == null) {
            artifactsPane = new JPanel();
            artifactsPane.setLayout(new BorderLayout());
            artifactsPane.add(getArtifactsScrollPane(), BorderLayout.CENTER);
            artifactsPane.setMaximumSize(new Dimension(400, 100));
        }

        return artifactsPane;
    }

    /**
     * Gets the artifacts scroll pane.
     *
     * @return the artifacts scroll pane
     */
    private JScrollPane getArtifactsScrollPane() {
        if (artifactsScrollPane == null) {
            artifactsScrollPane = new JScrollPane(getArtifactsTable());
            artifactsScrollPane.setMaximumSize(new Dimension(400, 100));
        }

        return artifactsScrollPane;
    }

    /**
     * Gets the artifacts table.
     *
     * @return the artifacts table
     */
    private JTable getArtifactsTable() {
        if (artifactsTable == null) {
            artifactsTable = new JTable();
            artifactsTable.setFillsViewportHeight(true);
        }

        return artifactsTable;
    }

    /*
      * (non-Javadoc)
      * @see net.lmxm.ute.gui.editors.tasks.AbstractTaskEditorPanel#addFields()
      */
    @Override
    protected void addFields() {
        super.addFields();

        addSeparator(LabelResourceType.ARTIFACTS);

        addRequiredLabel(LabelResourceType.ARTIFACT_COORDINATES);
        getContentPanel().add(getArtifactsPane());
    }

    private void addInputValidators() {
        if (getUserObject() instanceof MavenRepositoryDownloadTask) {
//            removeInputValidator(getArtifactCoordinatesTextField());
//
//            final InputValidator inputValidator = InputValidatorFactory.createMavenArtifactCoordinatesValidator(
//                    getArtifactCoordinatesTextField());
//
//            getArtifactCoordinatesTextField().setInputVerifier(inputValidator);
//            addInputValidator(inputValidator);
        }
    }

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.editors.AbstractEditorPanel#getEditedObjectClass()
	 */
	@Override
	protected Object getEditedObjectClass() {
		return new MavenRepositoryDownloadTask(new SequentialJob());
	}

    /*
      * (non-Javadoc)
      * @see net.lmxm.ute.gui.editors.tasks.AbstractTaskEditorPanel#loadData()
      */
    @Override
    public void loadData() {
        final String prefix = "loadData(): ";

        LOGGER.debug("{} entered", prefix);

        super.loadData();

        if (getUserObject() instanceof MavenRepositoryDownloadTask) {
            final MavenRepositoryDownloadTask mavenRepositoryDownloadTask = (MavenRepositoryDownloadTask) getUserObject();

            getArtifactsTable().setModel(new MavenArtifactsTableModel(mavenRepositoryDownloadTask.getArtifacts()));
        }

        addInputValidators();

        LOGGER.debug("{} leaving", prefix);
    }
}
