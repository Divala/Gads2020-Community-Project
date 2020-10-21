package com.xtremsystems.patientscheduler.data.patients.stats

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.xtremsystems.patientscheduler.data.Result

class PatientsStatsResponse {
    @SerializedName("result")
    @Expose
    val result: Result? = null
    @SerializedName("data")
    @Expose
    val data: StatsData? = null
}