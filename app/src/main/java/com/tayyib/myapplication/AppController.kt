package com.tayyib.myapplication

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppController:Application() {

    override fun onCreate() {
        super.onCreate()
    }


}