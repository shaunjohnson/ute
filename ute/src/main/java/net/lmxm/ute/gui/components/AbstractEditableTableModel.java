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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.lmxm.ute.utils.ResourcesUtils;

/**
 * The Class AbstractEditableTableModel.
 */
public abstract class AbstractEditableTableModel extends AbstractTableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4429594168236863932L;

	/** The Constant TEXT_SUFFIX. */
	private static final String TEXT_SUFFIX = "text";

	/**
	 * Builds the resource prefix.
	 * 
	 * @param guiComponentType the gui component type
	 * @return the string
	 */
	private static String buildResourcePrefix(final GuiComponentType guiComponentType) {
		final StringBuilder builder = new StringBuilder();
		builder.append(guiComponentType.getGuiComponentCategory().name());
		builder.append(".");
		builder.append(guiComponentType.name());
		builder.append(".");

		return builder.toString();
	}

	/**
	 * Gets the resource string.
	 * 
	 * @param guiComponentType the gui component type
	 * @param suffix the suffix
	 * @return the resource string
	 */
	private static final String getResourceString(final GuiComponentType guiComponentType, final String suffix) {
		final String resourcePrefix = buildResourcePrefix(guiComponentType);

		return ResourcesUtils.getString(resourcePrefix + suffix);
	}

	/** The column names. */
	private final List<String> columnNames;

	/**
	 * Instantiates a new abstract editable table model.
	 * 
	 * @param columnEnums the column enums
	 */
	public AbstractEditableTableModel(final EnumSet<GuiComponentTableColumn> columnEnums) {
		super();

		columnNames = new ArrayList<String>(columnEnums.size());

		for (final GuiComponentTableColumn columnEnum : columnEnums) {
			columnNames.add(getResourceString(columnEnum, TEXT_SUFFIX));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public final int getColumnCount() {
		return columnNames.size();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public final String getColumnName(final int column) {
		return columnNames.get(column);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public final boolean isCellEditable(final int row, final int column) {
		return true;
	}
}