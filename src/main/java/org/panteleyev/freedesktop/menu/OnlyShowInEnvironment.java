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
package org.panteleyev.freedesktop.menu;

/**
 * Registered OnlyShowIn environments.
 */
public enum OnlyShowInEnvironment {
    /**
     * GNOME Desktop.
     */
    GNOME("GNOME"),
    /**
     * GNOME Classic Desktop.
     */
    GNOME_CLASSIC("GNOME-Classic"),
    /**
     * GNOME Flashback Desktop.
     */
    GNOME_FLASHBACK("GNOME-Flashback"),
    /**
     * KDE Desktop.
     */
    KDE("KDE"),
    /**
     * LXDE Desktop.
     */
    LXDE("LXDE"),
    /**
     * LXQt Desktop.
     */
    LXQT("LXQt"),
    /**
     * MATÉ Desktop.
     */
    MATE("MATE"),
    /**
     * Razor-qt Desktop.
     */
    RAZOR("Razor"),
    /**
     * ROX Desktop.
     */
    ROX("ROX"),
    /**
     * Trinity Desktop.
     */
    TDE("TDE"),
    /**
     * Unity Shell.
     */
    UNITY("Unity"),
    /**
     * XFCE Desktop.
     */
    XFCE("XFCE"),
    /**
     * EDE Desktop.
     */
    EDE("EDE"),
    /**
     * Cinnamon Desktop.
     */
    CINNAMON("Cinnamon"),
    /**
     * Pantheon Desktop.
     */
    PANTHEON("Pantheon"),
    /**
     * Legacy menu systems.
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
