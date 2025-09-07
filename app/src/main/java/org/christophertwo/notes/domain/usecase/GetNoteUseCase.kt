package org.christophertwo.notes.domain.usecase

import org.christophertwo.notes.api.NoteRepository
import org.christophertwo.notes.domain.models.Note

class GetNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNoteById(id)?.toNote()
    }
}