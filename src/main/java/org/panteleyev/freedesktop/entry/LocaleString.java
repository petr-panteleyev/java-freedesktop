/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

import java.util.Objects;

/**
 * Implements string value with locale.
 *
 * @param value  string value
 * @param locale string locale, empty if default
 */
public record LocaleString(String value, String locale) {
    public LocaleString {
        value = Objects.requireNonNull(value).trim();
        locale = Objects.requireNonNull(locale).trim();
    }

    /**
     * Creates {@link LocaleString} instance with specified locale.
     *
     * @param value  string value
     * @param locale string locale
     * @return {@link LocaleString} instance
     */
    public static LocaleString localeString(String value, String locale) {
        return new LocaleString(value, locale);
    }

    /**
     * Creates {@link LocaleString} instance with default (empty) locale.
     *
     * @param value string value
     * @return {@link LocaleString} instance
     */
    public static LocaleString localeString(String value) {
        return new LocaleString(value, "");
    }
}
