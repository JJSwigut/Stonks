package com.jjswigut.stonks.di

import com.jjswigut.stonks.data.network.ktor.StonkService
import com.jjswigut.stonks.data.network.ktor.StonkServiceClient
import com.jjswigut.stonks.data.network.ktor.StonkServiceImpl
import com.jjswigut.stonks.dispatchers.Dispatcher
import com.jjswigut.stonks.dispatchers.DispatcherImpl
import com.jjswigut.stonks.repository.StonkRepository
import io.ktor.client.engine.android.Android
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    return startKoin {
        modules(
            appModule,
            networkingModule,
            coreModule,
        )
    }
}

private val networkingModule = module {
    single<StonkService> {
        StonkServiceImpl(
            client = StonkServiceClient(Android.create()),
        )
    }

    single {
        StonkRepository(
            stonkService = get()
        )
    }
}

private val coreModule = module {
    single<Dispatcher> {
        DispatcherImpl()
    }
}