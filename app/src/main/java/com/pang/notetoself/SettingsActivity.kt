package com.pang.notetoself

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {
    private var showDividers: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = getSharedPreferences("Note to self", Context.MODE_PRIVATE)

        showDividers = prefs.getBoolean("dividers", true)

        findViewById<Switch>(R.id.switch1).isChecked = showDividers

        findViewById<Switch>(R.id.switch1).setOnCheckedChangeListener {
            buttonView, isChecked ->
                showDividers = isChecked
//                Toast.makeText(this, "Switch has clicked, showDividers is $showDividers", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences("Note to self", Context.MODE_PRIVATE)
        val edit = prefs.edit()

        edit.putBoolean("dividers", showDividers)
        edit.apply()
    }
}