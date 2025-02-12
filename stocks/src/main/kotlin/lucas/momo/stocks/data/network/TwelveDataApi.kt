package lucas.momo.stocks.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import lucas.momo.stocks.data.network.model.StockExchangeResponse

class TwelveDataApi {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun getStockExchanges(): StockExchangeResponse {
        val url = "https://api.twelvedata.com/exchanges?type=stock"
        return client.get(url).body()
    }
}
