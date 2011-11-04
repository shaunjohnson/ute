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

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.codehaus.plexus.util.StringUtils;

/**
 * The Class FindReplacePattern.
 */
public class FindReplacePattern implements DomainBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -124788145989938819L;

	/** The find. */
	private String find;

	/** The replace. */
	private String replace;

	/**
	 * Gets the find.
	 * 
	 * @return the find
	 */
	public String getFind() {
		return find;
	}

	/**
	 * Gets the replace.
	 * 
	 * @return the replace
	 */
	public String getReplace() {
		return replace;
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	@Override
	public boolean isEmpty() {
		return StringUtils.isBlank(find) && StringUtils.isBlank(replace);
	}

	/**
	 * Checks if is valid.
	 * 
	 * @return true, if is valid
	 */
	public boolean isValid() {
		try {
			Pattern.compile(find);
			return true;
		}
		catch (final NullPointerException e) {
			return false;
		}
		catch (final PatternSyntaxException e) {
			return false;
		}
	}

	/**
	 * Sets the find.
	 * 
	 * @param find the new find
	 */
	public void setFind(final String find) {
		this.find = find;
	}

	/**
	 * Sets the replace.
	 * 
	 * @param replace the new replace
	 */
	public void setReplace(final String replace) {
		this.replace = replace;
	}
}
