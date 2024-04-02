package com.example.animedata.models

import java.util.Date

data class Anime(
    val id: Int,
    var name: String,
    var chapters: Int,
    var description: String,
    var released: String,
    var author: String
)