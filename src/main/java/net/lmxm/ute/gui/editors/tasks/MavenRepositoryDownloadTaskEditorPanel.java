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
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.MavenRepositoryDownloadTask;
import net.lmxm.ute.configuration.ConfigurationHolder;
import net.lmxm.ute.event.DocumentAdapter;
import net.lmxm.ute.gui.toolbars.AbstractTaskEditorToolBar;
import net.lmxm.ute.gui.validation.InputValidator;
import net.lmxm.ute.gui.validation.InputValidatorFactory;
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
		public MavenRepositoryDownloadTaskEditorToolBar(final ActionListener actionListener) {
			super(actionListener);
		}
	}

	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4516471145057934333L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenRepositoryDownloadTaskEditorPanel.class);

    /** The artifact coordinates text field. */
    private JTextField artifactCoordinatesTextField = null;

	/**
	 * Instantiates a new Maven repository download task editor panel.
	 *
	 * @param configurationHolder the configuration holder
	 * @param actionListener the action listener
	 */
	public MavenRepositoryDownloadTaskEditorPanel(final ConfigurationHolder configurationHolder,
                                                  final ActionListener actionListener) {
		super(LabelResourceType.MAVEN_REPOSITORY_DOWNLOAD_TASK, new MavenRepositoryDownloadTaskEditorToolBar(actionListener),
				configurationHolder, actionListener);

		addFields();
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
        getContentPanel().add(getArtifactCoordinatesTextField());
    }

    private void addInputValidators() {
        if (getUserObject() instanceof MavenRepositoryDownloadTask) {
            removeInputValidator(getArtifactCoordinatesTextField());

            final InputValidator inputValidator = InputValidatorFactory.createMavenArtifactCoordinatesValidator(
                    getArtifactCoordinatesTextField());

            getArtifactCoordinatesTextField().setInputVerifier(inputValidator);
            addInputValidator(inputValidator);
        }
    }

    /**
     * Gets the source relative path text field.
     *
     * @return the source relative path text field
     */
    private JTextField getArtifactCoordinatesTextField() {
        if (artifactCoordinatesTextField == null) {
            artifactCoordinatesTextField = new JTextField();
            artifactCoordinatesTextField.setFont(getMonospaceFont());
            artifactCoordinatesTextField.setMinimumSize(new Dimension(400, (int) artifactCoordinatesTextField.getSize()
                    .getHeight()));
            artifactCoordinatesTextField.setDragEnabled(true);
            artifactCoordinatesTextField.getDocument().addDocumentListener(new DocumentAdapter() {
                @Override
                public void valueChanged(final String newValue) {
                    if (getUserObject() instanceof MavenRepositoryDownloadTask) {
                        final MavenRepositoryDownloadTask mavenRepositoryDownloadTask = (MavenRepositoryDownloadTask) getUserObject();
                        mavenRepositoryDownloadTask.setArtifactCoordinates(newValue);
                    }
                }
            });
        }

        return artifactCoordinatesTextField;
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

            getArtifactCoordinatesTextField().setText(mavenRepositoryDownloadTask.getArtifactCoordinates());
        }

        addInputValidators();

        LOGGER.debug("{} leaving", prefix);
    }
}
