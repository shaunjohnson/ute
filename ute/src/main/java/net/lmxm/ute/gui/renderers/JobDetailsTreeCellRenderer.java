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
package net.lmxm.ute.gui.renderers;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import net.lmxm.ute.beans.DescribableBean;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.GroovyTask;
import net.lmxm.ute.beans.tasks.HttpDownloadTask;
import net.lmxm.ute.beans.tasks.SubversionExportTask;
import net.lmxm.ute.beans.tasks.SubversionUpdateTask;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.gui.nodes.RootTreeNode;
import net.lmxm.ute.gui.utils.ImageUtil;

/**
 * The Class JobDetailsTreeCellRenderer.
 */
public final class JobDetailsTreeCellRenderer extends JLabel implements TreeCellRenderer {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3104743001619688388L;

	/** The bold font. */
	private final Font boldFont;

	/**
	 * Copy of default tree cell renderer. Used for reference.
	 */
	private DefaultTreeCellRenderer render = null;

	/**
	 * Default constructor.
	 */
	public JobDetailsTreeCellRenderer() {
		super();

		boldFont = new Font(Font.DIALOG, Font.BOLD, 14);
		render = new DefaultTreeCellRenderer();
	}

	/**
	 * Renders tree cells.
	 * 
	 * @param tree Tree where the cell resides
	 * @param value Value of the cell
	 * @param isSelected Indicates whether the cell is selected
	 * @param expanded Indicates whether the cell is expanded
	 * @param leaf Indicates whether this is a leaf
	 * @param row Row number
	 * @param hasFocus Indicates whether the cell has focus
	 * @return the tree cell renderer component
	 */
	@Override
	public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean isSelected,
			final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
		final Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
		Font font = tree.getFont();

		// Set tree cell icon and text
		if (userObject instanceof Job) {
			final Job job = (Job) userObject;
			final List<Task> tasks = job.getTasks();
			final int taskCount = tasks == null ? 0 : tasks.size();

			setIcon(ImageUtil.JOB_ICON);
			setText(job.getId() + " (" + taskCount + ")");
		}
		else if (userObject instanceof FileSystemDeleteTask) {
			final FileSystemDeleteTask fileSystemDeleteTask = (FileSystemDeleteTask) userObject;

			setIcon(ImageUtil.DELETE_ICON);
			setText(fileSystemDeleteTask.getId());
		}
		else if (userObject instanceof FileSystemLocation) {
			final FileSystemLocation fileSystemLocation = (FileSystemLocation) userObject;

			setIcon(ImageUtil.DRIVE_ICON);
			setText(fileSystemLocation.getId());
		}
		else if (userObject instanceof GroovyTask) {
			final GroovyTask groovyTask = (GroovyTask) userObject;

			setIcon(ImageUtil.GROOVY_ICON);
			setText(groovyTask.getId());
		}
		else if (userObject instanceof HttpDownloadTask) {
			final HttpDownloadTask httpDownloadTask = (HttpDownloadTask) userObject;

			setIcon(ImageUtil.FOLDER_IMPORT_ICON);
			setText(httpDownloadTask.getId());
		}
		else if (userObject instanceof HttpLocation) {
			final HttpLocation httpLocation = (HttpLocation) userObject;

			setIcon(ImageUtil.NETWORK_HUB_ICON);
			setText(httpLocation.getId());
		}
		else if (userObject instanceof Property) {
			final Property property = (Property) userObject;

			setIcon(ImageUtil.PROPERTY_ICON);
			setText(property.getId());
		}
		else if (userObject instanceof SubversionExportTask) {
			final SubversionExportTask subversionExportTask = (SubversionExportTask) userObject;

			setIcon(ImageUtil.EXPORT_ICON);
			setText(subversionExportTask.getId());
		}
		else if (userObject instanceof SubversionRepositoryLocation) {
			final SubversionRepositoryLocation subversionRepositoryLocation = (SubversionRepositoryLocation) userObject;

			setIcon(ImageUtil.SUBVERSION_ICON);
			setText(subversionRepositoryLocation.getId());
		}
		else if (userObject instanceof SubversionUpdateTask) {
			final SubversionUpdateTask subversionUpdateTask = (SubversionUpdateTask) userObject;

			setIcon(ImageUtil.CHECKOUT_ICON);
			setText(subversionUpdateTask.getId());
		}
		else if (userObject instanceof RootTreeNode) {
			font = boldFont;
			setIcon(null);
			setText(value.toString());
		}
		else {
			setIcon(null);
			setText(value.toString());
		}

		setFont(font);

		// Set tree cell tooltip text
		if (userObject instanceof DescribableBean) {
			final DescribableBean describableBean = (DescribableBean) userObject;

			setToolTipText(describableBean.getDescription());
		}

		// Set tree cell colors
		if (isSelected && hasFocus) {
			setBackground(render.getBackgroundSelectionColor());
			setForeground(render.getTextSelectionColor());
			setBorder(new LineBorder(render.getBackgroundSelectionColor()));
		}
		else if (isSelected && !hasFocus) {
			setBackground(tree.getBackground());
			setForeground(tree.getForeground());
			setBorder(new LineBorder(render.getBackgroundSelectionColor()));
		}
		else if (!isSelected && hasFocus) {
			setBackground(tree.getBackground());
			setForeground(tree.getForeground());
			setBorder(new LineBorder(render.getBackgroundSelectionColor()));
		}
		else {
			setBackground(tree.getBackground());
			setForeground(tree.getForeground());
			setBorder(new LineBorder(tree.getBackground()));
		}

		setEnabled(tree.isEnabled());
		setOpaque(true);

		return this;
	}
}
