package com.xtremsystems.patientscheduler.data.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.xtremsystems.patientscheduler.data.Result

class AuthResponse {
    @SerializedName("result")
    @Expose
    val result: Result? = null
    @SerializedName("data")
    @Expose
    var data: AuthData? = null
}