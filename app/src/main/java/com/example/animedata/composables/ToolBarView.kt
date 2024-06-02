package com.example.animedata.composables

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.animedata.UserAuthActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {

    val context = LocalContext.current
    val navigate = Intent(context, UserAuthActivity::class.java)

    /*
        Description: Composable object that creates a UI system element that represents a NavigationBar in the application
     */
    TopAppBar(
        title = {
            Text(
                text = "Anime List",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Black
                )
            )
        },
        actions = {
            Button(
                onClick = {
                    ContextCompat.startActivity(context, navigate, null)
                }
            ) {
                Text(text = "Log Out")
            }
        },
        modifier = Modifier.fillMaxWidth(),
    )
}
