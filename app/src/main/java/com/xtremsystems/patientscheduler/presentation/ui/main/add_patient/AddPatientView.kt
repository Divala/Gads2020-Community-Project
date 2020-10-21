package com.xtremsystems.patientscheduler.presentation.ui.main.add_patient

interface AddPatientView {
    fun getAccessToken(): String
    fun getPatientName(): String
    fun showPatientIdError(s: String)
    fun showError(message: String)
    fun showMessage(message: String)
    fun getLocation(): String
    fun showLocationError(s: String)
}