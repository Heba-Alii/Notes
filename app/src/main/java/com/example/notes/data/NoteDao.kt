package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NotesEntity)

    @Delete
    suspend fun deleteNote(note: NotesEntity)

    @Update
    suspend fun updateNote(note: NotesEntity)

    @Query("SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NotesEntity>>

}