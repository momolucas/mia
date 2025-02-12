package lucas.momo.stocks

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import lucas.momo.stocks.data.repositories.StockExchangeRepository
import lucas.momo.stocks.domain.usecases.GetStockExchangeUseCase
import lucas.momo.stocks.mocks.STOCK_EXCHANGE_MOCK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetStockExchangeUseCaseTest {

    private lateinit var repository: StockExchangeRepository
    private lateinit var getStockExchangeUseCase: GetStockExchangeUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getStockExchangeUseCase = GetStockExchangeUseCase(repository)
    }

    @Test
    fun `test getStockExchangesUseCase success`(): Unit = runBlocking {
        val expected = listOf(STOCK_EXCHANGE_MOCK)

        coEvery { getStockExchangeUseCase.execute() } returns Result.success(
            listOf(STOCK_EXCHANGE_MOCK)
        )

        val stockExchanges = getStockExchangeUseCase.execute()

        assertTrue(stockExchanges.isSuccess)

        stockExchanges.onSuccess { stockExchangeResponse ->
            assertEquals(expected, stockExchangeResponse)
        }
    }

    @Test
    fun `test getStockExchangesUseCase failure`(): Unit = runBlocking {
        val expected = Exception("Error")

        coEvery { repository.getStockExchanges() } returns
            Result.failure(Exception("Error"))

        val stockExchanges = getStockExchangeUseCase.execute()

        assertTrue(stockExchanges.isFailure)
        assertEquals(expected.message, stockExchanges.exceptionOrNull()?.message)
    }
}
