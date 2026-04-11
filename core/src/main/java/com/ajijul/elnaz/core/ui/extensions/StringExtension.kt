package com.ajijul.elnaz.core.ui.extensions

import android.util.Patterns

fun String.isValidEmail(): Boolean = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()