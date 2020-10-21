package com.xtremsystems.patientscheduler.data.patients.add

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.xtremsystems.patientscheduler.data.Result
import com.xtremsystems.patientscheduler.data.patients.Data

class AddPatientResponse {
    @SerializedName("result")
    @Expose
    val result: Result? = null
    @SerializedName("data")
    @Expose
    val data: Data? = null
}