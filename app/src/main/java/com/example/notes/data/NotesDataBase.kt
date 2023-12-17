package com.example.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotesEntity::class], version = 2)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATA_BASE_NAME = "My data base"

        //to make one instance
        @Volatile
        private var instance: NotesDataBase? = null
        fun getInstance(context: Context): NotesDataBase {
            return instance ?: synchronized(this) { buildDataBase(context).also { instance = it } }
        }

        private fun buildDataBase(context: Context): NotesDataBase {
            return Room.databaseBuilder(context, NotesDataBase::class.java, DATA_BASE_NAME).build()
        }
    }
}