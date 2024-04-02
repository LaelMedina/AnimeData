package com.example.animedata.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ButtonAdd() {

    var showDialog by remember { mutableStateOf(false) }

    FloatingActionButton(onClick = {
        showDialog = true
    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add a new Anime to the list")
    }

    AnimeFormDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onSubmit = {}
    )

}

@Composable
fun AnimeFormDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Add a new Anime") },
            text = {
                AnimeForm(
                    onSubmit = onSubmit,
                    onCancel = onDismiss
                )
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}


