package net.lmxm.ute.beans;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static org.junit.Assert.*;

public class PreferenceTest {
    @Test
    public void testEqualsObject() {
        EqualsVerifier.forClass(Preference.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void testGetDisplayText() {
        assertEquals("preferenceId", new Preference("preferenceId").getDisplayText());
    }

    @Test
    public void testIsEmptyNewInstance() {
        final Preference preference = new Preference();
        assertTrue(preference.isEmpty());
    }

    @Test
    public void testIsEmptyIdSet() {
        final Preference preference = new Preference();
        preference.setId("preferenceId");
        assertFalse(preference.isEmpty());
    }

    @Test
    public void testIsEmptyTargetNameSet() {
        final Preference preference = new Preference();
        preference.setValue("preferenceValue");
        assertFalse(preference.isEmpty());
    }

    @Test
    public void testToString() {
        final Preference preference = new Preference();
        preference.setId("preferenceId");
        preference.setValue("preferenceValue");

        assertTrue(preference.toString().contains("preferenceId"));
        assertTrue(preference.toString().contains("preferenceValue"));
    }

    @Test
    public void testRemoveEmptyObjects() {
        final Preference preference1 = new Preference();
        preference1.setId("preferenceId");
        preference1.setValue("preferenceValue");

        final Preference preference2 = new Preference();
        preference2.setId("preferenceId");
        preference2.setValue("preferenceValue");

        assertEquals(preference1, preference2);
        preference1.removeEmptyObjects();
        assertEquals(preference1, preference2);
    }
}
