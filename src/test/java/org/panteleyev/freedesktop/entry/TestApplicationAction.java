/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.freedesktop.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestApplicationAction {

    @Test
    public void testDesktopActionBuilderValidation() {
        assertThrows(ValidationException.class, () -> new ApplicationActionBuilder("Name").build());
    }
}
