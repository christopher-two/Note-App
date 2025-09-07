package org.christophertwo.notes.data.repository

import kotlinx.coroutines.flow.Flow
import org.christophertwo.notes.api.NoteRepository
import org.christophertwo.notes.data.dao.NoteDao
import org.christophertwo.notes.data.entity.NoteEntity

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getNotes()
    }

    override suspend fun getSizeNotes(): Int {
        return noteDao.getSizeNotes()
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        return noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        return noteDao.deleteNote(note)
    }
}