package org.christophertwo.notes.di

import org.christophertwo.notes.presentation.screens.note.NoteViewModel
import org.christophertwo.notes.presentation.screens.notes.NotesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModelModules: Module
    get() = module {
        viewModelOf(::NotesViewModel)
        viewModelOf(::NoteViewModel)
    }