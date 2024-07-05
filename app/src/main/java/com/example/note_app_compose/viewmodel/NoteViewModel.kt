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
     var getSingleNote: LiveData<Note>? = null
    private val repository: NoteDataRepository

    init {
        val dataDao = NoteDataBase.getDataBase(application).noteDao()
        repository = NoteDataRepository(dataDao)
        getAllNotes = repository.getAllNotes
       // getSingleNote = repository.singleData!!
    }

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