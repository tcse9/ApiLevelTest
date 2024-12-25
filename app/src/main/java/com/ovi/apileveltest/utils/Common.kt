package com.ovi.apileveltest.utils

import com.ovi.apileveltest.BuildConfig

fun runInDebug(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}