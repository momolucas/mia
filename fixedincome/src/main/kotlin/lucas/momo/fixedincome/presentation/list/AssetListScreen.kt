package lucas.momo.fixedincome.presentation.list

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
import lucas.momo.fixedincome.data.model.AssetDocument

@Composable
fun AssetListScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFixedIncomes()
    }

    when (val state = uiState.value) {
        is AssetListViewModel.UiState.Loading -> {}

        is AssetListViewModel.UiState.Success -> {
            AssetListSuccess(modifier, state.fixedIncomes)
        }

        is AssetListViewModel.UiState.Error -> {}
    }
}

@Composable
fun AssetListSuccess(modifier: Modifier, assetDocuments: List<AssetDocument>) {
    Box(modifier = modifier) {
        LazyColumn {
            items(assetDocuments) { asset ->
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
