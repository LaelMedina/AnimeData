package com.example.animedata.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import com.example.animedata.Validators.AnimeValidators
import com.example.animedata.models.Anime
import com.example.animedata.store.AnimeStore

@RequiresApi(Build.VERSION_CODES.P)
class AnimesDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Animes.db"
        private const val TABLE_NAME = "ANIMES"
        private const val SQL_SELECT_QUERY = "SELECT * FROM $TABLE_NAME"
    }

    private val SQL_CREATE_ANIMES_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NAME TEXT, " +
            "CHAPTERS INTEGER, " +
            "DESCRIPTION TEXT, " +
            "RELEASED TEXT, " +
            "AUTHOR TEXT)";

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ANIMES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun addAnime(newAnime: Anime): Boolean {

        if (AnimeValidators.textFieldsValidator(newAnime)) {
            return false
            //return false if the char sequence is empty
        } else {

            val db = writableDatabase

            val contentValues = ContentValues().apply {
                put("NAME", newAnime.name)
                put("CHAPTERS", newAnime.chapters)
                put("DESCRIPTION", newAnime.description)
                put("RELEASED", newAnime.released)
                put("AUTHOR", newAnime.author)
            }

            val id = db.insert(TABLE_NAME, null, contentValues)

            newAnime.id = id

        }

        AnimeData.AnimeList.value.add(newAnime)

        return true
    }

    fun getAnimeList(): MutableState<MutableList<Anime>> {

        val db = readableDatabase

        val listCursor = db.rawQuery(SQL_SELECT_QUERY, null)

        if (listCursor.moveToFirst()) {
            do {
                val animeId = listCursor.getString(listCursor.getColumnIndexOrThrow("ID"))

                val animeName = listCursor.getString(listCursor.getColumnIndexOrThrow("NAME"))

                val animeChapters = listCursor.getInt(listCursor.getColumnIndexOrThrow("CHAPTERS"))

                val animeDescription =
                    listCursor.getString(listCursor.getColumnIndexOrThrow("DESCRIPTION"))

                val animeReleased =
                    listCursor.getString(listCursor.getColumnIndexOrThrow("RELEASED"))

                val animeAuthor = listCursor.getString(listCursor.getColumnIndexOrThrow("AUTHOR"))

                val newAnime = Anime(
                    id = animeId.toLong(),
                    name = animeName,
                    chapters = animeChapters,
                    description = animeDescription,
                    released = animeReleased,
                    author = animeAuthor
                )

                AnimeData.AnimeList.value.add(newAnime)

            } while (listCursor.moveToNext())

        }

        listCursor.close()

        return AnimeData.AnimeList
    }

    fun editAnime(initialAnime: Anime, updatedAnime: Anime): Boolean {

        if (AnimeValidators.textFieldsValidator(updatedAnime)) {
            return false
        } else {

            val db = writableDatabase

            val contentValues = ContentValues().apply {
                put("NAME", updatedAnime.name)
                put("CHAPTERS", updatedAnime.chapters)
                put("DESCRIPTION", updatedAnime.description)
                put("RELEASED", updatedAnime.released)
                put("AUTHOR", updatedAnime.author)
            }

            val selection = "ID = ?"

            val selectionArgs = arrayOf(initialAnime.id.toString())

            db.update(TABLE_NAME, contentValues, selection, selectionArgs)

            AnimeStore.editAnime(initialAnime,updatedAnime)

            return true
        }
    }

    fun deleteAnime(animeId: Long): Boolean {

        val db = writableDatabase

        val selection = "ID = ?"

        val selectionArgs = arrayOf(animeId.toString())

        val deletedRows = db.delete(TABLE_NAME, selection, selectionArgs)

        AnimeStore.deleteAnime(animeId)

        return deletedRows > 0
    }

}