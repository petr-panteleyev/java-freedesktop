/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

import org.testng.annotations.Test;

@Test
public class TestApplicationAction {

    @Test(expectedExceptions = ValidationException.class)
    public void testDesktopActionBuilderValidation() {
        new ApplicationActionBuilder("Name").build();
    }
}
