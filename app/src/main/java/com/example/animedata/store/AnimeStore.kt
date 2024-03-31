package com.example.animedata.store

import com.example.animedata.models.Anime

class AnimeStore {

    companion object {
        fun CreateAnime(newAnime: Anime, animeList: List<Anime>) {
            animeList.plus(newAnime)
        }

    }

}