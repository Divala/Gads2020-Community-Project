package com.xtremsystems.patientscheduler.presentation.ui.main.records.second_visit

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.xtremsystems.patientscheduler.data.patients.results.ResultsResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondVisitRecordsPresenter(
    var context: Context,
    var view: SecondVisitRecordsView,
    var service: PatientSchedulerService
) {
    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun postData(patient_id: Int) {
        val xray: String = view.getXray()
        val sputum: String = view.getSputum()
        val death: String = view.getDeath()
        val hosp: String = view.getHospitalization()
        val loss: String = view.getLossToFollowUp()
        val non: String = view.getNonOfTheAbove()


        service.postSecondVisitRecords(getToken(), patient_id, xray, sputum, death, hosp, loss, non)
            .enqueue(
                FirstVisitRecordsCallback(
                    context,
                    view
                )
            )
    }
}

class FirstVisitRecordsCallback(val context: Context, val view: SecondVisitRecordsView) : Callback<ResultsResponse> {
    override fun onFailure(call: Call<ResultsResponse>, t: Throwable) {
        if (!hasInternetConnection(context)) {
            view.showError("No internet connection...")
            return
        }
        view.showError("Unable to send records at the moment. Please try again later")
    }

    override fun onResponse(call: Call<ResultsResponse>, response: Response<ResultsResponse>) {

        Log.w(
            "PatientErr",
            GsonBuilder().setPrettyPrinting().create().toJson(response)
        )

        if (response.isSuccessful) {
            view.showMessage("Data posted successful")
            return
        }

        response.errorBody()?.let { getRequestErrorMessage(it) }?.let { view.showError(it) }
    }

}