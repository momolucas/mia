package lucas.momo.stocks

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import lucas.momo.stocks.data.network.TwelveDataApi
import lucas.momo.stocks.data.network.model.StockExchangeResponse
import lucas.momo.stocks.data.repositories.StockExchangeRepository
import lucas.momo.stocks.data.repositories.StockExchangeRepositoryImpl
import lucas.momo.stocks.mocks.STOCK_EXCHANGE_MOCK
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("detekt.All")
@OptIn(ExperimentalCoroutinesApi::class)
class StockExchangeRepositoryTest {

    private lateinit var twelveDataApi: TwelveDataApi
    private lateinit var stockExchangeRepository: StockExchangeRepository
    private lateinit var mockEngine: MockEngine

    @Before
    fun setUp() {
        mockEngine = MockEngine { request ->
            respond(
                content =
                """{"data": [{ "title": "Brazil Stock Exchange", "name": "Bovespa", "code": "BVMF", 
                    |"country": "Brazil", "timezone": "America/Paramaribo"}]}
                """.trimMargin(),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                )
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        twelveDataApi = TwelveDataApi()
        stockExchangeRepository = StockExchangeRepositoryImpl(twelveDataApi)
    }

    @Test
    fun `test getStockExchanges success`(): Unit = runBlocking {
        val stockExchanges = stockExchangeRepository.getStockExchanges()
        val expectedStockExchanges =
            StockExchangeResponse(stockExchanges = listOf(STOCK_EXCHANGE_MOCK))
        assertTrue(stockExchanges.isSuccess)
//        stockExchanges.onSuccess { stockExchangeResponse ->
//            assertEquals(expectedStockExchanges, stockExchangeResponse)
//        }
    }
}
