package com.xtremsystems.patientscheduler.presentation.ui.main.records.second_visit

interface SecondVisitRecordsView {
    fun getXray(): String
    fun getSputum(): String
    fun getDeath(): String
    fun getHospitalization(): String
    fun getLossToFollowUp(): String
    fun getNonOfTheAbove(): String
    fun getAccessToken(): String
    fun showError(s: String)
    fun showMessage(s: String)
}