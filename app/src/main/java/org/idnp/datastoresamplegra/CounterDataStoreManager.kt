package org.idnp.datastoresamplegra

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CounterDataStoreManager(private val context: Context) {

    suspend fun setPrefs(
        fontStyle: String,
        fontSize: String,
    ) {
        context.counterDataStore.edit { preferences ->
            preferences[STYLE_KEY] = fontStyle
            preferences[SIZE_KEY] = fontSize
        }
    }

    // A getter for the counter value flow
    val background: Flow<String>
        get() = context.counterDataStore.data.map { preferences ->
            preferences[STYLE_KEY] ?: "0"
        }
    val font: Flow<String>
        get() = context.counterDataStore.data.map { preferences ->
            preferences[SIZE_KEY] ?: "10"
        }

    companion object {
        private const val DATASTORE_NAME = "counter_preferences"

        private val STYLE_KEY = stringPreferencesKey("mode_key");
        private val SIZE_KEY = stringPreferencesKey("size_key");

        private val Context.counterDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}
