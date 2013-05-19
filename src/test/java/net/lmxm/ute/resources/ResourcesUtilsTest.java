/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 *
 * This file is part of Universal Task Executer.
 *
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.resources;

import net.lmxm.ute.resources.types.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ResourcesUtilsTest {
    private String generateResourceId(final ResourceType resourceType, final ResourceValueType resourceValueType) {
        final StringBuilder builder = new StringBuilder();
        builder.append("resourceCategory=");
        builder.append(resourceType.getResourceCategory());
        builder.append(", resourceType=");
        builder.append(resourceType);
        builder.append(", resourceValueType=");
        builder.append(resourceValueType);

        return builder.toString();
    }

    private void assertValuesAreSet(ResourceType[] resourceTypes) {
        for (ResourceType resourceType : resourceTypes) {
            for (ResourceValueType resourceValueType : resourceType.getResourceValueTypes()) {
                final String resource = ResourcesUtils.getResource(resourceType, resourceValueType);

                if (resourceValueType.isOptional() && resource == null) {
                    // Skip accelerator values that are null as these are optional
                    continue;
                }

                assertNotNull("Resource does not exist (" + generateResourceId(resourceType, resourceValueType) + ")", resource);
                assertFalse("Resource value is empty (" + generateResourceId(resourceType, resourceValueType) + ")", resource.equals(""));
            }
        }
    }

    @Test
    public void testApplicationResourceType() {
        assertValuesAreSet(ApplicationResourceType.values());
    }

    @Test
    public void testButtonResourceType() {
        assertValuesAreSet(ButtonResourceType.values());
    }

    @Test
    public void testConfirmationResourceType() {
        assertValuesAreSet(ConfirmationResourceType.values());
    }

    @Test
    public void testExceptionResourceType() {
        assertValuesAreSet(ExceptionResourceType.values());
    }

    @Test
    public void testLabelResourceType() {
        assertValuesAreSet(LabelResourceType.values());
    }

    @Test
    public void testMenuItemResourceType() {
        assertValuesAreSet(MenuItemResourceType.values());
    }

    @Test
    public void testMenuResourceType() {
        assertValuesAreSet(MenuResourceType.values());
    }

    @Test
    public void testScopeResourceType() {
        assertValuesAreSet(ScopeResourceType.values());
    }

    @Test
    public void testStatusChangeMessageResourceType() {
        assertValuesAreSet(StatusChangeMessageResourceType.values());
    }

    @Test
    public void testSubversionDepthResourceType() {
        assertValuesAreSet(SubversionDepthResourceType.values());
    }

    @Test
    public void testSubversionEventResourceType() {
        assertValuesAreSet(SubversionEventResourceType.values());
    }

    @Test
    public void testSubversionRevisionResourceType() {
        assertValuesAreSet(SubversionRevisionResourceType.values());
    }

    @Test
    public void testTableColumnResourceType() {
        assertValuesAreSet(TableColumnResourceType.values());
    }

    @Test
    public void testToolbarButtonResourceType() {
        assertValuesAreSet(ToolbarButtonResourceType.values());
    }

    @Test
    public void testTreeNodeResourceType() {
        assertValuesAreSet(TreeNodeResourceType.values());
    }

    @Test
    public void testValidatorResourceType() {
        assertValuesAreSet(ValidatorResourceType.values());
    }
}
