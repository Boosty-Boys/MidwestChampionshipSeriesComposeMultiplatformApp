package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.models.season.Week

class FakeLocalRepository : LocalRepository {
    override var selectedSeasonId: String? = INITIAL_SELECTED_SEASON_ID
    override var selectedLeagueId: String? = INITIAL_SELECTED_LEAGUE_ID
    override var selectedWeek: Week? = initialSelectedWeek

    companion object {
        private const val INITIAL_SELECTED_SEASON_ID = "1"
        private const val INITIAL_SELECTED_LEAGUE_ID = "premier"
        private val initialSelectedWeek = Week(1)
    }
}
