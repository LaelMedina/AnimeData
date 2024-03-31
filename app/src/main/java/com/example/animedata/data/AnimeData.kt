package com.example.animedata.data

import com.example.animedata.models.Anime
import java.util.Date

object AnimeList {
    val animeList = listOf<Anime>(
        Anime("Attack on Titan", 94, "none", "7/3/2013", "Hajime Isayama"),
        Anime("Fullmetal Alchemist Brotherhood", 64, "none", "5/3/2009", "Hiromu Arakawa"),
        Anime("Vinland Saga", 48, "none", "7/6/2019", "Makoto Yukimura"),
        Anime("Code Geass", 50, "none", "5/9/2006", "Ichirō Ōkouchi"),
        Anime("Cowboy Bebop", 26, "none", "3/3/1998", "Cain Kuga")
    )
}