package com.tayyib.myapplication.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object DataStoreManager {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

    fun provideDataStore(context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}