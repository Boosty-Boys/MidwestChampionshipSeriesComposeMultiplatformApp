package app.com.mcs.di

import app.com.mcs.data.CounterRepository
import app.com.mcs.data.CounterRepositoryImpl
import app.com.mcs.ui.SampleAppScreenModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single<CounterRepository> { CounterRepositoryImpl() }
    factory { SampleAppScreenModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}

fun initKoin() = initKoin {}
