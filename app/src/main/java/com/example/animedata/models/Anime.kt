package com.example.animedata.models

import java.util.Date

data class Anime(
    val id: Int,
    val name: String,
    val chapters: Int,
    val description: String,
    val released: String,
    val author: String
)