package com.ajijul.elnaz.core.ui.extensions

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ajijul.elnaz.domain.model.enums.AppError
import com.ajijul.elnaz.resources.R

sealed interface UiText {

    data class DynamicString(val value: String) : UiText

    class StringResource(@param:StringRes val id: Int, vararg val args: Any) : UiText

    @Composable
    fun asString(): String = when (this) {
        is DynamicString -> value
        is StringResource -> stringResource(id, args)
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}

fun AppError.asUiText(): UiText {

    return when (this) {

        AppError.Auth.InvalidEmail -> UiText.StringResource(R.string.err_invalid_email)
        AppError.Auth.InvalidPassword -> UiText.StringResource(R.string.err_invalid_password)
        AppError.Auth.InvalidCredentials -> UiText.StringResource(R.string.err_invalid_credentials)
        AppError.Auth.UserNotFound -> UiText.StringResource(R.string.err_user_not_found)
        AppError.Auth.UserAlreadyExists -> UiText.StringResource(R.string.err_user_exists)
        AppError.Auth.Unauthorized -> UiText.StringResource(R.string.err_unauthorized)


        AppError.Network.NoInternet -> UiText.StringResource(R.string.err_no_internet)
        AppError.Network.Timeout -> UiText.StringResource(R.string.err_timeout)
        AppError.Network.ServerError -> UiText.StringResource(R.string.err_server_error)
        AppError.Network.Serialization -> UiText.StringResource(R.string.err_serialization)


        AppError.Storage.ItemNotFound -> UiText.StringResource(R.string.err_item_not_found)
        AppError.Storage.ItemAlreadyExists -> UiText.StringResource(R.string.err_item_exists)
        AppError.Storage.OperationFailed -> UiText.StringResource(R.string.err_operation_failed)
        AppError.Storage.DiskFull -> UiText.StringResource(R.string.err_disk_full)

        AppError.Validation.FieldEmpty -> UiText.StringResource(R.string.err_field_empty)
        AppError.Validation.InvalidFormat -> UiText.StringResource(R.string.err_invalid_format)
        is AppError.Validation.MinLength -> {
            UiText.StringResource(R.string.err_min_length, requiredLength)
        }
        is AppError.Validation.PatternMismatch -> {
            UiText.StringResource(R.string.err_pattern_mismatch, description)
        }
        is AppError.Validation.Custom -> {
            UiText.DynamicString(message)
        }

        is AppError.Unknown -> {
            UiText.StringResource(R.string.err_unknown)
        }
    }

}