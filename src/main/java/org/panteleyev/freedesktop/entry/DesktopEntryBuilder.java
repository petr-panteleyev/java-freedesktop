/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

import org.panteleyev.freedesktop.menu.Category;
import org.panteleyev.freedesktop.menu.OnlyShowInEnvironment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implements Desktop Entry builder.
 * Methods correspond to Desktop Entry attributes defined in
 * <a href="https://specifications.freedesktop.org/desktop-entry-spec/desktop-entry-spec-1.1.html">freedesktop.org specification</a>.
 */
public class DesktopEntryBuilder {
    public static final String VERSION_1_0 = "1.0";

    private final DesktopEntryType type;

    private final Set<Entry> entries = new HashSet<>();
    private final List<ApplicationAction> actions = new ArrayList<>();


    /**
     * Creates desktop entry builder.
     *
     * @param type Desktop Entry type
     */
    public DesktopEntryBuilder(DesktopEntryType type) {
        this.type = Objects.requireNonNull(type, "Desktop Entry type cannot by null");
        entries.add(new Entry(Key.TYPE, type.toString(), ""));
    }

    /**
     * Version of the Desktop Entry Specification that the desktop entry conforms with. Entries that confirm with
     * this version of the specification should use 1.0. Note that the version field is not required to be present.
     *
     * @param version version
     */
    public DesktopEntryBuilder version(String version) {
        entries.add(new Entry(Key.VERSION,
                Objects.requireNonNull(version),
                ""));
        return this;
    }

    /**
     * Specific name of the application, for example &quot;Mozilla&quot;.
     *
     * @param name name
     */
    public DesktopEntryBuilder name(LocaleString name) {
        Objects.requireNonNull(name);
        entries.add(new Entry(Key.NAME, name.value(), name.locale()));
        return this;
    }

    /**
     * Generic name of the application, for example &quot;Web Browser&quot;.
     *
     * @param name name
     */
    public DesktopEntryBuilder genericName(LocaleString name) {
        entries.add(new Entry(Key.GENERIC_NAME, name.value(), name.locale()));
        return this;
    }

    /**
     * NoDisplay means &quot;this application exists, but don't display it in the menus&quot;. This can be useful to
     * e.g. associate this application with MIME types, so that it gets launched from a file manager (or other apps),
     * without having a menu entry for it (there are tons of good reasons for this, including e.g. the netscape
     * -remote, or kfmclient openURL kind of stuff).
     *
     * @param noDisplay noDisplay value
     */
    public DesktopEntryBuilder noDisplay(boolean noDisplay) {
        entries.add(new Entry(Key.NO_DISPLAY, Boolean.toString(noDisplay), ""));
        return this;
    }

    /**
     * Tooltip for the entry, for example &quot;View sites on the Internet&quot;. The value should not be redundant
     * with the values of Name and GenericName.
     *
     * @param comment comment
     */
    public DesktopEntryBuilder comment(LocaleString comment) {
        entries.add(new Entry(Key.COMMENT, comment.value(), comment.locale()));
        return this;
    }

    /**
     * Icon to display in file manager, menus, etc. If the name is an absolute path, the given file will be used. If
     * the name is not an absolute path, the algorithm described in the
     * <a href="https://freedesktop.org/wiki/Specifications/icon-theme-spec/">Icon Theme Specification</a> will be used
     * to locate the icon.
     *
     * @param icon Icon value
     * @return this
     */
    public DesktopEntryBuilder icon(LocaleString icon) {
        entries.add(new Entry(Key.ICON, icon.value(), icon.locale()));
        return this;
    }

    /**
     * Hidden. Hidden means the user deleted (at his level) something that was present
     * (at an upper level, e.g. in the system dirs). It's strictly equivalent to the .desktop file not existing at
     * all, as far as that user is concerned. This can also be used to &quot;uninstall&quot; existing files (e.g. due
     * to a renaming) - by letting <code>make install</code> install a file with <code>Hidden=true</code> in it.
     *
     * @param hidden Hidden value
     * @return this
     */
    public DesktopEntryBuilder hidden(boolean hidden) {
        entries.add(new Entry(Key.HIDDEN, Boolean.toString(hidden), ""));
        return this;
    }

