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
package net.lmxm.ute.gui.utils;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * The Class ImageUtil.
 */
public final class ImageUtil {

	/** The Constant ABOUT_ICON. */
	public static final ImageIcon ABOUT_ICON;

	/** The Constant ADD_JOB_ICON. */
	public static final Icon ADD_JOB_ICON;

	/** The Constant ADD_LOCATION_ICON. */
	public static final Icon ADD_LOCATION_ICON;

	/** The Constant ADD_PREFERENCE_ICON. */
	public static final Icon ADD_PREFERENCE_ICON;

	/** The Constant ADD_PROPERTY_ICON. */
	public static final Icon ADD_PROPERTY_ICON;

	/** The Constant APPLICATION_ICON_IMAGE. */
	public static final Image APPLICATION_ICON_IMAGE;

	/** The Constant CHECKOUT_DISABLED_ICON. */
	public static final Icon CHECKOUT_DISABLED_ICON;

	/** The Constant CHECKOUT_ICON. */
	public static final Icon CHECKOUT_ICON;

	/** The Constant CLEAR_ICON. */
	public static final Icon CLEAR_ICON;

	/** The Constant DELETE_PROPERTY_ICON. */
	public static final Icon DELETE_PROPERTY_ICON;

	/** The Constant DRIVE_ICON. */
	public static final Icon DRIVE_ICON;

	/** The Constant EDIT_PREFERENCES_ICON. */
	public static final Icon EDIT_PREFERENCES_ICON;

	/** The Constant EXECUTE_ICON. */
	public static final Icon EXECUTE_ICON;

	/** The Constant EXIT_ICON. */
	public static final Icon EXIT_ICON;

	/** The Constant EXPORT_DISABLED_ICON. */
	public static final Icon EXPORT_DISABLED_ICON;

	/** The Constant EXPORT_ICON. */
	public static final Icon EXPORT_ICON;

	/** The Constant FILE_SYSTEM_DELETE_TASK_DISABLED_ICON. */
	public static final Icon FILE_SYSTEM_DELETE_TASK_DISABLED_ICON;

	/** The Constant FILE_SYSTEM_DELETE_TASK_ICON. */
	public static final Icon FILE_SYSTEM_DELETE_TASK_ICON;

	/** The Constant FIND_REPLACE_DISABLED_ICON. */
	public static final Icon FIND_REPLACE_DISABLED_ICON;

	/** The Constant FIND_REPLACE_ICON. */
	public static final Icon FIND_REPLACE_ICON;

	/** The Constant FOLDER_IMPORT_DISABLED_ICON. */
	public static final Icon FOLDER_IMPORT_DISABLED_ICON;

	/** The Constant FOLDER_IMPORT_ICON. */
	public static final Icon FOLDER_IMPORT_ICON;

	/** The Constant GROOVY_DISABLED_ICON. */
	public static final Icon GROOVY_DISABLED_ICON;

	/** The Constant GROOVY_ICON. */
	public static final Icon GROOVY_ICON;

	/** The Constant JOB_ICON. */
	public static final Icon JOB_ICON;

	/** The Constant LOADER_ICON. */
	public static final Icon LOADER_ICON;

	/** The Constant NETWORK_HUB_ICON. */
	public static final Icon NETWORK_HUB_ICON;

	/** The Constant NEW_FILE_ICON. */
	public static final Icon NEW_FILE_ICON;

	/** The Constant OPEN_FILE_ICON. */
	public static final Icon OPEN_FILE_ICON;

	/** The Constant PREFERENCE_ICON. */
	public static final Icon PREFERENCE_ICON;

	/** The Constant PROPERTY_ICON. */
	public static final Icon PROPERTY_ICON;

	/** The Constant SAVE_FILE_AS_ICON. */
	public static final Icon SAVE_FILE_AS_ICON;

	/** The Constant SAVE_FILE_ICON. */
	public static final Icon SAVE_FILE_ICON;

	/** The Constant STOP_JOB_ICON. */
	public static final Icon STOP_JOB_ICON;

	/** The Constant SUBVERSION_ICON. */
	public static final Icon SUBVERSION_ICON;

