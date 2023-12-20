package com.example.notes.ui.notes

import androidx.lifecycle.*
import com.example.notes.data.NotesEntity
import com.example.notes.repo.NoteRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {
    val newNote = MutableLiveData<String>()
    private val repository = NoteRepository()
    private val _notes = MutableLiveData<List<NotesEntity>>()
    //instead of fun loadNotes
    var note: LiveData<List<NotesEntity>> = repository.getAllNotes().asLiveData()

//    init {
//        loadNotes()
//    }

    fun addNotes() {
        viewModelScope.launch {
            newNote.value?.let {
                repository.insertNewNote(NotesEntity(0, it, "jdh", false))
            }
        }
    }

//    private fun loadNotes() {
//        viewModelScope.launch {
//            repository.getAllNotes().collect() {
//
//                _notes.postValue(it)
//            }
//        }
//    }
}