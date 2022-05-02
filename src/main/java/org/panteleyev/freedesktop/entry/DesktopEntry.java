/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.Collection;
import java.util.Comparator;

import static java.util.Comparator.comparing;
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

    private static final Comparator<Entry> ENTRY_COMPARATOR = comparing(Entry::key).thenComparing(Entry::locale);

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
        var dir = getUserDesktopEntryDirectory();
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new RuntimeException("Unable to create directory " + dir.getAbsolutePath());
            }
        }

        if (!dir.isDirectory()) {
            throw new IllegalStateException(dir.getAbsolutePath() + " is not a directory");
        }

        write(new File(dir, name + "." + type.getFileExtension()));
    }

    /**
     * Writes Desktop Entry into the file.
     *
     * @param file desktop entry file
     */
    public void write(File file) {
        try (var out = new FileOutputStream(file)) {
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
        pr.println(entry.value());
    }
}


