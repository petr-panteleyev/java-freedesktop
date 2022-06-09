/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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
