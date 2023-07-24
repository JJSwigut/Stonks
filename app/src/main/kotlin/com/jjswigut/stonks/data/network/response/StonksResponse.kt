package com.jjswigut.stonks.data.network.response

import kotlinx.serialization.Serializable

/**
 * A data class representing a response that includes a list of StonkDTOs.
 *
 * @param stocks The list of StonkDTOs included in the response.
 */
@Serializable
data class StonksResponse(
    val stocks: List<StonkDTO>
)