	static {
		final Class<ImageUtil> thisClass = ImageUtil.class;

		// Load images
		APPLICATION_ICON_IMAGE = Toolkit.getDefaultToolkit().getImage(thisClass.getResource("/images/computer.png"));

		// Load icons
		ABOUT_ICON = new ImageIcon(thisClass.getResource("/images/information.png"));
		ADD_JOB_ICON = new ImageIcon(thisClass.getResource("/images/document--plus.png"));
		ADD_LOCATION_ICON = new ImageIcon(thisClass.getResource("/images/drive--plus.png"));
		ADD_PREFERENCE_ICON = new ImageIcon(thisClass.getResource("/images/plus.png"));
		ADD_PROPERTY_ICON = new ImageIcon(thisClass.getResource("/images/plus.png"));
		CLEAR_ICON = new ImageIcon(thisClass.getResource("/images/eraser.png"));
		DELETE_PROPERTY_ICON = new ImageIcon(thisClass.getResource("/images/cross.png"));
		DRIVE_ICON = new ImageIcon(thisClass.getResource("/images/drive.png"));
		EDIT_PREFERENCES_ICON = new ImageIcon(thisClass.getResource("/images/property.png"));
		EXECUTE_ICON = new ImageIcon(thisClass.getResource("/images/lightning.png"));
		EXIT_ICON = new ImageIcon(thisClass.getResource("/images/door-open-out.png"));
		JOB_ICON = new ImageIcon(thisClass.getResource("/images/document-task.png"));
		LOADER_ICON = new ImageIcon(thisClass.getResource("/images/loader.gif"));
		NETWORK_HUB_ICON = new ImageIcon(thisClass.getResource("/images/network-hub.png"));
		NEW_FILE_ICON = new ImageIcon(thisClass.getResource("/images/document.png"));
		OPEN_FILE_ICON = new ImageIcon(thisClass.getResource("/images/folder-open-document.png"));
		PREFERENCE_ICON = new ImageIcon(thisClass.getResource("/images/property.png"));
		PROPERTY_ICON = new ImageIcon(thisClass.getResource("/images/property.png"));
		SAVE_FILE_AS_ICON = new ImageIcon(thisClass.getResource("/images/disks-black.png"));
		SAVE_FILE_ICON = new ImageIcon(thisClass.getResource("/images/disk-black.png"));
		STOP_JOB_ICON = new ImageIcon(thisClass.getResource("/images/slash.png"));
		SUBVERSION_ICON = new ImageIcon(thisClass.getResource("/images/subversion.png"));

		// Load task icons
		CHECKOUT_ICON = new ImageIcon(thisClass.getResource("/images/checkout-icon.png"));
		FILE_SYSTEM_DELETE_TASK_ICON = new ImageIcon(thisClass.getResource("/images/cross-small.png"));
		EXPORT_ICON = new ImageIcon(thisClass.getResource("/images/export-icon.png"));
		FIND_REPLACE_ICON = new ImageIcon(thisClass.getResource("/images/edit-replace.png"));
		FOLDER_IMPORT_ICON = new ImageIcon(thisClass.getResource("/images/folder-import.png"));
		GROOVY_ICON = new ImageIcon(thisClass.getResource("/images/ConsoleIcon.png"));

		// Create disabled task icons
		CHECKOUT_DISABLED_ICON = createDisabledImage((ImageIcon) CHECKOUT_ICON);
		FILE_SYSTEM_DELETE_TASK_DISABLED_ICON = createDisabledImage((ImageIcon) FILE_SYSTEM_DELETE_TASK_ICON);
		EXPORT_DISABLED_ICON = createDisabledImage((ImageIcon) EXPORT_ICON);
		FIND_REPLACE_DISABLED_ICON = createDisabledImage((ImageIcon) FIND_REPLACE_ICON);
		FOLDER_IMPORT_DISABLED_ICON = createDisabledImage((ImageIcon) FOLDER_IMPORT_ICON);
		GROOVY_DISABLED_ICON = createDisabledImage((ImageIcon) GROOVY_ICON);
	}

	/**
	 * Creates a new grayed image based on the image passed in.
	 * 
	 * @param imageIcon Image icon to gray out
	 * @return New grayed out image
	 */
	private static ImageIcon createDisabledImage(final ImageIcon imageIcon) {
		final GrayFilter filter = new GrayFilter(true, 75);
		final ImageProducer imageSource = imageIcon.getImage().getSource();
		final ImageProducer imageProducer = new FilteredImageSource(imageSource, filter);
		final Image grayImage = Toolkit.getDefaultToolkit().createImage(imageProducer);

		return new ImageIcon(grayImage);
	}
}
