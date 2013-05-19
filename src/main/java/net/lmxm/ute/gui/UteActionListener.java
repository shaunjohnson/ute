package net.lmxm.ute.gui;

import net.lmxm.ute.enums.ActionCommand;

import java.awt.event.ActionListener;

/**
 * UTE action listener, which includes additional functionality over the standard ActionListener.
 */
public interface UteActionListener extends ActionListener {
    /**
     * Determines if the provided action command is supported by the action listener.
     *
     * @param actionCommand Action Command to test
     * @return True if the action command is supported, otherwise false
     */
    boolean supports(final ActionCommand actionCommand);
}
