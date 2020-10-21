package com.xtremsystems.patientscheduler.presentation.ui.main.reminders.first_visit

import android.content.Context
import android.util.Log
import com.xtremsystems.patientscheduler.data.patients.PatientResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstVisitRemindersPresenter(
    val context: Context,
    val view: FirstVisitRemindersView,
    private val service: PatientSchedulerService
) {
    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun getPatients() {

        service.getFirstVisitPatients(getToken()).enqueue(FirstVisitCallback(context, view))
    }

    fun setPresent(id: Int?) {
        service.setPresent(getToken(), id).enqueue(PatientPresentCallback(context, view))
    }

    fun setAbsent(id: Int?) {
        service.setAbsent(getToken(), id).enqueue(PatientAbsentCallback(context, view))
    }

    class PatientAbsentCallback(var context: Context, var view: FirstVisitRemindersView) :
        Callback<PatientResponse> {
        override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
            if (!hasInternetConnection(context)) {
                view.showError("No internet connection...")
                return
            }

            view.showError("Unable to set changes at the moment. Please try again later")
        }

        override fun onResponse(call: Call<PatientResponse>, response: Response<PatientResponse>) {

            if (response.isSuccessful) {
                view.refreshPatients()
                return
            }

            view.showError(response.errorBody()?.let { NetworkHelper.getRequestErrorMessage(it) })
        }

    }
}

class PatientPresentCallback(var context: Context, var view: FirstVisitRemindersView) :
    Callback<PatientResponse> {
    override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
        if (!hasInternetConnection(context)) {
            view.showError("No internet connection...")
            return
        }
        view.showError("Unable to set changes at the moment. Please try again later")
    }

    override fun onResponse(call: Call<PatientResponse>, response: Response<PatientResponse>) {
        if (response.isSuccessful) {
            view.refreshPatients()
            return
        }

        view.showError(response.errorBody()?.let { NetworkHelper.getRequestErrorMessage(it) })
    }

}

class FirstVisitCallback(var context: Context, var view: FirstVisitRemindersView) : Callback<PatientResponse> {
    override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
        if (!hasInternetConnection(context)) {
            view.showError("No internet connection...")
            return
        }
        view.showError("Unable to get patients at the moment. Please try again later")

    }

    override fun onResponse(call: Call<PatientResponse>, response: Response<PatientResponse>) {
        if (response.isSuccessful) {
            view.showPatients(response.body()?.data)
            return
        }
        view.showError(response.errorBody()?.let { NetworkHelper.getRequestErrorMessage(it) })
    }

}
