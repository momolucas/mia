package lucas.momo.fixedincome.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import lucas.momo.designsystem.components.ResumeListItem
import lucas.momo.designsystem.components.ResumeListItemData
import lucas.momo.designsystem.utils.toBrazilianDate
import lucas.momo.designsystem.utils.toBrazilianReal
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
                ResumeListItem(
                    ResumeListItemData(
                        title = asset.name,
                        titleEnd = asset.investedAmount.toBrazilianReal(),
                        firstLineLabel = asset.maturityDate.toBrazilianDate(),
                        firstLineLabelEnd = asset.yield,
                        secondLineLabel = "↑ ${asset.interestAndAmortization.toBrazilianReal()}",
                        secondLineLabelEnd = "↩ ${asset.interestAndAmortization.toBrazilianReal()}"
                    )
                )
            }
        }
    }
}
