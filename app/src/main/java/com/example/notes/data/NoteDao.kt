package com.example.notes.data

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Update
    fun updateNote(note: NotesEntity)

    @Query("SELECT * FROM NOTE_TABLE")
    fun getAllNotes(): List<NotesEntity>
}