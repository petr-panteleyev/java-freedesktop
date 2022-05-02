/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
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
