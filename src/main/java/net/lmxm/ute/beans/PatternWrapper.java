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
package net.lmxm.ute.beans;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class PatternWrapper.
 */
public class PatternWrapper implements Comparable<PatternWrapper>, DomainBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 56380193688254319L;

	/** The pattern. */
	private final Pattern pattern;

	/** The replacement. */
	private final String replacement;

	/**
	 * Instantiates a new pattern wrapper.
	 * 
	 * @param pattern the pattern
	 * @param replacement the replacement
	 */
	public PatternWrapper(final Pattern pattern, final String replacement) {
		super();

		checkNotNull(pattern, "Pattern may not be null");
		checkNotNull(replacement, "Pattern may not be null");
		checkArgument(StringUtils.isNotBlank(replacement), "Pattern may not be blank");

		this.pattern = pattern;
		this.replacement = replacement;
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
		else if (!pattern.equals(other.pattern)) {
			return false;
		}

		if (replacement == null) {
			if (other.replacement != null) {
				return false;
			}
		}
		else if (!replacement.equals(other.replacement)) {
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

	/**
	 * Gets the replacement.
	 * 
	 * @return the replacement
	 */
	public String getReplacement() {
		return replacement;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (pattern == null ? 0 : pattern.hashCode());
		result = prime * result + (replacement == null ? 0 : replacement.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return pattern == null && StringUtils.isBlank(replacement);
	}

	/*
	 * (non-Javadoc)
	 * @see net.lmxm.ute.beans.DomainBean#removeEmptyObjects()
	 */
	@Override
	public void removeEmptyObjects() {
		// No children objects
	}
}
