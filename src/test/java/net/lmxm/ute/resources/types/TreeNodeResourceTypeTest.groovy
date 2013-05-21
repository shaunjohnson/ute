package net.lmxm.ute.resources.types

import net.lmxm.ute.resources.ResourceCategory
import org.junit.Test

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.is
import static org.hamcrest.core.IsNull.nullValue
import static org.junit.Assert.assertThat

/**
 * Unit tests for TreeNodeResourceType.
 */
public final class TreeNodeResourceTypeTest {
    @Test
    public void testGetActionCommand() {
        for (final TreeNodeResourceType resourceType : TreeNodeResourceType.values()) {
            assertThat(resourceType.getActionCommand(), is(nullValue()));
        }
    }

    @Test
    public void testGetIcon() {
        for (final TreeNodeResourceType resourceType : TreeNodeResourceType.values()) {
            assertThat(resourceType.getIcon(), is(nullValue()));
        }
    }

    @Test
    public void testGetResourceCategory() {
        for (final TreeNodeResourceType resourceType : TreeNodeResourceType.values()) {
            assertThat(resourceType.getResourceCategory(), is(equalTo(ResourceCategory.TREE_NODE)));
        }
    }
}
