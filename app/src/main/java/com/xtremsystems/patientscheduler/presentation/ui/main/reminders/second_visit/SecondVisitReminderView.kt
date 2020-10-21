package com.xtremsystems.patientscheduler.presentation.ui.main.reminders.second_visit

import com.xtremsystems.patientscheduler.data.patients.Data

interface SecondVisitReminderView {
    fun getAccessToken(): String
    fun showPatients(data: List<Data>?)
    fun showError(message: String?)
    fun checkPresent(id: Int?)
    fun checkAbsent(id: Int?)
    fun refreshPatients()
}