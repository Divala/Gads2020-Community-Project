package com.xtremsystems.patientscheduler.helpers

import android.content.Context
import android.content.SharedPreferences
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys.Companion.PREF_NAME

class SharedPreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
    private val editor = prefs.edit()

    fun storePreference(key: String, value: String): Boolean {
        editor?.putString(key, value)
        editor?.apply()

        return prefs.contains(key)
    }

    fun storePreference(key: String, value: Int): Boolean {
        editor?.putInt(key, value)
        editor?.apply()

        return prefs.contains(key)
    }

    fun clearData() {
        editor.clear()
        editor.commit()
    }

    fun storePreference(key: String, value: Boolean): Boolean {
        editor?.putBoolean(key, value)
        editor?.apply()

        return prefs.contains(key)
    }

    fun retrievePreference(key: String, defaultValue: String): String? {
        return if (!prefs.contains(key)) null else prefs.getString(key, defaultValue)

    }

    fun retrievePreference(key: String, defaultValue: Int): Int? {
        return if (!prefs.contains(key)) null else prefs.getInt(key, defaultValue)
    }

    fun retrievePreference(key: String, defaultValue: Boolean): Boolean? {
        return if (!prefs.contains(key)) false else prefs.getBoolean(key, defaultValue)
    }
}