package com.ajijul.elnaz.extension

val Any.TAG: String
    get() {
        return javaClass.simpleName
    }