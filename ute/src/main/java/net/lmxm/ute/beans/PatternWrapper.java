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
package net.lmxm.ute.beans;

import java.util.regex.Pattern;

import com.google.common.base.Preconditions;

/**
 * The Class PatternWrapper.
 */
public class PatternWrapper implements Comparable<PatternWrapper> {

	/** The pattern. */
	private final Pattern pattern;

	/**
	 * Instantiates a new pattern wrapper.
	 * 
	 * @param pattern the pattern
	 */
	public PatternWrapper(final Pattern pattern) {
		super();

		Preconditions.checkNotNull(pattern, "Pattern may not be null");

		this.pattern = pattern;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final PatternWrapper patternWrapper) {
		return pattern.pattern().compareTo(patternWrapper.getPattern().pattern());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final PatternWrapper other = (PatternWrapper) obj;
		if (pattern == null) {
			if (other.pattern != null) {
				return false;
			}
		}
		else if (!pattern.pattern().equals(other.pattern.pattern())) {
			return false;
		}

		return true;
	}

	/**
	 * Gets the pattern.
	 * 
	 * @return the pattern
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (pattern == null ? 0 : pattern.pattern().hashCode());
		return result;
	}

}
