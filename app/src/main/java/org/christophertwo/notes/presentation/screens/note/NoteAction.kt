package org.christophertwo.notes.presentation.screens.note

sealed interface NoteAction {
    data class ChangeTitle(val title: String) : NoteAction
    data class ChangeContent(val content: String) : NoteAction
    data object SaveNote : NoteAction
}