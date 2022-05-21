package com.lab02.laboratorio4

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CounterDataStoreManager(private val context: Context) {

   suspend fun changeCounter(counterValue: Int) {
        context.counterDataStore.edit { preferences ->
            val currentCounterValue = preferences[COUNTER_KEY] ?: 0
            preferences[COUNTER_KEY] = counterValue
        }
    }

    suspend fun setCounter(counterValue: Int) {
        context.counterDataStore.edit { preferences ->
            preferences[COUNTER_KEY] = counterValue
        }
    }
    suspend fun setColor(counterValue: Int) {
        context.counterDataStore.edit { preferences ->
            preferences[COLOR_KEY] = counterValue
        }
    }
    suspend fun setStyle(counterValue: Int) {
        context.counterDataStore.edit { preferences ->
            preferences[STYLE_KEY] = counterValue
        }
    }

    // A getter for the counter value flow
    val counter: Flow<Int>
        get() = context.counterDataStore.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }
    val color: Flow<Int>
        get() = context.counterDataStore.data.map { preferences ->
            preferences[COLOR_KEY] ?: 0
        }
    val style: Flow<Int>
        get() = context.counterDataStore.data.map { preferences ->
            preferences[STYLE_KEY] ?: 0
        }

    companion object {
        private const val DATASTORE_NAME = "counter_preferences"

        private val COUNTER_KEY = intPreferencesKey("counter_key");
        private val COLOR_KEY = intPreferencesKey("color_key");
        private val STYLE_KEY = intPreferencesKey("style_key");
        private val Context.counterDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}