/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 *
 * This file is part of Universal Task Executor.
 *
 * Universal Task Executor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Universal Task Executor is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executor. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.gui.statusoutput;

import com.google.common.eventbus.Subscribe;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.event.StatusChangeEvent;
import net.lmxm.ute.executers.jobs.JobStatusEvent;
import net.lmxm.ute.resources.ImageUtil;
import net.lmxm.ute.resources.ResourcesUtils;
import net.lmxm.ute.resources.types.ButtonResourceType;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.lmxm.ute.executers.jobs.JobStatusEvent.JobStatusEventType.*;

/**
 * The Class StatusOutputTab.
 */
public final class StatusOutputTab extends JPanel implements ActionListener {

    /**
     * The Class CloseTabButton.
     */
    private class CloseTabButton extends JButton {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = -102643415573072210L;

        /**
         * Instantiates a new close tab button.
         *
         * @param actionListener the action listener
         */
        public CloseTabButton(final ActionListener actionListener) {
            setContentAreaFilled(false);
            setFocusable(false);
            setPreferredSize(new Dimension(17, 17));
            setToolTipText(ResourcesUtils.getResourceToolTipText(ButtonResourceType.CLOSE_TAB));
            setUI(new BasicButtonUI());

            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);

            addActionListener(actionListener);
            addMouseListener(new TabMouseListener());

            setRolloverEnabled(true);
        }

        /*
         * (non-Javadoc)
         * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
         */
        @Override
        protected void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            final Graphics2D graphics2D = (Graphics2D) graphics.create();

            if (getModel().isPressed()) {
                graphics2D.translate(1, 1);
            }

            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.setColor(getModel().isRollover() ? Color.RED : Color.BLACK);

            final int delta = 6;

            final int upperLeftX = delta;
            final int upperLeftY = delta;
            final int lowerRightX = getWidth() - delta - 1;
            final int lowerRightY = getHeight() - delta - 1;

            graphics2D.drawLine(upperLeftX, upperLeftY, lowerRightX, lowerRightY);

            final int upperRightX = getWidth() - delta - 1;
            final int upperRightY = delta;
            final int lowerLeftX = delta;
            final int lowerLeftY = getHeight() - delta - 1;

