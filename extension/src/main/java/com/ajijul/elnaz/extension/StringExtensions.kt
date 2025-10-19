package com.ajijul.elnaz.extension

import java.util.Locale

val String.Companion.empty: String get() = ""

fun String.toPascalCase(): String {
    return this.split('_')
        .joinToString("") { word ->
            word.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        }
}