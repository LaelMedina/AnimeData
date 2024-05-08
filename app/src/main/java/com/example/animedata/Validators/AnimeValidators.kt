package com.example.animedata.Validators

import com.example.animedata.models.Anime

class AnimeValidators {

    //Return true if the char sequence is empty
    companion object {
        fun textFieldsValidator(
            newAnime: Anime
        ): Boolean {
            return (
                    newAnime.name.isEmpty() or
                            newAnime.chapters.toString().isEmpty() or
                            newAnime.description.isEmpty() or
                            newAnime.released.isEmpty() or
                            newAnime.author.isEmpty()
                    )
        }
    }
}