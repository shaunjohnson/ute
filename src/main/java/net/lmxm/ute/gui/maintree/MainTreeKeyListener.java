package net.lmxm.ute.gui.maintree;

import com.google.common.collect.ImmutableMap;
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
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.lmxm.ute.enums.ActionCommand.*;

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

    private final Map<Class, ActionCommand> userObjectActionCommandMap;

    public MainTreeKeyListener(final MainTree mainTree, final UteActionListener actionListener) {
        this.mainTree = checkNotNull(mainTree, "Main tree may not be null");
        this.actionListener = checkNotNull(actionListener, "Action listener may not be null");

        userObjectActionCommandMap = buildUserObjectActionCommandMap();
    }

    private Map<Class, ActionCommand> buildUserObjectActionCommandMap() {
        final ImmutableMap.Builder<Class, ActionCommand> builder = new ImmutableMap.Builder<Class, ActionCommand>();

        builder.put(FileSystemLocation.class, DELETE_FILE_SYSTEM_LOCATION);
        builder.put(HttpLocation.class, DELETE_HTTP_LOCATION);
        builder.put(Job.class, DELETE_JOB);
        builder.put(MavenRepositoryLocation.class, DELETE_MAVEN_REPOSITORY_LOCATION);
        builder.put(Preference.class, DELETE_PREFERENCE);
        builder.put(Property.class, DELETE_PROPERTY);
        builder.put(SubversionRepositoryLocation.class, DELETE_SUBVERSION_REPOSITORY_LOCATION);
        builder.put(Task.class, DELETE_TASK);

        return builder.build();
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
            final ActionCommand actionCommand = userObjectActionCommandMap.get(userObject.getClass());

            if (actionCommand != null) {
                actionListener.actionPerformed(createActionEvent(actionCommand.name()));
            }
        }
    }
}
