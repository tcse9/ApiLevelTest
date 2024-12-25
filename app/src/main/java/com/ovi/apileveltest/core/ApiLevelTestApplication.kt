package com.ovi.apileveltest.core

import android.app.Application
import com.ovi.apileveltest.utils.runInDebug
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ApiLevelTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        runInDebug {
            Timber.plant(Timber.DebugTree())
        }
    }
}