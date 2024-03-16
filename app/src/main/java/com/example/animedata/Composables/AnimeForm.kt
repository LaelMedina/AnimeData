package com.example.animedata.Composables

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.animedata.Data.AnimeList
import com.example.animedata.Models.Anime
import com.example.animedata.Store.AnimeStore
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun AnimeForm(onSubmit: (Anime) -> Unit, onCancel: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var chapters by remember { mutableIntStateOf(0) }
    var description by remember { mutableStateOf("") }
    var released by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Anime Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = chapters.toString(),
            onValueChange = { chapters = it.toIntOrNull() ?: 0 },
            label = { Text("Anime Chapters") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Anime Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = released,
            onValueChange = { released = it },
            label = { Text("Released (YYYY-MM-DD)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Anime Author") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = {
                try {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                    val parsedReleased = dateFormat.parse(released)

                    val newAnime = Anime(name = name, chapters = chapters, description = description, released = parsedReleased ,author = author, )

                    AnimeStore.CreateAnime(newAnime, AnimeList.animeList)
                }catch (ex: Exception){
                    Toast.makeText(context,ex.message, Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}