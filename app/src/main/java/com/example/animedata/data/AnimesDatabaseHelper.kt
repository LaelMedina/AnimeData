package com.example.animedata.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.animedata.Validators.AnimeValidators
import com.example.animedata.models.Anime

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
            "AUTHOR TEXT)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ANIMES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addAnime(newAnime: Anime): Boolean {
        if (AnimeValidators.textFieldsValidator(newAnime)) {
            return false
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
            AnimeData.animeList.add(newAnime)
        }
        return true
    }

    fun getAnimeList(): MutableList<Anime> {
        val db = readableDatabase
        val listCursor = db.rawQuery(SQL_SELECT_QUERY, null)
        val animeList = mutableListOf<Anime>()

        if (listCursor.moveToFirst()) {
            do {
                val animeId = listCursor.getString(listCursor.getColumnIndexOrThrow("ID"))
                val animeName = listCursor.getString(listCursor.getColumnIndexOrThrow("NAME"))
                val animeChapters = listCursor.getInt(listCursor.getColumnIndexOrThrow("CHAPTERS"))
                val animeDescription = listCursor.getString(listCursor.getColumnIndexOrThrow("DESCRIPTION"))
                val animeReleased = listCursor.getString(listCursor.getColumnIndexOrThrow("RELEASED"))
                val animeAuthor = listCursor.getString(listCursor.getColumnIndexOrThrow("AUTHOR"))

                val newAnime = Anime(
                    id = animeId.toLong(),
                    name = animeName,
                    chapters = animeChapters,
                    description = animeDescription,
                    released = animeReleased,
                    author = animeAuthor
                )
                animeList.add(newAnime)
            } while (listCursor.moveToNext())
        }

        listCursor.close()
        AnimeData.animeList.clear()
        AnimeData.animeList.addAll(animeList)

        return animeList
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
            AnimeData.animeList.remove(initialAnime)
            AnimeData.animeList.add(updatedAnime)

            return true
        }
    }

    fun deleteAnime(animeId: Long): Boolean {
        val db = writableDatabase
        val selection = "ID = ?"
        val selectionArgs = arrayOf(animeId.toString())
        val deletedRows = db.delete(TABLE_NAME, selection, selectionArgs)

        val animeToDelete = AnimeData.animeList.find { it.id == animeId }
        if (animeToDelete != null) {
            AnimeData.animeList.remove(animeToDelete)
        }

        return deletedRows > 0
    }
}
