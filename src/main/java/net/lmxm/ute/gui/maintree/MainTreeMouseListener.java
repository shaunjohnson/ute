package net.lmxm.ute.gui.maintree;

import com.google.common.collect.ImmutableMap;
import net.lmxm.ute.beans.BeanType;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.TypeBean;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.*;
import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.maintree.nodes.*;
import net.lmxm.ute.gui.menus.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import static net.lmxm.ute.enums.ActionCommand.*;
import static net.lmxm.ute.enums.ActionCommand.DELETE_TASK;

/**
 * The listener interface for receiving mainTreeMouse events. The class that is interested in processing a
 * mainTreeMouse event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addMainTreeMouseListener<code> method. When
 * the mainTreeMouse event occurs, that object's appropriate
 * method is invoked.
 */
class MainTreeMouseListener extends MouseAdapter {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainTreeMouseListener.class);

    private final MainTree mainTree;

    private final Map<BeanType, JPopupMenu> userObjectPopupMenuMap;

    public MainTreeMouseListener(final MainTree mainTree, final UteActionListener actionListener) {
        this.mainTree = mainTree;

        userObjectPopupMenuMap = buildUserObjectPopupMenuMap(actionListener);
    }

    private Map<BeanType, JPopupMenu> buildUserObjectPopupMenuMap(final UteActionListener actionListener) {
        final ImmutableMap.Builder<BeanType, JPopupMenu> builder = new ImmutableMap.Builder<BeanType, JPopupMenu>();

        builder.put(BeanType.Job, new JobPopupMenu(actionListener));
        builder.put(BeanType.JobsRootTreeNode, new JobsRootPopupMenu(actionListener));
        builder.put(BeanType.FileSystemLocation, new FileSystemLocationPopupMenu(actionListener));
        builder.put(BeanType.FileSystemLocationsRootTreeNode, new FileSystemLocationsRootPopupMenu(actionListener));
        builder.put(BeanType.HttpLocation, new HttpLocationPopupMenu(actionListener));
        builder.put(BeanType.HttpLocationsRootTreeNode, new HttpLocationsRootPopupMenu(actionListener));
        builder.put(BeanType.MavenRepositoryLocation, new MavenRepositoryLocationPopupMenu(actionListener));
        builder.put(BeanType.MavenRepositoryLocationsRootTreeNode, new MavenRepositoryLocationsRootPopupMenu(actionListener));
        builder.put(BeanType.Preference, new PreferencePopupMenu(actionListener));
        builder.put(BeanType.PreferencesRootTreeNode, new PreferencesRootPopupMenu(actionListener));
        builder.put(BeanType.Property, new PropertyPopupMenu(actionListener));
        builder.put(BeanType.PropertiesRootTreeNode, new PropertiesRootPopupMenu(actionListener));
        builder.put(BeanType.SubversionRepositoryLocation, new SubversionRepositoryLocationsRootPopupMenu(actionListener));
        builder.put(BeanType.SubversionRepositoryLocationsRootTreeNode, new SubversionRepositoryLocationsRootPopupMenu(actionListener));
        builder.put(BeanType.Task, new TaskPopupMenu(actionListener));

        return builder.build();
    }

    /**
     * Handle popup trigger.
     *
     * @param mouseEvent the mouse event
     */
    public void handlePopupTrigger(final MouseEvent mouseEvent) {
        final String prefix = "mouseClicked() :";

        LOGGER.debug("{} entered", prefix);

        if (mouseEvent.isPopupTrigger()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();

            mainTree.selectTreeObjectAtLocation(x, y);
            final Object object = mainTree.getSelectedTreeObject();

            if (object != null) {
                LOGGER.debug("{} Selected tree object is of type: {}", prefix, object.getClass().getName());

                final TypeBean typeBean = (TypeBean)object;

                // Find popup menu appropriate to the item selected
                final JPopupMenu popupMenu = userObjectPopupMenuMap.get(typeBean.getType());
                if (popupMenu != null) {
                    popupMenu.show(mouseEvent.getComponent(), x, y);
                }
                else {
                    LOGGER.debug("{} no matching popup found", prefix);
                }
            }
            else {
                LOGGER.debug("{} no object selected", prefix);
            }
        }
        else {
            LOGGER.debug("{} not a popup trigger", prefix);
        }

        LOGGER.debug("{} leaving", prefix);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        handlePopupTrigger(mouseEvent);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        handlePopupTrigger(mouseEvent);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        handlePopupTrigger(mouseEvent);
    }
}
