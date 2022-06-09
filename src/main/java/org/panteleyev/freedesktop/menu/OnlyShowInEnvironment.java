/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
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
