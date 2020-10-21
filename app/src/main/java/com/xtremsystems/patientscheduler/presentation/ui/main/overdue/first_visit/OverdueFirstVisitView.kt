package com.xtremsystems.patientscheduler.presentation.ui.main.overdue.first_visit

import com.xtremsystems.patientscheduler.data.patients.Data


interface OverdueFirstVisitView {
    fun getAccessToken(): String
    fun showError(message: String)
    fun showPatients(data: List<Data>?)
    fun checkPresent(id: Int?)
    fun refreshPatients()
}