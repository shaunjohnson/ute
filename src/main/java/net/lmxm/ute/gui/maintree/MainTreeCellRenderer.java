/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executer.
 * 
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.gui.maintree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import net.lmxm.ute.beans.DescribableBean;
import net.lmxm.ute.beans.EnabledStateBean;
import net.lmxm.ute.beans.Preference;
import net.lmxm.ute.beans.Property;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.locations.FileSystemLocation;
import net.lmxm.ute.beans.locations.HttpLocation;
import net.lmxm.ute.beans.locations.MavenRepositoryLocation;
import net.lmxm.ute.beans.locations.SubversionRepositoryLocation;
import net.lmxm.ute.beans.tasks.*;
import net.lmxm.ute.gui.maintree.nodes.RootTreeNode;
import net.lmxm.ute.resources.ImageUtil;

/**
 * The Class MainTreeCellRenderer.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public final class MainTreeCellRenderer extends JLabel implements TreeCellRenderer {

	/** The bold font. */
	private static final Font boldFont;

	/** The Constant disabledFont. */
	private static final Font disabledFont;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3104743001619688388L;

	static {
		// Setup fonts
		boldFont = new Font(Font.DIALOG, Font.BOLD, 14);

		final Font defaultFont = UIManager.getDefaults().getFont("Tree.font");

		final Map attributes = defaultFont.getAttributes();
		attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
		disabledFont = new Font(attributes);
	}

	/**
	 * Copy of default tree cell renderer. Used for reference.
	 */
	private DefaultTreeCellRenderer render = null;

	/**
	 * Default constructor.
	 */
	public MainTreeCellRenderer() {
		super();

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
		setFont(tree.getFont());
		Color foregroundColor = tree.getForeground();

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

			setIcon(fileSystemDeleteTask.getEnabled() ? ImageUtil.FILE_SYSTEM_DELETE_TASK_ICON
					: ImageUtil.FILE_SYSTEM_DELETE_TASK_DISABLED_ICON);
			setText(fileSystemDeleteTask.getId());
		}
		else if (userObject instanceof FileSystemLocation) {
			final FileSystemLocation fileSystemLocation = (FileSystemLocation) userObject;

			setIcon(ImageUtil.DRIVE_ICON);
			setText(fileSystemLocation.getId());
		}
		else if (userObject instanceof FindReplaceTask) {
			final FindReplaceTask findReplaceTask = (FindReplaceTask) userObject;

			setIcon(findReplaceTask.getEnabled() ? ImageUtil.FIND_REPLACE_ICON : ImageUtil.FIND_REPLACE_DISABLED_ICON);
			setText(findReplaceTask.getId());
		}
		else if (userObject instanceof GroovyTask) {
			final GroovyTask groovyTask = (GroovyTask) userObject;

			setIcon(groovyTask.getEnabled() ? ImageUtil.GROOVY_TASK_ICON : ImageUtil.GROOVY_TASK_DISABLED_ICON);
			setText(groovyTask.getId());
		}
		else if (userObject instanceof HttpDownloadTask) {
			final HttpDownloadTask httpDownloadTask = (HttpDownloadTask) userObject;

			setIcon(httpDownloadTask.getEnabled() ? ImageUtil.HTTP_DOWNLOAD_TASK_ICON
					: ImageUtil.HTTP_DOWNLOAD_TASK_DISABLED_ICON);
			setText(httpDownloadTask.getId());
		}
		else if (userObject instanceof HttpLocation) {
			final HttpLocation httpLocation = (HttpLocation) userObject;

			setIcon(ImageUtil.NETWORK_HUB_ICON);
			setText(httpLocation.getId());
		}
        else if (userObject instanceof MavenRepositoryDownloadTask) {
            final MavenRepositoryDownloadTask mavenRepositoryDownloadTask = (MavenRepositoryDownloadTask) userObject;

            setIcon(mavenRepositoryDownloadTask.getEnabled() ? ImageUtil.MAVEN_REPOSITORY_DOWNLOAD_TASK_ICON
                    : ImageUtil.MAVEN_REPOSITORY_DOWNLOAD_TASK_DISABLED_ICON);
            setText(mavenRepositoryDownloadTask.getId());
        }
        else if (userObject instanceof MavenRepositoryLocation) {
			final MavenRepositoryLocation mavenRepositoryLocation = (MavenRepositoryLocation) userObject;

			setIcon(ImageUtil.MAVEN_REPOSITORY_LOCATION_ICON);
			setText(mavenRepositoryLocation.getId());
		}
		else if (userObject instanceof Preference) {
			final Preference preference = (Preference) userObject;

			setIcon(ImageUtil.PREFERENCE_ICON);
			setText(preference.getId());
		}
		else if (userObject instanceof Property) {
			final Property property = (Property) userObject;

			setIcon(ImageUtil.PROPERTY_ICON);
			setText(property.getId());
		}
		else if (userObject instanceof SubversionExportTask) {
			final SubversionExportTask subversionExportTask = (SubversionExportTask) userObject;

			setIcon(subversionExportTask.getEnabled() ? ImageUtil.SUBVERSION_EXPORT_TASK_ICON
					: ImageUtil.SUBVERSION_EXPORT_TASK_DISABLED_ICON);
			setText(subversionExportTask.getId());
		}
		else if (userObject instanceof SubversionRepositoryLocation) {
			final SubversionRepositoryLocation subversionRepositoryLocation = (SubversionRepositoryLocation) userObject;

			setIcon(ImageUtil.SUBVERSION_REPOSITORY_LOCATION_ICON);
			setText(subversionRepositoryLocation.getId());
		}
		else if (userObject instanceof SubversionUpdateTask) {
			final SubversionUpdateTask subversionUpdateTask = (SubversionUpdateTask) userObject;

			setIcon(subversionUpdateTask.getEnabled() ? ImageUtil.SUBVERSION_UPDATE_TASK_ICON
					: ImageUtil.SUBVERSION_UPDATE_TASK_DISABLED_ICON);
			setText(subversionUpdateTask.getId());
		}
		else if (userObject instanceof RootTreeNode) {
			setFont(boldFont);
			setIcon(null);
			setText(value.toString());
		}
		else {
			setIcon(null);
			setText(value.toString());
		}

		// Apply disabled bean styles
		if (isDisabled(userObject)) {
			foregroundColor = Color.LIGHT_GRAY;
			setFont(disabledFont);
		}

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
			setForeground(foregroundColor);
			setBorder(new LineBorder(render.getBackgroundSelectionColor()));
		}
		else if (!isSelected && hasFocus) {
			setBackground(tree.getBackground());
			setForeground(foregroundColor);
			setBorder(new LineBorder(render.getBackgroundSelectionColor()));
		}
		else {
			setBackground(tree.getBackground());
			setForeground(foregroundColor);
			setBorder(new LineBorder(tree.getBackground()));
		}

		setEnabled(tree.isEnabled());
		setOpaque(true);

		return this;
	}

	/**
	 * Checks if is disabled.
	 * 
	 * @param userObject the user object
	 * @return true, if is disabled
	 */
	private boolean isDisabled(final Object userObject) {
		if (userObject instanceof EnabledStateBean) {
			return !((EnabledStateBean) userObject).getEnabled();
		}
		else {
			return false;
		}
	}
}
