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
package net.lmxm.ute.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.google.common.eventbus.Subscribe;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.event.JobStatusListener;
import net.lmxm.ute.event.StatusChangeEvent;
import net.lmxm.ute.event.StatusChangeListener;
import net.lmxm.ute.executers.jobs.JobStatusEvent;
import static net.lmxm.ute.executers.jobs.JobStatusEvent.JobStatusEventType.*;
import net.lmxm.ute.gui.workers.ExecuteJobWorker;
import net.lmxm.ute.resources.ImageUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class StatusOutputPanel.
 */
@SuppressWarnings("serial")
public class StatusOutputPanel extends JPanel implements StatusChangeListener {

	/** The Constant ERROR. */
	private static final String ERROR = "ERROR";

	/** The Constant FATAL. */
	private static final String FATAL = "FATAL";

	/** The Constant HEADING. */
	private static final String HEADING = "HEADING";

	/** The Constant IMPORTANT. */
	private static final String IMPORTANT = "IMPORTANT";

	/** The Constant INFO. */
	private static final String INFO = "INFO";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StatusOutputPanel.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1701575052922365046L;

	/** The Constant TOOLBAR_BORDER. */
	private static final Border TOOLBAR_BORDER = BorderFactory.createEmptyBorder(0, 0, 0, 10);

	/** The clear output button. */
	private JButton clearOutputButton = null;

	/** The job progress bar. */
	private JProgressBar jobProgressBar = null;

	/** The job worker. */
	private ExecuteJobWorker jobWorker = null;

	/** The job worker mutex. */
	private final Object jobWorkerMutex = new Object();

	/** The output button tool bar. */
	private JToolBar outputButtonToolBar = null;

	/** The output pane. */
	private JTextPane outputPane = null;

	/** The output scroll pane. */
	private JScrollPane outputScrollPane = null;

	/** The stop job button. */
	private JButton stopJobButton = null;

	/** The style context. */
	private final StyleContext styleContext;

	/**
	 * Instantiates a new status output panel.
	 * 
	 * @param job the job
	 */
	public StatusOutputPanel(final Job job) {
		super();

		styleContext = createStyleContext();

		setLayout(new BorderLayout());
		add(getOutputButtonToolBar(), BorderLayout.NORTH);
		add(getOutputScrollPane(), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder());

		final JProgressBar progressBar = getJobProgressBar();
		progressBar.setVisible(true);
		progressBar.setValue(0);
		progressBar.setMaximum(job.getTasks().size());
	}

	/**
	 * Creates the style context.
	 * 
	 * @return the style context
	 */
	private StyleContext createStyleContext() {
		final StyleContext styleContext = new StyleContext();
		final Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);

		final Style errorStyle = styleContext.addStyle(ERROR, defaultStyle);
		StyleConstants.setForeground(errorStyle, Color.RED);

		final Style fatalStyle = styleContext.addStyle(FATAL, defaultStyle);
		StyleConstants.setBold(fatalStyle, true);
		StyleConstants.setForeground(fatalStyle, Color.RED);

		final Style headingStyle = styleContext.addStyle(HEADING, defaultStyle);
		StyleConstants.setBold(headingStyle, true);
		StyleConstants.setFontSize(headingStyle, 16);
		StyleConstants.setSpaceAbove(headingStyle, 20);
		StyleConstants.setSpaceBelow(headingStyle, 10);

		final Style importantStyle = styleContext.addStyle(IMPORTANT, defaultStyle);
		StyleConstants.setBold(importantStyle, true);

		final Style infoStyle = styleContext.addStyle(INFO, defaultStyle);
		StyleConstants.setLeftIndent(infoStyle, 10);

