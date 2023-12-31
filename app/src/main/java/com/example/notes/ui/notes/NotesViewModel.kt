package com.example.notes.ui.notes

import androidx.lifecycle.*
import com.example.notes.data.NotesEntity
import com.example.notes.repo.NoteRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.util.*

class NotesViewModel : ViewModel() {
    val newNote = MutableLiveData<String>()
    val searchText = MutableStateFlow<String>("")
    private val repository = NoteRepository()
    private val _notes = MutableLiveData<List<NotesEntity>>()

    //instead of fun loadNotes
    // var note: LiveData<List<NotesEntity>> = repository.getAllNotes().asLiveData()
    init {
        viewModelScope.launch {
            repository.getAllNotes().collect() {
                _notes.postValue(it)
            }
        }
        //debounce -> instead of search function to get all data user search for it from data base and take time to change this data
        // we use stateflow instead of live data to change data when user change his search
        viewModelScope.launch {
            searchText.debounce(1000).collect() {
                val result = repository.getFilteredNotes(it)
                _notes.postValue(result)
            }
        }
    }

    fun addNotes() {
        viewModelScope.launch {
            newNote.value?.let {
                repository.insertNewNote(NotesEntity(0, it, Date(), false))
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