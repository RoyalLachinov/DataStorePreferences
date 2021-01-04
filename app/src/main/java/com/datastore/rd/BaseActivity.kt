package com.datastore.rd

import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.*

open class BaseActivity : AppCompatActivity() {

    inline fun <reified T> getSavedValue(key: String, defaultValue:T): Any {
         var savedValue = Any()
         runBlocking {
             val operation = async {
                 val savedStr = DataStoreClass(this@BaseActivity).read(preferencesKey(key),
                     defaultValue!!).let {
                     it
                 }
                 savedValue = savedStr
             }
             operation.await()
         }
         return savedValue
     }

    fun <T> saveDesiredValue(key: Preferences.Key<T>, value: T) {
        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                DataStoreClass(this@BaseActivity).save(key, value)
            }.join()
        }
    }
}