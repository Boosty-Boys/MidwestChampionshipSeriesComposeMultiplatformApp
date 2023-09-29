package com.boostyboys.mcs.di

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
    factory { ScheduleScreenModel(get(), get()) }
    factory { StandingsScreenModel(get(), get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}

fun initKoin() = initKoin {}
