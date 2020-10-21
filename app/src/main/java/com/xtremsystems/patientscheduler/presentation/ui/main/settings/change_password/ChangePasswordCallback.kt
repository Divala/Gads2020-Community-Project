package com.xtremsystems.patientscheduler.presentation.ui.main.settings.change_password

import android.content.Context
import android.util.Log
import com.xtremsystems.patientscheduler.data.auth.AuthResponse
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordCallback(var view: ChangePasswordView, var context: Context) : Callback<AuthResponse> {
    override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
        if (!hasInternetConnection(context)) {
            view.showError("There is no network connection...")
            return
        }
        view.showError("Unable to change your password at the moment, please try again later")

    }

    override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>?) {
        Log.d("ChangeError", response?.message())
        if (response?.isSuccessful!!) {
            view.goToLoginActivity()
            return
        }

        if (response.code() == 401) {
            view.showError("Incorrect old password")

            return
        }
        view.showError("Unable to change your password at the moment, please try again later")


    }

}
