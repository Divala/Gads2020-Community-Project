package com.xtremsystems.patientscheduler.presentation.ui.login

import android.content.Context
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService

class LoginPresenter(var context: Context, var view: LoginView, var service: PatientSchedulerService) {
    fun login() {
        val name: String = view.getName()
        val password: String = view.getPassword()

        if (name.isEmpty()) {
            view.showNameError("Please enter your name")
            return
        }
        if (password.isEmpty()) {
            view.showPasswordError("Please enter your password")
            return
        }
        service.attemptLogin(name, password).enqueue(LoginCallback(context, view))
    }
}