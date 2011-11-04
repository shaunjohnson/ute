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
package net.lmxm.ute.beans.tasks;

import java.util.ArrayList;
import java.util.List;

import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.targets.FileSystemTarget;
import net.lmxm.ute.enums.Scope;

/**
 * The Class FindReplaceTask.
 */
public final class FindReplaceTask extends AbstractFilesTask implements FileSystemTargetTask {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4540300287061155288L;

	/** The patterns. */
	private List<FindReplacePattern> patterns;

	/** The scope. */
	private Scope scope;

	/** The target. */
	private FileSystemTarget target;

	/**
	 * Instantiates a new find replace task.
	 * 
	 * @param job the job
	 */
	public FindReplaceTask(final Job job) {
		super(job);

		patterns = new ArrayList<FindReplacePattern>();
		scope = Scope.LINE;
		target = new FileSystemTarget();
	}

	/**
	 * Gets the patterns.
	 * 
	 * @return the patterns
	 */
	public List<FindReplacePattern> getPatterns() {
		return patterns;
	}

	/**
	 * Gets the scope.
	 * 
	 * @return the scope
	 */
	public Scope getScope() {
		return scope;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.FileSystemTargetTask#getTarget()
	 */
	@Override
	public FileSystemTarget getTarget() {
		return target;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.IdentifiableDomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && patterns.isEmpty() && scope == null & target == null;
	}

	/**
	 * Sets the patterns.
	 * 
	 * @param patterns the new patterns
	 */
	public void setPatterns(final List<FindReplacePattern> patterns) {
		this.patterns = patterns;
	}

	/**
	 * Sets the scope.
	 * 
	 * @param scope the new scope
	 */
	public void setScope(final Scope scope) {
		this.scope = scope;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.tasks.FileSystemTargetTask#setTarget(net.lmxm.ute.beans.targets.FileSystemTarget)
	 */
	@Override
	public void setTarget(final FileSystemTarget target) {
		this.target = target;
	}
}
