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
