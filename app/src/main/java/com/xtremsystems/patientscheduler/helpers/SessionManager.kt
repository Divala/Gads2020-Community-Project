package com.xtremsystems.patientscheduler.helpers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys.Companion.IS_LOGGED_IN
import com.xtremsystems.patientscheduler.presentation.ui.login.LoginActivity

class SessionManager(private val context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(AppPrefKeys.SESSION_PREF_NAME, 0)
    private val editor = prefs.edit()

    fun createLoginSession() {
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.commit()
    }

    fun checkLogin() {
        if (!this.isLoggedIn()) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()

        val i = Intent(context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(i)
    }

    private fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }

}
