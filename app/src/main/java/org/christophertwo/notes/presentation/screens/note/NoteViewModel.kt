package org.christophertwo.notes.presentation.screens.note

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.christophertwo.notes.domain.usecase.GetNoteUseCase
import org.christophertwo.notes.domain.usecase.SaveNoteUseCase
import kotlin.random.Random
import kotlin.random.nextInt

class NoteViewModel(
    private val getNoteUseCase: GetNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(NoteState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                val noteId: String? = savedStateHandle["id"]
                if (noteId != null && noteId.toInt() != -1) {
                    val note = getNoteUseCase(noteId.toInt())
                    if (note != null) {
                        Log.d("NoteViewModel", "Note loaded: $note")
                        _state.value = _state.value.copy(
                            id = note.id,
                            title = note.title,
                            content = note.content,
                            isNewNote = false
                        )
                    }
                } else {
                    _state.value = _state.value.copy(isNewNote = true)
                }
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = NoteState()
        )

    fun onAction(action: NoteAction) {
        when (action) {
            is NoteAction.ChangeTitle -> _state.value = _state.value.copy(title = action.title)

            is NoteAction.ChangeContent -> {
                val updatedState = _state.value.copy(content = action.content)
                _state.value = updatedState
                if (updatedState.save) {
                    performSave(updatedState)
                }
            }

            is NoteAction.SaveNote -> {
                _state.update { currentState ->
                    val newTitle = currentState.title.ifEmpty { "Untitled" }
                    currentState.copy(
                        title = newTitle,
                        save = true
                    )
                }
            }
        }
    }

    private fun performSave(currentState: NoteState) {
        viewModelScope.launch {
            try {
                if (currentState.isNewNote) {
                    Log.d(
                        "NoteViewModel",
                        "Saving new note with title: ${currentState.title}, content: ${currentState.content}"
                    )
                    saveNoteUseCase.invoke(
                        id = Random.nextInt(1..99999),
                        title = currentState.title,
                        content = currentState.content
                    )
                } else {
                    saveNoteUseCase.invoke(
                        id = currentState.id,
                        title = currentState.title,
                        content = currentState.content
                    )
                    Log.d(
                        "NoteViewModel",
                        "Saving existing note with id: ${currentState.id}, title: ${currentState.title}, content: ${currentState.content}"
                    )
                }
            } catch (e: Exception) {
                Log.e("NoteViewModel", "Error saving note", e)
            } finally {
                _state.update {
                    it.copy(save = false) // Reset save flag after save attempt
                }
            }
        }
    }
}
