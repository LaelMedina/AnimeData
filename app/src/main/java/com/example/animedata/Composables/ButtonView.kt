package com.example.animedata.Composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animedata.Models.Anime


@Composable
fun ButtonAdd() {

    var showDialog by remember { mutableStateOf(false) }

    FloatingActionButton(onClick = {
        showDialog = true
    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add a new Anime to the list")
    }


//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalArrangement = Arrangement.Start
//    ){
//        IconButton(
//            onClick = { showDialog = true }
//        ) {
//            Icon(
//                imageVector = Icons.Filled.Add,
//                contentDescription = "Create a new Anime Object"
//            )
//        }
//    }

    AnimeFormDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onSubmit = {
            // Handle submitted Anime object
        }
    )

}

@Composable
fun AnimeFormDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (Anime) -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Create a new Anime Object") },
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
