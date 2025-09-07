package org.christophertwo.notes.domain.usecase

import org.christophertwo.notes.api.NoteRepository
import org.christophertwo.notes.domain.models.Note

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Int) {
        noteRepository.deleteNote(id)
    }
}