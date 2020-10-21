package com.xtremsystems.patientscheduler.presentation.ui.main.all_patients

import android.content.Context
import com.xtremsystems.patientscheduler.data.patients.PatientResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPatientsPresenter(
    private var context: Context,
    private var view: AllPatientsView,
    private var service: PatientSchedulerService
) {

    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun getPatients() {
        service.getAllPatients(getToken()).enqueue(AllPatientsCallback(context, view))
    }
}

class AllPatientsCallback(var context: Context, var view: AllPatientsView) :
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
