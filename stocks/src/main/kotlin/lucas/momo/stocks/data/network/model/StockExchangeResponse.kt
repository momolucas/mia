package lucas.momo.stocks.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockExchangeResponse(@SerialName("data") val stockExchanges: List<StockExchange>)

@Serializable
data class StockExchange(
    val title: String,
    val name: String,
    val code: String,
    val country: String,
    val timezone: String
)
