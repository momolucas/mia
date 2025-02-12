package lucas.momo.stocks.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import lucas.momo.designsystem.theme.MiaTheme
import lucas.momo.stocks.presentation.navigation.StocksNavGraph

@AndroidEntryPoint
class StocksActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge()
        setContent {
            MiaTheme {
                val navController = rememberNavController()
                var topBarTitle by remember { mutableStateOf("Stock Exchanges") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(title = { Text(topBarTitle) }) }
                ) { innerPadding ->

                    StocksNavGraph(
                        navController,
                        paddingValues = innerPadding,
                        onAppBarTitleChange = { newTitle -> topBarTitle = newTitle }
                    )
                }
            }
        }
    }
}
