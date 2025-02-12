package lucas.momo.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import lucas.momo.designsystem.theme.MiaTheme
import lucas.momo.stocks.presentation.ui.StockExchangesScreen

@AndroidEntryPoint
class StocksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StockExchangesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
