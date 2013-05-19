package net.lmxm.ute.subversion.utils;

import net.lmxm.ute.executers.tasks.subversion.SubversionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class SubversionUtilsTest {

	private Date testDate = null;

	private String testDateString = null;

	@Before
	public void setup() {
		final Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, 2011);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 25);

		testDate = calendar.getTime();
		testDateString = "{2011-12-25}";
	}

	@Test
	public void testFormatRevisionDateEmptyString() {
		final String string = SubversionUtils.formatRevisionDate(testDate);

		assertNotNull(string);
		assertEquals("{2011-12-25}", string);
	}

	@Test(expected = NullPointerException.class)
	public void testFormatRevisionDateNull() {
		SubversionUtils.formatRevisionDate(null);
	}

	@Test
	public void testIsHeadRevision() {
		assertFalse(SubversionUtils.isHeadRevision(null));
		assertFalse(SubversionUtils.isHeadRevision(""));
		assertFalse(SubversionUtils.isHeadRevision("    "));
		assertFalse(SubversionUtils.isHeadRevision("head"));
		assertFalse(SubversionUtils.isHeadRevision("123456789"));
		assertFalse(SubversionUtils.isHeadRevision(testDateString));
		assertFalse(SubversionUtils.isHeadRevision(" HEAD "));

		assertTrue(SubversionUtils.isHeadRevision("HEAD"));
	}

	@Test
	public void testIsRevisionDate() {
		assertFalse(SubversionUtils.isRevisionDate(null));
		assertFalse(SubversionUtils.isRevisionDate(""));
		assertFalse(SubversionUtils.isRevisionDate("    "));
		assertFalse(SubversionUtils.isRevisionDate("HEAD"));
		assertFalse(SubversionUtils.isRevisionDate("123456789"));
		assertFalse(SubversionUtils.isRevisionDate(" " + testDateString + " "));

		assertTrue(SubversionUtils.isRevisionDate(testDateString));
	}

	@Test
	public void testIsRevisionNumber() {
		assertFalse(SubversionUtils.isRevisionNumber(null));
		assertFalse(SubversionUtils.isRevisionNumber(""));
		assertFalse(SubversionUtils.isRevisionNumber("    "));
		assertFalse(SubversionUtils.isRevisionNumber("HEAD"));
		assertFalse(SubversionUtils.isRevisionNumber("{2011-13-25}"));
		assertFalse(SubversionUtils.isRevisionNumber("0123456789"));

		assertTrue(SubversionUtils.isRevisionNumber("0"));
		assertTrue(SubversionUtils.isRevisionNumber("123456789"));
	}

	@Test
	public void testParseRevisionDate() {
		final Date parsedDate = SubversionUtils.parseRevisionDate(testDateString);
		assertEquals(testDate, parsedDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseRevisionDateBlank() {
		SubversionUtils.parseRevisionDate("    ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseRevisionDateEmpty() {
		SubversionUtils.parseRevisionDate("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseRevisionDateKeyword() {
		SubversionUtils.parseRevisionDate("HEAD");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseRevisionDateNull() {
		SubversionUtils.parseRevisionDate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseRevisionDateNumber() {
		SubversionUtils.parseRevisionDate("123456789");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseRevisionDateSpaces() {
		SubversionUtils.parseRevisionDate(" " + testDateString + " ");
	}
}
