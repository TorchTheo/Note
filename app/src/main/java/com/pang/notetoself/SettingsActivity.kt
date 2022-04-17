package com.pang.notetoself

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import com.pang.notetoself.utils.Utils

class SettingsActivity : AppCompatActivity() {
    private var showDividers: Boolean = true
    private var caringMod: Boolean = false
    private var refresh: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = getSharedPreferences("Note to self", Context.MODE_PRIVATE)

        val edit = prefs.edit()

        showDividers = prefs.getBoolean("dividers", true)

        caringMod = prefs.getBoolean("caring_mod", false)

        refresh = prefs.getBoolean("refresh", false)


        if (caringMod)
            Utils.fontScale = Utils.CARING_MOD_SCALE
        else
            Utils.fontScale = Utils.NORMAL_MOD_SCALE

        Utils.setSize(findViewById<Switch>(R.id.switch1))
        findViewById<Switch>(R.id.switch1).isChecked = showDividers
//        Log.i("info", "textSize: ${findViewById<Switch>(R.id.switch1).textSize}")

        findViewById<Switch>(R.id.switch1).setOnCheckedChangeListener {
            buttonView, isChecked ->
                showDividers = isChecked
//                Toast.makeText(this, "Switch has clicked, showDividers is $showDividers", Toast.LENGTH_SHORT).show()
        }

        Utils.setSize(findViewById<Switch>(R.id.switch2))
        findViewById<Switch>(R.id.switch2).isChecked = caringMod
        findViewById<Switch>(R.id.switch2).setOnCheckedChangeListener {
            _, isChecked ->
                refresh = !refresh
                caringMod = isChecked
                if(isChecked)
                    Utils.fontScale = Utils.CARING_MOD_SCALE
                else
                    Utils.fontScale = Utils.NORMAL_MOD_SCALE
                edit.putBoolean("dividers", showDividers)
                edit.putBoolean("caring_mod", caringMod)
                edit.putBoolean("refresh", refresh)
                edit.apply()
                recreate()
        }
    }

    override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences("Note to self", Context.MODE_PRIVATE)
        val edit = prefs.edit()

        edit.putBoolean("dividers", showDividers)
        edit.putBoolean("caring_mod", caringMod)
        edit.putBoolean("refresh", refresh)
        edit.apply()
    }
}