package com.boostyboys.mcs.ui.teams

import app.cash.turbine.test
import com.boostyboys.mcs.data.impl.FakeLocalRepository
import com.boostyboys.mcs.data.impl.FakeMcsRepository
import com.boostyboys.mcs.runTestWithDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
}
