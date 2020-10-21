package com.xtremsystems.patientscheduler.presentation.ui.main.settings.change_password

import android.content.Context
import android.util.Log
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService

class ChangePasswordPresenter(
    val context: Context,
    val view: ChangePasswordView,
    val service: PatientSchedulerService
) {

    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun changePassword() {
        val oldPassword: String = view.getOldPassword()
        val newPassword: String = view.getNewPassword()
        val confirmPassword: String = view.getConfirmPassword()

        if (oldPassword.isEmpty()) {
            view.showOldPasswordError("Enter your old password")
            return
        }

        if (newPassword.isEmpty()) {
            view.showNewPasswordError("Enter a new password")
            return
        }

        if (newPassword.length < 6) {
            view.showNewPasswordError("Weak password, enter more than 6 characters")
            return
        }

        if (confirmPassword.isEmpty()) {
            view.showConfirmPasswordError("Confirm your password")
            return
        }

        if (newPassword != confirmPassword) {
            view.showConfirmPasswordError("Password not matching")
            return
        }

        service.changePassword(oldPassword, newPassword, getToken()).enqueue(ChangePasswordCallback(view, context))
    }
}