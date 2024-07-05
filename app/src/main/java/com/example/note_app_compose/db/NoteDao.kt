package com.example.note_app_compose.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note_app_compose.data.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNote(data: Note)

    @Query("SELECT * FROM tbl_note ORDER BY createdAt DESC")
    fun getAllData(): LiveData<List<Note>>

    @Delete
    suspend fun deleteNote(data: Note)
    @Update
    suspend fun updateNote(data: Note)

    @Query("SELECT * FROM tbl_note WHERE id = :noteId")
    fun getSingleNote(noteId: Int): LiveData<Note>
}