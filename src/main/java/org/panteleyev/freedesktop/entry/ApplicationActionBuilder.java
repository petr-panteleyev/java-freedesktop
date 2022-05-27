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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Implements Application Action builder.
 * Methods correspond to Desktop Entry attributes defined in
 * <a href="https://specifications.freedesktop.org/desktop-entry-spec/desktop-entry-spec-1.1.html#extra-actions">freedesktop.org specification</a>.
 */
public class ApplicationActionBuilder {
    private final String name;
    private final Set<Entry> entries = new HashSet<>();

    public ApplicationActionBuilder(String name) {
        Objects.requireNonNull(name, "Desktop Action name cannot be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Desktop Action name cannot be empty");
        }
        this.name = name.trim();
    }

    /**
     * Label that will be shown to the user. Since actions are always shown in the context of a specific application
     * (that is, as a submenu of a launcher), this only needs to be unambiguous within one application and should not
     * include the application name.
     *
     * @param name Name value
     * @return this
     */
    public ApplicationActionBuilder name(CharSequence name) {
        entries.add(new Entry(Key.NAME, name));
        return this;
    }

    /**
     * Icon to be shown together with the action. If the name is an absolute path, the given file will be used. If
     * the name is not an absolute path, the algorithm described in the
     * <a href="https://freedesktop.org/wiki/Specifications/icon-theme-spec/">Icon Theme Specification</a>
     * will be used to locate the icon. Implementations may choose to ignore it.
     *
     * @param icon Icon value
     * @return this
     */
    public ApplicationActionBuilder icon(CharSequence icon) {
        entries.add(new Entry(Key.ICON, icon));
        return this;
    }

    /**
     * Program to execute for this action, possibly with arguments. See the
     * <a href="https://specifications.freedesktop.org/desktop-entry-spec/desktop-entry-spec-1.1.html#exec-variables">Exec key</a>
     * for details on how this key works. The Exec key is required if DBusActivatable is not set to true in the main
     * desktop entry group. Even if DBusActivatable is true, Exec should be specified for compatibility with
     * implementations that do not understand DBusActivatable.
     *
     * @param exec Exec value
     * @return this
     */
    public ApplicationActionBuilder exec(String exec) {
        entries.add(new Entry(Key.EXEC, Objects.requireNonNull(exec).trim()));
        return this;
    }

    /**
     * Builds {@link ApplicationAction} instance.
     *
     * @return {@link ApplicationAction} instance
     */
    public ApplicationAction build() {
        validate();
        return new ApplicationAction(name, entries);
    }

    private void validate() {
        // Check for mandatory fields
        entries.stream()
                .filter(entry -> entry.key() == Key.NAME)
                .findAny()
                .orElseThrow(() -> new ValidationException("Action missing mandatory name"));
    }
}
