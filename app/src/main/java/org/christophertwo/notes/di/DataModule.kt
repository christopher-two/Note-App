package org.christophertwo.notes.di

import org.christophertwo.notes.api.NoteRepository
import org.christophertwo.notes.data.dao.NoteDao
import org.christophertwo.notes.data.database.NoteDatabase
import org.christophertwo.notes.data.repository.NoteRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val DataModule: Module
    get() = module {
        single<NoteDatabase> {
            NoteDatabase.getDatabase(get())
        }

        single<NoteDao> { get<NoteDatabase>().noteDao }

        single {
            NoteRepositoryImpl(get())
        }.bind(NoteRepository::class)
    }