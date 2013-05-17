package net.lmxm.ute.gui.editors.tasks;

import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.sources.HttpSource;
import net.lmxm.ute.beans.sources.MavenRepositorySource;
import net.lmxm.ute.beans.tasks.HttpSourceTask;
import net.lmxm.ute.beans.tasks.MavenRepositorySourceTask;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.gui.editors.AbstractEditorPanel;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for Maven repository location combo boxes.
 */
public final class MavenRepositoryLocationComboBoxActionListener implements ActionListener {
    /**
     * Logger handle.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenRepositoryLocationComboBoxActionListener.class);

    private final AbstractEditorPanel abstractEditorPanel;

    private final JComboBox targetComboBox;

    public MavenRepositoryLocationComboBoxActionListener(final AbstractEditorPanel abstractEditorPanel, final JComboBox targetComboBox) {
        this.abstractEditorPanel = abstractEditorPanel;
        this.targetComboBox = targetComboBox;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object userObject = abstractEditorPanel.getUserObject();

        if (userObject instanceof MavenRepositorySourceTask) {
            final MavenRepositorySource source = ((MavenRepositorySourceTask) userObject).getSource();

            if (source == null) {
                LOGGER.error("Maven Repository source is null");
                throw new GuiException(ExceptionResourceType.UNEXPECTED_ERROR);
            }

            if (targetComboBox.getSelectedIndex() == -1) {
                source.setLocation(null);
            }
            else {
                final MavenRepositoryLocation location = (MavenRepositoryLocation) targetComboBox.getSelectedItem();
                source.setLocation(location);
            }
        }
    }
}
