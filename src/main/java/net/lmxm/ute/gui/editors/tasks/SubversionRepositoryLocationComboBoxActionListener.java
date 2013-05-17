package net.lmxm.ute.gui.editors.tasks;

import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.sources.MavenRepositorySource;
import net.lmxm.ute.beans.sources.SubversionRepositorySource;
import net.lmxm.ute.beans.tasks.MavenRepositorySourceTask;
import net.lmxm.ute.beans.tasks.SubversionRepositorySourceTask;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.gui.editors.AbstractEditorPanel;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for Subversion repository location combo boxes.
 */
public final class SubversionRepositoryLocationComboBoxActionListener implements ActionListener {
    /**
     * Logger handle.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubversionRepositoryLocationComboBoxActionListener.class);

    private final AbstractEditorPanel abstractEditorPanel;

    private final JComboBox targetComboBox;

    public SubversionRepositoryLocationComboBoxActionListener(final AbstractEditorPanel abstractEditorPanel, final JComboBox targetComboBox) {
        this.abstractEditorPanel = abstractEditorPanel;
        this.targetComboBox = targetComboBox;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object userObject = abstractEditorPanel.getUserObject();

        if (userObject instanceof SubversionRepositorySourceTask) {
            final SubversionRepositorySource source = ((SubversionRepositorySourceTask) userObject).getSource();

            if (source == null) {
                LOGGER.error("Subversion Repository source is null");
                throw new GuiException(ExceptionResourceType.UNEXPECTED_ERROR);
            }

            if (targetComboBox.getSelectedIndex() == -1) {
                source.setLocation(null);
            }
            else {
                final SubversionRepositoryLocation location = (SubversionRepositoryLocation) targetComboBox
                        .getSelectedItem();
                source.setLocation(location);
            }
        }
    }
}
