package com.example.animedata.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import com.example.animedata.models.Anime

object AnimeData {
    val animeList = mutableStateListOf<Anime>()

    @RequiresApi(Build.VERSION_CODES.P)
    fun loadAnimeList(dbHelper: AnimesDatabaseHelper) {
        val animeFromDb = dbHelper.getAnimeList()
        animeList.clear()
        animeList.addAll(animeFromDb)
    }
}
