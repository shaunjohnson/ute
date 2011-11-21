package net.lmxm.ute.configuration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ConfigurationInterpolatorTest {

	@Test
	public void testInterpolateJobValues() {
		// TODO Not yet implemented
	}

	/**
	 * Test validate does not contain properties.
	 */
	@Test
	public void testValidateDoesNotContainProperties() {
		ConfigurationInterpolator.validateDoesNotContainProperties(null);
		ConfigurationInterpolator.validateDoesNotContainProperties("");
		ConfigurationInterpolator.validateDoesNotContainProperties("    ");
		ConfigurationInterpolator.validateDoesNotContainProperties("abc");
		ConfigurationInterpolator.validateDoesNotContainProperties("a{b}c");

		try {
			ConfigurationInterpolator.validateDoesNotContainProperties("${}");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationInterpolator.validateDoesNotContainProperties("${abc}");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationInterpolator.validateDoesNotContainProperties("before${abc}");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationInterpolator.validateDoesNotContainProperties("${abc}after");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}

		try {
			ConfigurationInterpolator.validateDoesNotContainProperties("before${abc}after");
			fail();
		}
		catch (final Exception e) {
			assertNotNull(e);
		}
	}
}
