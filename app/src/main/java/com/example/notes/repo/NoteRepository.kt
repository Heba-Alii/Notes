package com.example.notes.repo

import android.provider.ContactsContract
import com.example.notes.data.NotesDataBase
import com.example.notes.data.NotesEntity

class NoteRepository {
    val dao = NotesDataBase.getInstanceWithoutContext().noteDao()
    fun insertNewNote(note: NotesEntity) {
        dao.insertNote(note)

    }
}