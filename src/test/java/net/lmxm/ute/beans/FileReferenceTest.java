package net.lmxm.ute.beans;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReferenceTest {
    @Test
    public void testEqualsObject() {
        EqualsVerifier.forClass(FileReference.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void testGetName() {
        final FileReference fileReference = new FileReference();
        fileReference.setName("name");
        assertEquals("name", fileReference.getName());
    }

    @Test
    public void testGetTargetName() {
        final FileReference fileReference = new FileReference();
        fileReference.setTargetName("targetName");
        assertEquals("targetName", fileReference.getTargetName());
    }

    @Test
    public void testIsEmptyNewInstance() {
        final FileReference fileReference = new FileReference();
        assertTrue(fileReference.isEmpty());
    }

    @Test
    public void testIsEmptyNameSet() {
        final FileReference fileReference = new FileReference();
        fileReference.setName("name");
        assertFalse(fileReference.isEmpty());
    }

    @Test
    public void testIsEmptyTargetNameSet() {
        final FileReference fileReference = new FileReference();
        fileReference.setTargetName("targetName");
        assertFalse(fileReference.isEmpty());
    }

    @Test
    public void testToString() {
        final FileReference fileReference = new FileReference();
        fileReference.setName("fileReferenceName");
        fileReference.setTargetName("fileReferenceTargetName");

        assertTrue(fileReference.toString().contains("fileReferenceName"));
        assertTrue(fileReference.toString().contains("fileReferenceTargetName"));
    }

    @Test
    public void testRemoveEmptyObjects() {
        final FileReference fileReference1 = new FileReference();
        fileReference1.setName("name");
        fileReference1.setTargetName("targetName");

        final FileReference fileReference2 = new FileReference();
        fileReference2.setName("name");
        fileReference2.setTargetName("targetName");

        assertEquals(fileReference1, fileReference2);
        fileReference1.removeEmptyObjects();
        assertEquals(fileReference1, fileReference2);
    }
}
