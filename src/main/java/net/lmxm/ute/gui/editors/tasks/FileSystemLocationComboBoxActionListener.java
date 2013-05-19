package net.lmxm.ute.gui.editors.tasks;

import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.beans.tasks.FileSystemTargetTask;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.gui.editors.AbstractEditorPanel;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for File system location combo boxes.
 */
public final class FileSystemLocationComboBoxActionListener implements ActionListener {
    /**
     * Logger handle.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemLocationComboBoxActionListener.class);

    private final AbstractEditorPanel abstractEditorPanel;

    private final JComboBox targetComboBox;

    public FileSystemLocationComboBoxActionListener(final AbstractEditorPanel abstractEditorPanel, final JComboBox targetComboBox) {
        this.abstractEditorPanel = abstractEditorPanel;
        this.targetComboBox = targetComboBox;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object userObject = abstractEditorPanel.getUserObject();

        if (userObject instanceof FileSystemTargetTask) {
            final FileSystemTarget target = ((FileSystemTargetTask) userObject).getTarget();

            if (target == null) {
                LOGGER.error("File system target is null");
                throw new GuiException(ExceptionResourceType.UNEXPECTED_ERROR);
            }

            if (targetComboBox.getSelectedIndex() == -1) {
                target.setLocation(null);
            }
            else {
                final FileSystemLocation location = (FileSystemLocation) targetComboBox.getSelectedItem();
                target.setLocation(location);
            }
        }
    }
}
