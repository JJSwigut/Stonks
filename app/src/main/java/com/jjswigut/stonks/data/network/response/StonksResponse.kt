package com.jjswigut.stonks.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class StonksResponse(
    val stocks: List<StonkDTO>
)