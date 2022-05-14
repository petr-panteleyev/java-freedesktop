/*
 Copyright (c) 2022, Petr Panteleyev

 This program is free software: you can redistribute it and/or modify it under the
 terms of the GNU General Public License as published by the Free Software
 Foundation, either version 3 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with this
 program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.panteleyev.freedesktop.entry;

import org.panteleyev.freedesktop.menu.Category;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class TestDesktopEntryValidation {

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .categories(List.of(Category.IDE))
                },
                {
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name("Name")
                                .url("url")
                },
                {
                        new DesktopEntryBuilder(DesktopEntryType.LINK)
                                .name("Name")
                },
        };
    }


    @Test(dataProvider = "dataProvider", expectedExceptions = ValidationException.class)
    public void testNegativeValidation(DesktopEntryBuilder builder) {
        builder.build();
    }
}
