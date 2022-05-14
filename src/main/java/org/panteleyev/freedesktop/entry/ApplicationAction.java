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