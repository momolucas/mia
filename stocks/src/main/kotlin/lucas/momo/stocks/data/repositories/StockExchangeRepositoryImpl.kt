package lucas.momo.stocks.data.repositories

import javax.inject.Inject
import lucas.momo.stocks.data.network.TwelveDataApi
import lucas.momo.stocks.data.network.model.StockExchangeResponse

class StockExchangeRepositoryImpl @Inject constructor(
    private val api: TwelveDataApi
) : StockExchangeRepository {

    override suspend fun getStockExchanges(): Result<StockExchangeResponse> {
        return try {
            val responseData = api.getStockExchanges()
            Result.success(responseData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
