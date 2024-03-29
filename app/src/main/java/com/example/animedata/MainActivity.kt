package com.example.animedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.animedata.Composables.ButtonAdd
import com.example.animedata.Composables.Content
import com.example.animedata.Composables.Toolbar
import com.example.animedata.ui.theme.AnimeDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewContainer()
                }
            }
        }
    }
}

@Composable
fun ViewContainer() {
    Scaffold(
        topBar = { Toolbar() },
        content = { Content() },
        floatingActionButton = { ButtonAdd() },
        floatingActionButtonPosition = FabPosition.End
    )
}

