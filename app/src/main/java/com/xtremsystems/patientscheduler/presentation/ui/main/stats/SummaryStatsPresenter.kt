package com.xtremsystems.patientscheduler.presentation.ui.main.stats

import android.content.Context
import com.xtremsystems.patientscheduler.data.patients.stats.PatientsStatsResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SummaryStatsPresenter(
    private val context: Context,
    private val view: SummaryStatsView,
    private val service: PatientSchedulerService
) {
    fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun getStats() {
        service.getSummaryStats(getToken()).enqueue(PatientStatsCallback(context, view))
    }
}

class PatientStatsCallback(var context: Context, var view: SummaryStatsView) :
    Callback<PatientsStatsResponse> {
    override fun onFailure(call: Call<PatientsStatsResponse>, t: Throwable) {
        if (!hasInternetConnection(context)) {
            view.showError("No internet connection...")
            return
        }

        view.showError("Unable to get stats at the moment")
    }

    override fun onResponse(call: Call<PatientsStatsResponse>, response: Response<PatientsStatsResponse>) {
        if (response.isSuccessful) {
            view.showStats(response.body()?.data)
            return
        }

        response.errorBody()?.let { getRequestErrorMessage(it) }?.let { view.showError(it) }
    }

}
