package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.models.Week

// TODO replace with real local preferences storage
class LocalRepositoryImpl : LocalRepository {
    override var selectedSeasonNumber: String = ""
    override var selectedLeagueId: String = ""
    override var selectedWeek: Week = Week(1)
}
