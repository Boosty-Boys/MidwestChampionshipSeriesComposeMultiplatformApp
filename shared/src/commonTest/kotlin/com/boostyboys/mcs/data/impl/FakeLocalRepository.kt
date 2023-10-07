package com.boostyboys.mcs.data.impl

import com.boostyboys.mcs.Fake
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.impl.FakeLocalRepository.Companion.FakeLocalRepositoryOptions

class FakeLocalRepository(
    configuration: FakeLocalRepositoryOptions.() -> Unit = {},
) : LocalRepository, Fake<FakeLocalRepositoryOptions> {
    override var options: FakeLocalRepositoryOptions = FakeLocalRepositoryOptions().apply(configuration)

    override var selectedSeasonNumber: String = options.selectedSeasonNumber
    override var selectedLeagueId: String = options.selectedLeagueId
    override var selectedWeek: Int = options.selectedWeek

    companion object {
        const val initialSelectedSeasonNumber = "1"
        const val initialSelectedLeagueId = "premier"
        const val initialSelectedWeek = 1

        data class FakeLocalRepositoryOptions(
            var selectedSeasonNumber: String = initialSelectedSeasonNumber,
            var selectedLeagueId: String = initialSelectedLeagueId,
            var selectedWeek: Int = initialSelectedWeek,
        )
    }
}
