package org.faruk.spacex

import android.app.Application
import android.content.Context

class SpaceXApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: SpaceXApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}