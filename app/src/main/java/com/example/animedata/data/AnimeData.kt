package com.example.animedata.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.animedata.models.Anime

object AnimeData {
     var AnimeList: MutableState<MutableList<Anime>> = mutableStateOf(
        mutableStateListOf<Anime>
            (
            Anime(0,"Attack on Titan", 94, "none", "7/3/2013", "Hajime Isayama"),
            Anime(1,"Fullmetal Alchemist Brotherhood", 64, "none", "5/3/2009", "Hiromu Arakawa"),
            Anime(2,"Vinland Saga", 48, "none", "7/6/2019", "Makoto Yukimura"),
            Anime(3,"Code Geass", 50, "none", "5/9/2006", "Ichirō Ōkouchi"),
            Anime(4,"Cowboy Bebop", 26, "none", "3/3/1998", "Cain Kuga")
        )
    )

    //val animeList: State<List<Anime>> = AnimeList
}
