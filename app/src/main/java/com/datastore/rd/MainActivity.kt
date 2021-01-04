package com.datastore.rd


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.datastore.preferences.core.preferencesKey
import kotlin.random.Random

class MainActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnt = findViewById<Button>(R.id.btnSave)
        val btnRead = findViewById<Button>(R.id.btnRead)
        val key = findViewById<EditText>(R.id.key)
        val etValue = findViewById<EditText>(R.id.etValue)
        val value = findViewById<TextView>(R.id.value)

        bnt.setOnClickListener {
            saveDesiredValue(preferencesKey(key.text.toString()),etValue.text.toString())
            saveDesiredValue(preferencesKey("test"), Random.nextInt(100))

        }

        btnRead.setOnClickListener {
            value.text = "${getSavedValue(key.text.toString(),"")} " +
                    "${getSavedValue("test",0)}"
        }
    }
}