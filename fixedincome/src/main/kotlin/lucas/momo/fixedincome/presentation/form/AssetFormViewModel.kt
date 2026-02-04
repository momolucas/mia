package lucas.momo.fixedincome.presentation.form

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import lucas.momo.designsystem.utils.emptyString
import javax.inject.Inject

@HiltViewModel
class AssetFormViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Editing())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    sealed class UiState {
        data class Editing(
            var acquisitionDate: String = emptyString(),
            var priceAtPurchase: Double = 0.0,
            var brokerage: String = emptyString(),
            var indexer: String = emptyString(),
            var interestAndAmortization: String = emptyString(),
            var investedAmount: Double = 0.0,
            var maturityDate: String = emptyString(),
            var name: String = emptyString(),
            var status: Boolean = true,
            var type: String = emptyString(),
            var yield: String = emptyString(),
            var yieldType: String = emptyString()
        ) : UiState()

        data object Loading : UiState()
        data object Success : UiState()
        class Error(val message: String) : UiState()
    }
}

