package com.jjswigut.stonks.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}