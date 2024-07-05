package com.example.note_app_compose.repository

import androidx.lifecycle.LiveData
import com.example.note_app_compose.data.Note
import com.example.note_app_compose.db.NoteDao

class NoteDataRepository(private val dao: NoteDao) {
    val getAllNotes : LiveData<List<Note>> =dao.getAllData()
    var singleData : LiveData<Note>? = null

    suspend fun addData(note: Note){
        dao.addNote(note)
    }

    suspend fun deleteNote(note: Note){
        dao.deleteNote(note)
    } suspend fun updateNote(note: Note){
        dao.updateNote(note)
    }
    fun getSingleData(int: Int){
        singleData = dao.getSingleNote(noteId = int)
    }
}