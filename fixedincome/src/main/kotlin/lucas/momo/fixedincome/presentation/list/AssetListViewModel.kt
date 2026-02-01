package lucas.momo.fixedincome.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import lucas.momo.fixedincome.data.model.AssetDocument
import lucas.momo.fixedincome.domain.usecase.ObserveAssetsUseCase

@HiltViewModel
class AssetListViewModel @Inject constructor(
    private val observeFixedIncomeAssetUseCase: ObserveAssetsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadFixedIncomes() {
        viewModelScope.launch {
            observeFixedIncomeAssetUseCase()
                .catch { e ->
                    Log.e("AssetListViewModel", "Error loading fixed incomes", e)
                    _uiState.value = UiState.Error(e.message ?: "Unknown error")
                }
                .collect { assets ->
                    _uiState.value = UiState.Success(assets)
                }
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val fixedIncomes: List<AssetDocument>) : UiState()
        data class Error(val message: String) : UiState()
    }
}
