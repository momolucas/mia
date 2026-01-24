package lucas.momo.fixedincome.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import lucas.momo.designsystem.theme.LocalDimens
import lucas.momo.fixedincome.data.model.FixedIncomeAsset
import lucas.momo.fixedincome.presentation.viewmodels.FixedIncomeViewModel

@Composable
fun FixedIncomeScreen(
    modifier: Modifier = Modifier,
    viewModel: FixedIncomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFixedIncomes()
    }

    when (val state = uiState.value) {
        is FixedIncomeViewModel.UiState.Loading -> {}

        is FixedIncomeViewModel.UiState.Success -> {
            FixedIncomeSuccess(modifier, state.fixedIncomes)
        }

        is FixedIncomeViewModel.UiState.Error -> {}
    }
}

@Composable
fun FixedIncomeSuccess(modifier: Modifier, assets: List<FixedIncomeAsset>) {
    Box(modifier = modifier) {
        LazyColumn {
            items(assets) { asset ->
                ItemRow(asset)
            }
        }
    }
}

@Composable
fun ItemRow(asset: FixedIncomeAsset) {
    val dimens = LocalDimens.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimens.mediumPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = dimens.mediumElevation)
    ) {
        Column(
            Modifier
                .wrapContentSize()
                .padding(dimens.largePadding)
        ) {
            Text(
                text = asset.name,
                modifier = Modifier.padding(dimens.smallPadding),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = asset.name,
                modifier = Modifier.padding(dimens.smallPadding),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = asset.brokerage,
                modifier = Modifier.padding(dimens.smallPadding),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
