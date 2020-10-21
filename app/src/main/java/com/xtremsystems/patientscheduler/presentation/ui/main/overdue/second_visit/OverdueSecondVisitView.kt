package com.xtremsystems.patientscheduler.presentation.ui.main.overdue.second_visit

import com.xtremsystems.patientscheduler.data.patients.Data

interface OverdueSecondVisitView {
    fun getAccessToken(): String
    fun showError(message: String)
    fun showPatients(data: List<Data>?)
    fun checkPresent(id: Int?)
    fun refreshPatients()
}