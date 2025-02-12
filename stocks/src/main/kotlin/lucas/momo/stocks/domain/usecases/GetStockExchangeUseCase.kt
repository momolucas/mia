package lucas.momo.stocks.domain.usecases

import javax.inject.Inject
import lucas.momo.stocks.data.network.model.StockExchange
import lucas.momo.stocks.data.repositories.StockExchangeRepository

class GetStockExchangeUseCase @Inject constructor(private val repository: StockExchangeRepository) {

    suspend fun execute(): Result<List<StockExchange>> {
        return repository.getStockExchanges().fold(
            onSuccess = { Result.success(it.stockExchanges) },
            onFailure = { Result.failure(it) }
        )
    }
}
