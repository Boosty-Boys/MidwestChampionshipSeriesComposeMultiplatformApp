package com.boostyboys.mcs.di

import com.boostyboys.mcs.coroutineextensions.IoDispatcher
import com.boostyboys.mcs.coroutineextensions.dispatcherModule
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.impl.LocalRepositoryImpl
import com.boostyboys.mcs.data.impl.McsRepositoryImpl
import com.boostyboys.mcs.networking.api.McsManager
import com.boostyboys.mcs.networking.impl.McsManagerImpl
import com.boostyboys.mcs.ui.schedule.ScheduleScreenModel
import com.boostyboys.mcs.ui.teams.StandingsScreenModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single<LocalRepository> { LocalRepositoryImpl() }
    single<McsManager> { McsManagerImpl() }
    single<McsRepository> { McsRepositoryImpl(get()) }
    factory { ScheduleScreenModel(get(), get(), get(IoDispatcher)) }
    factory { StandingsScreenModel(get(), get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule, dispatcherModule)
}

fun initKoin() = initKoin {}
