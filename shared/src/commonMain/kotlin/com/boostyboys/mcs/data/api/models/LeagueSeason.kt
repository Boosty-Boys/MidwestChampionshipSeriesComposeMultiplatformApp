package com.boostyboys.mcs.data.api.models

data class LeagueSeason(
    val id: String,
    val name: String, // season number (i.e. "1")
    val league: League,
    val teams: List<Team>,
    val matches: List<Match>,
) {
    companion object {
        fun List<LeagueSeason>.toSeasons(): List<Season> {
            return groupBy { it.name }.map { (name, group) ->
                Season(
                    ids = group.map { it.id },
                    name = name,
                    leagueIds = group.map { it.league.id },
                )
            }.sortedBy { it.name }
        }
    }
}