    /**
     * A list of strings identifying the environments that should display a given desktop entry. Only one
     * of either OnlyShowIn or NotShowIn may appear in a group.
     *
     * @param onlyShowIn OnlyShowIn value
     * @return this
     */
    public DesktopEntryBuilder onlyShowIn(Set<OnlyShowInEnvironment> onlyShowIn) {
        var value = onlyShowIn.stream()
                .map(OnlyShowInEnvironment::getValue)
                .collect(Collectors.joining(";"))
                + ";";
        entries.add(new Entry(Key.ONLY_SHOW_IN, value, ""));
        return this;
    }

    /**
     * A list of strings identifying the environments that should not display a given desktop entry. Only one
     * of either OnlyShowIn or NotShowIn may appear in a group.
     *
     * @param notShowIn NotShowIn value
     * @return this
     */
    public DesktopEntryBuilder notShowIn(Set<OnlyShowInEnvironment> notShowIn) {
        var value = notShowIn.stream()
                .map(OnlyShowInEnvironment::getValue)
                .collect(Collectors.joining(";"))
                + ";";
        entries.add(new Entry(Key.NOT_SHOW_IN, value, ""));
        return this;
    }

    /**
     * A boolean value specifying if D-Bus activation is supported for this application. If this key is missing, the
     * default value is false. If the value is true then implementations should ignore the Exec key and send a D-Bus
     * message to launch the application. See
     * <a href="https://specifications.freedesktop.org/desktop-entry-spec/desktop-entry-spec-1.1.html#dbus">D-Bus Activation</a>
     * for more information on how this works. Applications should still include Exec= lines in their desktop files
     * for compatibility with implementations that do not understand the DBusActivatable key.
     *
     * @param dBusActivatable DBusActivatable value
     * @return this
     */
    public DesktopEntryBuilder dBusActivatable(boolean dBusActivatable) {
        entries.add(new Entry(Key.D_BUS_ACTIVATABLE, Boolean.toString(dBusActivatable), ""));
        return this;
    }

    /**
     * Path to an executable file on disk used to determine if the program is actually installed. If the path is not
     * an absolute path, the file is looked up in the $PATH environment variable. If the file is not present or if it
     * is not executable, the entry may be ignored (not be used in menus, for example).
     *
     * @param tryExec TryExec value
     * @return this
     */
    public DesktopEntryBuilder tryExec(String tryExec) {
        entries.add(new Entry(Key.TRY_EXEC, tryExec, ""));
        return this;
    }

    /**
     * Program to execute, possibly with arguments. See the
     * <a href="https://specifications.freedesktop.org/desktop-entry-spec/desktop-entry-spec-1.1.html#exec-variables">Exec key</a>
     * for details on how this key works. The Exec key is required if DBusActivatable is not set to true. Even if
     * DBusActivatable is true, Exec should be specified for compatibility with implementations that do not
     * understand DBusActivatable.
     *
     * @param exec Exec value
     * @return this
     */
    public DesktopEntryBuilder exec(String exec) {
        entries.add(new Entry(Key.EXEC, exec, ""));
        return this;
    }

    /**
     * If entry is of type Application, the working directory to run the program in.
     *
     * @param path Path value
     * @return this
     */
    public DesktopEntryBuilder path(String path) {
        entries.add(new Entry(Key.PATH, path, ""));
        return this;
    }

    /**
     * Whether the program runs in a terminal window.
     *
     * @param terminal Terminal value
     * @return this
     */
    public DesktopEntryBuilder terminal(boolean terminal) {
        entries.add(new Entry(Key.TERMINAL, Boolean.toString(terminal), ""));
        return this;
    }

