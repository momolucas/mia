package lucas.momo.stocks.data.repositories

import lucas.momo.stocks.data.network.model.StockExchangeResponse

interface StockExchangeRepository {
    suspend fun getStockExchanges(): Result<StockExchangeResponse>
}
