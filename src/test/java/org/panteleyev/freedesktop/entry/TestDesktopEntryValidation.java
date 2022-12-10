/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.freedesktop.entry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.panteleyev.freedesktop.menu.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDesktopEntryValidation {

    public static List<Arguments> dataProvider() {
        return List.of(
                Arguments.of(
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .categories(List.of(Category.IDE))
                ),
                Arguments.of(
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name("Name")
                                .url("url")
                ),
                Arguments.of(
                        new DesktopEntryBuilder(DesktopEntryType.LINK)
                                .name("Name")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testNegativeValidation(DesktopEntryBuilder builder) {
        assertThrows(ValidationException.class, builder::build);
    }
}
