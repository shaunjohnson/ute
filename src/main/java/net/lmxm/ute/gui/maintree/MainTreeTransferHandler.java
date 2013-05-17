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
package net.lmxm.ute.gui.maintree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.lmxm.ute.beans.IdentifiableBean;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.exceptions.GuiException;
import net.lmxm.ute.gui.maintree.nodes.IdentifiableBeanTreeNode;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MainTreeTransferHandler.
 */
public class MainTreeTransferHandler extends TransferHandler {

    /**
     * The LOGGER handle.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainTreeTransferHandler.class);

	/**
	 * The Class NodesTransferable.
	 */
	public class NodesTransferable implements Transferable {

		/** The tree nodes. */
		private final List<IdentifiableBeanTreeNode> treeNodes;

		/**
		 * Instantiates a new nodes transferable.
		 * 
		 * @param treeNodes the tree nodes
		 */
		public NodesTransferable(final IdentifiableBeanTreeNode[] treeNodes) {
			super();

			this.treeNodes = ImmutableList.copyOf(treeNodes);
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.datatransfer.Transferable#getTransferData(java.awt.datatransfer.DataFlavor)
		 */
		@Override
		public Object getTransferData(final DataFlavor dataFlavor) throws UnsupportedFlavorException {
			if (!isDataFlavorSupported(dataFlavor)) {
				throw new UnsupportedFlavorException(dataFlavor);
			}

			return treeNodes;
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
		 */
		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return dataFlavors;
		}

		/*
		 * (non-Javadoc)
		 * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
		 */
		@Override
		public boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
			return nodesFlavor.equals(dataFlavor);
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5111905564619258178L;

	/** The data flavors. */
	private final DataFlavor[] dataFlavors;

	/** The nodes flavor. */
	private final DataFlavor nodesFlavor;

	/** The nodes to remove. */
	private IdentifiableBeanTreeNode[] nodesToRemove;

	/**
	 * Instantiates a new main tree transfer handler.
	 */
	public MainTreeTransferHandler() {
		try {
			final String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=\""
					+ IdentifiableBeanTreeNode[].class.getName() + "\"";
			nodesFlavor = new DataFlavor(mimeType);

			dataFlavors = new DataFlavor[1];
			dataFlavors[0] = nodesFlavor;
		}
		catch (final ClassNotFoundException e) {
            LOGGER.error("Unable to configure tree drag and drop", e);
            throw new GuiException(ExceptionResourceType.UNEXPECTED_ERROR, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.TransferHandler#canImport(javax.swing.TransferHandler.TransferSupport)
	 */
	@Override
	public boolean canImport(final TransferHandler.TransferSupport support) {
		if (!support.isDrop()) {
			return false;
		}

		support.setShowDropLocation(true);
		if (!support.isDataFlavorSupported(nodesFlavor)) {
			return false;
		}

		// Do not allow a drop on the drag source selections.
		final JTree.DropLocation dropLocation = (JTree.DropLocation) support.getDropLocation();
		final TreePath destinationPath = dropLocation.getPath();

		final JTree tree = (JTree) support.getComponent();
		final int dropRow = tree.getRowForPath(destinationPath);

		final int[] selRows = tree.getSelectionRows();
		for (final int selRow : selRows) {
			if (selRow == dropRow) {
				return false;
			}
		}

		// Do not allow MOVE-action drops if a non-leaf node is
		// selected unless all of its children are also selected.
		final int action = support.getDropAction();
		if (action == MOVE) {
			return haveCompleteNode(tree);
		}

		// Do not allow a non-leaf node to be copied to a level
		// which is less than its source level.

		final IdentifiableBeanTreeNode target = (IdentifiableBeanTreeNode) destinationPath.getLastPathComponent();

		final TreePath path = tree.getPathForRow(selRows[0]);
		final IdentifiableBeanTreeNode firstNode = (IdentifiableBeanTreeNode) path.getLastPathComponent();
		if (firstNode.getChildCount() > 0 && target.getLevel() < firstNode.getLevel()) {
			return false;
		}

		return true;
	}

	/**
	 * Defensive copy used in createTransferable.
	 * 
	 * @param node the node
	 * @return the identifiable bean tree node
	 */
	private IdentifiableBeanTreeNode copy(final IdentifiableBeanTreeNode node) {
		return new IdentifiableBeanTreeNode((IdentifiableBean) node.getUserObject());
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.TransferHandler#createTransferable(javax.swing.JComponent)
	 */
	@Override
	protected Transferable createTransferable(final JComponent component) {
		final JTree tree = (JTree) component;
		final TreePath[] paths = tree.getSelectionPaths();

		if (paths != null) {
			// Make up a node array of copies for transfer and
			// another for/of the nodes that will be removed in
			// exportDone after a successful drop.
			final List<IdentifiableBeanTreeNode> copies = new ArrayList<IdentifiableBeanTreeNode>();
			final List<IdentifiableBeanTreeNode> toRemove = new ArrayList<IdentifiableBeanTreeNode>();

			final Object firstNode = paths[0].getLastPathComponent();

			// Don't allow non-identifiable bean tree nodes from being dragged
			if (!(firstNode instanceof IdentifiableBeanTreeNode)) {
				return null;
			}

			// Only allow tasks to be moved
			final Object userObject = ((IdentifiableBeanTreeNode) firstNode).getUserObject();
			if (!(userObject instanceof Task)) {
				return null;
			}

			final IdentifiableBeanTreeNode node = (IdentifiableBeanTreeNode) paths[0].getLastPathComponent();
			final IdentifiableBeanTreeNode copy = copy(node);
			copies.add(copy);
			toRemove.add(node);

			for (int i = 1; i < paths.length; i++) {
				final IdentifiableBeanTreeNode next = (IdentifiableBeanTreeNode) paths[i].getLastPathComponent();
				// Do not allow higher level nodes to be added to list.
				if (next.getLevel() < node.getLevel()) {
					break;
				}
				else if (next.getLevel() > node.getLevel()) { // child node
					copy.add(copy(next));
					// node already contains child
				}
				else { // sibling
					copies.add(copy(next));
					toRemove.add(next);
				}
			}

			final IdentifiableBeanTreeNode[] nodes = copies.toArray(new IdentifiableBeanTreeNode[copies.size()]);
			nodesToRemove = toRemove.toArray(new IdentifiableBeanTreeNode[toRemove.size()]);

			return new NodesTransferable(nodes);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.TransferHandler#exportDone(javax.swing.JComponent, java.awt.datatransfer.Transferable, int)
	 */
	@Override
	protected void exportDone(final JComponent source, final Transferable data, final int action) {
		if ((action & MOVE) == MOVE) {
			final JTree tree = (JTree) source;
			final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

			// Remove nodes saved in nodesToRemove in createTransferable.
			for (final IdentifiableBeanTreeNode element : nodesToRemove) {
				model.removeNodeFromParent(element);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.TransferHandler#getSourceActions(javax.swing.JComponent)
	 */
	@Override
	public int getSourceActions(final JComponent component) {
		return MOVE;
	}

	/**
	 * Have complete node.
	 * 
	 * @param tree the tree
	 * @return true, if successful
	 */
	private boolean haveCompleteNode(final JTree tree) {
		final int[] selRows = tree.getSelectionRows();
		TreePath path = tree.getPathForRow(selRows[0]);
		final IdentifiableBeanTreeNode first = (IdentifiableBeanTreeNode) path.getLastPathComponent();
		final int childCount = first.getChildCount();

		// first has children and no children are selected.
		if (childCount > 0 && selRows.length == 1) {
			return false;
		}

		// first may have children.
		for (int i = 1; i < selRows.length; i++) {
			path = tree.getPathForRow(selRows[i]);
			final IdentifiableBeanTreeNode next = (IdentifiableBeanTreeNode) path.getLastPathComponent();
			if (first.isNodeChild(next)) {
				// Found a child of first.
				if (childCount > selRows.length - 1) {
					// Not all children of first are selected.
					return false;
				}
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.TransferHandler#importData(javax.swing.TransferHandler.TransferSupport)
	 */
	@Override
	public boolean importData(final TransferHandler.TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}

		// Extract transfer data.
        List<IdentifiableBeanTreeNode> nodes = null;
		try {
			final Transferable transferable = support.getTransferable();
			nodes = (List<IdentifiableBeanTreeNode>) transferable.getTransferData(nodesFlavor);
		}
		catch (final UnsupportedFlavorException e) {
            throw new GuiException(ExceptionResourceType.DRAG_AND_DROP_ERROR, e);
		}
		catch (final IOException e) {
            throw new GuiException(ExceptionResourceType.DRAG_AND_DROP_ERROR, e);
		}

		// Get drop location info.
		final JTree.DropLocation dropLocation = (JTree.DropLocation) support.getDropLocation();
		final int childIndex = dropLocation.getChildIndex();
		final TreePath destinationPath = dropLocation.getPath();
		final IdentifiableBeanTreeNode parent = (IdentifiableBeanTreeNode) destinationPath.getLastPathComponent();
		final JTree tree = (JTree) support.getComponent();
		final MainTreeModel model = (MainTreeModel) tree.getModel();

		// Configure for drop mode.
		int index = childIndex; // DropMode.INSERT
		if (childIndex == -1) { // DropMode.ON
			index = parent.getChildCount();
		}

		// Add data to model.
		for (final IdentifiableBeanTreeNode node : nodes) {
			model.moveTaskNodeInto(node, parent, index++);
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName();
	}
}
