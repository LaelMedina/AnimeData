package com.example.animedata.composables

import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
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
import com.example.animedata.models.Anime
import com.example.animedata.store.AnimeStore
import java.util.Calendar
import kotlin.random.Random

@Composable
fun AnimeForm(onSubmit: () -> Unit, onCancel: () -> Unit) {

    /*
        Note: The id property of the Object is not properly initialized due to another Object might get the exact same number
        for its id leading to possible conflicts in the future, this must be fixed as soon as possible.
    */
    val id: Int = Random.nextInt(5, 100001)

    var name by remember { mutableStateOf("") }

    var chapters by remember { mutableIntStateOf(0) }

    var description by remember { mutableStateOf("") }

    var released by remember { mutableStateOf("") }

    var author by remember { mutableStateOf("") }

    val context = LocalContext.current


    /*
    * These values represent a field in the date format, if there is a better way to work with dates in kotlin then it should be done the better way instead of this
    * disaster I've made.
    * */
    val year: Int

    val month: Int

    val day: Int

    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)

    month = calendar.get(Calendar.MONTH)

    day = calendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            released = "$day/$month/$year"
        }, year, month, day
    )

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
            readOnly = true,
            label = { Text("Released (YYYY-MM-DD)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = "Pick a date",
            modifier = Modifier
                .size(40.dp)
                .padding(4.dp)
                .clickable {
                    mDatePickerDialog.show()
                }
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

                val newAnime = Anime(
                    id = id,
                    name = name,
                    chapters = chapters,
                    description = description,
                    released = released,
                    author = author
                )

                if (!AnimeStore.addAnime(newAnime)) {
                    Toast.makeText(
                        context,
                        "Make sure to enter all the Information, thank you",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(context, "Action completed Successfully!", Toast.LENGTH_SHORT)
                        .show()
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

