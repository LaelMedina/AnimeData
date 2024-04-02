package com.example.animedata.store

import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.animedata.data.AnimeData
import com.example.animedata.models.Anime

class AnimeStore {
    companion object {

        fun getAnimeList(): MutableState<MutableList<Anime>> {

            return AnimeData.AnimeList

        }

        fun addAnime(newAnime: Anime) {

            AnimeData.AnimeList.value.add(newAnime)

        }

        fun editAnime(){

        }

        fun deleteAnime(animeId: Int): Boolean {

            return AnimeData.AnimeList.value.removeIf { it.id == animeId }

        }

    }

}
