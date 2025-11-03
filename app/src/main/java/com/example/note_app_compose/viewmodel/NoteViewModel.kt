package com.example.note_app_compose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.db.NoteDataBase
import com.example.note_app_compose.repository.NoteDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteDataRepository
) : ViewModel() {
    val getAllNotes = repository.getAllNotes
    var getSingleNote: LiveData<Note>? = null

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun getSingleNote(id :Int){
        repository.getSingleData(id)
        getSingleNote = repository.singleData
    }
}