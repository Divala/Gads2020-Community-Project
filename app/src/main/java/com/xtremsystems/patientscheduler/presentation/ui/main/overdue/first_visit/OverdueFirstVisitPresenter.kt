package com.xtremsystems.patientscheduler.presentation.ui.main.overdue.first_visit

import android.content.Context
import com.xtremsystems.patientscheduler.data.patients.PatientResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverdueFirstVisitPresenter(
    var context: Context,
    var view: OverdueFirstVisitView,
    var service: PatientSchedulerService
) {
    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun getPatients() {
        service.getOverdueFirstVisit(getToken()).enqueue(OverdueFirstVisitCallback(context, view))
    }

    fun setPatientPresent(id: Int?) {
        service.setPresent(getToken(), id).enqueue(OverduePatientPresent(context, view))
    }
}

class OverduePatientPresent(var context: Context, var view: OverdueFirstVisitView) :
    Callback<PatientResponse> {
    override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
        if (!hasInternetConnection(context = context)) {
            view.showError("No internet connection...")
            return
        }
        view.showError("Unable to set changes. Please try again later")
    }

    override fun onResponse(call: Call<PatientResponse>, response: Response<PatientResponse>) {
        if (response.isSuccessful) {
            view.refreshPatients()
            return
        }

        response.errorBody()?.let { getRequestErrorMessage(it) }?.let { view.showError(it) }
    }

}

class OverdueFirstVisitCallback(var context: Context, var view: OverdueFirstVisitView) :
    Callback<PatientResponse> {
    override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
        if (!hasInternetConnection(context)) {
            view.showError("No internet connection...")
            return
        }
        view.showError("Unable to get patients. Please try again later")

    }

    override fun onResponse(call: Call<PatientResponse>, response: Response<PatientResponse>) {
        if (response.isSuccessful) {
            view.showPatients(response.body()?.data)
            return
        }

        response.errorBody()?.let { getRequestErrorMessage(it) }?.let { view.showError(it) }
    }

}
