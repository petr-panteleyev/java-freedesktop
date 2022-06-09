/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.freedesktop;

/**
 * Defines major environment variables.
 * {@see https://specifications.freedesktop.org/basedir-spec/basedir-spec-0.8.html}
 */
public enum EnvironmentVariable {
    /**
     * Defines the base directory relative to which user-specific data files should be stored. If
     * <code>$XDG_DATA_HOME</code> is either not set or empty, a default equal to <code>$HOME/.local/share</code>
     * should be used.
     */
    XDG_DATA_HOME,
    /**
     * Defines the base directory relative to which user-specific configuration files should be stored. If
     * <code>$XDG_CONFIG_HOME</code> is either not set or empty, a default equal to <code>$HOME/.config</code> should
     * be used.
     */
    XDG_CONFIG_HOME,
    /**
     * Defines the base directory relative to which user-specific state files should be stored. If
     * <code>$XDG_STATE_HOME</code> is either not set or empty, a default equal to <code>$HOME/.local/state</code>
     * should be used.
     */
    XDG_STATE_HOME,
    /**
     * Defines the preference-ordered set of base directories to search for data files in addition to the
     * <code>$XDG_DATA_HOME</code> base directory. The directories in $XDG_DATA_DIRS should be seperated with a colon
     * ':'. If <code>$XDG_DATA_DIRS</code> is either not set or empty, a value equal to
     * <code>/usr/local/share/:/usr/share/</code> should be used.
     */
    XDG_DATA_DIRS,
    /**
     * Defines the preference-ordered set of base directories to search for configuration files in addition to the
     * <code>$XDG_CONFIG_HOME</code> base directory. The directories in <code>$XDG_CONFIG_DIRS</code> should be
     * seperated with a colon ':'. If <code>$XDG_CONFIG_DIRS</code> is either not set or empty, a value equal to
     * <code>/etc/xdg</code> should be used.
     */
    XDG_CONFIG_DIRS,
    /**
     * Defines the base directory relative to which user-specific non-essential data files should be stored. If
     * <code>$XDG_CACHE_HOME</code> is either not set or empty, a default equal to <code>$HOME/.cache</code> should
     * be used.
     */
    XDG_CACHE_HOME,
    /**
     * Defines the base directory relative to which user-specific non-essential runtime files and other file objects
     * (such as sockets, named pipes, ...) should be stored.
     */
    XDG_RUNTIME_DIR
}
