package com.kou.gitflyer.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PrefsStore @Inject constructor(appContext: Context) : PrefsStoreAbstraction {

    private val dataStore = appContext.dataStore


    override fun getId() = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.USER_ID] }

    override fun getToken() = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.TOKEN] }

    override suspend fun clearDataStore() {
        dataStore.edit {
            it.clear()
        }
    }



    override suspend fun setId(id: String) {
        dataStore.edit {
            it[PreferencesKeys.USER_ID] = id
        }
    }

    override suspend fun setFireToken(id: String) {
        dataStore.edit {
            it[PreferencesKeys.TOKEN] = id
        }
    }

    private object PreferencesKeys {
        val USER_ID = stringPreferencesKey("id")
        val TOKEN = stringPreferencesKey("token")

    }
}