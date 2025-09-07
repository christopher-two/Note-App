package org.christophertwo.notes.presentation.screens.notes

import androidx.compose.runtime.Immutable
import org.christophertwo.notes.domain.models.Note

@Immutable
data class NotesState(
    val titleAppBar: String = "Notes",
    val notes: List<Note> = emptyList(),
    val selectedNote: Note? = null,
)