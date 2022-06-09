/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.freedesktop.directory;

import org.panteleyev.freedesktop.EnvironmentVariable;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;

/**
 * Implements
 * <a href="https://specifications.freedesktop.org/basedir-spec/basedir-spec-0.8.html/">XDG Base Directory Specification</a>.
 */
public abstract class XDGBaseDirectory {
    private XDGBaseDirectory() {
    }

    private static Optional<String> getDirectoryLocation(EnvironmentVariable env) {
        var value = System.getenv(env.name());
        return value == null || value.isEmpty() ?
                Optional.empty()
                : Optional.of(value);
    }

    /**
     * Returns the base directory relative to which user specific data files should be stored. If
     * <code>$XDG_DATA_HOME</code> is either not set or empty, a default equal to <code>$HOME/.local/share</code>
     * should be used.
     *
     * @return data files directory path
     */
    public static Path getDataHome() {
        return getDirectoryLocation(EnvironmentVariable.XDG_DATA_HOME)
                .map(Path::of)
                .orElseGet(() -> Path.of(getProperty("user.home"), ".local", "share"));
    }

    /**
     * Returns the base directory relative to which user specific configuration files should be stored. If
     * <code>$XDG_CONFIG_HOME</code> is either not set or empty, a default equal to <code>$HOME/.config</code> should
     * be used.
     *
     * @return configuration files directory path
     */
    public static Path getConfigHome() {
        return getDirectoryLocation(EnvironmentVariable.XDG_CONFIG_HOME)
                .map(Path::of)
                .orElseGet(() -> Path.of(getProperty("user.home"), ".config"));
    }

    /**
     * Returns directory that contains state data that should persist between (application) restarts, but that is not
     * important or portable enough to the user that it should be stored in <code>$XDG_DATA_HOME</code>.
     *
     * @return state data directory path
     */
    public static Path getStateHome() {
        return getDirectoryLocation(EnvironmentVariable.XDG_STATE_HOME)
                .map(Path::of)
                .orElseGet(() -> Path.of(getProperty("user.home"), ".local", "state"));
    }

    /**
     * Returns the preference-ordered set of base directories to search for data files in addition to the
     * <code>$XDG_DATA_HOME</code> base directory. The directories in <code>$XDG_DATA_DIRS</code> should be seperated
     * with a colon ':'.
     * <p>
     * If <code>$XDG_DATA_DIRS</code> is either not set or empty, a value equal to
     * <code>/usr/local/share/:/usr/share/</code> should be used.
     *
     * @return additional data files locations
     */
    public static Set<Path> getDataDirs() {
        var env = getDirectoryLocation(EnvironmentVariable.XDG_DATA_DIRS).orElse("/usr/local/share/:/usr/share/");
        return Arrays.stream(env.split(":"))
                .map(Path::of)
                .collect(Collectors.toSet());
    }

    /**
     * Returns the preference-ordered set of base directories to search for configuration files in addition to the
     * <code>$XDG_CONFIG_HOME</code> base directory. The directories in <code>$XDG_CONFIG_DIRS</code> should be
     * seperated with a colon ':'.
     * <p>
     * If <code>$XDG_CONFIG_DIRS</code> is either not set or empty, a value equal to <code>/etc/xdg</code> should be
     * used.
     *
     * @return additional configuration files locations
     */
    public static Set<Path> getConfigDirs() {
        var env = getDirectoryLocation(EnvironmentVariable.XDG_CONFIG_DIRS).orElse("/etc/xdg");
        return Arrays.stream(env.split(":"))
                .map(Path::of)
                .collect(Collectors.toSet());
    }

    /**
     * Returns the base directory relative to which user specific non-essential data files should be stored. If
     * <code>$XDG_CACHE_HOME</code> is either not set or empty, a default equal to <code>$HOME/.cache</code> should
     * be used.
     *
     * @return cache files directory path
     */
    public static Path getCacheHome() {
        return getDirectoryLocation(EnvironmentVariable.XDG_CACHE_HOME)
                .map(Path::of)
                .orElseGet(() -> Path.of(getProperty("user.home"), ".cache"));
    }

    /**
     * Returns the base directory relative to which user-specific non-essential runtime files and other file objects
     * (such as sockets, named pipes, ...) should be stored.
     *
     * @return runtime directory path
     */
    public static Optional<Path> getRuntimeDir() {
        return getDirectoryLocation(EnvironmentVariable.XDG_RUNTIME_DIR)
                .map(Path::of);
    }

    /**
     * Returns system desktop entries directory.
     *
     * @return system desktop entries directory
     */
    public static Path getSystemDesktopEntryDirectory() {
        return Path.of("/usr/share/applications");
    }

    /**
     * Returns user desktop entries directory.
     *
     * @return user desktop entries directory
     */
    public static Path getUserDesktopEntryDirectory() {
        return Path.of(getDataHome().toString(), "applications");
    }
}
