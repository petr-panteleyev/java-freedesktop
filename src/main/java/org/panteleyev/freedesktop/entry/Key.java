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
package org.panteleyev.freedesktop.entry;

import java.util.Set;

/**
 * Defines file entry keys.
 */
enum Key implements CharSequence {
    TYPE("Type"),
    VERSION("Version"),
    NAME("Name"),
    GENERIC_NAME("GenericName"),
    NO_DISPLAY("NoDisplay"),
    COMMENT("Comment"),
    ICON("Icon"),
    HIDDEN("Hidden"),
    ONLY_SHOW_IN("OnlyShowIn"),
    NOT_SHOW_IN("NotShowIn"),
    D_BUS_ACTIVATABLE("DBusActivatable"),
    TRY_EXEC("TryExec", DesktopEntryType.APPLICATION),
    EXEC("Exec", DesktopEntryType.APPLICATION),
    PATH("Path", DesktopEntryType.APPLICATION),
    TERMINAL("Terminal", DesktopEntryType.APPLICATION),
    ACTIONS("Actions", DesktopEntryType.APPLICATION),
    MIME_TYPE("MimeType", DesktopEntryType.APPLICATION),
    CATEGORIES("Categories", DesktopEntryType.APPLICATION),
    IMPLEMENTS("Implements"),
    KEYWORDS("Keywords", DesktopEntryType.APPLICATION),
    STARTUP_NOTIFY("StartupNotify", DesktopEntryType.APPLICATION),
    STARTUP_WM_CLASS("StartupWMClass", DesktopEntryType.APPLICATION),
    URL("URL", DesktopEntryType.LINK),
    PREFERS_NON_DEFAULT_GPU("PrefersNonDefaultGPU", DesktopEntryType.APPLICATION),
    SINGLE_MAIN_WINDOW("SingleMainWindow", DesktopEntryType.APPLICATION);

    private final String value;
    private final Set<DesktopEntryType> allowed;

    Key(String value, DesktopEntryType... allowed) {
        this.value = value;
        this.allowed = Set.of(allowed);
    }

    Key(String value) {
        this.value = value;
        this.allowed = DesktopEntryType.ALL;
    }

    Set<DesktopEntryType> getAllowed() {
        return allowed;
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public String toString() {
        return value;
    }
}
