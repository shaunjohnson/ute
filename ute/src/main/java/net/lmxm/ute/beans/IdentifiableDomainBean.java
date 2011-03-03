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

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class IdentifiableDomainBean.
 */
public abstract class IdentifiableDomainBean implements DomainBean, IdentifiableBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7677320296772799117L;

	/** The id. */
	private String id;

	@Override
	public int compareTo(final IdentifiableBean identifiableBean) {
		return new CompareToBuilder().append(this.id, identifiableBean.getId()).toComparison();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (obj.getClass() != getClass()) {
			return false;
		}

		final IdentifiableDomainBean rhs = (IdentifiableDomainBean)obj;

		return new EqualsBuilder().appendSuper(super.equals(obj)).append(id, rhs.getId()).isEquals();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@Override
	public final String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	@Override
	public final void setId(final String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getId();
	}
}
