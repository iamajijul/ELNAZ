package com.ajijul.elnaz.domain.model.enums

enum class DiscountStatus {
    ACTIVE,     // currently usable
    UPCOMING,   // not yet started
    EXPIRED,    // end date passed
    DISABLED    // manually turned off
}