/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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
