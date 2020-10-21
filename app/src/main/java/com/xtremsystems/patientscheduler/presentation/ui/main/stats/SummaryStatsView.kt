package com.xtremsystems.patientscheduler.presentation.ui.main.stats

import com.xtremsystems.patientscheduler.data.patients.stats.StatsData

interface SummaryStatsView {
    fun getAccessToken(): String
    fun showError(s: String)
    fun showStats(data: StatsData?)
}