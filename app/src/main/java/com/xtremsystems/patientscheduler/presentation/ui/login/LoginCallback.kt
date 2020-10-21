package com.xtremsystems.patientscheduler.presentation.ui.login

import android.content.Context
import com.xtremsystems.patientscheduler.data.auth.AuthData
import com.xtremsystems.patientscheduler.data.auth.AuthResponse
import com.xtremsystems.patientscheduler.helpers.AppPrefKeys
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import com.xtremsystems.patientscheduler.presentation.PatientScheduler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCallback(var context: Context, var view: LoginView) : Callback<AuthResponse> {
    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
        if (!hasInternetConnection(context)) {
            view.showError("No internet connection...")
            return
        }
        view.showError("Unable to login at the moment. Please try again later")
    }

    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
        if (response.isSuccessful) {

            saveAuthTokens(response.body()?.data)
            view.goToMainActivity()
            return
        }

        response.errorBody()?.let { getRequestErrorMessage(it) }?.let { view.showError(it) }
    }

    private fun saveAuthTokens(data: AuthData?) {
        data?.accessToken?.let { PatientScheduler.prefs?.storePreference(AppPrefKeys.ACCESS_TOKEN, it) }
    }

}
