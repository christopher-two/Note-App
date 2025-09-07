package org.christophertwo.notes.presentation.screens.note

import androidx.compose.runtime.Immutable

@Immutable
data class NoteState(
    val id: Int = -1,
    val title: String = "",
    val content: String = "",
    val isNewNote: Boolean = false,
    val save: Boolean = false
)