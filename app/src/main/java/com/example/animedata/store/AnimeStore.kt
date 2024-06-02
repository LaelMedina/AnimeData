package com.example.animedata.store

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.animedata.Validators.AnimeValidators
import com.example.animedata.data.AnimeData
import com.example.animedata.models.Anime

class AnimeStore {
    companion object {

        fun getAnimeList(): SnapshotStateList<Anime> {

            return AnimeData.animeList

        }

        fun addAnime(newAnime: Anime): Boolean {

            if (AnimeValidators.textFieldsValidator(newAnime)) {
                return false
            }

            return AnimeData.animeList.add(newAnime)

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

        fun deleteAnime(animeId: Long): Boolean {

            return AnimeData.animeList.removeIf { it.id == animeId }

        }

    }

}
