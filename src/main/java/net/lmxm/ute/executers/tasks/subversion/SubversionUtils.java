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
package net.lmxm.ute.executers.tasks.subversion;

import net.lmxm.ute.exceptions.TaskExecuterException;
import net.lmxm.ute.resources.types.ExceptionResourceType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Class SubversionUtils.
 */
public final class SubversionUtils {

	/** The Constant HEAD_REVISION. */
	public static final String HEAD_REVISION = "HEAD";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SubversionUtils.class);

	/** The Constant REVISION_DATE_FORMAT. */
	private static final String REVISION_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Format revision date.
	 * 
	 * @param date the date
	 * @return the string
	 */
	public static String formatRevisionDate(final Date date) {
		final String prefix = "formatRevisionDate() :";

		LOGGER.debug("{} entered. date={}", prefix, date);

		checkNotNull(date, "Date is null");

		final StringBuilder dateStringBuilder = new StringBuilder();
		dateStringBuilder.append("{");
		dateStringBuilder.append(new SimpleDateFormat(REVISION_DATE_FORMAT).format(date));
		dateStringBuilder.append("}");

		LOGGER.debug("{} returning {}", prefix, dateStringBuilder);

		return dateStringBuilder.toString();
	}

	/**
	 * Checks if is head revision.
	 * 
	 * @param revision the revision
	 * @return true, if is head revision
	 */
	public static boolean isHeadRevision(final String revision) {
		return StringUtils.equals(revision, HEAD_REVISION);
	}

	/**
	 * Checks if is revision date.
	 * 
	 * @param revision the revision
	 * @return true, if is revision date
	 */
	public static boolean isRevisionDate(final String revision) {
		return revision != null && revision.matches("\\{\\d{4}-\\d{2}-\\d{2}\\}");
	}

	/**
	 * Checks if is revision number.
	 * 
	 * @param revision the revision
	 * @return true, if is revision number
	 */
	public static boolean isRevisionNumber(final String revision) {
		return revision != null && revision.matches("(0)|([1-9][0-9]+)");
	}

	/**
	 * Parses the revision date.
	 * 
	 * @param dateString the date string
	 * @return the date
	 */
	public static Date parseRevisionDate(final String dateString) {
		final String prefix = "parseRevisionDate() :";

		LOGGER.debug("{} entered. dateString={}", prefix, dateString);

		checkArgument(isRevisionDate(dateString), "Date string is not a valid revision date");

        final String choppedRevision = dateString.substring(1, dateString.length() - 1);
        Date date = null;
		try {
			date = new SimpleDateFormat(REVISION_DATE_FORMAT).parse(choppedRevision);
		}
		catch (final ParseException e) {
			LOGGER.error("{} Invalid revision value", prefix);
            throw new TaskExecuterException(ExceptionResourceType.INVALID_SUBVERSION_REVISION_VALUE, e, choppedRevision);
		}

		LOGGER.debug("{} returning {}", prefix, date);

		return date;
	}

	/**
	 * Instantiates a new subversion utils.
	 */
	private SubversionUtils() {
		throw new AssertionError();
	}
}
