package com.jorfald.apitest

data class CryptoData(
    val data: List<Currency>
)

data class Currency (
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val changePercent24Hr: String?
)