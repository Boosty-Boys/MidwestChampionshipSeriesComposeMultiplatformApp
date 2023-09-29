package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.LocalRepository

// TODO replace with real local preferences storage
class LocalRepositoryImpl : LocalRepository {
    override var selectedSeasonNumber: String = ""
    override var selectedLeagueId: String = ""
    override var selectedWeek: Int = 1
}
