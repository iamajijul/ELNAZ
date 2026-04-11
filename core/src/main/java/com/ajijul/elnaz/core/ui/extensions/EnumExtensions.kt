package com.ajijul.elnaz.core.ui.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ajijul.elnaz.domain.model.enums.BalanceType
import com.ajijul.elnaz.domain.model.enums.PaymentStatus
import com.ajijul.elnaz.resources.R

// --- 1. Text Mappers ---

fun PaymentStatus.toUiText(): UiText {
    return when (this) {
        PaymentStatus.UNPAID -> UiText.StringResource(R.string.payment_status_unpaid)
        PaymentStatus.PARTIAL -> UiText.StringResource(R.string.payment_status_partial)
        PaymentStatus.PAID -> UiText.StringResource(R.string.payment_status_paid)
    }
}

fun BalanceType.toUiText(): UiText {
    return when (this) {
        BalanceType.RECEIVABLE -> UiText.StringResource(R.string.balance_type_receivable)
        BalanceType.PAYABLE -> UiText.StringResource(R.string.balance_type_payable)
    }
}

// --- 2. Color Mappers (Must be @Composable to access MaterialTheme) ---

@Composable
fun PaymentStatus.getContainerColor(): Color {
    return when (this) {
        PaymentStatus.UNPAID -> MaterialTheme.colorScheme.errorContainer      // Light Red
        PaymentStatus.PARTIAL -> MaterialTheme.colorScheme.tertiaryContainer  // Light Yellow/Orange
        PaymentStatus.PAID -> Color(0xFFE8F5E9)                      // Light Green (Custom)
    }
}

@Composable
fun PaymentStatus.getContentColor(): Color {
    return when (this) {
        PaymentStatus.UNPAID -> MaterialTheme.colorScheme.onErrorContainer    // Dark Red
        PaymentStatus.PARTIAL -> MaterialTheme.colorScheme.onTertiaryContainer// Dark Yellow/Orange
        PaymentStatus.PAID -> Color(0xFF2E7D32)                               // Dark Green (Custom)
    }
}