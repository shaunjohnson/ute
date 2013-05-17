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

import com.google.common.base.Objects;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * Preference represents a single name/value pair that may be used throughout a UTE configuration. Preference instances
 * are used much like variables in that they are defined once and used multiple times. Unlike Property instances, the
 * value of Preferences are not stored in a UTE configuration file, but are user specific. Preferences may be declared
 * and referred/used in a UTE configuration file, but only have value if the user defined the preference with a value.
 */
public final class Preference implements DomainBean, IdentifiableBean {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -4325574409193215376L;

    /**
     * Unique ID of the preference
     */
    private String id;

    /**
     * The current value of the preference.
     */
    private String value;

    /**
     * Instantiates a new, empty preference.
     */
    public Preference() {
        super();
    }

    /**
     * Instantiates a new preference with an initial ID.
     *
     * @param id the preference ID
     */
    public Preference(final String id) {
        super();

        this.id = id;
    }

    /**
     * Instantiates a new preference with an initial ID and value.
     *
     * @param id    the ID of the preference
     * @param value the value of the preference
     */
    public Preference(final String id, final String value) {
        super();

        this.id = id;
        this.value = value;
    }

    public int compareTo(final IdentifiableBean identifiableBean) {
        return new CompareToBuilder().append(this.id, identifiableBean.getId()).toComparison();
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof Preference) {
            final Preference that = (Preference) object;

            return Objects.equal(id, that.id) && Objects.equal(value, that.value);
        }
        else {
            return false;
        }
    }

    /**
     * Generates user friendly display text that may be used in the GUI.
     *
     * @return User friendly text
     */
    public String getDisplayText() {
        return getId();
    }

    /**
     * Gets the current ID.
     *
     * @return the current ID
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     */
    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, value);
    }

    /**
     * Determines if this object is empty and does not have any values set.
     */
    public boolean isEmpty() {
        return StringUtils.isEmpty(id) && StringUtils.isEmpty(value);
    }

    /**
     * Removes all empty children objects from this object.
     */
    public void removeEmptyObjects() {
        // No children objects
    }

    /**
     * Sets the ID of this preference.
     *
     * @param id the new ID
     */
    @Override
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Sets the value of this preference.
     *
     * @param value the new value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", id).add("value", value).toString();
    }
}
