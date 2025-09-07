package org.christophertwo.notes.presentation.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.christophertwo.notes.domain.usecase.DeleteNoteUseCase
import org.christophertwo.notes.domain.usecase.GetNotesUseCase

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = NotesState()
    )

    init {
        viewModelScope.launch {
            getNotesUseCase().collect { notesList ->
                _state.update { currentState ->
                    currentState.copy(notes = notesList.map { it.toNote() })
                }
            }
        }
    }

    fun onAction(action: NotesAction) {
        when (action) {
            NotesAction.DismissSheet -> _state.update {
                it.copy(
                    selectedNote = null
                )
            }

            is NotesAction.ShowSheet -> {
                _state.update { it.copy(selectedNote = action.note) }
            }

            is NotesAction.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase(action.id)
                }
            }
        }
    }
}
