package com.xtremsystems.patientscheduler.presentation.ui.main.reminders.first_visit

import com.xtremsystems.patientscheduler.data.patients.Data

interface FirstVisitRemindersView {
    fun getAccessToken(): String
    fun showPatients(data: List<Data>?)
    fun showError(message: String?)
    fun checkPresent(id: Int?)
    fun checkAbsent(id: Int?)
    fun refreshPatients()
}