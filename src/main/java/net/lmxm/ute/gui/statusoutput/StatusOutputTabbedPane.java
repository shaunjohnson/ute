package net.lmxm.ute.gui.statusoutput;

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.enums.ActionCommand;
import net.lmxm.ute.event.StatusChangeEventBus;
import net.lmxm.ute.executers.jobs.JobStatusEventBus;
import net.lmxm.ute.gui.UteActionListener;
import net.lmxm.ute.gui.workers.ExecuteJobWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Status output panel consisting of tabs.
 */
public final class StatusOutputTabbedPane extends JTabbedPane implements UteActionListener {
    /**
     * Construct a status output tabbed pane.
     */
    public StatusOutputTabbedPane() {
        setTabPlacement(JTabbedPane.TOP);
    }

    public void addTab(final ExecuteJobWorker jobWorker) {
        final Job job = jobWorker.getJob();
        final StatusOutputPanel statusOutputPanel = new StatusOutputPanel(jobWorker);
        final StatusOutputTab statusOutputTab = new StatusOutputTab(job, this);

        JobStatusEventBus.register(statusOutputPanel, statusOutputTab);
        StatusChangeEventBus.register(statusOutputPanel, statusOutputTab);

        insertTab("", null, statusOutputPanel, null, 0);
        setSelectedIndex(0);
        setTabComponentAt(0, statusOutputTab);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final ActionCommand actionCommand = ActionCommand.valueOf(actionEvent.getActionCommand());

        if (actionCommand == ActionCommand.CLOSE_ALL_TABS) {
            final int tabCount = getTabCount();
            final List<Integer> inactiveTabIndices = new ArrayList<Integer>();

            for (int i = 0; i < tabCount; i++) {
                final StatusOutputTab tab = (StatusOutputTab) getTabComponentAt(i);

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
                removeTabAt(index);
            }
        }
    }

    @Override
    public boolean supports(ActionCommand actionCommand) {
        return ActionCommand.CLOSE_ALL_TABS.equals(actionCommand);
    }
}
