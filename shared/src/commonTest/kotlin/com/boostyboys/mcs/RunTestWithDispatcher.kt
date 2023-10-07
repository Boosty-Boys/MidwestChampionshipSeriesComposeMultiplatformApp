package com.boostyboys.mcs

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
fun runTestWithDispatcher(
    dispatcher: TestDispatcher,
    testBody: suspend TestScope.() -> Unit,
) {
    try {
        Dispatchers.setMain(dispatcher)
        runTest(testBody = testBody)
    } finally {
        Dispatchers.resetMain()
    }
}
