package com.boostyboys.mcs.ui.schedule

import app.cash.turbine.test
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
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
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.seasonTwo
import com.boostyboys.mcs.runTestWithDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ScheduleScreenModelTest {

    private lateinit var classUnderTest: ScheduleScreenModel
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var fakeLocalRepository: LocalRepository
    private lateinit var fakeMcsRepository: McsRepository

    private fun setupFakes(
        mcsRepositoryConfiguration: FakeMcsRepositoryOptions.() -> Unit = {},
        localRepositoryConfiguration: FakeLocalRepositoryOptions.() -> Unit = {},
    ) {
        fakeLocalRepository = FakeLocalRepository(localRepositoryConfiguration)
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

    @Test
    fun `Action_UpdateSelectedSeason updates season and triggers Action_Initialize`() = runTestWithDispatcher(dispatcher) {
        setupFakes()

        val expected = ScheduleViewState.Content(
            selectedSeason = seasonTwo,
            selectedLeague = premierLeague,
            selectedWeek = 1,
            seasons = defaultSeasonsList,
            leagues = defaultLeaguesList,
            weeks = defaultWeeksList,
            matches = listOf(matchOne),
        )

        // TODO figure out how to spy the ScreenModel so we can just verify that Action_Initialize was called
        classUnderTest.viewState.test {
            classUnderTest.handleAction(ScheduleAction.UpdateSelectedSeason(seasonTwo))
            skipItems(1)
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}
