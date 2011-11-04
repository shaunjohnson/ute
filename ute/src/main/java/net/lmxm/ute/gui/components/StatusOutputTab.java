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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

import net.lmxm.ute.gui.ActionConstants;
import net.lmxm.ute.gui.menus.StatusOutputTabPopupMenu;
import net.lmxm.ute.gui.utils.ImageUtil;
import net.lmxm.ute.listeners.JobStatusListener;
import net.lmxm.ute.listeners.StatusChangeEvent;
import net.lmxm.ute.listeners.StatusChangeListener;

import com.google.common.base.Preconditions;

/**
 * The Class StatusOutputTab.
 */
@SuppressWarnings("serial")
public class StatusOutputTab extends JPanel implements ActionListener, JobStatusListener, StatusChangeListener {

	/**
	 * The Class CloseTabButton.
	 */
	private class CloseTabButton extends JButton {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -102643415573072210L;

		/**
		 * Instantiates a new close tab button.
		 */
		public CloseTabButton(final ActionListener actionListener) {
			setContentAreaFilled(false);
			setFocusable(false);
			setPreferredSize(new Dimension(17, 17));
			setToolTipText("Close this tab");
			setUI(new BasicButtonUI());

			setBorder(BorderFactory.createEtchedBorder());
			setBorderPainted(false);

			addActionListener(closeButtonActionListener);
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
	 * 
	 * @see TabMouseEvent
	 */
	private class TabMouseListener extends MouseAdapter {

		/**
		 * Handle popup trigger.
		 * 
		 * @param mouseEvent the mouse event
		 */
		public void handlePopupTrigger(final MouseEvent mouseEvent) {
			if (mouseEvent.isPopupTrigger()) {
				final int x = mouseEvent.getX();
				final int y = mouseEvent.getY();

				getStatusOutputTabPopupMenu().show(mouseEvent.getComponent(), x, y);
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

		@Override
		public void mouseEntered(final MouseEvent mouseEvent) {
			final Component component = mouseEvent.getComponent();
			if (component instanceof JButton) {
				((JButton) component).setBorderPainted(true);
			}
		}

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

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7600868895544725246L;

	/** The close button. */
	private JButton closeButton = null;

	/** The close button action listener. */
	private final ActionListener closeButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent e) {
			final int i = tabbedPane.indexOfTabComponent(StatusOutputTab.this);
			if (i != -1) {
				tabbedPane.remove(i);
			}
		}
	};

	/** The loader icon. */
	private JLabel loaderIcon = null;

	/** The status output tab popup menu. */
	private StatusOutputTabPopupMenu statusOutputTabPopupMenu = null;

	/** The tabbed pane. */
	private final JTabbedPane tabbedPane;

	/** The title label. */
	private JLabel titleLabel = null;

	/** The title text. */
	private final String titleText;

	/**
	 * Instantiates a new status output tab.
	 * 
	 * @param tabbedPane the tabbed pane
	 * @param titleText the title
	 */
	public StatusOutputTab(final JTabbedPane tabbedPane, final String titleText) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));

		Preconditions.checkNotNull(tabbedPane, "Tabbed pane may not be null");
		Preconditions.checkNotNull(titleText, "Title text may not be null");

		this.tabbedPane = tabbedPane;
		this.titleText = titleText;

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
		final String actionCommand = actionEvent.getActionCommand();

		if (actionCommand.equals(ActionConstants.CLOSE_ALL_TABS)) {
			tabbedPane.removeAll();
		}
	}

	/**
	 * Gets the close button.
	 * 
	 * @return the close button
	 */
	private JButton getCloseButton() {
		if (closeButton == null) {
			closeButton = new CloseTabButton(this) {
				{
					setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
					setVisible(false);
				}
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
			};
		}

		return titleLabel;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobAborted()
	 */
	@Override
	public void jobAborted() {
		jobIsNotRunning();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobCompleted()
	 */
	@Override
	public void jobCompleted() {
		jobIsNotRunning();
	}

	/**
	 * Job is not running.
	 */
	private void jobIsNotRunning() {
		getLoaderIcon().setVisible(false);
		getCloseButton().setVisible(true);
	}

	/**
	 * Job is running.
	 */
	private void jobIsRunning() {
		getLoaderIcon().setVisible(true);
		getCloseButton().setVisible(false);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobStarted()
	 */
	@Override
	public void jobStarted() {
		jobIsRunning();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobStopped()
	 */
	@Override
	public void jobStopped() {
		jobIsNotRunning();
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskCompleted()
	 */
	@Override
	public void jobTaskCompleted() {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskSkipped()
	 */
	@Override
	public void jobTaskSkipped() {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.JobStatusListener#jobTaskStarted()
	 */
	@Override
	public void jobTaskStarted() {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.listeners.StatusChangeListener#statusChange(net.lmxm.ute.listeners.StatusChangeEvent)
	 */
	@Override
	public void statusChange(final StatusChangeEvent statusChangeEvent) {
		if (statusChangeEvent.getEventType().isErrorType()) {
			getTitleLabel().setForeground(Color.RED);
		}
	}
}
