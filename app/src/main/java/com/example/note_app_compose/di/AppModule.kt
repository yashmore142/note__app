package com.example.note_app_compose.di


import android.content.Context
import com.example.note_app_compose.db.NoteDataBase
import com.example.note_app_compose.repository.NoteDataRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDataBase {
        return NoteDataBase.getDataBase(context)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDataBase): NoteDataRepository {
        return NoteDataRepository(database.noteDao())
    }
}
