package com.sijan.podcastsapp.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

// Define the preferences DataStore delegate
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "podcastsAppPreferences")