    /**
     * Identifiers for application actions. This can be used to tell the application to make a specific action,
     * different from the default behavior. The
     * <a href="https://specifications.freedesktop.org/desktop-entry-spec/desktop-entry-spec-1.1.html#extra-actions">Application actions</a>
     * section describes how actions work.
     *
     * @param actions Application actions
     * @return this
     */
    public DesktopEntryBuilder actions(List<ApplicationAction> actions) {
        this.actions.addAll(actions);
        var value = actions.stream()
                .map(ApplicationAction::name)
                .collect(Collectors.joining(";"))
                + ";";
        entries.add(new Entry(Key.ACTIONS, value, ""));
        return this;
    }

    /**
     * The MIME type(s) supported by this application.
     *
     * @param mimeType MimeType value
     * @return this
     */
    public DesktopEntryBuilder mimeType(List<String> mimeType) {
        var value = String.join(";", mimeType) + ";";
        entries.add(new Entry(Key.MIME_TYPE, value, ""));
        return this;
    }

    /**
     * Categories in which the entry should be shown in a menu.
     *
     * @param categories Categories value
     * @return this
     */

    public DesktopEntryBuilder categories(List<Category> categories) {
        var value = categories.stream()
                .map(Category::toString)
                .collect(Collectors.joining(";"))
                + ";";
        entries.add(new Entry(Key.CATEGORIES, value, ""));
        return this;
    }

    /**
     * A list of strings which may be used in addition to other metadata to describe this entry. This can be useful
     * e.g. to facilitate searching through entries. The values are not meant for display, and should not be redundant
     * with the values of Name or GenericName.
     *
     * @param keywords Keywords value
     * @param locale   Locale string
     * @return this
     */
    public DesktopEntryBuilder keywords(List<String> keywords, String locale) {
        var value = String.join(";", keywords) + ";";
        entries.add(new Entry(Key.KEYWORDS, value, locale));
        return this;
    }

    /**
     * A list of strings which may be used in addition to other metadata to describe this entry. This can be useful
     * e.g. to facilitate searching through entries. The values are not meant for display, and should not be redundant
     * with the values of Name or GenericName.
     *
     * @param keywords Keywords value for default Locale
     * @return this
     */
    public DesktopEntryBuilder keywords(List<String> keywords) {
        return keywords(keywords, "");
    }

    /**
     * If true, it is KNOWN that the application will send a &quot;remove&quot; message when started with the
     * DESKTOP_STARTUP_ID environment variable set. If false, it is KNOWN that the application does not work with
     * startup notification at all (does not show any window, breaks even when using StartupWMClass, etc.). If
     * absent, a reasonable handling is up to implementations (assuming false, using StartupWMClass, etc.). (See the
     * <a href="https://www.freedesktop.org/wiki/Specifications/startup-notification-spec/">Startup Notification Protocol Specification</a>
     * for more details).
     *
     * @param startupNotify StartupNotify value
     * @return this
     */
    public DesktopEntryBuilder startupNotify(boolean startupNotify) {
        entries.add(new Entry(Key.STARTUP_NOTIFY, Boolean.toString(startupNotify), ""));
        return this;
    }

    /**
     * If specified, it is known that the application will map at least one window with the given string as its WM
     * class or WM name hint (see the
     * <a href="https://www.freedesktop.org/wiki/Specifications/startup-notification-spec/">Startup Notification Protocol Specification</a>
     * for more details).
     *
     * @param startupWmClass StartupWMClass value
     * @return this
     */
    public DesktopEntryBuilder startupWmClass(String startupWmClass) {
        entries.add(new Entry(Key.STARTUP_WM_CLASS, startupWmClass, ""));
        return this;
    }

    /**
     * If entry is {@link DesktopEntryType#LINK} type, the URL to access.
     *
     * @param url URL value
     * @return this
     */
    public DesktopEntryBuilder url(String url) {
        entries.add(new Entry(Key.URL, url, ""));
        return this;
    }

    /**
     * Builds {@link DesktopEntry} instance.
     *
     * @return {@link DesktopEntry} instance
     */
    public DesktopEntry build() {
        validate();
        return new DesktopEntry(type, entries, actions);
    }

    private void validate() {
        if (entries.isEmpty()) {
            throw new ValidationException("Mandatory entries are missing");
        }
    }
}
