package com.example.animedata.Data

import com.example.animedata.Models.Anime
import java.util.Date

object AnimeList{
    val animeList = listOf<Anime>(
        Anime("Attack on Titan", 94, "none", Date(2013, 3, 7), "Hajime Isayama"),
        Anime("Fullmetal Alchemist Brotherhood", 64, "none", Date(2009,3,5), "Hiromu Arakawa"),
        Anime("Vinland Saga", 48, "none", Date(2019,6,7),"Makoto Yukimura"),
        Anime("Code Geass", 50, "none", Date(2006, 9, 5), "Ichirō Ōkouchi"),
        Anime("Cowboy Bebop",26, "none", Date(1998, 3, 3), "Cain Kuga")
    )
}