package com.jjswigut.stonks

import android.app.Application
import com.jjswigut.stonks.di.initKoin
import com.jjswigut.stonks.feature.stonklist.StonkListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * The main Application class of the app.
 *
 * Initializes Koin for dependency injection on creation.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            module {
                viewModel { StonkListViewModel(get(), get()) }
            }
        )
    }
}
