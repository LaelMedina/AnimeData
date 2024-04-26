package com.example.animedata.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DeleteAnimeDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
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

