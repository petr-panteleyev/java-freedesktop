/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

import org.panteleyev.freedesktop.menu.Category;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.panteleyev.freedesktop.entry.LocaleString.localeString;

@Test
public class TestDesktopEntryValidation {

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name(localeString("Test Application"))
                },
                {
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .url("value")
                },
                {
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .categories(List.of(Category.IDE))
                }
        };
    }


    @Test(dataProvider = "dataProvider", expectedExceptions = ValidationException.class, enabled = false)
    public void testNegativeValidation(DesktopEntryBuilder builder) {
        builder.build();
    }
}
