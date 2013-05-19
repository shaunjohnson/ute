package net.lmxm.ute.gui;

import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ApplicationResourceType;

import javax.swing.*;

/**
 * File chooser configured for saving UTE configuration files as something new.
 */
public final class SaveAsFileChooser extends JFileChooser {
    public SaveAsFileChooser(String currentDirectory) {
        super(currentDirectory);

        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setDialogType(JFileChooser.SAVE_DIALOG);
        setFileFilter(getFileFilter());
        setDialogTitle(ResourcesUtils.getResourceTitle(ApplicationResourceType.SAVE_FILE_AS));
    }
}
