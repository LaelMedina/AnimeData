package com.example.animedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.animedata.Models.Anime
import com.example.animedata.ui.theme.AnimeDataTheme
import java.util.Date

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
                    MyView()
                }
            }
        }
    }
}

private val animeList = listOf<Anime>(
    Anime("Attack on Titan", 94, "none", Date(2013, 4, 7), "Hajime Isayama"),
    Anime("Fullmetal Alchemist Brotherhood", 64, "none", Date(2009,4,5), "Hiromu Arakawa"),
    Anime("Vinland Saga", 48, "none", Date(2019,7,7),"Makoto Yukimura"),
    Anime("Code Geass", 50, "none",Date(2006, 10, 5), "Ichirō Ōkouchi"),
    Anime("Cowboy Bebop",26, "none", Date(1998, 4, 3), "Cain Kuga")
)

@Composable
fun MyView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Anime List",
            style = TextStyle(
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Black
            )
        )



    }
}
