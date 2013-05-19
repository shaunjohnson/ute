package net.lmxm.ute.gui.maintree;

import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.maintree.nodes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    private MainTree mainTree;

    public MainTreeMouseListener(MainTree mainTree) {
        this.mainTree = mainTree;
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
                JPopupMenu popupMenu = null;

                LOGGER.debug("{} Selected tree object is of type: {}", prefix, object.getClass().getName());

                // Find popup menu appropriate to the item selected
                if (object instanceof JobsRootTreeNode) {
                    popupMenu = mainTree.getJobsRootPopupMenu();
                }
                else if (object instanceof Job) {
                    popupMenu = mainTree.getJobPopupMenu();
                }
                else if (object instanceof Task) {
                    popupMenu = mainTree.getTaskPopupMenu();
                }
                else if (object instanceof FileSystemLocationsRootTreeNode) {
                    popupMenu = mainTree.getFileSystemLocationsRootPopupMenu();
                }
                else if (object instanceof FileSystemLocation) {
                    popupMenu = mainTree.getFileSystemLocationPopupMenu();
                }
                else if (object instanceof HttpLocationsRootTreeNode) {
                    popupMenu = mainTree.getHttpLocationsRootPopupMenu();
                }
                else if (object instanceof HttpLocation) {
                    popupMenu = mainTree.getHttpLocationPopupMenu();
                }
                else if (object instanceof MavenRepositoryLocationsRootTreeNode) {
                    popupMenu = mainTree.getMavenRepositoryLocationsRootPopupMenu();
                }
                else if (object instanceof MavenRepositoryLocation) {
                    popupMenu = mainTree.getMavenRepositoryLocationPopupMenu();
                }
                else if (object instanceof SubversionRepositoryLocationsRootTreeNode) {
                    popupMenu = mainTree.getSubversionRepositoryLocationsRootPopupMenu();
                }
                else if (object instanceof SubversionRepositoryLocation) {
                    popupMenu = mainTree.getSubversionRepositoryLocationPopupMenu();
                }
                else if (object instanceof PreferencesRootTreeNode) {
                    popupMenu = mainTree.getPreferencesRootPopupMenu();
                }
                else if (object instanceof Preference) {
                    popupMenu = mainTree.getPreferencePopupMenu();
                }
                else if (object instanceof PropertiesRootTreeNode) {
                    popupMenu = mainTree.getPropertiesRootPopupMenu();
                }
                else if (object instanceof Property) {
                    popupMenu = mainTree.getPropertyPopupMenu();
                }
                else {
                    LOGGER.debug("{} unsupported object; no popup will be displayed", prefix);
                }

                // Display the popup menu if a match was found
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
