package lucas.momo.fixedincome.presentation.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import lucas.momo.designsystem.theme.LocalDimens

@Composable
fun AssetFormScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetFormViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is AssetFormViewModel.UiState.Editing -> {
            AssetFormEditing(state)
        }

        is AssetFormViewModel.UiState.Loading -> {

        }

        is AssetFormViewModel.UiState.Success -> {

        }

        is AssetFormViewModel.UiState.Error -> {
        }
    }
}

@Composable
fun AssetFormEditing(assetEditing: AssetFormViewModel.UiState.Editing) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(LocalDimens.current.mediumPadding)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.mediumPadding)
    ) {
        // --- Campos de Texto ---
        OutlinedTextField(
            value = assetEditing.name,
            onValueChange = { },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = assetEditing.type,
                onValueChange = { /* implementar no VM */ },
                label = { Text("Tipo") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = assetEditing.brokerage,
                onValueChange = { },
                label = { Text("Corretora") },
                modifier = Modifier.weight(1f)
            )
        }
//
//        // --- Campos Numéricos ---
//        OutlinedTextField(
//            value = state.investedAmount,
//            onValueChange = { viewModel.onInvestedAmountChange(it) },
//            label = { Text("Valor Investido") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // --- Campos de Data (Helper Customizado) ---
//        ReadonlyDatePicker(
//            label = "Data de Aquisição",
//            date = state.acquisitionDate,
//            onDateSelected = { viewModel.onAcquisitionDateChange(it) }
//        )
//
//        // --- Switch de Status ---
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text("Status (Ativo)", style = MaterialTheme.typography.bodyLarge)
//            Switch(
//                checked = state.status,
//                onCheckedChange = { /* implementar updateStatus no VM */ }
//            )
//        }
//
//        // --- Botão Salvar ---
//        Button(
//            onClick = { viewModel.salvarNoFirestore() },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = !state.isLoading
//        ) {
//            if (state.isLoading) {
//                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
//            } else {
//                Text("Salvar Registro")
//            }
//        }
    }
}