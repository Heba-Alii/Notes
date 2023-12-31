package com.example.notes.repo

import com.example.notes.data.NotesDataBase
import com.example.notes.data.NotesEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository {
    val dao = NotesDataBase.getInstanceWithoutContext().noteDao()

    suspend fun insertNewNote(note: NotesEntity) {
        dao.insertNote(note)
    }

    fun getAllNotes(): Flow<List<NotesEntity>> {
        return dao.getAllNotes()
    }

    //% to filter all search content the letter user will enter
    suspend fun getFilteredNotes(searchItems: String) = dao.filterNotes("%$searchItems%")
}