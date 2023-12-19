package com.example.notes.repo

import com.example.notes.data.NotesDataBase
import com.example.notes.data.NotesEntity

class NoteRepository {
    val dao = NotesDataBase.getInstanceWithoutContext().noteDao()

    suspend fun insertNewNote(note: NotesEntity) {
        dao.insertNote(note)
    }

    suspend fun getAllNotes(): List<NotesEntity> {
        return dao.getAllNotes()
    }
}