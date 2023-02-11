package com.gamesidekicks.stardewapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application that sets up Timber in the DEBUG BuildConfig.
 * Read Timber's documentation for production setups.
 */
@HiltAndroidApp
class StardewApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ///if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}