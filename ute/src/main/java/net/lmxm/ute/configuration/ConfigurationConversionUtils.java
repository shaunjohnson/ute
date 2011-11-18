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
package net.lmxm.ute.configuration;

import net.lmxm.ute.enums.Scope;
import net.lmxm.ute.enums.SubversionDepth;
import net.lmxm.ute.exceptions.ConfigurationException;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import noNamespace.ScopeType;
import noNamespace.SubversionDepthType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ConfigurationConversionUtils.
 */
public final class ConfigurationConversionUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationConversionUtils.class);

	/**
	 * Convert scope to scope type.
	 * 
	 * @param scope the scope
	 * @return the scope type. enum
	 */
	public static ScopeType.Enum convertScopeToScopeType(final Scope scope) {
		final String prefix = "convertScopeToScopeType() :";

		LOGGER.debug("{} entered", prefix);

		final ScopeType.Enum scopeType;

		if (scope == Scope.FILE) {
			scopeType = ScopeType.FILE;
		}
		else if (scope == Scope.LINE) {
			scopeType = ScopeType.LINE;
		}
		else {
			LOGGER.error("{} Unsupported scope \"{}\"", prefix, scope);
			throw new ConfigurationException(ExceptionResourceType.UNSUPPORTED_SCOPE, scope);
		}

		LOGGER.debug("{} returning {}", prefix, scopeType);

		return scopeType;
	}

	/**
	 * Convert scope type to scope.
	 * 
	 * @param scopeType the scope type
	 * @return the scope
	 */
	public static Scope convertScopeTypeToScope(final ScopeType.Enum scopeType) {
		final Scope scope;

		if (scopeType == ScopeType.FILE) {
			scope = Scope.FILE;
		}
		else if (scopeType == ScopeType.LINE) {
			scope = Scope.LINE;
		}
		else {
			LOGGER.error("convertScopeTypeToScope() : Unsupported scope type \"{}\"", scopeType);
			throw new ConfigurationException(ExceptionResourceType.UNSUPPORTED_SCOPE_TYPE, scopeType);
		}

		return scope;
	}

	/**
	 * Convert subversion depth to subversion depth type.
	 * 
	 * @param depth the depth
	 * @return the subversion depth type. enum
	 */
	public static SubversionDepthType.Enum convertSubversionDepthToSubversionDepthType(final SubversionDepth depth) {
		final String prefix = "convertSubversionDepthToSubversionDepthType() :";

		LOGGER.debug("{} entered", prefix);

		final SubversionDepthType.Enum subversionDepthType;

		if (depth == SubversionDepth.EMPTY) {
			subversionDepthType = SubversionDepthType.EMPTY;
		}
		else if (depth == SubversionDepth.EXCLUDE) {
			subversionDepthType = SubversionDepthType.EXCLUDE;
		}
		else if (depth == SubversionDepth.FILES) {
			subversionDepthType = SubversionDepthType.FILES;
		}
		else if (depth == SubversionDepth.IMMEDIATES) {
			subversionDepthType = SubversionDepthType.IMMEDIATES;
		}
		else if (depth == SubversionDepth.INFINITY) {
			subversionDepthType = SubversionDepthType.INFINITY;
		}
		else {
			LOGGER.error("{} : Unsupported Subversion depth \"{}\"", prefix, depth);
			throw new ConfigurationException(ExceptionResourceType.UNSUPPORTED_SUBVERSION_DEPTH, depth);
		}

		LOGGER.debug("{} returning {}", prefix, subversionDepthType);

		return subversionDepthType;
	}

	/**
	 * Convert subversion depth type to subversion depth.
	 * 
	 * @param subversionDepthType the subversion depth type
	 * @return the subversion depth
	 */
	public static SubversionDepth convertSubversionDepthTypeToSubversionDepth(
			final SubversionDepthType.Enum subversionDepthType) {
		final SubversionDepth subversionDepth;

		if (subversionDepthType == SubversionDepthType.EMPTY) {
			subversionDepth = SubversionDepth.EMPTY;
		}
		else if (subversionDepthType == SubversionDepthType.EXCLUDE) {
			subversionDepth = SubversionDepth.EXCLUDE;
		}
		else if (subversionDepthType == SubversionDepthType.FILES) {
			subversionDepth = SubversionDepth.FILES;
		}
		else if (subversionDepthType == SubversionDepthType.IMMEDIATES) {
			subversionDepth = SubversionDepth.IMMEDIATES;
		}
		else if (subversionDepthType == SubversionDepthType.INFINITY) {
			subversionDepth = SubversionDepth.INFINITY;
		}
		else {
			LOGGER.error("convertSubversionDepthTypeToSubversionDepth() : Unsupported Subversion depth type \"{}\"",
					subversionDepthType);
			throw new ConfigurationException(ExceptionResourceType.UNSUPPORTED_SUBVERSION_DEPTH_TYPE,
					subversionDepthType);
		}

		return subversionDepth;
	}

	/**
	 * Instantiates a new configuration conversion utils.
	 */
	private ConfigurationConversionUtils() {
		throw new AssertionError();
	}
}
