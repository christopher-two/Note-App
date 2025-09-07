package org.christophertwo.notes.domain.models

import org.christophertwo.notes.data.entity.NoteEntity
import java.time.LocalDate

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDate
) {
    fun toNoteEntity(): NoteEntity {
        return NoteEntity(
            id = id,
            title = title,
            content = content,
            timestamp = date
        )
    }
}