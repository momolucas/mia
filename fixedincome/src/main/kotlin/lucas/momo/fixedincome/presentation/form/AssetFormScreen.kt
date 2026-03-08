package lucas.momo.fixedincome.presentation.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import lucas.momo.designsystem.components.CurrencyTextField
import lucas.momo.designsystem.components.RequiredTextField
import lucas.momo.designsystem.theme.LocalDimens
import lucas.momo.fixedincome.R

@Composable
fun AssetFormScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetFormViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is AssetFormViewModel.UiState.Editing -> {
            AssetFormEditing(
                modifier = modifier,
                editingState = state,
                viewModel = viewModel
            )
        }

        is AssetFormViewModel.UiState.Loading -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(text = "Saving...")
            }
        }

        is AssetFormViewModel.UiState.Success -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Asset saved successfully!")
            }
        }

        is AssetFormViewModel.UiState.Error -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Error: ${state.message}")
                Button(onClick = { viewModel.onSave() }) {
                    Text(text = "Retry")
                }
            }
        }
    }
}

@Suppress("LongMethod")
@Composable
private fun AssetFormEditing(
    modifier: Modifier = Modifier,
    editingState: AssetFormViewModel.UiState.Editing,
    viewModel: AssetFormViewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(LocalDimens.current.mediumPadding)
            .verticalScroll(scrollState)
    ) {
        RequiredTextField(
            value = editingState.name.value,
            onValueChange = { viewModel.onNameChanged(it) },
            label = R.string.asset_form_label_name
        )

        RequiredTextField(
            value = editingState.acquisitionDate.value,
            onValueChange = { viewModel.onAcquisitionDateChanged(it) },
            label = R.string.asset_form_label_acquisition_date
        )

        CurrencyTextField(
            value = editingState.assetPriceAtPurchase.value,
            onValueChange = { viewModel.onAssetPriceAtPurchaseChanged(it) },
            label = R.string.asset_form_label_price_at_purchase
        )

        CurrencyTextField(
            value = editingState.brokerage.value,
            onValueChange = { viewModel.onBrokerageChanged(it) },
            label = R.string.asset_form_label_brokerage
        )

        RequiredTextField(
            value = editingState.indexer.value,
            onValueChange = { viewModel.onIndexerChanged(it) },
            label = R.string.asset_form_label_indexer
        )

        RequiredTextField(
            value = editingState.interestAndAmortization.value,
            onValueChange = { viewModel.onInterestAndAmortizationChanged(it) },
            label = R.string.asset_form_label_interest_and_amortization
        )

        CurrencyTextField(
            value = editingState.investedAmount.value,
            onValueChange = { viewModel.onInvestedAmountChanged(it) },
            label = R.string.asset_form_label_invested_amount
        )

        RequiredTextField(
            value = editingState.maturityDate.value,
            onValueChange = { viewModel.onMaturityDateChanged(it) },
            label = R.string.asset_form_label_maturity_date
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Status",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = editingState.status.value,
                onCheckedChange = { viewModel.onStatusChanged(it) }
            )
        }

        RequiredTextField(
            value = editingState.type.value,
            onValueChange = { viewModel.onTypeChanged(it) },
            label = R.string.asset_form_label_type
        )

        RequiredTextField(
            value = editingState.yield.value,
            onValueChange = { viewModel.onYieldChanged(it) },
            label = R.string.asset_form_label_yield
        )

        RequiredTextField(
            value = editingState.yieldType.value,
            onValueChange = { viewModel.onYieldTypeChanged(it) },
            label = R.string.asset_form_label_yield_type
        )

        Button(
            onClick = { viewModel.onSave() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.asset_form_save))
        }
    }
}
