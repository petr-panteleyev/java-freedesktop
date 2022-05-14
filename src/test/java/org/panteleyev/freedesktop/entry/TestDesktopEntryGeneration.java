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

import static org.panteleyev.freedesktop.entry.DesktopEntryBuilder.localeString;
import org.panteleyev.freedesktop.menu.Category;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Test
public class TestDesktopEntryGeneration {

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {
                        // Minimal case
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name("Test Application"),
                        """
                        [Desktop Entry]
                        Type=Application
                        Name=Test Application
                        """
                },
                {
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name("Test Application")
                                .name(localeString("Тестовое приложение", "ru_RU"))
                                .comment("Super test application")
                                .noDisplay(false)
                                .categories(List.of(Category.DEVELOPMENT, Category.IDE)),
                        """
                        [Desktop Entry]
                        Type=Application
                        Name=Test Application
                        Name[ru_RU]=Тестовое приложение
                        NoDisplay=false
                        Comment=Super test application
                        Categories=Development;IDE;
                        """
                },
                {
                        // Example from spec
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .version(DesktopEntryBuilder.VERSION_1_0)
                                .name("Foo Viewer")
                                .comment("The best viewer for Foo objects available!")
                                .customEntry("X-KDE-StartupNotify", "true")
                                .tryExec("fooview")
                                .exec("fooview %F")
                                .customEntry("X-KDE-StartupNotify", localeString("false", "en_US"))
                                .icon("fooview")
                                .mimeType(List.of("image/x-foo"))
                                .actions(List.of(
                                        new ApplicationActionBuilder("Gallery")
                                                .exec("fooview --gallery")
                                                .name("Browse Gallery")
                                                .build(),
                                        new ApplicationActionBuilder("Create")
                                                .exec("fooview --create-new")
                                                .name("Create a new Foo!")
                                                .icon("fooview-new")
                                                .build()
                                )
                        ),
                        """
                        [Desktop Entry]
                        Type=Application
                        Version=1.0
                        Name=Foo Viewer
                        Comment=The best viewer for Foo objects available!
                        Icon=fooview
                        TryExec=fooview
                        Exec=fooview %F
                        Actions=Gallery;Create;
                        MimeType=image/x-foo;
                        X-KDE-StartupNotify=true
                        X-KDE-StartupNotify[en_US]=false
                        
                        [Desktop Action Gallery]
                        Name=Browse Gallery
                        Exec=fooview --gallery
                        
                        [Desktop Action Create]
                        Name=Create a new Foo!
                        Icon=fooview-new
                        Exec=fooview --create-new
                        """
                }
        };
    }

    @Test(dataProvider = "dataProvider")
    public void testGeneration(DesktopEntryBuilder builder, String expected) throws Exception {
        var desktopEntry = builder.build();
        try (var out = new ByteArrayOutputStream()) {
            desktopEntry.write(out);
            var str = out.toString(StandardCharsets.UTF_8);
            assertEquals(str, expected);
        }
    }
}
