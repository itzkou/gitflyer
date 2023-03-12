package com.kou.gitflyer.data.prefs

import kotlinx.coroutines.flow.Flow

interface PrefsStoreAbstraction {

    fun getId(): Flow<String?>
    fun getToken(): Flow<String?>
    suspend fun clearDataStore()


    suspend fun setId(id: String)
    suspend fun setFireToken(id: String)
}