		return styleContext;
	}

	/**
	 * Gets the clear output button.
	 * 
	 * @return the clear output button
	 */
	private JButton getClearOutputButton() {
		if (clearOutputButton == null) {
			clearOutputButton = new JButton() {
				{
					setIcon(ImageUtil.CLEAR_ICON);
					setText("Clear");

					addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(final ActionEvent e) {
							getOutputPane().setText("");
						}
					});
				}
			};
		}
		return clearOutputButton;
	}

	/**
	 * Gets the job progress bar.
	 * 
	 * @return the job progress bar
	 */
	private JProgressBar getJobProgressBar() {
		if (jobProgressBar == null) {
			jobProgressBar = new JProgressBar(SwingConstants.HORIZONTAL) {
				{
					setVisible(false);
				}
			};
		}

		return jobProgressBar;
	}

	/**
	 * Gets the output button tool bar.
	 * 
	 * @return the output button tool bar
	 */
	private JToolBar getOutputButtonToolBar() {
		if (outputButtonToolBar == null) {
			outputButtonToolBar = new JToolBar() {
				{
					setBorder(TOOLBAR_BORDER);

					add(getStopJobButton());
					add(getClearOutputButton());
					add(getJobProgressBar());
				}
			};
		}
		return outputButtonToolBar;
	}

	/**
	 * Instantiates a new status output pane.
	 * 
	 * @return the output pane
	 */
	public JTextPane getOutputPane() {
		if (outputPane == null) {
			outputPane = new JTextPane(new DefaultStyledDocument(styleContext)) {
				{
					setEditable(false);
				}
			};
		}

		return outputPane;
	}

	/**
	 * Gets the output scroll pane.
	 * 
	 * @return the output scroll pane
	 */
	private JScrollPane getOutputScrollPane() {
		if (outputScrollPane == null) {
			outputScrollPane = new JScrollPane() {
				{
					setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					setViewportView(getOutputPane());
					setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				}
			};
		}
		return outputScrollPane;
	}

	/**
	 * Gets the status change listener.
	 * 
	 * @return the status change listener
	 */
	public StatusChangeListener getStatusChangeListener() {
		return this;
	}

	/**
	 * Gets the stop job button.
	 * 
	 * @return the stop job button
	 */
	private JButton getStopJobButton() {
		if (stopJobButton == null) {
			stopJobButton = new JButton() {
				{
					setText("Stop");
					setIcon(ImageUtil.STOP_JOB_ICON);

					addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(final ActionEvent e) {
							synchronized (jobWorkerMutex) {
								if (jobWorker != null) {
									final String prefix = "getStopJobButton().actionPerformed() :";

									LOGGER.debug("{} Sending cancel to job worker thread", prefix);

									jobWorker.cancel(true);
								}
							}
						}
					});
				}
			};
		}
		return stopJobButton;
	}

    @Subscribe
    public void handleJobStatusChange(final JobStatusEvent jobStatusEvent) {
        JobStatusEvent.JobStatusEventType eventType = jobStatusEvent.getEventType();
        if (eventType == JobAborted || eventType == JobCompleted || eventType == JobStopped) {
            jobIsNolongerRunning();
        }
        else if (eventType == TaskCompleted || eventType == TaskSkipped) {
            getJobProgressBar().setValue(getJobProgressBar().getValue() + 1);
        }
    }

	/**
	 * Job is nolonger running.
	 */
	private void jobIsNolongerRunning() {
		synchronized (jobWorkerMutex) {
			// Clear connection to worker thread
			jobWorker = null;

			// Update GUI
			getStopJobButton().setEnabled(false);
			getJobProgressBar().setVisible(false);
		}
	}

	/**
	 * Sets the job worker.
	 * 
	 * @param jobWorker the new job worker
	 */
	public void setJobWorker(final ExecuteJobWorker jobWorker) {
		synchronized (jobWorkerMutex) {
			this.jobWorker = jobWorker;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.StatusChangeListener#statusChange(net.lmxm.ute.listeners .StatusChangeEvent)
	 */
	@Override
	public void statusChange(final StatusChangeEvent statusChangeEvent) {
		final String styleName;

		switch (statusChangeEvent.getEventType()) {
			case ERROR:
				styleName = ERROR;
				break;
			case FATAL:
				styleName = FATAL;
				break;
			case HEADING:
				styleName = HEADING;
				break;
			case IMPORTANT:
				styleName = IMPORTANT;
				break;
			case INFO:
				styleName = INFO;
				break;
			default:
				throw new IllegalArgumentException("Unsupported event type " + statusChangeEvent.getEventType());
		}

		final JTextPane outputPane = getOutputPane();
		final Document document = outputPane.getDocument();

		try {
			document.insertString(document.getLength(), statusChangeEvent.getMessage() + "\n",
					styleContext.getStyle(styleName));
		}
		catch (final BadLocationException e) {
			e.printStackTrace();
		}

		outputPane.setCaretPosition(document.getLength());
	}
}
