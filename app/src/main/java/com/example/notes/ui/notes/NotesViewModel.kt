package com.example.notes.ui.notes

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.data.NotesEntity
import com.example.notes.repo.NoteRepository

class NotesViewModel : ViewModel() {
    val newNote = MutableLiveData<String>()
    val repo = NoteRepository()

    fun addNote() {
        newNote.value?.let {
            repo.insertNewNote(NotesEntity(0, it, "1/1", false))
            //to add empty text
            newNote.postValue("")
        }
    }
}