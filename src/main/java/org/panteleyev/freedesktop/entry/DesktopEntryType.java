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

    static final Set<DesktopEntryType> ALL = Set.of(DesktopEntryType.values());

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

    /**
     * Returns file extension for desktop entry type. Desktop entry files should have the <code>.desktop</code>
     * extension, except for files of type Directory which should have the <code>.directory</code> extension.
     *
     * @return file extension
     */
    public String getFileExtension() {
        return fileExtension;
    }
}
