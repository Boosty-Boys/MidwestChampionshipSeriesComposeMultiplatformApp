package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.models.season.Week

// TODO replace with real local preferences storage
class LocalRepositoryImpl : LocalRepository {
    override var selectedSeasonId: String? = null
    override var selectedLeagueId: String? = null
    override var selectedWeek: Week? = null
}
