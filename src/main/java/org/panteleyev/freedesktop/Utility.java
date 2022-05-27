/*
 Copyright (C) 2022 Petr Panteleyev

 This program is free software: you can redistribute it and/or modify it under the
 terms of the GNU General Public License as published by the Free Software
 Foundation, either version 3 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with this
 program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.panteleyev.freedesktop;

import java.util.Optional;

/**
 * Provides utility methods.
 */
public abstract class Utility {
    private Utility() {
    }

    /**
     * Returns <code>true</code> if operating system is of Linux family.
     *
     * @return <code>true</code> if Linux
     */
    public static boolean isLinux() {
        return System.getProperty("os.name", "").toLowerCase().startsWith("linux");
    }

    /**
     * Returns current executable command. Useful to build
     * {@link org.panteleyev.freedesktop.entry.DesktopEntryBuilder#exec(String)},
     * {@link org.panteleyev.freedesktop.entry.DesktopEntryBuilder#tryExec(String)} and
     * {@link org.panteleyev.freedesktop.entry.DesktopEntryBuilder#icon(CharSequence)}
     * values.
     *
     * @return executable command
     */
    public static Optional<String> getExecutablePath() {
        return ProcessHandle.current().info().command();
    }
}
