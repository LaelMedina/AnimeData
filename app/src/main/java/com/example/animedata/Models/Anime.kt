package com.example.animedata.Models

import java.util.Date

data class Anime(
    val name : String,
    val chapters : Int,
    val description: String,
    val released: Date,
    val author: String
)