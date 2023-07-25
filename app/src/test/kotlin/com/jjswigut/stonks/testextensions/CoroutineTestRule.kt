package com.jjswigut.stonks.testextensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A Junit rule that provides a [TestDispatcher] to support coroutine testing.
 *
 * @param dispatcher The [TestDispatcher] that will be used during tests.
 */
class CoroutineTestRule(
    val dispatcher: TestDispatcher = TestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher.dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
