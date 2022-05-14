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
package org.panteleyev.freedesktop.menu;

/**
 * Registered OnlyShowIn environments.
 */
public enum OnlyShowInEnvironment {
    /**
     * GNOME Desktop
     */
    GNOME("GNOME"),
    /**
     * KDE Desktop
     */
    KDE("KDE"),
    /**
     * ROX Desktop
     */
    ROX("ROX"),
    /**
     * XFCE Desktop
     */
    XFCE("XFCE"),
    /**
     * Legacy menu systems
     */
    OLD("Old");

    private final String value;

    OnlyShowInEnvironment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
