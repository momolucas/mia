package lucas.momo.fixedincome.presentation.form

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import lucas.momo.designsystem.utils.emptyString
import lucas.momo.fixedincome.presentation.common.FormField

@HiltViewModel
class AssetFormViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Editing())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onFormEvent(event: FormEvent) {
        val currentUiState = _uiState.value
        if (currentUiState is UiState.Editing) {
            when (event) {
                is FormEvent.NameChanged -> {
                    _uiState.update {
                        currentUiState.copy(
                            name = FormField(value = event.name, isValid = event.name.isNotBlank())
                        )
                    }
                }

                is FormEvent.PriceAtPurchaseChanged -> {
                    _uiState.update {
                        currentUiState.copy(
                            priceAtPurchase = FormField(
                                value = event.value,
                                isValid = event.value >= 0.0
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiState {
        data class Editing(
            var name: FormField<String> = FormField(emptyString()),
            var priceAtPurchase: FormField<Double> = FormField(0.0)
        ) : UiState()

        class Error(val message: String) : UiState()

        data object Loading : UiState()

        data object Success : UiState()
    }

    sealed class FormEvent {
        class NameChanged(val name: String) : FormEvent()
        class PriceAtPurchaseChanged(val value: Double) : FormEvent()
    }
}
