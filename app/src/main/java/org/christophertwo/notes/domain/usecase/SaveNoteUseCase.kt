package org.christophertwo.notes.domain.usecase

import org.christophertwo.notes.api.NoteRepository
import org.christophertwo.notes.data.entity.NoteEntity
import java.time.LocalDate

class SaveNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(
        id: Int,
        title: String,
        content: String
    ){
        noteRepository.insertNote(
            NoteEntity(
                id = id,
                title = title,
                content = content,
                timestamp = LocalDate.now()
            )
        )
    }
}