/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop;

/**
 * Defines major environment variables.
 * {@see https://specifications.freedesktop.org/basedir-spec/basedir-spec-0.6.html}
 */
public enum EnvironmentVariable {
    XDG_DATA_HOME,
    XDG_CONFIG_HOME,
    XDG_DATA_DIRS,
    XDG_CONFIG_DIRS,
    XDG_CACHE_HOME
}
