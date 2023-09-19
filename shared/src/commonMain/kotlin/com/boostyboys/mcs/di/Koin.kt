package com.boostyboys.mcs.di

import com.boostyboys.mcs.data.CounterRepository
import com.boostyboys.mcs.data.CounterRepositoryImpl
import com.boostyboys.mcs.ui.SampleAppScreenModel
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
