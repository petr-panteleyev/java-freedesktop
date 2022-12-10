/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.freedesktop.entry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.panteleyev.freedesktop.menu.Category;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.panteleyev.freedesktop.entry.DesktopEntryBuilder.localeString;

public class TestDesktopEntryGeneration {

    public static List<Arguments> dataProvider() {
        return List.of(
                Arguments.of(
                        // Minimal case
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name("Test Application"),
                        """
                                [Desktop Entry]
                                Type=Application
                                Name=Test Application
                                """
                ),
                Arguments.of(
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .name("Test Application")
                                .name(localeString("Тестовое приложение", "ru_RU"))
                                .comment("Super test application")
                                .noDisplay(false)
                                .singleMainWindow(true)
                                .categories(List.of(Category.DEVELOPMENT, Category.IDE)),
                        """
                                [Desktop Entry]
                                Type=Application
                                Name=Test Application
                                Name[ru_RU]=Тестовое приложение
                                NoDisplay=false
                                Comment=Super test application
                                Categories=Development;IDE;
                                SingleMainWindow=true
                                """
                ),
                Arguments.of(
                        // Example from spec
                        new DesktopEntryBuilder(DesktopEntryType.APPLICATION)
                                .version(DesktopEntryBuilder.VERSION_1_5)
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
                                Version=1.5
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
                )
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void testGeneration(DesktopEntryBuilder builder, String expected) throws Exception {
        var desktopEntry = builder.build();
        try (var out = new ByteArrayOutputStream()) {
            desktopEntry.write(out);
            var str = out.toString(StandardCharsets.UTF_8);
            assertEquals(expected, str);
        }
    }
}
