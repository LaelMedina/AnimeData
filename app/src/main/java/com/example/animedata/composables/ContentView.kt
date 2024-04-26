package com.example.animedata.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animedata.models.Anime
import com.example.animedata.store.AnimeStore


@Composable
fun Content() {

    val animeList: List<Anime> by AnimeStore.getAnimeList()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(animeList) { item ->
            ListItemRow(item)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemRow(item: Anime) {

    val moreInformation = remember { mutableStateOf(false) }

    var isEditing by remember { mutableStateOf(false) }

    var isDeleting by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(100, 0, LinearEasing)
                    )
            ) {
                //Anime name
                Text(
                    text = item.name,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    ),
                )

                //Anime Author Name
                Text(
                    text = item.author,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    ),
                )

                if (moreInformation.value) {
                    //Anime Released Date
                    Text(
                        text = " Released: " + item.released,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        ),
                    )

                    //Anime Total Chapters
                    Text(
                        text = " Chapters: " + item.chapters,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        ),
                    )

                    //Anime Description
                    Text(
                        text = " Description: " + item.description,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        ),
                    )

                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                    ) {

                        IconButton(
                            onClick = {
                                isEditing = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Edit Anime"
                            )
                        }

                        if (isEditing) {
                            EditAnimeFormDialog(
                                isEditing,
                                onDismiss = { isEditing = false },
                                onSubmit = true,
                                item
                            )
                        }

                        IconButton(
                            onClick = {
                                isDeleting = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete Anime"
                            )
                        }

                        DeleteAnimeDialog(
                            showDialog = isDeleting,
                            onDismiss = { isDeleting = false },
                            onSubmit = {
                                AnimeStore.deleteAnime(item.id)
                                isDeleting = false
                            }
                        )


                    }
                }

            }

            IconButton(
                onClick = {
                    if (moreInformation.value) moreInformation.value = false
                    else if (!moreInformation.value) moreInformation.value = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Show more Information",
                    modifier = Modifier.rotate(
                        if (moreInformation.value) 180f else 360f
                    )
                )
            }
        }
    }
}
