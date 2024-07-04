package com.example.note_app_compose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.db.NoteDataBase
import com.example.note_app_compose.repository.NoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

     val getAllNotes: LiveData<List<Note>>
    private val repository: NoteDataRepository

    init {
        val dataDao = NoteDataBase.getDataBase(application).noteDao()
        repository = NoteDataRepository(dataDao)
        getAllNotes = repository.getAllNotes
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(note)
        }
    }
}