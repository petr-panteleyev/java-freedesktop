/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

import java.util.Set;

/**
 * Defines type of desktop entry.
 */
public enum DesktopEntryType {
    APPLICATION("Application", "desktop"),
    LINK("Link", "desktop"),
    DIRECTORY("Directory", "directory");

    public static final Set<DesktopEntryType> ALL = Set.of(DesktopEntryType.values());

    private final String value;
    private final String fileExtension;

    DesktopEntryType(String value, String fileExtension) {
        this.value = value;
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
