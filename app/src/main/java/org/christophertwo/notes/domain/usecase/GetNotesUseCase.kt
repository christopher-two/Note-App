package org.christophertwo.notes.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.christophertwo.notes.api.NoteRepository
import org.christophertwo.notes.data.entity.NoteEntity

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(): Flow<List<NoteEntity>> {
        return noteRepository.getNotes()
    }
}