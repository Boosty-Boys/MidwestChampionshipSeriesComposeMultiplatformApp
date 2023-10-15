package com.boostyboys.mcs.data.api

import com.boostyboys.mcs.data.api.models.Week

interface LocalRepository {
    var selectedSeasonNumber: String
    var selectedLeagueId: String
    var selectedWeek: Week
}
