package com.jjswigut.stonks.data.network.ktor

import androidx.annotation.StringRes
import com.jjswigut.stonks.R

/**
 * A sealed class representing the errors that can occur when calling the StonkService.
 *
 * @param messageRes A string resource ID representing the error message.
 */
sealed class StonkServiceError(@StringRes open val messageRes: Int) {
    object MalformedJsonError : StonkServiceError(R.string.malformed_json_text)
    object GeneralError : StonkServiceError(R.string.generic_error_text)
}
