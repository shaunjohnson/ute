package net.lmxm.ute.beans;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindReplacePatternTest {
    @Test
    public void testEqualsObject() {
        EqualsVerifier.forClass(FindReplacePattern.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void testGetFind() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.setFind("find");
        assertEquals("find", findReplacePattern.getFind());
    }

    @Test
    public void testGetReplace() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.setReplace("replace");
        assertEquals("replace", findReplacePattern.getReplace());
    }

    @Test
    public void testIsEmptyNewInstance() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        assertTrue(findReplacePattern.isEmpty());
    }

    @Test
    public void testIsEmptyFindSet() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.setFind("findValue");
        assertFalse(findReplacePattern.isEmpty());
    }

    @Test
    public void testIsEmptyReplaceSet() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.setReplace("replaceValue");
        assertFalse(findReplacePattern.isEmpty());
    }

    @Test
    public void testIsValidNewInstance() {
        assertFalse(new FindReplacePattern().isValid());
    }

    @Test
    public void testIsValidInvalidValue() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.setFind("][");
        assertFalse(findReplacePattern.isValid());
    }

    @Test
    public void testIsValidValidValue() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.setFind("[0-9]");
        assertTrue(findReplacePattern.isValid());
    }

    @Test
    public void testToString() {
        final FindReplacePattern findReplacePattern = new FindReplacePattern();
        findReplacePattern.setFind("findValue");
        findReplacePattern.setReplace("replaceValue");

        assertTrue(findReplacePattern.toString().contains("findValue"));
        assertTrue(findReplacePattern.toString().contains("replaceValue"));
    }

    @Test
    public void testRemoveEmptyObjects() {
        final FindReplacePattern findReplacePattern1 = new FindReplacePattern();
        findReplacePattern1.setFind("findValue");
        findReplacePattern1.setReplace("replaceValue");

        final FindReplacePattern findReplacePattern2 = new FindReplacePattern();
        findReplacePattern2.setFind("findValue");
        findReplacePattern2.setReplace("replaceValue");

        assertEquals(findReplacePattern1, findReplacePattern2);
        findReplacePattern1.removeEmptyObjects();
        assertEquals(findReplacePattern1, findReplacePattern2);
    }
}
