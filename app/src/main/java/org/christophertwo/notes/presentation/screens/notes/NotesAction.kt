package org.christophertwo.notes.presentation.screens.notes

import org.christophertwo.notes.domain.models.Note

sealed interface NotesAction {
    object DismissSheet : NotesAction
    data class ShowSheet(val note: Note) : NotesAction
    data class DeleteNote(val id: Int) : NotesAction
}