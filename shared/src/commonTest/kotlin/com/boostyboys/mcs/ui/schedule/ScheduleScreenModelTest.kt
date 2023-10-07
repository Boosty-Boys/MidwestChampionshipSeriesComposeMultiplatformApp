package com.boostyboys.mcs.ui.schedule

import app.cash.turbine.test
import com.boostyboys.mcs.data.api.LocalRepository
import com.boostyboys.mcs.data.api.McsRepository
import com.boostyboys.mcs.data.impl.FakeLocalRepository
import com.boostyboys.mcs.data.impl.FakeLocalRepository.Companion.FakeLocalRepositoryOptions
import com.boostyboys.mcs.data.impl.FakeMcsRepository
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.FakeMcsRepositoryOptions
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.challengerLeague
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultLeaguesList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultSeasonsList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultWeeksList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.matchOne
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.premierLeague
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.seasonOne
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.seasonTwo
import com.boostyboys.mcs.runTestWithDispatcher
import com.boostyboys.mcs.state.HandleActionProxy.Companion.clearProxy
import com.boostyboys.mcs.state.HandleActionProxy.Companion.verifyActionHandled
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runCurrent
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

        classUnderTest.enableProxy()
    }

    @Test
    fun `Action_Initialize emits ViewState_Loading when data is loading`() = runTestWithDispatcher(dispatcher) {
        setupFakes()
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
        classUnderTest.updateState { copy(selectedSeason = seasonOne) }
        val expected = seasonTwo

        classUnderTest.handleAction(ScheduleAction.UpdateSelectedSeason(seasonTwo))
        runCurrent()

        assertEquals(expected, classUnderTest.state.value.selectedSeason)
        classUnderTest.verifyActionHandled(ScheduleAction.Initialize)
        classUnderTest.clearProxy()
    }

    @Test
    fun `Action_UpdateSelectedLeague updates league and triggers Action_Initialize`() = runTestWithDispatcher(dispatcher) {
        setupFakes()
        classUnderTest.updateState { copy(selectedLeague = premierLeague) }
        val expected = challengerLeague

        classUnderTest.handleAction(ScheduleAction.UpdateSelectedLeague(challengerLeague))
        runCurrent()

        assertEquals(expected, classUnderTest.state.value.selectedLeague)
        classUnderTest.verifyActionHandled(ScheduleAction.Initialize)
        classUnderTest.clearProxy()
    }

    @Test
    fun `Action_UpdateSelectedWeek updates week and triggers Action_Initialize`() = runTestWithDispatcher(dispatcher) {
        setupFakes()
        classUnderTest.updateState { copy(selectedWeek = 1) }
        val expected = 2

        classUnderTest.handleAction(ScheduleAction.UpdateSelectedWeek(2))
        runCurrent()

        assertEquals(expected, classUnderTest.state.value.selectedWeek)
        classUnderTest.verifyActionHandled(ScheduleAction.Initialize)
        classUnderTest.clearProxy()
    }

    @Test
    fun `Action_HandleMatchClicked emits Effect_NavigateToMatchDetails`() = runTestWithDispatcher(dispatcher) {
        setupFakes()
        val expected = ScheduleEffect.NavigateToMatchDetails(matchOne)

        classUnderTest.effect.test {
            classUnderTest.handleAction(ScheduleAction.HandleMatchClicked(matchOne))
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}
