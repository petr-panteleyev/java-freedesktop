/*
 Copyright (c) Petr Panteleyev. All rights reserved.
 Licensed under the BSD license. See LICENSE file in the project root for full license information.
 */
package org.panteleyev.freedesktop.entry;

record Entry(CharSequence key, CharSequence value) {
    String locale() {
        return value instanceof LocaleString localeString ? localeString.locale() : "";
    }
}
