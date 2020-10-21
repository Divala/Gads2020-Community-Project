package com.xtremsystems.patientscheduler.presentation.ui.main.settings.change_password

interface ChangePasswordView {
    fun getOldPassword(): String
    fun showOldPasswordError(s: String)
    fun getNewPassword(): String
    fun showNewPasswordError(s: String)
    fun getConfirmPassword(): String
    fun showConfirmPasswordError(s: String)
    fun getAccessToken(): String
    fun goToLoginActivity()
    fun showError(s: String)

}