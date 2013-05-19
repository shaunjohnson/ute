package net.lmxm.ute.gui;

import javax.swing.*;

/**
 * File chooser configured for saving UTE configuration files.
 */
public final class SaveFileChooser extends JFileChooser {
    public SaveFileChooser(String currentDirectory) {
        super(currentDirectory);

        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setDialogType(JFileChooser.SAVE_DIALOG);
        setFileFilter(getFileFilter());
    }
}
