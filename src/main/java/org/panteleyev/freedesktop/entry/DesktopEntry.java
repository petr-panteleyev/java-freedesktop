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

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;

import static org.panteleyev.freedesktop.directory.XDGBaseDirectory.getUserDesktopEntryDirectory;

/**
 * Implements Desktop Entry specification.
 *
 * <h1>Location</h1>
 * By default Desktop entry files are located in either<br>
 * <code>$XDG_DATA_HOME/applications</code><br>
 * or<br>
 * <code>$HOME/.local/share/applications</code>
 *
 * <h1>File Extension</h1>
 * Desktop entry files should have the <code>.desktop</code> extension, except for files of Type Directory which
 * should have the <code>.directory</code> extension.
 */
public class DesktopEntry {
    private static final String DESKTOP_ENTRY = "[Desktop Entry]";
    private static final String DESKTOP_ACTION_TEMPLATE = "[Desktop Action %s]";

    private static final Comparator<Entry> ENTRY_COMPARATOR = ((Comparator<Entry>) (o1, o2) -> {
        if (o1.key() instanceof Key k1 && o2.key() instanceof Key k2) {
            return k1.compareTo(k2);
        } else if (o1.key() instanceof String s1 && o2.key() instanceof String s2) {
            return s1.compareTo(s2);
        } else if (o1.key() instanceof Key && o2.key() instanceof String) {
            return -1;
        } else {
            return 1;
        }
    }).thenComparing(Entry::locale);

    private final DesktopEntryType type;
    private final Collection<Entry> entries;
    private final Collection<ApplicationAction> actions;

    DesktopEntry(DesktopEntryType type, Collection<Entry> entries, Collection<ApplicationAction> actions) {
        this.type = type;
        this.entries = entries;
        this.actions = actions;
    }

    /**
     * Writes Desktop Entry into the file with a given name. Name must not include path and file extension. File
     * extension and directory are calculated based on system environment variables and Desktop Entry type.
     *
     * @param name file name without extension
     */
    public void write(String name) {
        try {
            var dir = getUserDesktopEntryDirectory();
            Files.createDirectories(dir);
            write(Path.of(dir.toString(), name + "." + type.getFileExtension()));
        } catch (FileAlreadyExistsException ex) {
            throw new RuntimeException("Unable to create directory " + ex.getFile());
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    /**
     * Writes Desktop Entry into the file.
     *
     * @param path path to desktop entry file
     */
    public void write(Path path) {
        try (var out = Files.newOutputStream(path)) {
            write(out);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    /**
     * Writes Desktop Entry into the output stream.
     *
     * @param outputStream output stream
     */
    public void write(OutputStream outputStream) {
        try (var out = new PrintWriter(outputStream)) {
            out.println(DESKTOP_ENTRY);

            entries.stream()
                    .sorted(ENTRY_COMPARATOR)
                    .forEach(entry -> writeEntry(out, entry));

            // Write desktop actions
            for (var action : actions) {
                out.println();
                out.println(String.format(DESKTOP_ACTION_TEMPLATE, action.name()));
                action.entries().stream()
                        .sorted(ENTRY_COMPARATOR)
                        .forEach(entry -> writeEntry(out, entry));
            }
        }
    }

    private void writeEntry(PrintWriter pr, Entry entry) {
        pr.print(entry.key());
        if (!entry.locale().isEmpty()) {
            pr.print("[" + entry.locale() + "]");
        }
        pr.print("=");
        pr.println(entry.value().toString());
    }
}


