/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.directory;

import org.panteleyev.freedesktop.EnvironmentVariable;

import java.io.File;
import java.util.Optional;

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
    public static String getDataHome() {
        return getDirectoryLocation(EnvironmentVariable.XDG_DATA_HOME).orElseGet(() -> {
            var home = System.getProperty("user.home");
            return home + "/.local/share";
        });
    }

    /**
     * Returns {@link File} object corresponding to {@link #getDataHome()}.
     *
     * @return data files directory
     */
    public static File getDataHomeDirectory() {
        return new File(getDataHome());
    }

    /**
     * Returns the base directory relative to which user specific configuration files should be stored. If
     * <code>$XDG_CONFIG_HOME</code> is either not set or empty, a default equal to <code>$HOME/.config</code> should
     * be used.
     *
     * @return configuration files directory path
     */
    public static String getConfigHome() {
        return getDirectoryLocation(EnvironmentVariable.XDG_CONFIG_HOME).orElseGet(() -> {
            var home = System.getProperty("user.home");
            return home + "/.config";
        });
    }

    /**
     * Returns {@link File} object corresponding to {@link #getConfigHome()}.
     *
     * @return configuration files directory
     */
    public static File getConfigDirectory() {
        return new File(getConfigHome());
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
    public static String getDataDirs() {
        return getDirectoryLocation(EnvironmentVariable.XDG_DATA_DIRS).orElse("/usr/local/share/:/usr/share/");
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
    public static String getConfigDirs() {
        return getDirectoryLocation(EnvironmentVariable.XDG_CONFIG_DIRS).orElse(" /etc/xdg");
    }

    /**
     * Returns the base directory relative to which user specific non-essential data files should be stored. If
     * <code>$XDG_CACHE_HOME</code> is either not set or empty, a default equal to <code>$HOME/.cache</code> should
     * be used.
     *
     * @return cache files directory path
     */
    public static String getCacheHome() {
        return getDirectoryLocation(EnvironmentVariable.XDG_CACHE_HOME).orElseGet(() -> {
            var home = System.getProperty("user.home");
            return home + "/.cache";
        });
    }

    /**
     * Returns {@link File} object corresponding to {@link #getCacheHome()}.
     *
     * @return cache files directory
     */
    public static File getCacheDirectory() {
        return new File(getCacheHome());
    }

    /**
     * Returns system desktop entries directory.
     *
     * @return system desktop entries directory
     */
    public static File getSystemDesktopEntryDirectory() {
        return new File("/usr/share/applications");
    }

    /**
     * Returns user desktop entries directory.
     *
     * @return user desktop entries directory
     */
    public static File getUserDesktopEntryDirectory() {
        return new File(getDataHomeDirectory(), "applications");
    }
}