            graphics2D.drawLine(upperRightX, upperRightY, lowerLeftX, lowerLeftY);
            graphics2D.dispose();
        }

        /*
         * (non-Javadoc)
         * @see javax.swing.JButton#updateUI()
         */
        @Override
        public void updateUI() {
            // Not used
        }
    }

    /**
     * The listener interface for receiving tabMouse events. The class that is interested in processing a tabMouse event
     * implements this interface, and the object created with that class is registered with a component using the
     * component's <code>addTabMouseListener<code> method. When
     * the tabMouse event occurs, that object's appropriate
     * method is invoked.
     */
    private class TabMouseListener extends MouseAdapter {

        /**
         * Handle popup trigger.
         *
         * @param mouseEvent the mouse event
         */
        public void handlePopupTrigger(final MouseEvent mouseEvent) {
            if (mouseEvent.isPopupTrigger()) {
                getStatusOutputTabPopupMenu().show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            }
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
         * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseEntered(final MouseEvent mouseEvent) {
            final Component component = mouseEvent.getComponent();
            if (component instanceof JButton) {
                ((JButton) component).setBorderPainted(true);
            }
        }

        /*
         * (non-Javadoc)
         * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseExited(final MouseEvent mouseEvent) {
            final Component component = mouseEvent.getComponent();
            if (component instanceof JButton) {
                ((JButton) component).setBorderPainted(false);
            }
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

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 7600868895544725246L;

    /**
     * The close button.
     */
    private JButton closeButton = null;

    /**
     * The close button action listener.
     */
    private final ActionListener closeButtonActionListener = new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
            final int i = tabbedPane.indexOfTabComponent(StatusOutputTab.this);
            if (i != -1) {
                tabbedPane.remove(i);
            }
        }
    };

    /**
     * The job being monitored.
     */
    private final Job job;

    /**
     * The job running.
     */
    private boolean jobRunning = false;

    /**
     * The loader icon.
     */
    private JLabel loaderIcon = null;

    /**
     * The status output tab popup menu.
     */
    private StatusOutputTabPopupMenu statusOutputTabPopupMenu = null;

    /**
     * The tabbed pane.
     */
    private final JTabbedPane tabbedPane;

    /**
     * The title label.
     */
    private JLabel titleLabel = null;

    /**
     * The title text.
     */
    private final String titleText;

    /**
     * Instantiates a new status output tab.
     *
     * @param job the job being executed
     * @param tabbedPane the tabbed pane
     */
    public StatusOutputTab(final Job job, final JTabbedPane tabbedPane) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.job = checkNotNull(job, "Job not be null");
        this.tabbedPane = checkNotNull(tabbedPane, "Tabbed pane may not be null");
        this.titleText = job.getId();

        setOpaque(false);

        add(getTitleLabel());
        add(getLoaderIcon());
        add(getCloseButton());
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final ActionCommand actionCommand = ActionCommand.valueOf(actionEvent.getActionCommand());

        if (actionCommand == ActionCommand.CLOSE_ALL_TABS) {
            final int tabCount = tabbedPane.getTabCount();
            final List<Integer> inactiveTabIndices = new ArrayList<Integer>();

            for (int i = 0; i < tabCount; i++) {
                final StatusOutputTab tab = (StatusOutputTab) tabbedPane.getTabComponentAt(i);

                if (!tab.isJobRunning()) {
                    inactiveTabIndices.add(i);
                }
            }

            // Remove tabs in reverse order to avoid having to re-base indices
            Collections.sort(inactiveTabIndices, new Comparator<Integer>() {
                @Override
                public int compare(final Integer integer1, final Integer integer2) {
                    return integer2.compareTo(integer1);
                }
            });

            for (final int index : inactiveTabIndices) {
                tabbedPane.removeTabAt(index);
            }
        }
    }

    /**
     * Gets the close button.
     *
     * @return the close button
     */
    private JButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new CloseTabButton(closeButtonActionListener) {
                {
                    setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
                    setVisible(false);
                }

                private static final long serialVersionUID = 3020430249664319601L;
            };
        }

        return closeButton;
    }

    /**
     * Gets the loader icon.
     *
     * @return the loader icon
     */
    private JLabel getLoaderIcon() {
        if (loaderIcon == null) {
            loaderIcon = new JLabel(ImageUtil.LOADER_ICON) {
                {
                    setVisible(false);
                }

                private static final long serialVersionUID = 313313336758561472L;
            };
        }

        return loaderIcon;
    }

    /**
     * Gets the status output tab popup menu.
     *
     * @return the status output tab popup menu
     */
    protected StatusOutputTabPopupMenu getStatusOutputTabPopupMenu() {
        if (statusOutputTabPopupMenu == null) {
            statusOutputTabPopupMenu = new StatusOutputTabPopupMenu(this);
        }

        return statusOutputTabPopupMenu;
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    private JLabel getTitleLabel() {
        if (titleLabel == null) {
            titleLabel = new JLabel(titleText) {
                {
                    setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                }

                private static final long serialVersionUID = -5013925475998662493L;
            };
        }

        return titleLabel;
    }

    @Subscribe
    public void handleJobStatusChange(final JobStatusEvent jobStatusEvent) {
        if (jobStatusEvent.getJob().equals(job)) {
            JobStatusEvent.JobStatusEventType eventType = jobStatusEvent.getEventType();
            if (eventType == JobAborted || eventType == JobCompleted || eventType == JobStopped) {
                jobIsNotRunning();
            }
            else if (eventType == JobStarted) {
                jobIsRunning();
            }
        }
    }

    @Subscribe
    public void handleStatusChange(final StatusChangeEvent statusChangeEvent) {
        if (statusChangeEvent.getJob().equals(job) && statusChangeEvent.getEventType().isErrorType()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    getTitleLabel().setForeground(Color.RED);
                }
            });
        }
    }

    /**
     * Checks if is job running.
     *
     * @return true, if is job running
     */
    public boolean isJobRunning() {
        return jobRunning;
    }

    /**
     * Job is not running.
     */
    private void jobIsNotRunning() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jobRunning = false;
                getLoaderIcon().setVisible(false);
                getCloseButton().setVisible(true);
            }
        });
    }

    /**
     * Job is running.
     */
    private void jobIsRunning() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jobRunning = true;
                getLoaderIcon().setVisible(true);
                getCloseButton().setVisible(false);
            }
        });
    }
}
