package org.christophertwo.notes

import android.app.Application
import org.christophertwo.notes.di.DataModule
import org.christophertwo.notes.di.UseCaseModule
import org.christophertwo.notes.di.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class Application : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            allowOverride(true)
            printLogger(Level.DEBUG)
            modules(
                ViewModelModules,
                DataModule,
                UseCaseModule
            )
        }
    }
}