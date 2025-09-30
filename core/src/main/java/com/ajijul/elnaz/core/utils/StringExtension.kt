package com.ajijul.elnaz.core.utils

import android.util.Patterns

fun String.isValidEmail(): Boolean = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()