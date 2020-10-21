package com.xtremsystems.patientscheduler.data.patients

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.xtremsystems.patientscheduler.data.Result


class PatientResponse {
    @SerializedName("result")
    @Expose
    val result: Result? = null
    @SerializedName("data")
    @Expose
    val data: List<Data>? = null
}