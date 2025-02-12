package lucas.momo.stocks.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lucas.momo.stocks.data.network.model.StockExchange
import lucas.momo.stocks.domain.usecases.GetStockExchangeUseCase
import lucas.momo.stocks.extensions.toMessageApiError

@HiltViewModel
class StockExchangeViewModel @Inject constructor(
    private val getStockExchangeUseCase: GetStockExchangeUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getStockExchanges() {
        viewModelScope.launch {
            getStockExchangeUseCase.execute().fold(
                onSuccess = { stockExchanges ->
                    _uiState.value = UiState.Success(stockExchanges)
                },
                onFailure = { exception ->
                    _uiState.value = UiState.Error(exception.toMessageApiError())
                }
            )
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val stockExchanges: List<StockExchange>) : UiState()
        data class Error(val message: String) : UiState()
    }
}
