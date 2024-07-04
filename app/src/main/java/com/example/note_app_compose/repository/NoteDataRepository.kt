package com.example.note_app_compose.repository

import androidx.lifecycle.LiveData
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.db.NoteDao

class NoteDataRepository(private val dao: NoteDao) {
    val getAllNotes : LiveData<List<Note>> =dao.getAllData()

    suspend fun addData(note: Note){
        dao.addNote(note)
    }
}