package com.example.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [NotesEntity::class], version = 2)
@TypeConverters(Converter::class)
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

        fun getInstanceWithoutContext(): NotesDataBase {
            return instance!!
        }

        //Migration in room to add new version
        private fun buildDataBase(context: Context): NotesDataBase {
            return Room.databaseBuilder(context, NotesDataBase::class.java, DATA_BASE_NAME)
                // .fallbackToDestructiveMigration().build()
                .addMigrations(MIGRATION1_2).build()
        }

        //Manual Migration
        private val MIGRATION1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE NOTE_TABLE ADD COLUMN reminderTime INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE NOTE_TABLE ADD COLUMN reminderDate INTEGER NOT NULL DEFAULT 0")
            }

        }
    }
}