package com.example.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NotesEntity
import com.example.notes.repo.NoteRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {
    val newNote = MutableLiveData<String>()
    private val _notes = MutableLiveData<List<NotesEntity>>()
    var note: LiveData<List<NotesEntity>> = _notes
    private val repository = NoteRepository()
init {
    loadNotes()
}

    fun addNotes() {
        viewModelScope.launch {
            newNote.value?.let {
                repository.insertNewNote(NotesEntity(0, it, "jdh", false))
            }
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _notes.postValue(repository.getAllNotes())
        }
    }
}