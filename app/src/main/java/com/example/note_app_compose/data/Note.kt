package com.example.note_app_compose.data

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var color: Int = 0,
    var createdAt: Long,
    var pin: Boolean = false
)