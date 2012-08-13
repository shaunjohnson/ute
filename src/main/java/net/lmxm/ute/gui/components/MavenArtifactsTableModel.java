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

import net.lmxm.ute.beans.FileReference;
import net.lmxm.ute.beans.MavenArtifact;
import net.lmxm.ute.beans.tasks.FilesTask;
import net.lmxm.ute.resources.types.TableColumnResourceType;
import org.apache.commons.lang.ObjectUtils;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/**
 * The Class MavenArtifactsTableModel.
 */
public class MavenArtifactsTableModel extends AbstractEditableTableModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7120573245444473620L;

    /** The row data. */
	private final List<MavenArtifact> artifacts;

	/**
	 * Instantiates a new Maven artifacts table model.
	 *
	 * @param artifacts the list of MavenArtifact objects
	 */
	public MavenArtifactsTableModel(final List<MavenArtifact> artifacts) {
        super(EnumSet.of(TableColumnResourceType.MAVEN_ARTIFACT_COORDINATES, TableColumnResourceType.TARGET_FILE_NAME));

		this.artifacts = artifacts;

		cleanRowData();
	}

	/**
	 * Clean row data.
	 */
	private void cleanRowData() {
		final Iterator<MavenArtifact> iterator = artifacts.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().isEmpty()) {
				iterator.remove();
			}
		}

        artifacts.add(new MavenArtifact());
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return artifacts.size();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(final int row, final int column) {
		final MavenArtifact artifact = artifacts.get(row);

		if (column == 0) {
			return artifact.getCoordinates();
		}
        else if (column == 1) {
            return artifact.getTargetName();
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
		final MavenArtifact artifact = artifacts.get(row);
		final String valueString = ObjectUtils.toString(value, "");

		if (column == 0) {
            artifact.setCoordinates(valueString);
		}
        else if (column == 1) {
            artifact.setTargetName(valueString);
        }
		else {
			throw new IllegalArgumentException("Column index does not exist"); // TODO
		}

		fireTableCellUpdated(row, column);

		// Add a blank row if needed
		if (!artifacts.get(artifacts.size() - 1).isEmpty()) {
            artifacts.add(new MavenArtifact());
		}
	}
}
