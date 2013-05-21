package net.lmxm.ute.validation.rules;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static net.lmxm.ute.UteTestAssert.assertEmpty;
import static net.lmxm.ute.UteTestAssert.assertNotBlank;
import static net.lmxm.ute.UteTestAssert.assertNotEmpty;

/**
 * Unit tests for MavenArtifactCoordinatesTextValidationRule.
 */
public final class MavenArtifactCoordinatesTextValidationRuleTest {

    /** The rule. */
    private MavenArtifactCoordinatesTextValidationRule rule = null;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        rule = new MavenArtifactCoordinatesTextValidationRule();
    }

    /**
     * Test validate.
     */
    @Test
    public void testValidate() {
        final List<String> messages = rule.validate("foobar");
        assertNotNull(messages);
        assertNotEmpty(messages);
        assertNotBlank(messages.get(0));
    }

    /**
     * Test validate blank.
     */
    @Test
    public void testValidateBlank() {
        final List<String> messages = rule.validate("    ");
        assertNotNull(messages);
        assertEmpty(messages);
    }

    /**
     * Test validate empty.
     */
    @Test
    public void testValidateEmpty() {
        final List<String> messages = rule.validate("");
        assertNotNull(messages);
        assertEmpty(messages);
    }

    /**
     * Test validate http.
     */
    @Test
    public void testValidateBasicCoordinates() {
        final List<String> messages = rule.validate("groupId:artifactId:1.0.0");
        assertNotNull(messages);
        assertEmpty(messages);
    }

    /**
     * Test validate http fragment.
     */
    @Test
    public void testValidateCoordinatesWithExtension() {
        final List<String> messages = rule.validate("groupId:artifactId:jar:1.0.0");
        assertNotNull(messages);
        assertEmpty(messages);
    }

    /**
     * Test validate http params.
     */
    @Test
    public void testValidateCoordinatesWithClassifier() {
        final List<String> messages = rule.validate("groupId:artifactId:jar:client:1.0.0");
        assertNotNull(messages);
        assertEmpty(messages);
    }

    /**
     * Test validate null.
     */
    @Test(expected = NullPointerException.class)
    public void testValidateNull() {
        final List<String> messages = rule.validate(null);
        assertNotNull(messages);
        assertEmpty(messages);
    }

    /**
     * Test validate wrong type.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValidateWrongType() {
        final List<String> messages = rule.validate(42);
        assertNotNull(messages);
        assertEmpty(messages);
    }
}
