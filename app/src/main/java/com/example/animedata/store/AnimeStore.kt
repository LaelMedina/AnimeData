package com.example.animedata.store

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.animedata.data.AnimeData
import com.example.animedata.models.Anime

class AnimeStore {
    companion object {

        fun getAnimeList(): State<List<Anime>> {
            return AnimeData.animeList
        }

        fun addAnime(newAnime: Anime) {
            AnimeData.AnimeList.value.add(newAnime)
        }
    }

}
