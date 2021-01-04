package com.datastore.rd

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first


class DataStoreClass(private val context: Context) {

    private fun dataStore(): DataStore<Preferences> = context.createDataStore("settings")

    suspend  fun <T> save(key: Preferences.Key<T>, value: T) {
        dataStore().edit {
            it[key] = value
        }
    }

    suspend fun <T> read(key: Preferences.Key<T>, defaultValue: T): T {
        val pre = dataStore().data.first()
        return pre[key] ?: defaultValue
    }

}