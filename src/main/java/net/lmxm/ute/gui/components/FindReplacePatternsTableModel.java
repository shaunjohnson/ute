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

import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.resources.types.TableColumnResourceType;
import org.apache.commons.lang.ObjectUtils;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/**
 * The Class FindReplacePatternsTableModel.
 */
public class FindReplacePatternsTableModel extends AbstractEditableTableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1878986045758513228L;

	/** The row data. */
	private final List<FindReplacePattern> rowData;

	/**
	 * Instantiates a new find replace patterns table model.
	 * 
	 * @param findReplaceTask the find replace task
	 */
	public FindReplacePatternsTableModel(final FindReplaceTask findReplaceTask) {
		super(EnumSet.of(TableColumnResourceType.FIND, TableColumnResourceType.REPLACEMENT));

		rowData = findReplaceTask.getPatterns();

		cleanRowData();
	}

	/**
	 * Clean row data.
	 */
	private void cleanRowData() {
		final Iterator<FindReplacePattern> iterator = rowData.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().isEmpty()) {
				iterator.remove();
			}
		}

		rowData.add(new FindReplacePattern());
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
		final FindReplacePattern findReplacePattern = rowData.get(row);

		if (column == 0) {
			return findReplacePattern.getFind();
		}
		else if (column == 1) {
			return findReplacePattern.getReplace();
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
		final FindReplacePattern findReplacePattern = rowData.get(row);
		final String valueString = ObjectUtils.toString(value, "");

		if (column == 0) {
			findReplacePattern.setFind(valueString);
		}
		else if (column == 1) {
			findReplacePattern.setReplace(valueString);
		}
		else {
			throw new IllegalArgumentException("Column index does not exist"); // TODO
		}

		fireTableCellUpdated(row, column);

		// Add a blank row if needed
		if (!rowData.get(rowData.size() - 1).isEmpty()) {
			rowData.add(new FindReplacePattern());
		}
	}
}
