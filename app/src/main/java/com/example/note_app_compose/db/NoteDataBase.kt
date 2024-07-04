package com.example.note_app_compose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note_app_compose.data.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDataBase? = null

        fun getDataBase(context : Context):NoteDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "tbl_note"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}