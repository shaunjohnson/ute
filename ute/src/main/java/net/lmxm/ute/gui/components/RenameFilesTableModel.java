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

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.tasks.RenameFilesTask;

import org.apache.commons.lang.ObjectUtils;

/**
 * The Class RenameFilesTableModel.
 */
public class RenameFilesTableModel extends AbstractEditableTableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1009294184442779820L;

	/** The row data. */
	private final List<FileReference> rowData;

	/**
	 * Instantiates a new rename files table model.
	 * 
	 * @param renameFilesTask the rename files task
	 */
	public RenameFilesTableModel(final RenameFilesTask renameFilesTask) {
		super(EnumSet.of(TableColumnResourceType.FILE_NAME, TableColumnResourceType.TARGET_FILE_NAME));

		rowData = renameFilesTask.getFiles();

		cleanRowData();
	}

	/**
	 * Clean row data.
	 */
	private void cleanRowData() {
		final Iterator<FileReference> iterator = rowData.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().isEmpty()) {
				iterator.remove();
			}
		}

		rowData.add(new FileReference());
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return rowData.size();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(final int row, final int column) {
		final FileReference fileReference = rowData.get(row);

		if (column == 0) {
			return fileReference.getName();
		}
		else if (column == 1) {
			return fileReference.getTargetName();
		}
		else {
			throw new IllegalArgumentException("Column index does not exist"); // TODO
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(final Object value, final int row, final int column) {
		final FileReference fileReference = rowData.get(row);
		final String valueString = ObjectUtils.toString(value, "");

		if (column == 0) {
			fileReference.setName(valueString);
		}
		else if (column == 1) {
			fileReference.setTargetName(valueString);
		}
		else {
			throw new IllegalArgumentException("Column index does not exist"); // TODO
		}

		fireTableCellUpdated(row, column);

		// Add a blank row if needed
		if (!rowData.get(rowData.size() - 1).isEmpty()) {
			rowData.add(new FileReference());
		}
	}
}
