package com.vmodev.baomoivmo.news.repository

import kotlinx.coroutines.CoroutineDispatcher

class AppDispatchers(val main: CoroutineDispatcher, val io:CoroutineDispatcher)