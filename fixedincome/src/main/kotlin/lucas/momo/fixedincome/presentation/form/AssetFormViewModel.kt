package lucas.momo.fixedincome.presentation.form

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import lucas.momo.designsystem.utils.emptyString
import lucas.momo.fixedincome.data.model.AssetDocument
import lucas.momo.fixedincome.presentation.common.FormField

@Suppress("TooManyFunctions")
@HiltViewModel
class AssetFormViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Editing())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        updateState { it.copy(name = FormField(value = name, isValid = true)) }
    }

    fun onAcquisitionDateChanged(date: String) {
        updateState {
            it.copy(
                acquisitionDate = FormField(
                    value = date,
                    isValid = isValidDate(date)
                )
            )
        }
    }

    fun onAssetPriceAtPurchaseChanged(value: String) {
        updateState {
            it.copy(
                assetPriceAtPurchase = FormField(
                    value = value,
                    isValid = isValidBigDecimal(value)
                )
            )
        }
    }

    fun onBrokerageChanged(brokerage: String) {
        updateState { it.copy(brokerage = FormField(value = brokerage, isValid = true)) }
    }

    fun onIndexerChanged(indexer: String) {
        updateState { it.copy(indexer = FormField(value = indexer, isValid = true)) }
    }

    fun onInterestAndAmortizationChanged(value: String) {
        updateState {
            it.copy(
                interestAndAmortization = FormField(
                    value = value,
                    isValid = isValidBigDecimal(value)
                )
            )
        }
    }

    fun onInvestedAmountChanged(value: String) {
        updateState {
            it.copy(
                investedAmount = FormField(
                    value = value,
                    isValid = isValidBigDecimal(value)
                )
            )
        }
    }

    fun onMaturityDateChanged(date: String) {
        updateState { it.copy(maturityDate = FormField(value = date, isValid = isValidDate(date))) }
    }

    fun onStatusChanged(status: Boolean) {
        updateState { it.copy(status = FormField(value = status, isValid = true)) }
    }

    fun onTypeChanged(type: String) {
        updateState { it.copy(type = FormField(value = type, isValid = true)) }
    }

    fun onYieldChanged(yield: String) {
        updateState { it.copy(yield = FormField(value = yield, isValid = true)) }
    }

    fun onYieldTypeChanged(yieldType: String) {
        updateState { it.copy(yieldType = FormField(value = yieldType, isValid = true)) }
    }

    fun onSave() {
        val currentState = _uiState.value
        if (currentState is UiState.Editing) {
            if (isFormValid(currentState)) {
                handleSave(currentState)
            } else {
                // Perhaps set error or do nothing, since UI shows errors
            }
        }
    }

    private fun updateState(update: (UiState.Editing) -> UiState.Editing) {
        _uiState.update { current ->
            if (current is UiState.Editing) update(current) else current
        }
    }

    private fun handleSave(state: UiState.Editing) {
        AssetDocument(
            id = UUID.randomUUID().toString(),
            name = state.name.value,
            acquisitionDate = LocalDate.parse(
                state.acquisitionDate.value,
                DateTimeFormatter.ISO_LOCAL_DATE
            ).atStartOfDay().toInstant(java.time.ZoneOffset.UTC),
            assetPriceAtPurchase = state.assetPriceAtPurchase.value.toBigDecimal(),
            brokerage = state.brokerage.value,
            indexer = state.indexer.value,
            interestAndAmortization = state.interestAndAmortization.value.toBigDecimal(),
            investedAmount = state.investedAmount.value.toBigDecimal(),
            maturityDate = LocalDate.parse(
                state.maturityDate.value,
                DateTimeFormatter.ISO_LOCAL_DATE
            ).atStartOfDay().toInstant(java.time.ZoneOffset.UTC),
            status = state.status.value,
            type = state.type.value,
            yield = state.yield.value,
            yieldType = state.yieldType.value
        )
    }

    private fun isValidDate(dateString: String): Boolean {
        return try {
            DateTimeFormatter.ISO_LOCAL_DATE.parse(dateString)
            true
        } catch (_: Exception) {
            false
        }
    }

    private fun isValidBigDecimal(value: String): Boolean {
        return try {
            value.toBigDecimal()
            true
        } catch (_: Exception) {
            false
        }
    }

    private fun isFormValid(state: UiState.Editing): Boolean {
        return state.name.value.isNotBlank() &&
            isValidDate(state.acquisitionDate.value) &&
            isValidBigDecimal(state.assetPriceAtPurchase.value) &&
            state.brokerage.value.isNotBlank() &&
            state.indexer.value.isNotBlank() &&
            isValidBigDecimal(state.interestAndAmortization.value) &&
            isValidBigDecimal(state.investedAmount.value) &&
            isValidDate(state.maturityDate.value) &&
            state.type.value.isNotBlank() &&
            state.yield.value.isNotBlank() &&
            state.yieldType.value.isNotBlank()
    }

    sealed class UiState {
        data class Editing(
            var name: FormField<String> = FormField(emptyString()),
            var acquisitionDate: FormField<String> = FormField(emptyString()),
            var assetPriceAtPurchase: FormField<String> = FormField(emptyString()),
            var brokerage: FormField<String> = FormField(emptyString()),
            var indexer: FormField<String> = FormField(emptyString()),
            var interestAndAmortization: FormField<String> = FormField(emptyString()),
            var investedAmount: FormField<String> = FormField(emptyString()),
            var maturityDate: FormField<String> = FormField(emptyString()),
            var status: FormField<Boolean> = FormField(false),
            var type: FormField<String> = FormField(emptyString()),
            var yield: FormField<String> = FormField(emptyString()),
            var yieldType: FormField<String> = FormField(emptyString())
        ) : UiState()

        class Error(val message: String) : UiState()

        data object Loading : UiState()

        data object Success : UiState()
    }
}
