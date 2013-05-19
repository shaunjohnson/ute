package net.lmxm.ute.gui.maintree;

import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.gui.UteActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The listener interface for receiving mainTreeKey events. The class that is interested in processing a mainTreeKey
 * event implements this interface, and the object created with that class is registered with a component using the
 * component's <code>addMainTreeKeyListener<code> method. When
 * the mainTreeKey event occurs, that object's appropriate
 * method is invoked.
 */
class MainTreeKeyListener extends KeyAdapter {

    private final MainTree mainTree;

    private final ActionListener actionListener;

    public MainTreeKeyListener(final MainTree mainTree, final UteActionListener actionListener) {
        this.mainTree = checkNotNull(mainTree, "Main tree may not be null");
        this.actionListener = checkNotNull(actionListener, "Action listener may not be null");
    }

    /**
     * Creates the action event.
     *
     * @param actionCommand the action command
     * @return the action event
     */
    private ActionEvent createActionEvent(final String actionCommand) {
        return new ActionEvent(this, ActionEvent.ACTION_PERFORMED, actionCommand);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE) {
            final Object userObject = mainTree.getSelectedTreeObject();
            final ActionEvent actionEvent;

            if (userObject instanceof FileSystemLocation) {
                actionEvent = createActionEvent(ActionCommand.DELETE_FILE_SYSTEM_LOCATION.name());
            }
            else if (userObject instanceof HttpLocation) {
                actionEvent = createActionEvent(ActionCommand.DELETE_HTTP_LOCATION.name());
            }
            else if (userObject instanceof Job) {
                actionEvent = createActionEvent(ActionCommand.DELETE_JOB.name());
            }
            else if (userObject instanceof MavenRepositoryLocation) {
                actionEvent = createActionEvent(ActionCommand.DELETE_MAVEN_REPOSITORY_LOCATION.name());
            }
            else if (userObject instanceof Preference) {
                actionEvent = createActionEvent(ActionCommand.DELETE_PREFERENCE.name());
            }
            else if (userObject instanceof Property) {
                actionEvent = createActionEvent(ActionCommand.DELETE_PROPERTY.name());
            }
            else if (userObject instanceof SubversionRepositoryLocation) {
                actionEvent = createActionEvent(ActionCommand.DELETE_SUBVERSION_REPOSITORY_LOCATION.name());
            }
            else if (userObject instanceof Task) {
                actionEvent = createActionEvent(ActionCommand.DELETE_TASK.name());
            }
            else {
                actionEvent = null;
            }

            if (actionEvent != null) {
                actionListener.actionPerformed(actionEvent);
            }
        }
    }
}
