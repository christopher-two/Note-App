package org.christophertwo.notes.presentation.screens.notes

import org.christophertwo.notes.utils.RoutesDrawer

sealed interface NotesAction {
    object DismissSheet : NotesAction
    data class ShowSheet(val drawer: RoutesDrawer? = null) : NotesAction
    data class DeleteNote(val id: Int) : NotesAction
}