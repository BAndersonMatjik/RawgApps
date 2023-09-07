package com.dev.rawgapps

import android.app.Application
import com.dev.rawgapps.common.isApplicationDebuggable
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //Setup Timber Just Running on Debug Mode
        if (isApplicationDebuggable()){
            Timber.plant(Timber.DebugTree())
        }
    }
}