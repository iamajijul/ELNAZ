package com.ajijul.elnaz.features_manager

enum class SplitInstallSessionStatus {
    UNKNOWN,
    PENDING,
    REQUIRES_USER_CONFIRMATION,
    DOWNLOADING,
    DOWNLOADED,
    INSTALLING,
    INSTALLED,
    FAILED,
    CANCELED
}