package com.samkt.apiresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samkt.apiresult.ui.theme.ApiResultTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiResultTheme {
                val viewModel: MainViewModel = viewModel()
                val mainScreenState = viewModel.mainScreenState.collectAsState().value
                HomeScreenContent(mainScreenState = mainScreenState)
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    mainScreenState: MainScreenState,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (mainScreenState.loading) {
            CircularProgressIndicator()
        }
        if (!mainScreenState.loading && mainScreenState.quote != null) {
            Text(text = mainScreenState.quote.content)
        }
        mainScreenState.errorMessage?.let {
            Text(text = it)
        }
    }
}
