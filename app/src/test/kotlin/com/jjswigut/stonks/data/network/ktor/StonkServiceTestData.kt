package com.jjswigut.stonks.data.network.ktor

object StonkServiceTestData {
    val successfulStonksJson = """
    {
      "stocks": [
        {
          "ticker": "^GSPC",
          "name": "S&P 500",
          "currency": "USD",
          "current_price_cents": 318157,
          "quantity": null,
          "current_price_timestamp": 1681845832
        }
      ]
    }
    """.trimIndent()

    val malformedStonksJson = """
    {
      "stocks": [
        {
          "ticker": "^GSPC",
          "name": "S&P 500",
          "currency": "USD",
          "current_price_cents": 318157,
          "quantity": null,
          "current_malformedmalformeed
        }
      ]
    }
    """.trimIndent()

    val emptyStonksJson = """
    {
      "stocks": []
    }
    """.trimIndent()

    const val stonksErrorUrl =
        "https://storage.googleapis.com/cash-homework/cash-stocks-api/error"
    const val stonksUrl =
        "https://storage.googleapis.com/cash-homework/cash-stocks-api/portfolio.json"
    const val stonksEmptyUrl =
        "https://storage.googleapis.com/cash-homework/cash-stocks-api/portfolio_empty.json"
    const val stonksMalformedUrl =
        "https://storage.googleapis.com/cash-homework/cash-stocks-api/portfolio_malformed.json"
}
