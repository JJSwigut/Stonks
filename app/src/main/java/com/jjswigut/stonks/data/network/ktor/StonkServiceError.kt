package com.jjswigut.stonks.data.network.ktor

import com.jjswigut.stonks.R

sealed class StonkServiceError(open val messageRes: Int) {
    object MalformedJsonError : StonkServiceError(R.string.malformed_json_text)
    object GeneralError : StonkServiceError(R.string.generic_error_text)
}