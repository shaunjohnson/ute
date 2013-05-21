package net.lmxm.ute.resources.types;

import net.lmxm.ute.resources.ResourceCategory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for ApplicationResourceType.
 */
public final class ApplicationResourceTypeTest {
    @Test
    public void testGetActionCommand() {
        for (final ApplicationResourceType resourceType : ApplicationResourceType.values()) {
            assertThat(resourceType.getActionCommand(), is(nullValue()));
        }
    }

    @Test
    public void testGetIcon() {
        for (final ApplicationResourceType resourceType : ApplicationResourceType.values()) {
            assertThat(resourceType.getIcon(), is(nullValue()));
        }
    }

    @Test
    public void testGetResourceCategory() {
        for (final ApplicationResourceType resourceType : ApplicationResourceType.values()) {
            assertThat(resourceType.getResourceCategory(), is(equalTo(ResourceCategory.APPLICATION)));
        }
    }
}
