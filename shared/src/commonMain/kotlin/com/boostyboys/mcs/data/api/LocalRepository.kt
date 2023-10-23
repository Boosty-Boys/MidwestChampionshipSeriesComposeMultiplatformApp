package com.boostyboys.mcs.data.api

import com.boostyboys.mcs.data.api.models.season.Week

interface LocalRepository {
    var selectedSeasonNumber: String?
    var selectedLeagueId: String?
    var selectedWeek: Week?
}
