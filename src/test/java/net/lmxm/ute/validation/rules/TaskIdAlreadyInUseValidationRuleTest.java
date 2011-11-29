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
package net.lmxm.ute.validation.rules;

import static junit.framework.Assert.assertNotNull;
import static net.lmxm.ute.UteTestAssert.assertEmpty;
import static net.lmxm.ute.UteTestAssert.assertNotBlank;
import static net.lmxm.ute.UteTestAssert.assertNotEmpty;

import java.util.List;

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.beans.jobs.Job;
import net.lmxm.ute.beans.jobs.SequentialJob;
import net.lmxm.ute.beans.tasks.FileSystemDeleteTask;
import net.lmxm.ute.beans.tasks.Task;
import net.lmxm.ute.configuration.ConfigurationHolder;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class TaskIdAlreadyInUseValidationRuleTest.
 */
public class TaskIdAlreadyInUseValidationRuleTest {

	/** The Constant id. */
	private static final String id = "id";

	/** The rule. */
	private ConfigurationHolder configurationHolder = null;

	/** The job. */
	private Job job = null;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		job = new SequentialJob("job");
		job.getTasks().add(new FileSystemDeleteTask(id, job));

		final Configuration configuration = new Configuration();
		configuration.getJobs().add(job);

		configurationHolder = new ConfigurationHolder() {
			@Override
			public Configuration getConfiguration() {
				return configuration;
			}
		};
	}

	/**
	 * Test task id already in use validation rule.
	 */
	@Test
	public void testTaskIdAlreadyInUseValidationRule() {
		new TaskIdAlreadyInUseValidationRule(new FileSystemDeleteTask(job), new ConfigurationHolder() {
			@Override
			public Configuration getConfiguration() {
				return new Configuration();
			}
		});
	}

	/**
	 * Test task id already in use validation rule null configuration holder.
	 */
	@Test(expected = NullPointerException.class)
	public void testTaskIdAlreadyInUseValidationRuleNullConfigurationHolder() {
		new TaskIdAlreadyInUseValidationRule(new FileSystemDeleteTask(job), null);
	}

	/**
	 * Test task id already in use validation rule null location.
	 */
	@Test(expected = NullPointerException.class)
	public void testTaskIdAlreadyInUseValidationRuleNullLocation() {
		new TaskIdAlreadyInUseValidationRule(null, new ConfigurationHolder() {
			@Override
			public Configuration getConfiguration() {
				return new Configuration();
			}
		});
	}

	/**
	 * Test validate.
	 */
	@Test
	public void testValidate() {
		final Task task = new FileSystemDeleteTask(id, job);
		final ValidationRule rule = new TaskIdAlreadyInUseValidationRule(task, configurationHolder);

		final List<String> messages = rule.validate("id");
		assertNotNull(messages);
		assertNotEmpty(messages);
		assertNotBlank(messages.get(0));
	}

	/**
	 * Test validate blank.
	 */
	@Test
	public void testValidateBlank() {
		final Task task = new FileSystemDeleteTask(id, job);
		final ValidationRule rule = new TaskIdAlreadyInUseValidationRule(task, configurationHolder);

		final List<String> messages = rule.validate("    ");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate different id.
	 */
	@Test
	public void testValidateDifferentId() {
		final Task task = new FileSystemDeleteTask(id, job);
		final ValidationRule rule = new TaskIdAlreadyInUseValidationRule(task, configurationHolder);

		final List<String> messages = rule.validate("foobar");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate empty.
	 */
	@Test
	public void testValidateEmpty() {
		final Task task = new FileSystemDeleteTask(id, job);
		final ValidationRule rule = new TaskIdAlreadyInUseValidationRule(task, configurationHolder);

		final List<String> messages = rule.validate("");
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate null.
	 */
	@Test(expected = NullPointerException.class)
	public void testValidateNull() {
		final Task task = new FileSystemDeleteTask(id, job);
		final ValidationRule rule = new TaskIdAlreadyInUseValidationRule(task, configurationHolder);

		final List<String> messages = rule.validate(null);
		assertNotNull(messages);
		assertEmpty(messages);
	}

	/**
	 * Test validate wrong type.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testValidateWrongType() {
		final Task task = new FileSystemDeleteTask(id, job);
		final ValidationRule rule = new TaskIdAlreadyInUseValidationRule(task, configurationHolder);

		final List<String> messages = rule.validate(42);
		assertNotNull(messages);
		assertEmpty(messages);
	}
}
