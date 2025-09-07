package org.christophertwo.notes.di

import org.christophertwo.notes.domain.usecase.DeleteNoteUseCase
import org.christophertwo.notes.domain.usecase.GetNoteUseCase
import org.christophertwo.notes.domain.usecase.GetNotesUseCase
import org.christophertwo.notes.domain.usecase.SaveNoteUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val UseCaseModule: Module
    get() = module {
        factoryOf(::GetNotesUseCase)
        factoryOf(::DeleteNoteUseCase)
        factoryOf(::GetNoteUseCase)
        factoryOf(::SaveNoteUseCase)
    }