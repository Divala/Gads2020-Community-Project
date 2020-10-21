package com.xtremsystems.patientscheduler.presentation.ui.main.overdue.second_visit

import android.content.Context
import android.util.Log
import com.xtremsystems.patientscheduler.data.patients.PatientResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverdueSecondVisitPresenter(
    private var context: Context,
    private var view: OverdueSecondVisitView,
    private var service: PatientSchedulerService
) {

    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun getOverduePatients() {
        service.getOverdueSecondVisitPatients(getToken()).enqueue(OverdueSecondVisitCallback(context, view))

    }

    fun setPresentCheck(id: Int?) {
        service.setPresent(getToken(), id).enqueue(OverdueSecondVisitPresentCallback(context, view))
    }
}

class OverdueSecondVisitPresentCallback(var context: Context, var view: OverdueSecondVisitView) :
    Callback<PatientResponse> {
    override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
        if (!hasInternetConnection(context)) {
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

class OverdueSecondVisitCallback(var context: Context, var view: OverdueSecondVisitView) :
    Callback<PatientResponse> {
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

        response.errorBody()?.let { getRequestErrorMessage(it) }?.let { view.showError(it) }
    }

}
