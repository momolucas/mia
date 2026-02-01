package lucas.momo.fixedincome.presentation.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
                onFormEvent = viewModel::onFormEvent
            )
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
private fun AssetFormEditing(
    modifier: Modifier = Modifier,
    editingState: AssetFormViewModel.UiState.Editing,
    onFormEvent: (AssetFormViewModel.FormEvent) -> Unit
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
            onValueChange = { onFormEvent(AssetFormViewModel.FormEvent.NameChanged(it)) },
            label = R.string.asset_form_label_name,
            isError = !editingState.name.isValid
        )
    }
}
