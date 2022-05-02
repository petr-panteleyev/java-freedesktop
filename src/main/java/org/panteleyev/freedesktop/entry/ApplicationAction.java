/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
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