package com.example.animedata.store

import androidx.compose.runtime.MutableState
import com.example.animedata.Validators.AnimeValidators
import com.example.animedata.data.AnimeData
import com.example.animedata.models.Anime

class AnimeStore {
    companion object {

        fun getAnimeList(): MutableState<MutableList<Anime>> {

            return AnimeData.AnimeList

        }

        fun addAnime(newAnime: Anime): Boolean {

            if (AnimeValidators.textFieldsValidator(newAnime)) {
                return false
            }

            return AnimeData.AnimeList.value.add(newAnime)

        }

        fun editAnime(initialAnime: Anime, updatedAnime: Anime): Boolean {

            return if (AnimeValidators.textFieldsValidator(updatedAnime)) {
                false
            } else {
                initialAnime.name = updatedAnime.name

                initialAnime.chapters = updatedAnime.chapters

                initialAnime.description = updatedAnime.description

                initialAnime.released = updatedAnime.released

                initialAnime.author = updatedAnime.author

                true
            }
        }

        fun deleteAnime(animeId: Int): Boolean {

            return AnimeData.AnimeList.value.removeIf { it.id == animeId }

        }

    }

}
