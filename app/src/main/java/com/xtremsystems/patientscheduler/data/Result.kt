package com.xtremsystems.patientscheduler.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("code")
    @Expose
    var code: Int? = null
    @SerializedName("error")
    @Expose
    var error: Any? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
}