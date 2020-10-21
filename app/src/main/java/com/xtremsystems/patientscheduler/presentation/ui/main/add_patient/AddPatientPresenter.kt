package com.xtremsystems.patientscheduler.presentation.ui.main.add_patient

import android.content.Context
import android.util.Log
import com.xtremsystems.patientscheduler.data.patients.add.AddPatientResponse
import com.xtremsystems.patientscheduler.data.remote.PatientSchedulerService
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.getRequestErrorMessage
import com.xtremsystems.patientscheduler.helpers.NetworkHelper.hasInternetConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPatientPresenter(
    private var context: Context,
    private var view: AddPatientView,
    private var service: PatientSchedulerService
) {

    private fun getToken(): String {
        return "Bearer " + view.getAccessToken()
    }

    fun addPatient() {
        val location: String = view.getLocation()
        val patientName: String = view.getPatientName()

        if (location.isEmpty()) {
            view.showLocationError("Please select location")

            return
        }
        if (patientName.isEmpty()) {
            view.showPatientIdError("Please enter patient name")
            return
        }

        service.addPatient(getToken(), patientName, location)
            .enqueue(AddPatientCallback(context, view))
    }
}

class AddPatientCallback(var context: Context, var view: AddPatientView) :
    Callback<AddPatientResponse> {
    override fun onFailure(call: Call<AddPatientResponse>, t: Throwable) {
        Log.d("ErrorLog", t.message)
        if (!hasInternetConnection(context)) {
            view.showError("No internet connection...")
            return
        }

        view.showError("Unable to add patients. Please try again later")

    }

    override fun onResponse(
        call: Call<AddPatientResponse>,
        response: Response<AddPatientResponse>
    ) {
        if (response.isSuccessful) {
            view.showMessage(response.body()?.data?.patientId + " has been added successfully")
            return
        }
        response.errorBody()?.let { getRequestErrorMessage(it) }?.let { view.showError(it) }

    }

}
