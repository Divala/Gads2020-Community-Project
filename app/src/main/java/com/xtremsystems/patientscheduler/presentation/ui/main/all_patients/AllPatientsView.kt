package com.xtremsystems.patientscheduler.presentation.ui.main.all_patients

import com.xtremsystems.patientscheduler.data.patients.Data

interface AllPatientsView {
    fun getAccessToken(): String
    fun showError(message: String)
    fun showPatients(data: List<Data>?)
}