package lucas.momo.mia

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import lucas.momo.designsystem.theme.LocalDimens
import lucas.momo.designsystem.theme.MiaTheme
import lucas.momo.stocks.presentation.ui.StocksActivity

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        enableEdgeToEdge()
        setContent {
            MiaTheme {
                val context = LocalContext.current
                val dimens = LocalDimens.current
                Scaffold(
                    Modifier.fillMaxSize(),
                    topBar = {},
                    content = { paddingValues ->
                        Box(
                            Modifier
                                .padding(paddingValues)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                modifier = Modifier
                                    .height(dimens.buttonHeight)
                                    .wrapContentSize(),
                                colors = ButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    disabledContainerColor =
                                    MaterialTheme.colorScheme.primaryContainer,
                                    disabledContentColor =
                                    MaterialTheme.colorScheme.onPrimaryContainer
                                ),
                                onClick = {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            StocksActivity::class.java
                                        )
                                    )
                                }
                            ) {
                                Text(text = "Stocks USA")
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiaTheme {
        Greeting("Android")
    }
}
