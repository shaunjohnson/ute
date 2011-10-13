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
package net.lmxm.ute.gui;

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

import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.gui.utils.ImageUtil;
import net.lmxm.ute.gui.workers.ExecuteJobWorker;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class StatusOutputPanel.
 */
public class StatusOutputPanel extends JPanel implements JobStatusListener, StatusChangeListener {

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
			clearOutputButton = new JButton();
			clearOutputButton.setIcon(ImageUtil.CLEAR_ICON);
			clearOutputButton.setText("Clear");
			clearOutputButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					getOutputPane().setText("");
				}
			});
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
			jobProgressBar = new JProgressBar(SwingConstants.HORIZONTAL);
			jobProgressBar.setVisible(false);
		}

		return jobProgressBar;
	}

	/**
	 * Gets the job status listener.
	 * 
	 * @return the job status listener
	 */
	public JobStatusListener getJobStatusListener() {
		return this;
	}

	/**
	 * Gets the output button tool bar.
	 * 
	 * @return the output button tool bar
	 */
	private JToolBar getOutputButtonToolBar() {
		if (outputButtonToolBar == null) {
			outputButtonToolBar = new JToolBar();

			outputButtonToolBar.setBorder(TOOLBAR_BORDER);

			outputButtonToolBar.add(getStopJobButton());
			outputButtonToolBar.add(getClearOutputButton());
			outputButtonToolBar.add(getJobProgressBar());
		}
		return outputButtonToolBar;
	}

	/**
	 * Instantiates a new status output pane.
	 */
	public JTextPane getOutputPane() {
		if (outputPane == null) {
			outputPane = new JTextPane(new DefaultStyledDocument(styleContext));
			outputPane.setEditable(false);
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
			outputScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			outputScrollPane.setViewportView(getOutputPane());
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
			stopJobButton = new JButton();
			stopJobButton.setText("Stop");
			stopJobButton.setIcon(ImageUtil.STOP_JOB_ICON);
			stopJobButton.addActionListener(new ActionListener() {
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
		return stopJobButton;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobAborted()
	 */
	@Override
	public void jobAborted() {
		synchronized (jobWorkerMutex) {
			jobWorker = null;
			getStopJobButton().setEnabled(false);
			getJobProgressBar().setVisible(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobCompleted()
	 */
	@Override
	public void jobCompleted() {
		synchronized (jobWorkerMutex) {
			jobWorker = null;
			getStopJobButton().setEnabled(false);
			getJobProgressBar().setVisible(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobStopped()
	 */
	@Override
	public void jobStopped() {
		synchronized (jobWorkerMutex) {
			jobWorker = null;
			getStopJobButton().setEnabled(false);
			getJobProgressBar().setVisible(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskCompleted()
	 */
	@Override
	public void jobTaskCompleted() {
		getJobProgressBar().setValue(getJobProgressBar().getValue() + 1);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskSkipped()
	 */
	@Override
	public void jobTaskSkipped() {
		getJobProgressBar().setValue(getJobProgressBar().getValue() + 1);
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
