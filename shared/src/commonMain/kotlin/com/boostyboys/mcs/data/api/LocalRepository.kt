package com.boostyboys.mcs.data.api

import com.boostyboys.mcs.data.api.models.season.Week

interface LocalRepository {
    var selectedLeagueId: String?
    var selectedSeasonId: String?
    var selectedWeek: Week?
}
