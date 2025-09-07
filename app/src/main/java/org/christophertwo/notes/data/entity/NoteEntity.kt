package org.christophertwo.notes.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.christophertwo.notes.domain.models.Note
import java.time.LocalDate

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val timestamp: LocalDate,
) {
    fun toNote(): Note {
        return Note(
            id = id,
            title = title,
            content = content,
            date = timestamp
        )
    }
}