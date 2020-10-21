package com.xtremsystems.patientscheduler.presentation.ui.main.reminders.second_visit

import android.content.Context
import com.xtremsystems.patientscheduler.data.patients.PatientResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondVisitReminderPresenter(
    var context: Context,
    var view: SecondVisitReminderView,
    var service: PatientSchedulerService
) {
    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun getPatients() {
        service.getSecondVisitPatients(getToken()).enqueue(SecondVisitCallback(context, view))
    }

    fun setPresent(id: Int?) {

        service.setSecondVisitPresent(getToken(), id).enqueue(SecondVisitPresentCallback(context, view))
    }

    fun setAbsent(id: Int?) {

        service.setSecondVisitAbsent(getToken(), id).enqueue(SecondVisitPresentCallback(context, view))
    }

    class SecondVisitCallback(val context: Context, val view: SecondVisitReminderView) : Callback<PatientResponse> {
        override fun onResponse(call: Call<PatientResponse>, response: Response<PatientResponse>) {
            if (response.isSuccessful) {
                view.showPatients(response.body()?.data)
                return
            }

            view.showError(response.errorBody()?.let { getRequestErrorMessage(it) })
        }

        override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
            if (!hasInternetConnection(context)) {
                view.showError("No internet connection...")
            }

            view.showError("Unable to get patients. Please try again later")
        }

    }
}

class SecondVisitPresentCallback(var context: Context, var view: SecondVisitReminderView) :
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

        view.showError(response.errorBody()?.let { getRequestErrorMessage(it) })
    }

}
