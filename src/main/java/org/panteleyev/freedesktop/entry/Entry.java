/*
 Copyright © 2022 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.freedesktop.entry;

record Entry(CharSequence key, CharSequence value) {
    String locale() {
        return value instanceof LocaleString localeString ? localeString.locale() : "";
    }
}
