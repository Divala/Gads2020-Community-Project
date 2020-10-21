package com.xtremsystems.patientscheduler.presentation.ui.login

interface LoginView {
    fun getName(): String
    fun getPassword(): String
    fun showNameError(s: String)
    fun showPasswordError(s: String)
    fun goToMainActivity()
    fun showError(s: String)
}