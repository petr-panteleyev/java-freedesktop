/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.freedesktop.entry;

import java.util.Collection;

/**
 * Implements Desktop Action.
 */
public class ApplicationAction {
    private final String name;
    private final Collection<Entry> entries;

    ApplicationAction(String name, Collection<Entry> entries) {
        this.name = name;
        this.entries = entries;
    }

    String name() {
        return name;
    }

    Collection<Entry> entries() {
        return entries;
    }
}