/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

import org.panteleyev.freedesktop.menu.Category;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.panteleyev.freedesktop.entry.LocaleString.localeString;
import static org.testng.Assert.assertEquals;

@Test
public class TestDesktopEntryGeneration {

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {
                        // Minimal case
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name(localeString("Test Application")),
                        """
                        [Desktop Entry]
                        Type=Application
                        Name=Test Application
                        """
                },
                {
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name(localeString("Test Application"))
                                .name(localeString("Тестовое приложение", "ru_RU"))
                                .comment(localeString("Super test application"))
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
                                .name(localeString("Foo Viewer"))
                                .comment(localeString("The best viewer for Foo objects available!"))
                                .tryExec("fooview")
                                .exec("fooview %F")
                                .icon(localeString("fooview"))
                                .mimeType(List.of("image/x-foo"))
                                .actions(List.of(
                                        new ApplicationActionBuilder("Gallery")
                                                .exec("fooview --gallery")
                                                .name(localeString("Browse Gallery"))
                                                .build(),
                                        new ApplicationActionBuilder("Create")
                                                .exec("fooview --create-new")
                                                .name(localeString("Create a new Foo!"))
                                                .icon(localeString("fooview-new"))
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
