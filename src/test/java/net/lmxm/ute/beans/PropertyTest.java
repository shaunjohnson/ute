package net.lmxm.ute.beans;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {
    @Test
    public void testEqualsObject() {
        EqualsVerifier.forClass(Property.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void testGetDisplayText() {
        assertEquals("propertyId", new Property("propertyId").getDisplayText());
    }

    @Test
    public void testGetId() {
        final Property property = new Property();
        property.setId("id");
        assertEquals("id", property.getId());
    }

    @Test
    public void testGetValue() {
        final Property property = new Property();
        property.setValue("value");
        assertEquals("value", property.getValue());
    }

    @Test
    public void testIsEmptyNewInstance() {
        final Property property = new Property();
        assertTrue(property.isEmpty());
    }

    @Test
    public void testIsEmptyIdSet() {
        final Property property = new Property();
        property.setId("propertyId");
        assertFalse(property.isEmpty());
    }

    @Test
    public void testIsEmptyTargetNameSet() {
        final Property property = new Property();
        property.setValue("propertyValue");
        assertFalse(property.isEmpty());
    }

    @Test
    public void testToString() {
        final Property property = new Property();
        property.setId("propertyId");
        property.setValue("propertyValue");

        assertTrue(property.toString().contains("propertyId"));
        assertTrue(property.toString().contains("propertyValue"));
    }

    @Test
    public void testRemoveEmptyObjects() {
        final Property property1 = new Property();
        property1.setId("propertyId");
        property1.setValue("propertyValue");

        final Property property2 = new Property();
        property2.setId("propertyId");
        property2.setValue("propertyValue");

        assertEquals(property1, property2);
        property1.removeEmptyObjects();
        assertEquals(property1, property2);
    }
}
