package lucas.momo.stocks.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import lucas.momo.stocks.presentation.ui.StockExchangesScreen

@Composable
fun StocksNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onAppBarTitleChange: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = StocksRoutes.STOCK_EXCHANGES_ROUTE) {
        composable(StocksRoutes.STOCK_EXCHANGES_ROUTE) {
            StockExchangesScreen(paddingValues, onAppBarTitleChange)
        }
    }
}
