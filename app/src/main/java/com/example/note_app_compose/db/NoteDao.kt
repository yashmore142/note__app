package com.example.note_app_compose.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.note_app_compose.data.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNote(data: Note)

    @Query("SELECT * FROM tbl_note ORDER BY id ASC")
    fun getAllData() : LiveData<List<Note>>

}