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
package net.lmxm.ute.gui.matchers;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * The Class UserObjectEqualsMatcher.
 */
public class UserObjectEqualsMatcher implements TreeNodeMatcher {

	/** The user object. */
	private final Object userObject;

	/**
	 * Instantiates a new user object equals matcher.
	 * 
	 * @param userObject the user object
	 */
	public UserObjectEqualsMatcher(final Object userObject) {
		super();

		this.userObject = userObject;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.gui.matchers.TreeNodeMatcher#matches(javax.swing.tree.TreeNode)
	 */
	@Override
	public boolean matches(final TreeNode treeNode) {
		return ((DefaultMutableTreeNode) treeNode).getUserObject().equals(userObject);
	}
}
