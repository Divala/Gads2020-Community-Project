package com.xtremsystems.patientscheduler.data.patients.results

data class Result(
    val code: Int,
    val error: Any,
    val message: String,
    val success: Boolean
)