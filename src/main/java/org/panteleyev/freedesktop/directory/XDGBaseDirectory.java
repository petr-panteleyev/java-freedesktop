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
 * <a href="https://specifications.freedesktop.org/basedir-spec/basedir-spec-0.6.html/">XDG Base Directory Specification</a>.
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
