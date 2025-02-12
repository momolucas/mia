package lucas.momo.stocks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import lucas.momo.stocks.domain.usecases.GetStockExchangeUseCase
import lucas.momo.stocks.extensions.toMessageApiError
import lucas.momo.stocks.mocks.STOCK_EXCHANGE_MOCK
import lucas.momo.stocks.presentation.viewmodels.StockExchangeViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StockExchangeViewModelTest {
    private lateinit var getStockExchangeUseCase: GetStockExchangeUseCase
    private lateinit var stockExchangeViewModel: StockExchangeViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        getStockExchangeUseCase = mockk()
        stockExchangeViewModel = StockExchangeViewModel(getStockExchangeUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getStockExchangeUseCase in ViewModel success`(): Unit = runTest {
        coEvery { getStockExchangeUseCase.execute() } returns
            Result.success(listOf(STOCK_EXCHANGE_MOCK))

        lateinit var collectJob: Job
        collectJob = launch {
            stockExchangeViewModel.uiState.collect { uiState ->
                if (uiState is StockExchangeViewModel.UiState.Success) {
                    assertEquals(listOf(STOCK_EXCHANGE_MOCK), uiState.stockExchanges)
                    collectJob.cancel()
                }
            }
        }

        stockExchangeViewModel.getStockExchanges()
        advanceUntilIdle()
    }

    @Test
    fun `test getStockExchangeUseCase in ViewModel failure`(): Unit = runTest {
        val expected = Exception().toMessageApiError()

        coEvery { getStockExchangeUseCase.execute() } returns
            Result.failure(Exception())

        lateinit var collectJob: Job
        collectJob = launch {
            stockExchangeViewModel.uiState.collect { uiState ->
                if (uiState is StockExchangeViewModel.UiState.Error) {
                    assertEquals(expected, uiState.message)
                    collectJob.cancel()
                }
            }
        }

        stockExchangeViewModel.getStockExchanges()
        advanceUntilIdle()
    }
}
