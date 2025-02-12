package lucas.momo.stocks.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import lucas.momo.designsystem.theme.LocalDimens
import lucas.momo.stocks.R
import lucas.momo.stocks.data.network.model.StockExchange
import lucas.momo.stocks.presentation.viewmodels.StockExchangeViewModel

@Composable
fun StockExchangesScreen(
    paddingValues: PaddingValues,
    onAppBarTitleChange: (String) -> Unit,
    viewModel: StockExchangeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val appBarTitle = stringResource(R.string.stocks_stocks_exchanges_title)

    LaunchedEffect(Unit) {
        onAppBarTitleChange(appBarTitle)
        viewModel.getStockExchanges()
    }

    Box(Modifier.padding(paddingValues)) {
        when (uiState) {
            is StockExchangeViewModel.UiState.Loading -> StockExchangesLoading()
            is StockExchangeViewModel.UiState.Success -> {
                StockExchanges((uiState as StockExchangeViewModel.UiState.Success).stockExchanges)
            }

            is StockExchangeViewModel.UiState.Error -> {
                StockExchangesError((uiState as StockExchangeViewModel.UiState.Error).message)
            }
        }
    }
}

@Composable
fun StockExchangesLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun StockExchanges(stockExchanges: List<StockExchange>) {
    LazyColumn {
        items(stockExchanges.size) { index ->
            ItemRow(stockExchanges[index])
        }
    }
}

@Composable
fun ItemRow(stockExchange: StockExchange) {
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
                text = stockExchange.title,
                modifier = Modifier.padding(dimens.smallPadding),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stockExchange.name,
                modifier = Modifier.padding(dimens.smallPadding),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stockExchange.country,
                modifier = Modifier.padding(dimens.smallPadding),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun StockExchangesError(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun StockExchangesLoadingPreview() {
    StockExchangesLoading()
}

@Preview(showBackground = true)
@Composable
fun ItemRowPreview() {
    ItemRow(
        StockExchange(
            title = "Brazil Stock Exchange",
            name = "B3",
            code = "BVMF",
            country = "Brazil",
            timezone = "America/Paramaribo"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun StockExchangesErrorPreview() {
    StockExchangesError("Something went wrong")
}
