package com.example.animedata.composables

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.animedata.models.Anime
import com.example.animedata.store.AnimeStore
import java.text.BreakIterator

@Composable
fun DeleteAnimeDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Delete Anime") },
            text = {
                   Text(text = "Do you really want to delete this Anime?")
            },
            confirmButton = {
                OutlinedButton(onClick = onSubmit) {
                    Text("YES")
                }

            },
            dismissButton = {
                OutlinedButton(onClick = onDismiss) {
                    Text("NO")
                }
            }
        )
    }
}

