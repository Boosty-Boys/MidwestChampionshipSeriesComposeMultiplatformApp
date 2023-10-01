package com.boostyboys.mcs.ui.schedule

import app.cash.turbine.test
import com.boostyboys.mcs.data.impl.FakeLocalRepository
import com.boostyboys.mcs.data.impl.FakeLocalRepository.Companion.FakeLocalRepositoryOptions
import com.boostyboys.mcs.data.impl.FakeMcsRepository
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.FakeMcsRepositoryOptions
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultLeaguesList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultSeasonsList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultWeeksList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.matchOne
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.premierLeague
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.seasonOne
import com.boostyboys.mcs.runTestWithDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ScheduleScreenModelTest {

    private lateinit var classUnderTest: ScheduleScreenModel
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var fakeMcsRepository: FakeMcsRepository

    private fun setupFakes(
        mcsRepositoryConfiguration: FakeMcsRepositoryOptions.() -> Unit = {},
        localRepositoryConfiguration: FakeLocalRepositoryOptions.() -> Unit = {},
    ) {
        fakeMcsRepository = FakeMcsRepository(mcsRepositoryConfiguration)

        classUnderTest = ScheduleScreenModel(
            localRepository = FakeLocalRepository(localRepositoryConfiguration),
            mcsRepository = fakeMcsRepository,
            dispatcher = dispatcher,
        )
    }

    @Test
    fun `Action_Initialize emits ViewState_Loading when data is loading`() = runTestWithDispatcher(dispatcher) {
        val expected = ScheduleViewState.Loading

        classUnderTest.viewState.test {
            classUnderTest.handleAction(ScheduleAction.Initialize)
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Action_Initialize emits ViewState_Content when data loads successfully`() = runTestWithDispatcher(dispatcher) {
        setupFakes()
        val expected = ScheduleViewState.Content(
            selectedSeason = seasonOne,
            selectedLeague = premierLeague,
            selectedWeek = 1,
            seasons = defaultSeasonsList,
            leagues = defaultLeaguesList,
            weeks = defaultWeeksList,
            matches = listOf(matchOne),
        )

        classUnderTest.viewState.test {
            classUnderTest.handleAction(ScheduleAction.Initialize)
            skipItems(1)
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}