package com.boostyboys.mcs.ui.teams

import app.cash.turbine.test
import com.boostyboys.mcs.data.impl.FakeLocalRepository
import com.boostyboys.mcs.data.impl.FakeMcsRepository
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.challengerLeague
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultError
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultLeaguesList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultSeasonsList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.defaultTeamsList
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.premierLeague
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.seasonOne
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.seasonTwo
import com.boostyboys.mcs.data.impl.FakeMcsRepository.Companion.teamOne
import com.boostyboys.mcs.runTestWithDispatcher
import com.boostyboys.mcs.state.HandleActionProxy.Companion.clearProxy
import com.boostyboys.mcs.state.HandleActionProxy.Companion.verifyActionHandled
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class StandingsScreenModelTest {

    private lateinit var classUnderTest: StandingsScreenModel
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var fakeLocalRepository: FakeLocalRepository
    private lateinit var fakeMcsRepository: FakeMcsRepository

    @BeforeTest
    fun setup() {
        fakeLocalRepository = FakeLocalRepository()
        fakeMcsRepository = FakeMcsRepository()

        classUnderTest = StandingsScreenModel(
            localRepository = fakeLocalRepository,
            mcsRepository = fakeMcsRepository,
            dispatcher = dispatcher,
        )

        classUnderTest.enableProxy()
    }

    @Test
    fun `Action_Initialize emits ViewState_Loading when data is loading`() = runTestWithDispatcher(dispatcher) {
        val expected = StandingsViewState.Loading

        classUnderTest.viewState.test {
            classUnderTest.handleAction(StandingsAction.Initialize)
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Action_Initialize emits ViewState_Content when data loads successfully`() = runTestWithDispatcher(dispatcher) {
        val expected = StandingsViewState.Content(
            selectedSeason = seasonOne,
            selectedLeague = premierLeague,
            seasons = defaultSeasonsList,
            leagues = defaultLeaguesList,
            teams = defaultTeamsList,
        )

        classUnderTest.viewState.test {
            classUnderTest.handleAction(StandingsAction.Initialize)
            skipItems(1)
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Action_Initialize emits ViewState_Error when data fails to load`() = runTestWithDispatcher(dispatcher) {
        val expected = StandingsViewState.Error("Error loading data")
        fakeMcsRepository.getSeasonsResponse[0] = defaultError

        classUnderTest.viewState.test {
            classUnderTest.handleAction(StandingsAction.Initialize)
            skipItems(1)
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Action_UpdateSelectedSeason updates season and triggers Action_Initialize`() = runTestWithDispatcher(dispatcher) {
        classUnderTest.updateState { copy(selectedSeason = seasonOne) }
        val expected = seasonTwo

        classUnderTest.handleAction(StandingsAction.UpdateSelectedSeason(seasonTwo))
        runCurrent()

        assertEquals(expected, classUnderTest.state.value.selectedSeason)
        classUnderTest.verifyActionHandled(StandingsAction.Initialize)
        classUnderTest.clearProxy()
    }

    @Test
    fun `Action_UpdateSelectedLeague updates league and triggers Action_Initialize`() = runTestWithDispatcher(dispatcher) {
        classUnderTest.updateState { copy(selectedLeague = premierLeague) }
        val expected = challengerLeague

        classUnderTest.handleAction(StandingsAction.UpdateSelectedLeague(challengerLeague))
        runCurrent()

        assertEquals(expected, classUnderTest.state.value.selectedLeague)
        classUnderTest.verifyActionHandled(StandingsAction.Initialize)
        classUnderTest.clearProxy()
    }

    @Test
    fun `Action_HandleTeamClicked emits Effect_NavigateToMatchDetails`() = runTestWithDispatcher(dispatcher) {
        val expected = StandingsEffect.NavigateToTeamDetails(teamOne)

        classUnderTest.effect.test {
            classUnderTest.handleAction(StandingsAction.HandleTeamClicked(teamOne))
            assertEquals(expected, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}
