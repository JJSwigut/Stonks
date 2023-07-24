package com.jjswigut.stonks.testextensions

import com.jjswigut.stonks.dispatchers.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

/**
 * A test-specific implementation of the Dispatcher interface.
 *
 * Overrides the main, IO, and default coroutine dispatchers to use a common TestDispatcher, which allows control over the execution of coroutines in tests.
 */
class TestDispatcher : Dispatcher {
    val testDispatcher = StandardTestDispatcher()

    override val main: CoroutineDispatcher
        get() = testDispatcher

    override val io: CoroutineDispatcher
        get() = testDispatcher

    override val default: CoroutineDispatcher
        get() = testDispatcher
}
