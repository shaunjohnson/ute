package net.lmxm.ute.resources.types;

import net.lmxm.ute.resources.ResourceCategory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for ConfirmationResourceType.
 */
public final class ConfirmationResourceTypeTest {
    @Test
    public void testGetActionCommand() {
        for (final ConfirmationResourceType resourceType : ConfirmationResourceType.values()) {
            assertThat(resourceType.getActionCommand(), is(nullValue()));
        }
    }

    @Test
    public void testGetIcon() {
        for (final ConfirmationResourceType resourceType : ConfirmationResourceType.values()) {
            assertThat(resourceType.getIcon(), is(nullValue()));
        }
    }

    @Test
    public void testGetResourceCategory() {
        for (final ConfirmationResourceType resourceType : ConfirmationResourceType.values()) {
            assertThat(resourceType.getResourceCategory(), is(equalTo(ResourceCategory.CONFIRMATION)));
        }
    }
}
