package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.LocalRepository

class FakeLocalRepository : LocalRepository {
    override var selectedSeasonNumber: String = initialSelectedSeasonNumber
    override var selectedLeagueId: String = initialSelectedLeagueId
    override var selectedWeek: Int = initialSelectedWeek

    companion object {
        const val initialSelectedSeasonNumber = "1"
        const val initialSelectedLeagueId = "premier"
        const val initialSelectedWeek = 1
    }
}
