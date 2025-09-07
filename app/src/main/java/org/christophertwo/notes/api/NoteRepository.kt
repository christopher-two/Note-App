package org.christophertwo.notes.api

import kotlinx.coroutines.flow.Flow
import org.christophertwo.notes.data.entity.NoteEntity

interface NoteRepository {
    suspend fun getNotes(): Flow<List<NoteEntity>>
    suspend fun getSizeNotes(): Int
    suspend fun getNoteById(id: Int): NoteEntity?
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNote(id: Int)
}