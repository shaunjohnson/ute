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
package net.lmxm.ute.executors.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.lmxm.ute.beans.FindReplacePattern;
import net.lmxm.ute.beans.PatternWrapper;
import net.lmxm.ute.beans.tasks.FindReplaceTask;
import net.lmxm.ute.utils.testimpl.TestStatusChangeListener;

import org.junit.Before;
import org.junit.Test;

public class FindReplaceTaskExecutorTest {

	private static final TestStatusChangeListener STATUS_CHANGE_LISTENER = new TestStatusChangeListener();

	private FindReplaceTaskExecutor executor = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		final FindReplaceTask task = new FindReplaceTask();

		executor = new FindReplaceTaskExecutor(task, STATUS_CHANGE_LISTENER);
	}

	/**
	 * Test apply pattern.
	 */
	@Test
	public void testApplyPattern() {
		final Pattern pattern = Pattern.compile("^test$");

		assertEquals("foo", executor.applyPattern("foo", new PatternWrapper(pattern, "bar")));
		assertEquals("bar", executor.applyPattern("test", new PatternWrapper(pattern, "bar")));
	}

	/**
	 * Test convert find replace patterns to regex patterns.
	 */
	@Test
	public void testConvertFindReplacePatternsToRegexPatterns() {
		final List<FindReplacePattern> patternList = new ArrayList<FindReplacePattern>();

		List<PatternWrapper> patterns = executor.convertFindReplacePatternsToRegexPatterns(patternList);

		assertNotNull(patterns);
		assertTrue(patterns.size() == 0);

		final FindReplacePattern findReplacePattern = new FindReplacePattern();
		findReplacePattern.setFind("^foo$");
		findReplacePattern.setReplace("bar");

		patternList.add(findReplacePattern);
		patterns = executor.convertFindReplacePatternsToRegexPatterns(patternList);

		assertNotNull(patterns);
		assertTrue(patterns.size() == 1);

		final PatternWrapper patternWrapper = patterns.get(0);
		assertNotNull(patternWrapper);

		final Pattern pattern = patternWrapper.getPattern();
		assertTrue(pattern.matcher("foo").matches());
		assertFalse(pattern.matcher(" foo ").matches());
		assertFalse(pattern.matcher("Foo").matches());

		final String replacement = patternWrapper.getReplacement();
		assertNotNull(replacement);
		assertEquals("bar", replacement);
	}

	/**
	 * Test convert find replace patterns to regex patterns null list.
	 */
	@Test(expected = NullPointerException.class)
	public void testConvertFindReplacePatternsToRegexPatternsNullList() {
		executor.convertFindReplacePatternsToRegexPatterns(null);
	}

	@Test
	public void testExecute() {
		// fail("Not yet implemented");
	}

	@Test
	public void testFindReplaceContent() {
		// fail("Not yet implemented");
	}

	@Test
	public void testFindReplaceFileContent() {
		// fail("Not yet implemented");
	}

	@Test
	public void testFindReplaceFileLineContent() {
		// fail("Not yet implemented");

		// TODO Test multiple patterns applied to a line to make sure that all patterns are applied.
	}

	@Test
	public void testFindReplaceInFiles() {
		// fail("Not yet implemented");
	}

	@Test
	public void testFindReplaceTaskExecutor() {
		// fail("Not yet implemented");
	}
}
