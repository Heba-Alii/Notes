package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: NotesEntity)

    @Delete
    suspend fun deleteNote(note: NotesEntity)

    @Update
    suspend fun updateNote(note: NotesEntity)

    @Query("SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    suspend fun getAllNotes(): List<NotesEntity>

}