package com.xtremsystems.patientscheduler.presentation.ui.main

import com.xtremsystems.patientscheduler.data.patients.Patient

interface MainView {
    fun showError(s: String)
    fun shoPendingPatients(patients: List<Patient>?)
}